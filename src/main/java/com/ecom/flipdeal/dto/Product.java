package com.ecom.flipdeal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String category;       // Represents the type of product
    private int inventory;         // Tracks stock availability
    private double rating;         // Customer feedback, possibly from 1 to 5
    private String currency;       // Currency format for pricing
    private double price;             // Cost of the product
    private String origin;         // Where the product is sourced from
    private String product;        // Name of the product
    private String arrival;        // Optional field for tracking new arrivals
    private Discount discount;     // Holds discount information (percentage, description)


}
