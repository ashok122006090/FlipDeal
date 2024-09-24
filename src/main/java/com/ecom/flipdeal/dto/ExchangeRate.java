package com.ecom.flipdeal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private String base; // The base currency
    private String date; // The date of the exchange rates
    private Map<String, Double> rates; // A map of currency codes to their exchange rates

    // Getters and Setters are provided by the @Data annotation
}
