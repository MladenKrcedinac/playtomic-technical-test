package com.playtomic.tests.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopUpRequest {

    @NonNull
    Long walletId;

    @NonNull
    String creditCardNumber;

    @NonNull
    BigDecimal amount;
}
