package org.example.restaurantmanagementsystem.repositories;

import org.example.restaurantmanagementsystem.model.Bill;

import java.util.HashMap;
import java.util.Optional;

public class BillRepositoryImpl implements BillRepository{
    HashMap<Long, Bill> billHashMap = new HashMap<>();
    @Override
    public Bill save(Bill entity) {
        if(entity.getId() == 0){
            entity.setId(billHashMap.size() +1);
            billHashMap.put(entity.getId(),entity);
        }
        return entity;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return Optional.ofNullable(billHashMap.get(id));
    }
}
