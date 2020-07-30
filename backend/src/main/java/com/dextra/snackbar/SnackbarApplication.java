package com.dextra.snackbar;

import com.dextra.snackbar.model.discount.DiscountCalculatorManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SnackbarApplication {

	@Bean
	public DiscountCalculatorManager discountCalculatorManager() {
		return new DiscountCalculatorManager();
	}

	public static void main(String[] args) {
		SpringApplication.run(SnackbarApplication.class, args);
	}

}
