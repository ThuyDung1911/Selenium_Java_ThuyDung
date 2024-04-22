package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiểm tra chức năng thêm sản phẩm không có biển thể chưa tồn tại trong giỏ hàng vào giỏ hàng")
    public void TC_AddProductNoVariantNotExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
//        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
//        if (checkExistProduct) {
//            getCartPage().removeProductFromCartDetail(excel.getCellData("productName", 1));
//        }
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiểm tra chức năng thêm sản phẩm có biển thể chưa tồn tại trong giỏ hàng vào giỏ hàng")
    public void TC_AddProductVariantNotExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
//        String keyProductName = excel.getCellData("productName", 2) + " - " + excel.getCellData("variantName", 2);
//        boolean checkExistProduct = getCartPage().checkProductExistInCart(keyProductName);
//        if (checkExistProduct) {
//            getCartPage().removeProductFromCartDetail(keyProductName);
//        }
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3, description = "Kiểm tra chức năng thêm sản phẩm không có biển thể đã tồn tại trong giỏ hàng vào giỏ hàng")
    public void TC_AddProductNoVariantExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4, description = "Kiểm tra chức năng thêm sản phẩm có biển thể đã tồn tại trong giỏ hàng vào giỏ hàng")
    public void TC_AddProductVariantExistToCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        String keyProductName = excel.getCellData("productName", 2) + " - " + excel.getCellData("variantName", 2);
        boolean checkExistProduct = getCartPage().checkProductExistInCart(keyProductName);
        if (checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        }
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5, description = "Kiểm tra chức năng thêm sản phẩm đã tồn tại trong giỏ hàng với số lượng vượt quá số lượng tồn kho")
    public void TC_AddProductExistToCartOverQuantity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().addProductOverQuantityToCart(excel.getCellData("productName",1), "901");
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6, description = "Kiểm tra chức năng thêm sản phẩm chưa tồn tại trong giỏ hàng với số lượng vượt quá số lượng tồn kho")
    public void TC_AddProductNotExistToCartOverQuantity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().removeProductFromCartDetail(excel.getCellData("productName", 1));
        }
        getCartPage().addProductOverQuantityToCart(excel.getCellData("productName",1), "901");
    }

    //check update quantity product in cart
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7, description = "Kiem tra khi cap nhat so luong san pham hop le trong gio hang")
    public void TC_UpdateQuantityValidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().updateQuantityProductInCart(excel.getCellData("productName", 1), "3");
    }
    //check add product over quantity
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8, description = "Kiem tra khi cap nhat san pham vao gio hang vuot qua so luong")
    public void TC_UpdateQuantityInvalidProductInCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().updateQuantityProductInCart(excel.getCellData("productName", 1), "1000");
    }

    //check remove product from cart
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9, description = "Kiem tra khi xoa san pham khoi gio hang chi tiet")
    public void TC_RemoveProductFromCartDetailWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().removeProductFromCartDetail(excel.getCellData("productName", 1));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10, description = "Kiem tra khi xoa san pham khoi gio hang dropdown")
    public void TC_RemoveProductFromCartWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "AddProductToCart");
        boolean checkExistProduct = getCartPage().checkProductExistInCart(excel.getCellData("productName", 1));
        if (!checkExistProduct) {
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        }
        getCartPage().removeProductFromCart(excel.getCellData("productName", 1));
    }


//
//
//    @Test(priority = 0, description = "Kiem tra them 1 san pham vao gio hang")
//    public void TC_AddProductToCartWithNoAccount() {
//        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
//    }
//
//    @Test(priority = 1, description = "Kiem tra khi them nhieu san pham chua ton tai trong gio hang vao gio hang")
//    public void TC_AddProductsToCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//    }
//
//    @Test(priority = 2, description = "Kiem tra khi them san pham da ton tai trong gio hang vao gio hang")
//    public void TC_AddProductsDuplicateToCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "4");
//    }
//
//    //check remove product from cart
//    @Test(priority = 4, description = "Kiem tra khi xoa san pham khoi gio hang")
//    public void TC_RemoveProductFromCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
//    }
//    @Test(priority = 4, description = "Kiem tra khi xoa het san pham khoi gio hang")
//    public void TC_RemoveAllProductFromCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
////        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCart(PropertiesHelper.getValue("product_P01"));
//    }
//
//    //check update quantity product in cart
//    @Test(priority = 5, description = "Kiem tra khi cap nhat so luong san pham trong gio hang")
//    public void TC_UpdateQuantityProductInCartWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        //getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().updateQuantityProductInCart(PropertiesHelper.getValue("product_P01"), "1");
//    }
//
//    //check remove product from cart
//    @Test(priority = 6, description = "Kiem tra khi xoa san pham khoi gio hang")
//    public void TC_RemoveProductFromCartDetailWithNoAccount() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P02"), "3");
//        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
//    }
//    //check remove all product from cart
//    @Test(priority = 7, description = "Kiem tra xoa san pham de trong gio hang")
//    public void TC_RemoveAllProductFromCart() {
//        getCartPage().addProductToCart(PropertiesHelper.getValue("product_P01"), "2");
//        getCartPage().removeProductFromCartDetail(PropertiesHelper.getValue("product_P01"));
//    }
    //check add product continuously
//    @Test(priority = 9, description = "Kiem tra khi them san pham lien tuc")
//    public void TC_AddProductToCartContinuously() {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
//        getCartPage().checkQuantityAvailabelFail(PropertiesHelper.getValue("product_P01"));
//    }





}
