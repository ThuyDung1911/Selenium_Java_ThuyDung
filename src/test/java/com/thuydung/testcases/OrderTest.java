package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.pages.LoginPage;
import com.thuydung.utils.JiraCreateIssue;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiem tra luồng dat hang thanh cong khi co coupon")
    public void TC_FlowOrderSuccessWithCouponValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính", PropertiesHelper.getValue("COUPON_VALID"));
    }
    // Add order success
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiem tra dat hang thanh cong khi coupon het han")
    public void TC_OrderProductWithCouponInvalid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
//        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
//        getCartPage().addProductToCart("Cosy Thuy Dung GBNXJUZQ", "1");
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getCartPage().addProductToCart("Cosy Thuy Dung Update VFYJWRFN", "1");
        getOrderPage().checkOutOrder("Chỉ giao hàng vào giờ hành chính","DUNG2");
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3, description = "Kiểm tra truy cập trang shipping info khi có sản phẩm trong giỏ hàng")
    public void TC_OpenShippingInfoDisplay() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "2");
        getOrderPage().testOpenShippingInfoHaveProductInCart();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4, description = "Kiểm tra truy cập trang shipping info khi không có sản phẩm trong giỏ hàng")
    public void TC_CheckOutOrder() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest@gmail.com", "123456");
        getOrderPage().testOpenShippingInfoWithoutCartEmpty();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5, description = "Kiem tra truy cap trang shippging info khi chua dang nhap")
    public void TC_OpenShippingInfoWithoutLogin() {
        getOrderPage().testOpenShippingInfoWithoutLogin();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6, description = "Kiem tra thong tin dia chi tai trang shipping info so voi thong tin dia chi them o profile")
    public void TC_ShippingInfoWithProfile() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testCheckShippingInfoWithProfile();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7, description = "Kiem tra khi them dia chi tai man shipping info")
    public void TC_AddNewAddressInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getOrderPage().testAddNewAddressInShippingInfo(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8, description = "Kiem tra khi sua dia chi tai man shipping info")
    public void TC_EditAddressInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getOrderPage().testEditAddressInShippingInfo(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9, description = "Kiem tra khi chon dia chi trong shipping info")
    public void TC_SelectAddressInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testSelectAddressInShippingInfoWithAddress("3");
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10, description = "Kiem tra truy cap trang delivery info tu trang shipping info khi khong co dia chi")
    public void TC_OpenDeliveryInfoWithoutProductInCart() {
        getLoginPage().loginSuccessWithCustomerAccount("dungtest2@gmail.com", "123456");
        getOrderPage().testOpenDeliveryInfoWithoutAddress();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 11, description = "Kiem tra truy cap trang delivery info tu URL")
    public void TC_OpenDeliveryInfoFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithURL();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 12, description  = "Kiem tra truy cap trang delivery info tu trang shipping info")
    public void TC_OpenDeliveryInfoFromShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testOpenDeliveryInfoWithShippingInfo();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 13, description = "Kiem tra thong tin danh sach san pham tai trang delivery info")
    public void TC_CheckProductInDeliveryInfoDisplay() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testProductInDeliveryInfoDisplay();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 14, description = "Kiem tra khi chon phuong thuc van chuyen")
    public void TC_SelectShippingMethod() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testSelectShippingMethod("Home Delivery");
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 15, description = "Kiem tra khi truy cap trang Payment tu trang Shipping Info")
    public void TC_OpenPaymentInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testOpenPaymentInfoFromShippingInfoDisplay();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 16, description = "Kiem tra khi truy cap trang Payment tu URL")
    public void TC_OpenPaymentInfoFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().openPaymentInfoFromURL();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 17, description = "Kiem tra thong tin don hang tai trang Payment so voi gio hang")
    public void TC_InfoOrderInPaymentInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testInfoOrderInPaymentInfo();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 18, description = "Kiem tra khi apply discount coupon hop le")
    public void TC_ApplyDiscountCouponValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountValid(PropertiesHelper.getValue("COUPON_VALID"));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 19, description = "Kiem tra khi apply discount coupon khong ton tai")
    public void TC_ApplyDiscountCouponNotExist() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountNotExist("DUNG3");
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 20, description = "Kiem tra khi apply discount coupon da het han")
    public void TC_ApplyCouponDiscountExpired() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung FRCMXCFY", "1");
        getOrderPage().testApplyCouponDiscountExpired("DUNG2");
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 21, description = "Kiem tra thong tin tong tien khi khong co discount tai trang Payment")
    public void TC_CheckTotalPriceInPaymentInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));

        getOrderPage().testTotalPriceInPaymentInfoWithNoDiscountCoupon();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 22, description = "Kiem tra thong tin tong tien khi co discount tai trang Payment")
    public void TC_CheckTotalPriceInPaymentInfoWithDiscountCoupon() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
        getOrderPage().testTotalPriceInPaymentInfoWithDiscountCoupon();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 23, description = "Kiem tra khi chon dong y dieu khoan")
    public void TC_SelectAgreeTerms() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testSelectAgreeTerms();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 24, description = "Kiem tra khi khong chon dong y dieu khoan")
    public void TC_NotSelectAgreeTerms() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testNotSelectAgreeTerms();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 25, description = "Kiem tra khi chon phuong thuc thanh toan Cash on Delivery")
    public void TC_SelectPaymentMethodCashOnDelivery() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testChoosePaymentMethodCashOnDelivery();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 26, description = "Kiem tra khi an vao link Terms and Conditions")
    public void TC_ClickInTermsAndConditions() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testClickInTermsAndConditions();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 27, description = "Kiem tra khi an vao link Privacy Policy")
    public void TC_ClickInPrivacyPolicy() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getOrderPage().testClickInPrivacyPolicy();
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 29, description = "kiem tra khi truy cap trang Confirm Order tu trang Shipping Info")
    public void TC_OpenConfirmOrderFromShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        //getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromShippingInfo();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 30, description = "Kiem tra khi dat don hang thanh cong")
    public void TC_MessageOrderSuccess() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testMessageOrderSuccess();
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 31, description = "Kiem tra khi huy don hang")
    public void TC_CancelOrder() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        WebUI.openURL("https://cms.anhtester.com/purchase_history");
        By orderNotCancel = By.xpath("(//a[@title='Cancel'])[1]/ancestor::tr/td[1]");
        getOrderPage().testCancelOrder(WebUI.getElementText(orderNotCancel));
    }
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 28, description = "Kiem tra khi truy cap trang Confirm Order tu URL")
    public void TC_OpenConfirmOrderFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getCartPage().addProductToCart("Gio qua Tet Thuy Dung CZRFANYB", "1");
        getOrderPage().testOpenConfirmOrderFromURL();
    }

}
