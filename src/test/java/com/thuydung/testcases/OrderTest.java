package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    public ExcelHelper excelLogin;
    public ExcelHelper excelUpdateProfile;

    @Test(priority = 1, description = "Kiem tra luong dat hang thanh cong khi co coupon")
    public void testFlowOrderSuccess() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung Update VFYJWRFN", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }
    // Add order success
    @Test(priority = 2, description = "Kiem tra dat hang thanh cong khi coupon het han")
    public void testOrderProduct() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
//        getCartPage().addProductToCart("Cosy Thuy Dung GBNXJUZQ", "1");
//        getCartPage().addProductToCart("Tiramisu", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }

    // Checkout order without product
    @Test(priority = 2, description = "Kiem tra dat hang khong co san pham")
    public void testCheckoutOrderWithoutProduct() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }

    @Test(priority = 3, description = "Kiem tra truy cap trang shippging info khi co san pham trong gio hang")
    public void testOpenShippingInfoDisplay() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
        getOrderPage().testOpenShippingInfoHaveProductInCart();
    }

    @Test(priority = 4, description = "Kiem tra truy cap trang shippging info khi khong co san pham trong gio hang")
    public void testCheckOutOrder() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest@gmail.com", "123456");
        getOrderPage().testOpenShippingInfoWithoutCartEmpty();
    }

    @Test(priority = 5, description = "Kiem tra truy cap trang shippging info khi chua dang nhap")
    public void testOpenShippingInfoWithoutLogin() {
        getOrderPage().testOpenShippingInfoWithoutLogin();
    }

    @Test(priority = 6, description = "Kiem tra thong tin dia chi tai trang shipping info so voi thong tin dia chi them o profile")
    public void testShippingInfoWithProfile() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testCheckShippingInfoWithProfile();
    }

    @Test(priority = 7, description = "Kiem tra khi them dia chi tai man shipping info")
    public void testAddNewAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testAddNewAddressInShippingInfo(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }

    @Test(priority = 8, description = "Kiem tra khi sua dia chi tai man shipping info")
    public void testEditAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testEditAddressInShippingInfo(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }
    @Test(priority = 9, description = "Kiem tra khi chon dia chi trong shipping info")
    public void testSelectAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testSelectAddressInShippingInfoWithAddress("3");
    }
    @Test(priority = 10, description = "Kiem tra truy cap trang delivery info tu trang shipping info khi khong co dia chi")
    public void testOpenDeliveryInfoWithoutProductInCart() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest2@gmail.com", "123456");
        getOrderPage().testOpenDeliveryInfoWithoutAddress();
    }
    @Test(priority = 11, description = "Kiem tra truy cap trang delivery info tu URL")
    public void testOpenDeliveryInfoFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithURL();
    }
    @Test(priority = 12, description  = "Kiem tra truy cap trang delivery info tu trang shipping info")
    public void testOpenDeliveryInfoFromShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithShippingInfo();
    }
    @Test(priority = 12, description = "Kiem tra thong tin danh sach san pham tai trang delivery info")
    public void testCheckProductInDeliveryInfoDisplay() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testProductInDeliveryInfoDisplay();
    }
    @Test(priority = 13, description = "Kiem tra khi chon phuong thuc van chuyen")
    public void testSelectShippingMethod() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testSelectShippingMethod("Home Delivery");
    }
    @Test(priority = 14, description = "Kiem tra khi truy cap trang Payment tu trang Shipping Info")
    public void testOpenPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenPaymentInfoFromShippingInfoDisplay();
    }
    @Test(priority = 15, description = "Kiem tra khi truy cap trang Payment tu URL")
    public void testOpenPaymentInfoFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().openPaymentInfoFromURL();
    }
    @Test(priority = 16, description = "Kiem tra thong tin don hang tai trang Payment so voi gio hang")
    public void testInfoOrderInPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testInfoOrderInPaymentInfo();
    }
    @Test(priority = 17, description = "Kiem tra khi apply discount coupon hop le")
    public void testApplyDiscountCouponValid() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testApplyCouponDiscountValid(PropertiesHelper.getValue("COUPON_VALID"));
    }

    @Test(priority = 18, description = "Kiem tra khi apply discount coupon khong ton tai")
    public void testApplyDiscountCouponNotExist() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testApplyCouponDiscountNotExist("DUNG3");
    }
    @Test(priority = 19, description = "Kiem tra khi apply discount coupon da het han")
    public void testApplyCouponDiscountExpried() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testApplyCouponDiscountExpired("DUNG2");
    }
    @Test(priority = 19, description = "Kiem tra thong tin tong tien khi khong co discount tai trang Payment")
    public void testCheckTotalPriceInPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testTotalPriceInPaymentInfoWithNoDiscountCoupon();
    }
    @Test(priority = 20, description = "Kiem tra thong tin tong tien khi co discount tai trang Payment")
    public void testCheckTotalPriceInPaymentInfoWithDiscountCoupon() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testTotalPriceInPaymentInfoWithDiscountCoupon();
    }
    @Test(priority = 21, description = "Kiem tra khi chon dong y dieu khoan")
    public void testSelectAgreeTerms() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testSelectAgreeTerms();
    }
    @Test(priority = 22, description = "Kiem tra khi khong chon dong y dieu khoan")
    public void testNotSelectAgreeTerms() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testNotSelectAgreeTerms();
    }
    @Test(priority = 23, description = "Kiem tra khi chon phuong thuc thanh toan Cash on Delivery")
    public void testSelectPaymentMethodCashOnDelivery() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testChoosePaymentMethodCashOnDelivery();
    }
    @Test(priority = 24, description = "Kiem tra khi an vao link Terms and Conditions")
    public void testClickInTermsAndConditions() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testClickInTermsAndConditions();
    }
    @Test(priority = 25, description = "Kiem tra khi an vao link Privacy Policy")
    public void testClickInPrivacyPolicy() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testClickInPrivacyPolicy();
    }
    @Test(priority = 26, description = "Kiem tra khi truy cap trang Confirm Order tu URL")
    public void testOpenConfirmOrderFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromURL();
    }
    @Test(priority = 27, description = "kiem tra khi truy cap trang Confirm Order tu trang Shipping Info")
    public void testOpenConfirmOrderFromShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        //getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromShippingInfo();
    }
    @Test(priority = 28, description = "Kiem tra khi dat don hang thanh cong")
    public void testMessageOrderSuccess() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testMessageOrderSuccess();
    }
    @Test(priority = 29, description = "Kiem tra khi huy don hang")
    public void testCancelOrder() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        WebUI.openURL("https://cms.anhtester.com/purchase_history");
        By orderNotCancel = By.xpath("(//a[@title='Cancel'])[1]/ancestor::tr/td[1]");
        getOrderPage().testCancelOrder(WebUI.getElementText(orderNotCancel));
    }










}
