package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.pages.LoginPage;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    public ExcelHelper excelLogin;
    public ExcelHelper excelUpdateProfile;

    //Update Info Basic Customer without name
    @Test(priority = 1)
    public void TC_UpdateInfoBasicProfileCustomerWithoutName() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerWithoutName(excelUpdateProfile.getCellData("name", 2), excelUpdateProfile.getCellData("phone", 2), excelUpdateProfile.getCellData("photo", 2), excelUpdateProfile.getCellData("password", 2), excelUpdateProfile.getCellData("confirm password", 2));
    }

    //Update Info Basic Customer without phone
    @Test(priority = 2)
    public void TC_UpdateInfoBasicProfileCustomerWithoutPhone() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerWithoutPhone(excelUpdateProfile.getCellData("name", 3), excelUpdateProfile.getCellData("phone", 3), excelUpdateProfile.getCellData("photo", 3), excelUpdateProfile.getCellData("password", 3), excelUpdateProfile.getCellData("confirm password", 3));
    }

    //Update Info Basic Customer no match password
    @Test(priority = 3)
    public void TC_UpdateInfoBasicProfileCustomerNoMatchPassword() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerNoMatchPassword(excelUpdateProfile.getCellData("name", 4), excelUpdateProfile.getCellData("phone", 4), excelUpdateProfile.getCellData("photo", 4), excelUpdateProfile.getCellData("password", 4), excelUpdateProfile.getCellData("confirm password", 4));
