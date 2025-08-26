package com.example.demo.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemDto {
    @NotNull
    @Size(min=3,message = "should be min 3 charcters ",max=10)
    private String itemName;
    private Double itemPrice;
    private String itemCategory;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
