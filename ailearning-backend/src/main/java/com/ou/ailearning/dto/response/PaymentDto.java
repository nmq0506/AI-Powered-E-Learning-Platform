package com.ou.ailearning.dto.response;

public class PaymentDto {
    private String date;
    private Double totalAmount;

    public PaymentDto(String date, Double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
