package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

//    @Test(priority = 1)
//    public void testAddProductToCart() {
//        getCartPage().testAddProductToCart(PropertiesHelper.getValue("product_P01"),"2");
//    }
    @Test(priority = 1)
    public void testAddManyProductNoDuplicateToCart() {
        getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"),"2", PropertiesHelper.getValue("product_P02"),"3");
    }
    @Test(priority = 2)
    public void testAddManyProductDuplicateToCart() {
        getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"),"2", PropertiesHelper.getValue("product_P01"),"3");
    }
    @Test(priority = 3)
    public void testCartEmpty() {
        getCartPage().testCartEmpty();
    }
    @Test(priority = 4)
    public void removeProductFromCart() {
        getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"),"2", PropertiesHelper.getValue("product_P02"),"3");
        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
    }
    //check cart detail
    @Test(priority = 5)
    public void testCartDetail() {
        getCartPage().testCartDetail(PropertiesHelper.getValue("product_P01"),"2");
    }
    //check update quantity product in cart
    @Test(priority = 6)
    public void testUpdateQuantityProductInCart() {
        getCartPage().testUpdateQuantityProductInCart(PropertiesHelper.getValue("product_P01"),"5");
    }
    //check remove product from cart
    @Test(priority = 7)
    public void testRemoveProductFromCart() {
        getCartPage().testRemoveProductFromCart(PropertiesHelper.getValue("product_P01"));
    }
    //check remove all product from cart


}
