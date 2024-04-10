package com.thuydung.requests;

import java.math.BigDecimal;

public class OrderAmount {
    private BigDecimal subTotal;
    private BigDecimal shipping;
    private BigDecimal tax;
    private BigDecimal couponDiscount;
    private BigDecimal total;
    public OrderAmount() {
    }
    public OrderAmount(BigDecimal subTotal, BigDecimal shipping, BigDecimal tax, BigDecimal couponDiscount, BigDecimal total) {
        this.subTotal = subTotal;
        this.shipping = shipping;
        this.tax = tax;
        this.couponDiscount = couponDiscount;
        this.total = total;
    }
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(BigDecimal couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
