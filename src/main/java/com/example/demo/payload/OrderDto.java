package com.example.demo.payload;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class OrderDto {

    @Positive(message = "should be > 0")
    private Integer orderQuantity;
    private Double orderAmount;
    private LocalDate orderDate;
    private Long itemId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
