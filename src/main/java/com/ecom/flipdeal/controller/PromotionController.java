// PromotionController.java
package com.ecom.flipdeal.controller;

import com.ecom.flipdeal.client.FlipDealClient;
import com.ecom.flipdeal.dto.Discount;
import com.ecom.flipdeal.dto.Product;
import com.ecom.flipdeal.service.PromotionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/applyPromotions/{id}")
    public ResponseEntity<List<Product>> applyPromotions(@PathVariable String id) throws IOException {
        List<Product> products = promotionService.applyPromotions(id); // Call the service to apply promotions
        return new ResponseEntity<>(products, HttpStatus.OK); // Return the products list with HTTP 200 status
    }

}