package com.ecom.flipdeal;

import com.ecom.flipdeal.client.FlipDealClient;
import com.ecom.flipdeal.dto.ExchangeRate;
import com.ecom.flipdeal.dto.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class FlipdealApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FlipdealApplication.class, args);
//	List<Product> list = FlipDealClient.fetchProductDetails();
//	for (Product product : list) {
//		System.out.println(product);
//	}
//
//		ExchangeRate exchangeRate = FlipDealClient.fetchExchangeRates();
//		System.out.println(exchangeRate);
//
	}
}
