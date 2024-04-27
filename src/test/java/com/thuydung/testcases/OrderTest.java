package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.pages.LoginPage;
import com.thuydung.utils.JiraCreateIssue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 1, description = "Kiem tra chuc nang tao don hang thanh cong khi co ap ma giam gia hop le")
    public void TC_FlowOrderSuccessWithCouponValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().checkOutOrder(excel.getCellData("note", 2), PropertiesHelper.getValue("COUPON_VALID"));
    }

    // Add order success
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 2, description = "Kiem tra chuc nang tao don hang thanh cong khi co ap ma giam gia da het han")
    public void TC_OrderProductWithCouponInvalid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        getOrderPage().checkOutOrder(excel.getCellData("note", 1), "DUNG2");
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 3, description = "Kiem tra chuc nang tao don hang thanh cong khi khong ap ma giam gia")
    public void TC_OrderProductWithNoCoupon() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        getOrderPage().checkOutOrder(excel.getCellData("note", 3));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 4, description = "Kiem tra truy cap trang shipping info khi co san pham trong gio hang")
    public void TC_OpenShippingInfoDisplay() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 3), excel.getCellData("quantity", 3));
        getOrderPage().testOpenShippingInfoHaveProductInCart();
        getOrderPage().testCheckShippingInfoWithProfile();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 5, description = "Kiem tra truy cap trang shipping info khi khong co san pham trong gio hang")
    public void TC_CheckOutOrderWithoutCartEmpty() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

        getOrderPage().testOpenShippingInfoWithoutCartEmpty();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 6, description = "Kiem tra chi tiet hoa don")
    public void TC_CheckOrderDetail() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        getOrderPage().checkOutOrder(excel.getCellData("note", 3));
        getOrderPage().checkHistoryOrder(excel.getCellData("note", 3));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 7, description = "Kiem tra truy cap trang shipping info khi chua dang nhap")
    public void TC_OpenShippingInfoWithoutLogin() {
        getOrderPage().testOpenShippingInfoWithoutLogin();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 8, description = "Kiem tra chuc nang them dia chi giao hang hop le")
    public void TC_AddNewAddressInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getOrderPage().testAddNewAddressInShippingInfo(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 9, description = "Kiem tra chuc nang them dia chi giao hang khong hop le")
    public void TC_AddNewAddressInvalidInShippingInfo() {
        try {
            ExcelHelper excel = new ExcelHelper();
            excel.setExcelFile("DataTest/Register.xlsx", "Register");
            getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
            excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
            getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

            //        excel.setExcelFile("DataTest/Login.xlsx", "Login");
            //        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
            excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
            excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
            getOrderPage().testAddNewAddressInvalidInShippingInfo(excel.getCellData("address", 2), excel.getCellData("country", 2), excel.getCellData("state", 2), excel.getCellData("city", 2), excel.getCellData("postal code", 2), excel.getCellData("phone", 2));
        } catch (Exception e) {
            e.printStackTrace();
//            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
//                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//                js.executeScript("location.reload()");
//            }
        }
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 10, description = "Kiem tra chuc nang sua dia chi giao hang hop le")
    public void TC_EditAddressInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));

        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getOrderPage().testEditAddressInShippingInfo(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 11, description = "Kiem tra chuc nang sua dia chi giao hang khong hop le")
    public void TC_EditAddressInvalidInShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));

        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getOrderPage().testEditAddressInvalidInShippingInfo(excel.getCellData("address", 2), excel.getCellData("country", 2), excel.getCellData("state", 2), excel.getCellData("city", 2), excel.getCellData("postal code", 2), excel.getCellData("phone", 2));
    }

//    @JiraCreateIssue(isCreateIssue = true)
//    @Test(priority = 9, description = "Kiem tra khi chon dia chi trong shipping info")
//    public void TC_SelectAddressInShippingInfo() {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
//        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
//        getOrderPage().testSelectAddressInShippingInfoWithAddress("3");
//    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 12, description = "Kiem tra truy cap trang delivery info tu trang shipping info khi khong co san pham trong gio hang")
    public void TC_OpenDeliveryInfoWithoutProductInCart() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));

