package com.thuydung.requests;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariant {
    private String variantName;
    private String variantPrice;
    private String variantSKU;
    private String variantQuantity;

    public ProductVariant() {

    }
    public ProductVariant(String variantName, String variantPrice, String variantSKU, String variantQuantity) {
        this.variantName = variantName;
        this.variantPrice = variantPrice;
        this.variantSKU = variantSKU;
        this.variantQuantity = variantQuantity;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(String variantPrice) {
        this.variantPrice = variantPrice;
    }

    public String getVariantSKU() {
        return variantSKU;
    }

    public void setVariantSKU(String variantSKU) {
        this.variantSKU = variantSKU;
    }

    public String getVariantQuantity() {
        return variantQuantity;
    }

    public void setVariantQuantity(String variantQuantity) {
        this.variantQuantity = variantQuantity;
    }
}
