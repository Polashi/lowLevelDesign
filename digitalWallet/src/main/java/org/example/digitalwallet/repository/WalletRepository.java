package org.example.digitalwallet.repository;

import org.example.digitalwallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    public Optional<Wallet> findByUserId(int userId);
}
