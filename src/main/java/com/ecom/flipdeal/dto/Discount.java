package com.ecom.flipdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Discount {
    private double amount;
    private String discountTag;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDiscountTag() {
        return discountTag;
    }

    public void setDiscountTag(String discountTag) {
        this.discountTag = discountTag;
    }
}
