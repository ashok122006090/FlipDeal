package com.ecom.flipdeal.service.Impl;

import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.Product;
import com.ecom.flipdeal.service.PromotionStrategy;
import org.springframework.stereotype.Component;

@Component("promotionSetA")
public class PromotionsStrategySetA implements PromotionStrategy {

    @Override
    public Discount applyPromotions(Product product) {
        Discount discountDetails = new Discount();
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
        discountDetails.setAmount(highestDiscount);
        discountDetails.setDiscountTag(discountTag);


        return discountDetails;
    }
}
