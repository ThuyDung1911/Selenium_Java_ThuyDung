package com.thuydung.testcases;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.common.BaseTest;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    public ExcelHelper excel;

    // Add order success
//    @Test(priority = 1)
//    public void testOrderProducts() {
//        excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getOrderPage().order("Giao hàng càng sớm càng tốt", excel.getCellData("email", 4), excel.getCellData("password", 4));
//    }
    // Add order success
    @Test(priority = 2)
    public void testOrderProduct() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
        getCartPage().addProductToCart("Cosy Thuy Dung GBNXJUZQ", "2");
//        getCartPage().addProductToCart("Tiramisu", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }
    // Checkout order without product
    @Test(priority = 3)
    public void testCheckoutOrderWithoutProduct() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }
    @Test(priority = 4)
    public void checkOrderSummary() {
        getOrderPage().checkOrderSummary();
    }
}
