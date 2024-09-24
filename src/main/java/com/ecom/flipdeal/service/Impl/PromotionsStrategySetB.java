package com.ecom.flipdeal.service.Impl;

import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.Product;
import com.ecom.flipdeal.service.PromotionStrategy;
import org.springframework.stereotype.Component;

@Component("promotionSetB")
public class PromotionsStrategySetB implements PromotionStrategy {

    @Override
    public Discount applyPromotions(Product product) {

        Discount DiscountDetails = new Discount();
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

        DiscountDetails.setDiscountTag(discountTag);
        DiscountDetails.setAmount(highestDiscount);
        return DiscountDetails;

    }
}