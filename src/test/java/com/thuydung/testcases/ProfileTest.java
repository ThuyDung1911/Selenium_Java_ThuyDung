package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.pages.LoginPage;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    public ExcelHelper excel;

    // Update Info Basic Valid Customer
    @Test(priority = 1)
    public void testUpdateProfile() {
        excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomer();
    }
    //Update Email Customer
    @Test(priority = 2)
    public void updateEmail() {
        excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getProfilePage().updateEmail();
    }
    //Add New Address Customer
    @Test(priority = 3)
    public void testAddNewAddress() {
        excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getProfilePage().addNewAddress();
    }
}
