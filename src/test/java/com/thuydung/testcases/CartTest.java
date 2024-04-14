package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    //de cart empty xong thi test
    @Test(priority = 1, description = "Kiem tra them san pham khong co variant vao gio hang")
    public void testAddProductNoVariantToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
    }
    @Test(priority = 1, description = "Kiem tra them san pham co variant vao gio hang")
    public void testAddProductVariantToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "2");
    }

    @Test(priority = 2, description = "Kiem tra khi them san pham da ton tai trong gio hang vao gio hang")
    public void testAddProductsDuplicateToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "3");
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "4");
    }

    @Test(priority = 3, description = "Kiem tra khi them nhieu san pham chua ton tai trong gio hang vao gio hang")
    public void testAddProductsToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
    }
    @Test(priority = 8, description = "Kiem tra khi them san pham vao gio hang vuot qua so luong")
    public void testAddProductToCartOverQuantity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().checkQuantityAddToCart("Cosy Thuy Dung OOTVUJLN", "901");
    }
    //check update quantity product in cart
    @Test(priority = 2, description = "Kiem tra khi cap nhat so luong san pham hop le trong gio hang")
    public void testUpdateQuantityValidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "2");
        //getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().updateQuantityProductInCart("Cosy Thuy Dung OOTVUJLN", "10");
    }
    //check add product over quantity
    @Test(priority = 8, description = "Kiem tra khi cap nhat san pham vao gio hang vuot qua so luong")
    public void testUpdateQuantityInvalidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung HGRRAWZE", "2");
        //getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().updateQuantityProductInCart("Cosy Thuy Dung HGRRAWZE", "1000");
    }

    //check remove product from cart
    @Test(priority = 3, description = "Kiem tra khi xoa san pham khoi gio hang chi tiet")
    public void testRemoveProductFromCartDetailWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));

        getCartPage().addProductToCart("Cosy Thuy Dung HGRRAWZE", "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().removeProductFromCartDetail("Cosy Thuy Dung HGRRAWZE");
    }
    @Test(priority = 4, description = "Kiem tra khi xoa san pham khoi gio hang dropdown")
    public void testRemoveProductFromCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung HGRRAWZE", "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
        getCartPage().removeProductFromCart("Cosy Thuy Dung HGRRAWZE");

    }


//
//
//    @Test(priority = 0, description = "Kiem tra them 1 san pham vao gio hang")
//    public void testAddProductToCartWithNoAccount() {
//        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
//    }
//
//    @Test(priority = 1, description = "Kiem tra khi them nhieu san pham chua ton tai trong gio hang vao gio hang")
//    public void testAddProductsToCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//    }
//
//    @Test(priority = 2, description = "Kiem tra khi them san pham da ton tai trong gio hang vao gio hang")
//    public void testAddProductsDuplicateToCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "4");
//    }
//
//    //check remove product from cart
//    @Test(priority = 4, description = "Kiem tra khi xoa san pham khoi gio hang")
//    public void testRemoveProductFromCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
//    }
//    @Test(priority = 4, description = "Kiem tra khi xoa het san pham khoi gio hang")
//    public void testRemoveAllProductFromCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
////        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
//    }
//
//    //check update quantity product in cart
//    @Test(priority = 5, description = "Kiem tra khi cap nhat so luong san pham trong gio hang")
//    public void testUpdateQuantityProductInCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        //getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().updateQuantityProductInCart(PropertiesHelper.getValue("product_P01"), "1");
//    }
//
//    //check remove product from cart
//    @Test(priority = 6, description = "Kiem tra khi xoa san pham khoi gio hang")
//    public void testRemoveProductFromCartDetailWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
//    }
//    //check remove all product from cart
//    @Test(priority = 7, description = "Kiem tra xoa san pham de trong gio hang")
//    public void testRemoveAllProductFromCart() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
//    }
    //check add product continuously
//    @Test(priority = 9, description = "Kiem tra khi them san pham lien tuc")
//    public void testAddProductToCartContinuously() {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
//        getCartPage().checkQuantityAvailabelFail(PropertiesHelper.getValue("product_P01"));
//    }





}
