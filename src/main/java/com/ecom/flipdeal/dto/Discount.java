package com.ecom.flipdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {
    private double amount;
    private String discountTag;
}
