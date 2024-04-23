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
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiểm tra chức năng tạo đơn hàng thành công khi có áp mã giảm giá hợp lệ")
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
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiểm tra chức năng tạo đơn hàng thành công khi có áp mã giảm giá đã hết hạn")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3, description = "Kiểm tra chức năng tạo đơn hàng thành công khi không áp mã giảm giá")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4, description = "Kiểm tra truy cập trang shipping info khi có sản phẩm trong giỏ hàng")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5, description = "Kiểm tra truy cập trang shipping info khi không có sản phẩm trong giỏ hàng")
    public void TC_CheckOutOrderWithoutCartEmpty() {
//        getLoginPage().loginSuccessWithCustomerAccount("dungtest@gmail.com", "123456");
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddress(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));

        getOrderPage().testOpenShippingInfoWithoutCartEmpty();
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 6, description = "Kiểm tra chi tiết hóa đơn")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7, description = "Kiểm tra truy cập trang shipping info khi chưa đăng nhập")
    public void TC_OpenShippingInfoWithoutLogin() {
        getOrderPage().testOpenShippingInfoWithoutLogin();
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8, description = "Kiểm tra chức năng thêm địa chỉ giao hàng hợp lệ")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9, description = "Kiểm tra chức năng thêm địa chỉ giao hàng không hợp lệ")
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
            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                js.executeScript("location.reload()");
            }
        }
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10, description = "Kiểm tra chức năng sửa địa chỉ giao hàng hợp lệ")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 11, description = "Kiểm tra chức năng sửa địa chỉ giao hàng không hợp lệ")
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

//    @JiraCreateIssue(isCreateIssue = false)
//    @Test(priority = 9, description = "Kiem tra khi chon dia chi trong shipping info")
//    public void TC_SelectAddressInShippingInfo() {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
//        getCartPage().addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
//        getOrderPage().testSelectAddressInShippingInfoWithAddress("3");
//    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 12, description = "Kiểm tra truy cập trang delivery info từ trang shipping info khi không có địa chỉ")
    public void TC_OpenDeliveryInfoWithoutProductInCart() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));

//        getLoginPage().loginSuccessWithCustomerAccount("dungtest2@gmail.com", "123456");
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "Order");
        getCartPage().addProductToCart(excel.getCellData("productName", 1), excel.getCellData("quantity", 1));

        getOrderPage().testOpenDeliveryInfoWithoutAddress();
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 13, description = "Kiểm tra truy cập trang delivery info từ trang shipping info khi có địa chỉ")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 14, description = "Kiểm tra khi chọn phương thức vận chuyển")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 15, description = "Kiểm tra truy cập trang Payment từ trang Shipping Info")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 16, description = "Kiểm tra khi chọn phương thức thanh toán Cash on Delivery")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 17, description = "Kiểm tra khi chưa đồng ý điều kiện, điều khoản")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 18, description = "Kiểm tra chức năng hủy đơn hàng")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 19, description = "Kiểm tra áp mã coupon hợp lệ")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 20, description = "Kiểm tra áp mã coupon không tồn tại")
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
            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                js.executeScript("location.reload()");
            }
        }

    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 21, description = "Kiểm tra áp mã coupon đã hết hạn")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 22, description = "Kiểm tra khi ấn vào link Terms and Conditions")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 23, description = "Kiểm tra khi ấn vào link Privacy Policy")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 24, description = "Kiểm tra truy cập trang Confirm order từ URL")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 25, description = "Kiểm tra truy cập trang Payment từ URL")
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

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 26, description = "Kiểm tra truy cập trang delivery info từ URL")
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
