package org.example.ecommerce.service;

import org.example.ecommerce.model.*;
import org.example.ecommerce.repository.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

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

    public OrderServiceImpl(OrderDetailRepository orderDetailRepository,AddressRepository addressRepository, OrderRepository orderRepository, ProductRepository productRepository,UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws Exception{
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new Exception("User not found");
        }

        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if(optionalAddress.isEmpty()){
            throw new Exception("Address not found");
        }
        User user = optionalUser.get();
        Address address = optionalAddress.get();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(Pair<Integer, Integer> pair: orderDetails){
            int productId = pair.getFirst();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(optionalProduct.isEmpty()){
                throw new Exception("Product not found");
            }
            Product product = optionalProduct.get();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(pair.getSecond());
            orderDetail.setProduct(product);
            orderDetailList.add(orderDetailRepository.save(orderDetail));
        }
        Order order = new Order();
        order.setOrderDetails(orderDetailList);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setUser(user);
        order.setDeliveryAddress(address);
        return orderRepository.save(order);
    }


}
