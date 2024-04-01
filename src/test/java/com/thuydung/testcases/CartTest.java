package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    //    @Test(priority = 1)
//    public void testAddProductToCart() {
//        getCartPage().testAddProductToCart(PropertiesHelper.getValue("product_P01"),"2");
//    }
    @Test(priority = 0)
    public void testAddManyProductNoDuplicateToCart1() {
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
//        getCartPage().testAddProductToCart(PropertiesHelper.getValue("product_P02"), "2");
//        getCartPage().testAddProductToCart(PropertiesHelper.getValue("product_P02"), "3");

    }
    @Test(priority = 0, description = "Kiem tra them 1 san pham vao gio hang")
    public void testAddProductToCart() {
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
    }

    @Test(priority = 1, description = "Kiem tra khi them nhieu san pham chua ton tai trong gio hang vao gio hang")
    public void testAddProductsToCart() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        //getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"), "2", PropertiesHelper.getValue("product_P02"), "3");
    }

    @Test(priority = 2, description = "Kiem tra khi them san pham da ton tai trong gio hang vao gio hang")
    public void testAddProductsDuplicateToCart() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "4");
        //getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"), "2", PropertiesHelper.getValue("product_P01"), "3");
    }

    @Test(priority = 3, description = "Kiem tra khi gio hang rong")
    public void testCartEmpty() {
        getCartPage().checkCartEmpty();
    }
    //check remove product from cart
    @Test(priority = 4, description = "Kiem tra khi xoa san pham khoi gio hang")
    public void testRemoveProductFromCart() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        //getCartPage().testAddManyProductToCart(PropertiesHelper.getValue("product_P01"), "2", PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
    }

    //check cart detail
    @Test(priority = 5)
    public void testCartDetail() {
        getCartPage().checkCartDetail(PropertiesHelper.getValue("product_P01"), "2");
    }

    //check update quantity product in cart
    @Test(priority = 6)
    public void testUpdateQuantityProductInCart() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().updateQuantityProductInCart(PropertiesHelper.getValue("product_P01"), "1");
    }

    //check remove product from cart
    @Test(priority = 7)
    public void testRemoveProductFromCartDetail() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
    }
    //check remove all product from cart
    @Test(priority = 8)
    public void testRemoveAllProductFromCart() {
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P02"));
    }

}
