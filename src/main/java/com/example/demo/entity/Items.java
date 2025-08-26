package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true, length = 20)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private Double itemPrice;

    @Column(name = "item_category", nullable = false, length = 15)
    private String itemCategory;

    public Long getId() {
        return id;
    }



    public String getItemName() {
        return itemName;
    }


    public Double getItemPrice() {
        return itemPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCategory() {
        return itemCategory;
    }
    }


