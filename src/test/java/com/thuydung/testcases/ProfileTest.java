package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.pages.LoginPage;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    public ExcelHelper excelLogin;
    public ExcelHelper excelUpdateProfile;

    // Update Info Basic Valid Customer
    @Test(priority = 1)
    public void TC_UpdateInfoBasicValidProfileCustomer() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicValidProfileCustomer(excelUpdateProfile.getCellData("name",1), excelUpdateProfile.getCellData("phone",1), excelUpdateProfile.getCellData("photo",1), excelUpdateProfile.getCellData("password",1), excelUpdateProfile.getCellData("confirm password",1));
    }
    //Update Info Basic Customer without name
    @Test(priority = 2)
    public void TC_UpdateInfoBasicProfileCustomerWithoutName() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerWithoutName(excelUpdateProfile.getCellData("email",2), excelUpdateProfile.getCellData("phone",2), excelUpdateProfile.getCellData("photo",2), excelUpdateProfile.getCellData("password",2), excelUpdateProfile.getCellData("confirm password",2));
    }
    //Update Info Basic Customer without phone
    @Test(priority = 3)
    public void TC_UpdateInfoBasicProfileCustomerWithoutPhone() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerWithoutPhone(excelUpdateProfile.getCellData("name",3), excelUpdateProfile.getCellData("phone",3), excelUpdateProfile.getCellData("photo",3), excelUpdateProfile.getCellData("password",3), excelUpdateProfile.getCellData("confirm password",3));
    }
    //Update Info Basic Customer no match password
    @Test(priority = 4)
    public void TC_UpdateInfoBasicProfileCustomerNoMatchPassword() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerNoMatchPassword(excelUpdateProfile.getCellData("name",4), excelUpdateProfile.getCellData("phone",4), excelUpdateProfile.getCellData("photo",4), excelUpdateProfile.getCellData("password",4), excelUpdateProfile.getCellData("confirm password",4));
//        getLoginPage().loginFailWithIncorrectPassword(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 4));
//        getLoginPage().loginFailWithIncorrectPassword(excelLogin.getCellData("email", 4), excelLogin.getCellData("corfirm password", 4));
//        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 4));

    }
    //Update Info Basic Customer with password < 6 characters
    @Test(priority = 5)
    public void TC_UpdateInfoBasicProfileCustomerPasswordLessCharacter() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerPasswordLessCharacter(excelUpdateProfile.getCellData("name",5), excelUpdateProfile.getCellData("phone",5), excelUpdateProfile.getCellData("photo",5), excelUpdateProfile.getCellData("password",5), excelUpdateProfile.getCellData("confirm password",5));
//        WebUI.clickElement(DashboardPage.buttonLogout);
//        WebUI.waitForPageLoaded();
//        WebUI.clickElement(HomePage.linkLogin);
//        getLoginPage().loginFailWithIncorrectPassword(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 5));
//        WebUI.clickElement(DashboardPage.buttonLogout);
//        WebUI.waitForPageLoaded();
//        WebUI.clickElement(HomePage.linkLogin);
//        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 4));
    }
    //Update Email Valid Customer
    @Test(priority = 6)
    public void updateValidEmailCustomer() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateValidEmailCustomer(excelUpdateProfile.getCellData("email",1));
    }
    //Update Email Customer with Current Email
    @Test(priority = 7)
    public void updateEmailCustomerWithCurrentEmail() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateEmailCustomerWithCurrentEmail(excelLogin.getCellData("email", 4));
    }
    //Update Email Customer with Email already exists
    @Test(priority = 8)
    public void updateEmailCustomerWithEmailExist() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateProfileWithEmailExist(excelUpdateProfile.getCellData("email",2));
    }
    //Update Email Customer with Email invalid
    @Test(priority = 9)
    public void updateEmailCustomerWithEmailInvalid() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateProfileWithNewEmailIncorrectFormat(excelUpdateProfile.getCellData("email",3));
    }
    //Add New Address Customer
    @Test(priority = 3)
    public void testAddNewAddress() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddress();
    }
}
