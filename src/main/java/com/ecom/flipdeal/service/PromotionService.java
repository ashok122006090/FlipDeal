package com.ecom.flipdeal.service;

import com.ecom.flipdeal.client.FlipDealClient;
import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.ExchangeRate;
import com.ecom.flipdeal.dto.Product;
import com.ecom.flipdeal.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//hour
@Service
public class PromotionService {

    @Autowired
    private Map<String, PromotionStrategy> promotionStrategies;
    private static final String OUTPUT_FILE_PATH = "target/output.json";

    public List<Product> applyPromotions(String promotionSet) throws IOException {


        // Fetch product details and exchange rates
        List<Product> products = FlipDealClient.fetchProductDetails();
        ExchangeRate exchangeRateData = FlipDealClient.fetchExchangeRates();
        Map<String, Double> exchangeRates = exchangeRateData.getRates();

        // Convert all prices to INR
        List<Product> productsDetails = Common.changeCurrenyToINR(products, exchangeRates);

        // Apply the appropriate promotion set

//promotion
        PromotionStrategy strategy = getStrategy(promotionSet);
        for (Product product : productsDetails) {
            Discount discount = strategy.applyPromotions(product);
            product.setDiscount(discount);
        }
        return productsDetails;


    }


    private PromotionStrategy getStrategy(String promotiontype) {
        promotiontype = "promotionSetA";
        // map
        if (promotionStrategies.containsKey(promotiontype)) {
            return promotionStrategies.get(promotiontype);

        }
        throw new RuntimeException("no proper input ");
    }

}
