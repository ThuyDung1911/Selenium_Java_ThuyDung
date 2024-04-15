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
    public void TC_FlowOrderSuccess() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
//        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }
    // Add order success
    @Test(priority = 2, description = "Kiem tra dat hang thanh cong khi coupon het han")
    public void TC_OrderProduct() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
//        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
//        getCartPage().addProductToCart("Cosy Thuy Dung GBNXJUZQ", "1");
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getCartPage().addProductToCart("Cosy Thuy Dung Update VFYJWRFN", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }

    // Checkout order without product
    @Test(priority = 2, description = "Kiem tra dat hang khong co san pham")
    public void TC_CheckoutOrderWithoutProduct() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính");
    }

    @Test(priority = 3, description = "Kiem tra truy cap trang shippging info khi co san pham trong gio hang")
    public void TC_OpenShippingInfoDisplay() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
        getOrderPage().testOpenShippingInfoHaveProductInCart();
    }

    @Test(priority = 4, description = "Kiem tra truy cap trang shippging info khi khong co san pham trong gio hang")
    public void TC_CheckOutOrder() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest@gmail.com", "123456");
        getOrderPage().testOpenShippingInfoWithoutCartEmpty();
    }

    @Test(priority = 5, description = "Kiem tra truy cap trang shippging info khi chua dang nhap")
    public void TC_OpenShippingInfoWithoutLogin() {
        getOrderPage().testOpenShippingInfoWithoutLogin();
    }

    @Test(priority = 6, description = "Kiem tra thong tin dia chi tai trang shipping info so voi thong tin dia chi them o profile")
    public void TC_ShippingInfoWithProfile() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testCheckShippingInfoWithProfile();
    }

    @Test(priority = 7, description = "Kiem tra khi them dia chi tai man shipping info")
    public void TC_AddNewAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testAddNewAddressInShippingInfo(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }

    @Test(priority = 8, description = "Kiem tra khi sua dia chi tai man shipping info")
    public void TC_EditAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testEditAddressInShippingInfo(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }
    @Test(priority = 9, description = "Kiem tra khi chon dia chi trong shipping info")
    public void TC_SelectAddressInShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testSelectAddressInShippingInfoWithAddress("3");
    }
    @Test(priority = 10, description = "Kiem tra truy cap trang delivery info tu trang shipping info khi khong co dia chi")
    public void TC_OpenDeliveryInfoWithoutProductInCart() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest2@gmail.com", "123456");
        getOrderPage().testOpenDeliveryInfoWithoutAddress();
    }
    @Test(priority = 11, description = "Kiem tra truy cap trang delivery info tu URL")
    public void TC_OpenDeliveryInfoFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithURL();
    }
    @Test(priority = 12, description  = "Kiem tra truy cap trang delivery info tu trang shipping info")
    public void TC_OpenDeliveryInfoFromShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithShippingInfo();
    }
    @Test(priority = 12, description = "Kiem tra thong tin danh sach san pham tai trang delivery info")
    public void TC_CheckProductInDeliveryInfoDisplay() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testProductInDeliveryInfoDisplay();
    }
    @Test(priority = 13, description = "Kiem tra khi chon phuong thuc van chuyen")
    public void TC_SelectShippingMethod() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testSelectShippingMethod("Home Delivery");
    }
    @Test(priority = 14, description = "Kiem tra khi truy cap trang Payment tu trang Shipping Info")
    public void TC_OpenPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testOpenPaymentInfoFromShippingInfoDisplay();
    }
    @Test(priority = 15, description = "Kiem tra khi truy cap trang Payment tu URL")
    public void TC_OpenPaymentInfoFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().openPaymentInfoFromURL();
    }
    @Test(priority = 16, description = "Kiem tra thong tin don hang tai trang Payment so voi gio hang")
    public void TC_InfoOrderInPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testInfoOrderInPaymentInfo();
    }
    @Test(priority = 17, description = "Kiem tra khi apply discount coupon hop le")
    public void TC_ApplyDiscountCouponValid() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountValid(PropertiesHelper.getValue("COUPON_VALID"));
    }

    @Test(priority = 18, description = "Kiem tra khi apply discount coupon khong ton tai")
    public void TC_ApplyDiscountCouponNotExist() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountNotExist("DUNG3");
    }
    @Test(priority = 19, description = "Kiem tra khi apply discount coupon da het han")
    public void TC_ApplyCouponDiscountExpried() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountExpired("DUNG2");
    }
    @Test(priority = 19, description = "Kiem tra thong tin tong tien khi khong co discount tai trang Payment")
    public void TC_CheckTotalPriceInPaymentInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));

        getOrderPage().testTotalPriceInPaymentInfoWithNoDiscountCoupon();
    }
    @Test(priority = 20, description = "Kiem tra thong tin tong tien khi co discount tai trang Payment")
    public void TC_CheckTotalPriceInPaymentInfoWithDiscountCoupon() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testTotalPriceInPaymentInfoWithDiscountCoupon();
    }
    @Test(priority = 21, description = "Kiem tra khi chon dong y dieu khoan")
    public void TC_SelectAgreeTerms() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testSelectAgreeTerms();
    }
    @Test(priority = 22, description = "Kiem tra khi khong chon dong y dieu khoan")
    public void TC_NotSelectAgreeTerms() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testNotSelectAgreeTerms();
    }
    @Test(priority = 23, description = "Kiem tra khi chon phuong thuc thanh toan Cash on Delivery")
    public void TC_SelectPaymentMethodCashOnDelivery() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testChoosePaymentMethodCashOnDelivery();
    }
    @Test(priority = 24, description = "Kiem tra khi an vao link Terms and Conditions")
    public void TC_ClickInTermsAndConditions() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testClickInTermsAndConditions();
    }
    @Test(priority = 25, description = "Kiem tra khi an vao link Privacy Policy")
    public void TC_ClickInPrivacyPolicy() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getOrderPage().testClickInPrivacyPolicy();
    }
    @Test(priority = 26, description = "Kiem tra khi truy cap trang Confirm Order tu URL")
    public void TC_OpenConfirmOrderFromURL() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromURL();
    }
    @Test(priority = 27, description = "kiem tra khi truy cap trang Confirm Order tu trang Shipping Info")
    public void TC_OpenConfirmOrderFromShippingInfo() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        //getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromShippingInfo();
    }
    @Test(priority = 28, description = "Kiem tra khi dat don hang thanh cong")
    public void TC_MessageOrderSuccess() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testMessageOrderSuccess();
    }
    @Test(priority = 29, description = "Kiem tra khi huy don hang")
    public void TC_CancelOrder() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        WebUI.openURL("https://cms.anhtester.com/purchase_history");
        By orderNotCancel = By.xpath("(//a[@title='Cancel'])[1]/ancestor::tr/td[1]");
        getOrderPage().testCancelOrder(WebUI.getElementText(orderNotCancel));
    }










}
