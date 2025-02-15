package org.example.digitalwallet.controller;

import org.example.digitalwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class WalletController {

    @Autowired
    private WalletService walletService;



}
