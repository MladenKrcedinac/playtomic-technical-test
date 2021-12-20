package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.TopUpRequest;
import com.playtomic.tests.wallet.model.Wallet;

public interface WalletService {

    Wallet getById(Long id);

    Wallet topUp(TopUpRequest request);
}
