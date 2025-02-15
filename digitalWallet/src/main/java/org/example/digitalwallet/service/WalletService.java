package org.example.digitalwallet.service;

import org.example.digitalwallet.dto.FundRequestDTO;
import org.example.digitalwallet.dto.TransferRequestDTO;
import org.example.digitalwallet.entity.Wallet;
import org.example.digitalwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public boolean transferFunds(TransferRequestDTO transferRequest) throws Exception {
        Optional<Wallet> senderWallet = walletRepository.findByUserId(transferRequest.getSenderId());
        Optional<Wallet> receiverWallet = walletRepository.findByUserId(transferRequest.getReceiverId());
        if (senderWallet.isEmpty() || receiverWallet.isEmpty()) {
            throw new Exception("Invalid sender or receiver details");
        }
        int amountRequested = transferRequest.getAmount();
        int senderWalletBalance = senderWallet.get().getBalance();
        int receiverWalletBalance = receiverWallet.get().getBalance();
        if (senderWallet.get().getBalance() < amountRequested) {
            throw new Exception("Insufficient fund");
        }
        senderWallet.get().setBalance(senderWalletBalance - amountRequested);
        receiverWallet.get().setBalance(receiverWalletBalance + amountRequested);

        walletRepository.save(senderWallet.get());
        walletRepository.save(receiverWallet.get());
        return true;
    }

    public boolean addFunds(FundRequestDTO fundRequest) throws Exception {
        Optional<Wallet> walletOptional = walletRepository.findByUserId(fundRequest.getUserId());
        if (walletOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        Wallet wallet = walletOptional.get();
        int currentBalance = wallet.getBalance();
        wallet.setBalance(currentBalance + fundRequest.getAmount());
        walletRepository.save(wallet);
        return true;

    }

    public boolean withdrawFund(FundRequestDTO fundRequest) throws Exception {
        Optional<Wallet> walletOptional = walletRepository.findByUserId(fundRequest.getUserId());
        if (walletOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        Wallet wallet = walletOptional.get();
        int currentBalance = wallet.getBalance();
        if(currentBalance < fundRequest.getAmount()){
            throw new Exception("Insufficient amount");
        }
        wallet.setBalance(currentBalance - fundRequest.getAmount());
        walletRepository.save(wallet);
        return true;
    }

    public int checkBalance(int userId) throws Exception{
        Optional<Wallet> walletOptional = walletRepository.findByUserId(userId);
        if(walletOptional.isEmpty()){
            throw new Exception("User not found");
        }
        return walletOptional.get().getBalance();
    }
}
