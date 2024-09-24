package com.ecom.flipdeal.utils;

import com.ecom.flipdeal.dto.Product;

import java.util.List;
import java.util.Map;

public class Common {
    public static List<Product> changeCurrenyToINR(List<Product> products, Map<String, Double> currencyExchange) {
        for (Product product : products) {
            if (!"INR".equalsIgnoreCase(product.getCurrency())) {
                product.setPrice((calculatePriceInINR(product, currencyExchange)));
            }
        }
        return products;
    }

    private static double calculatePriceInINR(Product product, Map<String, Double> currencyExchange) {
        return product.getPrice() * (double) currencyExchange.get(product.getCurrency());
    }
}
