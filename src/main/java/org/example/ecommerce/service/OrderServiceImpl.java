package org.example.ecommerce.service;

import org.example.ecommerce.model.*;
import org.example.ecommerce.repository.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;
    private OrderDetailRepository orderDetailRepository;
    private InventoryRepository inventoryRepository;
    private HighDemandProductRepository highDemandProductRepository;

    public OrderServiceImpl(OrderDetailRepository orderDetailRepository,AddressRepository addressRepository,
                            OrderRepository orderRepository, ProductRepository productRepository,
                            UserRepository userRepository, InventoryRepository inventoryRepository,
                            HighDemandProductRepository highDemandProductRepository) {
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.inventoryRepository = inventoryRepository;
        this.highDemandProductRepository = highDemandProductRepository;
    }

    /*The request to place an order will contain the following information:
        Before creating an order we need to check the following things:
        1. Does the user exist in the system? If not then we need to throw an exception.
        2. Does the address exist in the system? If not then we need to throw an exception.
        3. Does the address belong to the user? If not then we need to throw an exception.
        4. Do all the products have enough quantity to fill the order? If not then we need to throw an exception.
        If all the above checks pass, then we need to update the inventory with the updated quantity, create an order
            (with status as placed) and return the order details.
        We should handle for concurrent requests, i.e. we should not overbook the inventory. We should allow concurrent
            requests to place an order only if the inventory has enough quantity to fulfill the order.
        Few products might be facing a lot of demand but their supply is limited eg. iPhones. For such products we will
            store max number of quantity per order that a user can order. Details for such products will be stored in the high_demand_products table.*/

    @Transactional
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws Exception{
        // Validate user and address
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }
        if (!addressRepository.existsById(addressId)) {
            throw new IllegalArgumentException("Address does not exist");
        }
        if (!addressRepository.belongsToUser(addressId, userId)) {
            throw new IllegalArgumentException("Address does not belong to the user");
        }

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(Pair<Integer, Integer> pair: orderDetails){
            int productId = pair.getFirst();
            int orderedQuantity = pair.getSecond();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(optionalProduct.isEmpty()){
                throw new Exception("Product not found");
            }
            Product product = optionalProduct.get();
            Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_Id(product.getId());

            if(optionalInventory.isEmpty()){
                throw new Exception("Product is out of stock");
            }
            Inventory inventory = optionalInventory.get();
            int quantity = inventory.getQuantity();

            //check high-demand product limit
            Optional<HighDemandProduct> optionalHighDemandProduct = highDemandProductRepository.findByProductId(productId);
            if(optionalHighDemandProduct.isPresent() && orderedQuantity > optionalHighDemandProduct.get().getMaxQuantityPerProduct()){
                throw new Exception("Ordered quantity exceeds limit for high-demand product");
            }
            if(quantity < orderedQuantity){
                throw new Exception("Product is out of stock"); // use case 4
            }else{
                inventory.setQuantity(quantity - orderedQuantity);
                inventoryRepository.save(inventory);
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(orderedQuantity);
            orderDetail.setProduct(product);
            orderDetailList.add(orderDetail);
        }
        Order order = new Order();
        order.setOrderDetails(orderDetailList);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setUser(userRepository.findById(userId).orElseThrow(() -> new Exception("User not found")));
        order.setDeliveryAddress(addressRepository.findById(addressId).orElseThrow(()-> new Exception("Address not found")));
        orderRepository.save(order);
        for(OrderDetail orderDetail: orderDetailList){
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }
        return order;
    }


}
