package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void testAddProductToCart() {
        getCartPage().testAddProductToCart(PropertiesHelper.getValue("product_P01"),"2");
    }
    @Test(priority = 2)
    public void testCartEmpty() {
        getCartPage().testCartEmpty();
    }
}
