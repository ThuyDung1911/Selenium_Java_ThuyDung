package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //đăng nhập thành công vào trang Admin quản trị
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 1, description = "Đăng nhập thành công vào trang Admin quản trị")
    public void TC_LoginSuccessAdminPage() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
    }

    //đăng nhập thành công với tài khoản customer
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 2, description = "Đăng nhập thành công với tài khoản customer")
    public void TC_LoginSuccessWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
    }

    //đăng nhập thành công với tài khoản seller
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 3, description = "Đăng nhập thành công với tài khoản seller")
    public void TC_LoginSuccessWithSellerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithSellerAccount(excel.getCellData("email", 6), excel.getCellData("password", 6));
    }

    //đăng nhập với email để trống
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 4, description = "Đăng nhập với email để trống")
    public void TC_LoginFailWithEmailNull() {
        getLoginPage().loginFailWithEmailNull();
    }

    //đăng nhập với password trống
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 5, description = "Đăng nhập với password trống")
    public void TC_LoginFailWithNullPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithNullPassword(excel.getCellData("email", 2));
    }

    //đăng nhập với password không đúng
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 6, description = "Đăng nhập với password không đúng")
    public void TC_LoginFailWithIncorrectPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithIncorrectPassword(excel.getCellData("email", 3), excel.getCellData("password", 3));
    }

    //đăng nhập với email không tồn tại
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 7, description = "Đăng nhập với email không tồn tại")
    public void TC_LoginFailWithEmailDoesNotExist() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithEmailDoesNotExist(excel.getCellData("email", 1), excel.getCellData("password", 1));

    }

    //đăng nhập với email sai định dạng
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 8, description = "Đăng nhập với email sai định dạng")
    public void TC_LoginFailWithInvalidEmailFormat() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithInvalidEmailFormat(excel.getCellData("email", 7), excel.getCellData("password", 7));

    }






}
