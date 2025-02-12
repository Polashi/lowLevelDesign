package org.example.ecommerce.repository;

import org.example.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    default boolean belongsToUser(int addressId, int userId){
        return findById(addressId).map(address -> address.getUser()
                .equals(userId)).orElse(false);
    }

    boolean existsById(int id);
}
