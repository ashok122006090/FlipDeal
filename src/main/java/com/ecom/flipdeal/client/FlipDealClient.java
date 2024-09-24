package com.ecom.flipdeal.client;


import com.ecom.flipdeal.dto.ExchangeRate;
import com.ecom.flipdeal.dto.Product;
import com.ecom.flipdeal.utils.HttpCaller;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FlipDealClient {

    private static final String PRODUCTS_API_URL = "https://mock.coverself.net/rest/hiring/products";
    private static final String EXCHANGE_RATES_API_URL = "https://mock.coverself.net/rest/hiring/exchange-rates";
    private static final OkHttpClient client = new OkHttpClient();

    //Rest Template also
    // Fetch product details from the API
    public static List<Product> fetchProductDetails() throws IOException {
        String response = HttpCaller.get(PRODUCTS_API_URL, null);
        System.out.println("Product API Response: " + response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Product.class));
    }

    // Fetch exchange rates from the API
    public static ExchangeRate fetchExchangeRates() throws IOException {
        String response = HttpCaller.get(EXCHANGE_RATES_API_URL, null);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Exchange Rates API Response: " + response);

        // Parse response to ExchangeRate object
        return objectMapper.readValue(response, ExchangeRate.class);
    }

    // Convert product prices to INR using the exchange rates

}
