package com.ecom.flipdeal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private String base; // The base currency
    private String date; // The date of the exchange rates
    private Map<String, Double> rates; // A map of currency codes to their exchange rates

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
// Getters and Setters are provided by the @Data annotation
}