//        getLoginPage().loginFailWithIncorrectPassword(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 4));
//        getLoginPage().loginFailWithIncorrectPassword(excelLogin.getCellData("email", 4), excelLogin.getCellData("corfirm password", 4));
//        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 4));

    }

    //Update Info Basic Customer with password < 6 characters
    @Test(priority = 4)
    public void TC_UpdateInfoBasicProfileCustomerPasswordLessCharacter() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateInfoBasicProfileCustomerPasswordLessCharacter(excelUpdateProfile.getCellData("name", 5), excelUpdateProfile.getCellData("phone", 5), excelUpdateProfile.getCellData("photo", 5), excelUpdateProfile.getCellData("password", 5), excelUpdateProfile.getCellData("confirm password", 5));
        //getProfilePage().updateInfoBasicValidProfileCustomer(excelUpdateProfile.getCellData("name", 1), excelUpdateProfile.getCellData("phone", 1), excelUpdateProfile.getCellData("photo", 1), excelUpdateProfile.getCellData("password", 1), excelUpdateProfile.getCellData("confirm password", 1));
    }
    // Update Info Basic Valid Customer
    @Test(priority = 5)
    public void TC_UpdateInfoBasicValidProfileCustomer() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelUpdateProfile.getCellData("password", 5));
        getProfilePage().updateInfoBasicValidProfileCustomer(excelUpdateProfile.getCellData("name", 1), excelUpdateProfile.getCellData("phone", 1), excelUpdateProfile.getCellData("photo", 1), excelUpdateProfile.getCellData("password", 1), excelUpdateProfile.getCellData("confirm password", 1));
    }

    //Update Email Valid Customer
    @Test(priority = 6)
    public void updateValidEmailCustomer() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getLoginPage().loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().updateValidEmailCustomer(excelUpdateProfile.getCellData("email", 1));
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
        getProfilePage().updateProfileWithEmailExist(excelUpdateProfile.getCellData("email", 2));
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

    //Add New Address Customer Valid
    @Test(priority = 10)
    public void testAddNewAddressValid() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressValid(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }

    //Add New Address Customer without Address
    @Test(priority = 11)
    public void testAddNewAddressWithoutAddress() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutAddress(excelUpdateProfile.getCellData("address", 2), excelUpdateProfile.getCellData("country", 2), excelUpdateProfile.getCellData("state", 2), excelUpdateProfile.getCellData("city", 2), excelUpdateProfile.getCellData("postal code", 2), excelUpdateProfile.getCellData("phone", 2));
    }

    //Add New Address Customer without Country
    @Test(priority = 12)
    public void testAddNewAddressWithoutCountry() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutCountry(excelUpdateProfile.getCellData("address", 3), excelUpdateProfile.getCellData("postal code", 3), excelUpdateProfile.getCellData("phone", 3));
    }

    //Add New Address Customer without State
    @Test(priority = 13)
    public void testAddNewAddressWithoutState() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutState(excelUpdateProfile.getCellData("address", 5), excelUpdateProfile.getCellData("country", 5), excelUpdateProfile.getCellData("postal code", 5), excelUpdateProfile.getCellData("phone", 5));
    }

    //Add New Address Customer without City
    @Test(priority = 14)
    public void testAddNewAddressWithoutCity() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutCity(excelUpdateProfile.getCellData("address", 5), excelUpdateProfile.getCellData("country", 5), excelUpdateProfile.getCellData("state", 5), excelUpdateProfile.getCellData("postal code", 5), excelUpdateProfile.getCellData("phone", 5));
    }

    //Add New Address Customer without Postal Code
    @Test(priority = 15)
    public void testAddNewAddressWithoutPostalCode() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutPostalCode(excelUpdateProfile.getCellData("address", 6), excelUpdateProfile.getCellData("country", 6), excelUpdateProfile.getCellData("state", 6), excelUpdateProfile.getCellData("city", 6), excelUpdateProfile.getCellData("postal code", 6), excelUpdateProfile.getCellData("phone", 6));
    }

    //Add New Address Customer without Phone
    @Test(priority = 16)
    public void testAddNewAddressWithoutPhone() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().addNewAddressWithoutPhone(excelUpdateProfile.getCellData("address", 7), excelUpdateProfile.getCellData("country", 7), excelUpdateProfile.getCellData("state", 7), excelUpdateProfile.getCellData("city", 7), excelUpdateProfile.getCellData("postal code", 7), excelUpdateProfile.getCellData("phone", 7));
    }

    // Edit Address Customer Valid
    @Test(priority = 17)
    public void testEditAddressValid() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressValid(excelUpdateProfile.getCellData("address", 1), excelUpdateProfile.getCellData("country", 1), excelUpdateProfile.getCellData("state", 1), excelUpdateProfile.getCellData("city", 1), excelUpdateProfile.getCellData("postal code", 1), excelUpdateProfile.getCellData("phone", 1));
    }

    // Edit Address Customer without Address
    @Test(priority = 18)
    public void testEditAddressWithoutAddress() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutAddress(excelUpdateProfile.getCellData("address", 2), excelUpdateProfile.getCellData("country", 2), excelUpdateProfile.getCellData("state", 2), excelUpdateProfile.getCellData("city", 2), excelUpdateProfile.getCellData("postal code", 2), excelUpdateProfile.getCellData("phone", 2));
    }

    // Edit Address Customer without Country
    @Test(priority = 19)
    public void testEditAddressWithoutCountry() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutCountry(excelUpdateProfile.getCellData("address", 3), excelUpdateProfile.getCellData("country",3), excelUpdateProfile.getCellData("postal code", 3), excelUpdateProfile.getCellData("phone", 3));
    }

    // Edit Address Customer without State
    @Test(priority = 20)
    public void testEditAddressWithoutState() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutState(excelUpdateProfile.getCellData("address", 4), excelUpdateProfile.getCellData("country", 4), excelUpdateProfile.getCellData("state",4), excelUpdateProfile.getCellData("postal code", 4), excelUpdateProfile.getCellData("phone", 4));
    }

    // Edit Address Customer without City
    @Test(priority = 21)
    public void testEditAddressWithoutCity() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutCity(excelUpdateProfile.getCellData("address", 5), excelUpdateProfile.getCellData("country", 5), excelUpdateProfile.getCellData("state", 5), excelUpdateProfile.getCellData("city", 5), excelUpdateProfile.getCellData("postal code", 5), excelUpdateProfile.getCellData("phone", 5));
    }

    // Edit Address Customer without Postal Code
    @Test(priority = 22)
    public void testEditAddressWithoutPostalCode() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutPostalCode(excelUpdateProfile.getCellData("address", 6), excelUpdateProfile.getCellData("country", 6), excelUpdateProfile.getCellData("state", 6), excelUpdateProfile.getCellData("city", 6), excelUpdateProfile.getCellData("postal code", 6), excelUpdateProfile.getCellData("phone", 6));
    }

    // Edit Address Customer without Phone
    @Test(priority = 23)
    public void testEditAddressWithoutPhone() {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelUpdateProfile = new ExcelHelper();
        excelUpdateProfile.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        getProfilePage().editAddressWithoutPhone(excelUpdateProfile.getCellData("address", 7), excelUpdateProfile.getCellData("country", 7), excelUpdateProfile.getCellData("state", 7), excelUpdateProfile.getCellData("city", 7), excelUpdateProfile.getCellData("postal code", 7), excelUpdateProfile.getCellData("phone", 7));
    }

}
