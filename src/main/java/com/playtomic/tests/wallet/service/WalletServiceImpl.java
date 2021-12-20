package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.TopUpRequest;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final StripeService stripeService;

    @Override
    public Wallet getById(Long id) throws ResourceNotFoundException {
        Optional<Wallet> optionalWallet = Optional.ofNullable(walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for id: " + id)));

        return optionalWallet.get();
    }

    @Override
    public Wallet topUp(TopUpRequest request) {
        Wallet wallet = getById(request.getWalletId());
        stripeService.charge(request.getCreditCardNumber(), request.getAmount());
        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        return walletRepository.save(wallet);
    }

}
