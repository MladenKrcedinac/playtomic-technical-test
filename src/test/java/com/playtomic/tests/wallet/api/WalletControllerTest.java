package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repo.WalletRepository;
import com.playtomic.tests.wallet.service.WalletServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletServiceImpl walletService;

    @MockBean
    private WalletRepository walletRepository;

    @Test
    void getExistingWallet() throws Exception {
        Mockito.when(walletService.getById(1L)).thenReturn(new Wallet(1L, BigDecimal.valueOf(1000)));

        mockMvc.perform(MockMvcRequestBuilders.get("/wallet/get/{id}", 1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(walletService).getById(1L);
    }

    @Test
    void getNonExistingWallet() throws Exception {
        Mockito.when(walletService.getById(2L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/wallet/get/{id}", 2L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(walletService).getById(2L);
    }

}