package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.model.TopUpRequest;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("get/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable("id") Long id) {
        log.info("Fetching Wallet with id: {}", id);
        return ResponseEntity.ok().body(walletService.getById(id));
    }

    @PostMapping("/topUp")
    public ResponseEntity<Wallet> topUpMoney(@Validated @RequestBody TopUpRequest request) {
        log.info("Top up Wallet with id: {}, for amount: {}", request.getWalletId(), request.getAmount());
        return ResponseEntity.ok().body(walletService.topUp(request));
    }

}
