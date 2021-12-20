package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repo.WalletRepository;
import com.playtomic.tests.wallet.service.StripeService;
import com.playtomic.tests.wallet.service.WalletServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private StripeService stripeService;

    private WalletServiceImpl walletService;

    @BeforeEach
    void setUp() {
        walletService = new WalletServiceImpl(walletRepository, stripeService);
    }

    @Test
    void getExistingWallet() {
        Wallet wallet = new Wallet(1L, BigDecimal.valueOf(1000));
        Mockito.when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));

        Wallet result = walletService.getById(1L);
        Assertions.assertEquals(wallet.getBalance(), result.getBalance());

        Mockito.verify(walletRepository).findById(1L);
    }

    @Test
    void getNonExistingWallet() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            walletService.getById(1L);
        });
        Mockito.verify(walletRepository).findById(1L);
    }

}