//        getLoginPage().loginSuccessWithCustomerAccount("dungtest2@gmail.com", "123456");
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));

        getOrderPage().testOpenDeliveryInfoWithoutAddress();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 13, description = "Kiem tra truy cap trang delivery info tu trang shipping info khi co dia chi")
    public void TC_OpenDeliveryInfoFromShippingInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));

        getOrderPage().testOpenDeliveryInfoWithShippingInfo();
        getOrderPage().testProductInDeliveryInfoDisplay();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 14, description = "Kiem tra khi chon phuong thuc van chuyen")
    public void TC_SelectShippingMethod() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 3), excel.getCellData("quantity", 3));
        getOrderPage().testSelectShippingMethod("Home Delivery");
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 15, description = "Kiem tra truy cap trang Payment tu trang Shipping Info")
    public void TC_OpenPaymentInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 3), excel.getCellData("quantity", 3));
        getOrderPage().testOpenPaymentInfoFromShippingInfoDisplay();
        getOrderPage().testInfoOrderInPaymentInfo();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 16, description = "Kiem tra khi chon phuong thuc thanh toan Cash on Delivery")
    public void TC_SelectPaymentMethodCashOnDelivery() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 3), excel.getCellData("quantity", 3));
        getOrderPage().testChoosePaymentMethodCashOnDelivery();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 17, description = "Kiem tra khi chua dong y dieu kien, dieu khoan")
    public void TC_NotSelectAgreeTerms() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 3), excel.getCellData("quantity", 3));
        getOrderPage().testNotSelectAgreeTerms();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 18, description = "Kiem tra chuc nang huy don hang")
    public void TC_CancelOrder() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 2), excel.getCellData("quantity", 2));
        getOrderPage().checkOutOrder(excel.getCellData("note", 3));
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/purchase_history");
        By orderNotCancel = By.xpath("(//a[@title='Cancel'])[1]/ancestor::tr/td[1]");
        getOrderPage().testCancelOrder(WebUI.getElementText(orderNotCancel));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 19, description = "Kiem tra ap ma coupon hop le")
    public void TC_ApplyDiscountCouponValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
        getLoginPage().logOutRoleAdmin();
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testApplyCouponDiscountValid(PropertiesHelper.getValue("COUPON_VALID"));
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 20, description = "Kiem tra ap ma coupon khong ton tai")
    public void TC_ApplyDiscountCouponNotExist() {
        try {
            ExcelHelper excel = new ExcelHelper();
            excel.setExcelFile("DataTest/Register.xlsx", "Register");
            getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
            excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
            getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
            excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
            getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
            getOrderPage().testApplyCouponDiscountNotExist("DUNG3");
        } catch (Exception e) {
            e.printStackTrace();
//            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
//                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//                js.executeScript("location.reload()");
//            }
        }

    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 21, description = "Kiem tra ap ma coupon da het han")
    public void TC_ApplyCouponDiscountExpired() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testApplyCouponDiscountExpired("DUNG2");
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 22, description = "Kiem tra khi an vao lien ket Terms and Conditions")
    public void TC_ClickInTermsAndConditions() {
        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testClickInTermsAndConditions();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 23, description = "Kiem tra khi an vao lien ket Privacy Policy")
    public void TC_ClickInPrivacyPolicy() {
        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//       getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testClickInPrivacyPolicy();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 24, description = "Kiem tra truy cap trang Confirm order tu URL")
    public void TC_OpenConfirmOrderFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testOpenConfirmOrderFromURL();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 25, description = "Kiem tra truy cap trang Payment tu URL")
    public void TC_OpenPaymentInfoFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().openPaymentInfoFromURL();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 26, description = "Kiem tra truy cap trang Delivery Info tu URL")
    public void TC_OpenDeliveryInfoFromURL() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));
        getOrderPage().testOpenDeliveryInfoWithURL();
    }
}
