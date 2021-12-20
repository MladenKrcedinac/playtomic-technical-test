package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repo.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@Bean
	CommandLineRunner run(WalletRepository walletRepository) {
		return args -> {
			walletRepository.save(new Wallet(null, BigDecimal.valueOf(1_000_000.00)));
			walletRepository.save(new Wallet(null, BigDecimal.valueOf(2_000_000.00)));
			walletRepository.save(new Wallet(null, BigDecimal.valueOf(3_000_000.00)));
			walletRepository.save(new Wallet(null, BigDecimal.valueOf(4_000_000.00)));
		};
	}
}
