package com.thuydung.requests;

import java.math.BigDecimal;

public class Cart {
    private String nameProduct;
    private String nameVariant;
    private BigDecimal price;
    private int quantity;

    public Cart() {
    }
    public Cart(String nameProduct, String nameVariant, BigDecimal price, int quantity) {
        this.nameProduct = nameProduct;
        this.nameVariant = nameVariant;
        this.price = price;
        this.quantity = quantity;
    }
    public String getNameVariant() {
        return nameVariant;
    }
    public void setNameVariant(String nameVariant) {
        this.nameVariant = nameVariant;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
