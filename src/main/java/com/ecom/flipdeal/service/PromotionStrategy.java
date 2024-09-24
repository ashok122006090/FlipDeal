package com.ecom.flipdeal.service;

import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.Product;
import org.springframework.stereotype.Component;


public interface PromotionStrategy {
    public Discount applyPromotions(Product product);
// type

}


