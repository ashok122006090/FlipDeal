package com.ecom.flipdeal.service;

import com.ecom.flipdeal.client.FlipDealClient;
import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.ExchangeRate;
import com.ecom.flipdeal.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PromotionService {
    private static final String OUTPUT_FILE_PATH = "target/output.json";

    public String applyPromotions(String promotionSet) {
        try {
            // Fetch product details and exchange rates
            List<Product> products = FlipDealClient.fetchProductDetails();
            ExchangeRate exchangeRateData = FlipDealClient.fetchExchangeRates();
            Map<String, Double> exchangeRates = exchangeRateData.getRates();

            // Convert all prices to INR
            FlipDealClient.convertPricesToINR(products, exchangeRates);

            // Apply the appropriate promotion set
            if ("promotionSetA".equalsIgnoreCase(promotionSet)) {
                applyPromotionSetA(products);
            } else if ("promotionSetB".equalsIgnoreCase(promotionSet)) {
                applyPromotionSetB(products);
            }

            // Save the output to a JSON file
            saveOutputToJson(products);
            return "Promotions applied successfully! Output saved to " + OUTPUT_FILE_PATH;

        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }

    private void applyPromotionSetA(List<Product> products) {
        for (Product product : products) {
            double highestDiscount = 0;
            String discountTag = "";

            // 7% discount if product origin is Africa
            if ("Africa".equals(product.getOrigin())) {
                double discount = product.getPrice() * 0.07;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get 7% off";
                }
            }

            // 4% discount if rating is 2, 8% if rating < 2
            if (product.getRating() == 2) {
                double discount = product.getPrice() * 0.04;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get 4% off";
                }
            } else if (product.getRating() < 2) {
                double discount = product.getPrice() * 0.08;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get 8% off";
                }
            }

            // Flat Rs 100 discount for electronics or furnishing costing Rs 500 or more
            if (product.getPrice() >= 500 &&
                    ("electronics".equalsIgnoreCase(product.getCategory()) ||
                            "furnishing".equalsIgnoreCase(product.getCategory()))) {
                double discount = 100;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get Rs 100 off";
                }
            }

            // Default 2% discount if no other discount applied and price > Rs 1000
            if (highestDiscount == 0 && product.getPrice() > 1000) {
                highestDiscount = product.getPrice() * 0.02;
                discountTag = "get 2% off";
            }

            // Apply the discount if any
            if (highestDiscount > 0) {
                product.setDiscount(new Discount(highestDiscount, discountTag));
            }
        }
    }

    private void applyPromotionSetB(List<Product> products) {
        for (Product product : products) {
            double highestDiscount = 0;
            String discountTag = "";

            // 12% discount if inventory > 20
            if (product.getInventory() > 20) {
                double discount = product.getPrice() * 0.12;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get 12% off";
                }
            }

            // 7% discount if product is new
            if ("new".equalsIgnoreCase(product.getArrival())) {
                double discount = product.getPrice() * 0.07;
                if (discount > highestDiscount) {
                    highestDiscount = discount;
                    discountTag = "get 7% off";
                }
            }

            // Default 2% discount if no other discount applied and price > Rs 1000
            if (highestDiscount == 0 && product.getPrice() > 1000) {
                highestDiscount = product.getPrice() * 0.02;
                discountTag = "get 2% off";
            }

            // Apply the discount if any
            if (highestDiscount > 0) {
                product.setDiscount(new Discount(highestDiscount, discountTag));
            }
        }
    }

    private void saveOutputToJson(List<Product> products) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(OUTPUT_FILE_PATH), products);
    }
}
