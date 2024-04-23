package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    //đăng ký tài khoản Customer thành công vào với thông tin hợp lệ
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1)
    public void TC_RegisterSuccessCustomerWithValidBasicInfo() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerSuccessCustomerAccount(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
    }

    //Đăng ký tài khoản Customer không thành công khi không nhập fullname
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2)
    public void TC_RegisterFailCustomerWithoutFullname() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailWithoutFullname(excel.getCellData("fullname", 2), excel.getCellData("email", 2), excel.getCellData("password", 2), excel.getCellData("confirm password", 2));
    }

    //Đăng ký tài khoản Customer không thành công khi không nhập email
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3)
    public void TC_RegisterFailCustomerWithoutEmail() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailWithoutEmail(excel.getCellData("fullname", 3), excel.getCellData("email", 3), excel.getCellData("password", 3), excel.getCellData("confirm password", 3));
    }

    //Đăng ký tài khoản Customer không thành công khi không nhập email đã tồn tại
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4)
    public void TC_RegisterFailCustomerWithExistEmail() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailWithExistEmail(excel.getCellData("fullname", 4), excel.getCellData("email", 4), excel.getCellData("password", 4), excel.getCellData("confirm password", 4));
    }

    //Đăng ký tài khoản Customer không thành công khi nhập email không đúng định dạng
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5)
    public void TC_RegisterFailCustomerWithInvalidEmail() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailWithInvalidEmail(excel.getCellData("fullname", 5), excel.getCellData("email", 5), excel.getCellData("password", 5), excel.getCellData("confirm password", 5));
    }

    //Đăng ký tài khoản Customer không thành công khi không nhập password
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6)
    public void TC_RegisterFailCustomerWithoutPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailWithoutPassword(excel.getCellData("fullname", 6), excel.getCellData("email", 6), excel.getCellData("password", 6), excel.getCellData("confirm password", 6));
    }

    //Đăng ký tài khoản Customer không thành công khi nhập password ít hơn 6 ký tự
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7)
    public void TC_RegisterFailCustomerWithPasswordLessCharacter() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailCustomerWithPasswordLessCharacter(excel.getCellData("fullname", 7), excel.getCellData("email", 7), excel.getCellData("password", 7), excel.getCellData("confirm password", 7));
    }

    //Đăng ký tài khoản Customer không thành công khi nhap password không trùng khớp
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8)
    public void TC_RegisterFailCustomerWithPasswordNotMatch() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailCustomerWithPasswordNotMatch(excel.getCellData("fullname", 8), excel.getCellData("email", 8), excel.getCellData("password", 8), excel.getCellData("confirm password", 8));
    }

    //Đăng ký tài khoản Customer không thành công khi không chấp nhận điều khoản
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9)
    public void TC_RegisterFailCustomerWithoutAcceptTerm() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Register.xlsx", "Register");
        getRegisterPage().registerFailCustomerWithoutAcceptTerm(excel.getCellData("fullname", 1), excel.getCellData("email", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
    }
}
