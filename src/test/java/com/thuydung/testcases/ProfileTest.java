package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {
    
    //Update Info Basic Customer without name
    @JiraCreateIssue(isCreateIssue = true)
    @Test(priority = 1)
    public void TC_UpdateInfoBasicProfileCustomerWithoutName() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getProfilePage().updateInfoBasicProfileCustomerWithoutName(excel.getCellData("name", 2), excel.getCellData("phone", 2), excel.getCellData("photo", 2), excel.getCellData("password", 2), excel.getCellData("confirm password", 2));
    }

    //Update Info Basic Customer without phone
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2)
    public void TC_UpdateInfoBasicProfileCustomerWithoutPhone() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getProfilePage().updateInfoBasicProfileCustomerWithoutPhone(excel.getCellData("name", 3), excel.getCellData("phone", 3), excel.getCellData("photo", 3), excel.getCellData("password", 3), excel.getCellData("confirm password", 3));
    }

    //Update Info Basic Customer no match password
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3)
    public void TC_UpdateInfoBasicProfileCustomerNoMatchPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getProfilePage().updateInfoBasicProfileCustomerNoMatchPassword(excel.getCellData("name", 4), excel.getCellData("phone", 4), excel.getCellData("photo", 4), excel.getCellData("password", 4), excel.getCellData("confirm password", 4));

    }

    //Update Info Basic Customer with password < 6 characters
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4)
    public void TC_UpdateInfoBasicProfileCustomerPasswordLessCharacter() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getProfilePage().updateInfoBasicProfileCustomerPasswordLessCharacter(excel.getCellData("name", 5), excel.getCellData("phone", 5), excel.getCellData("photo", 5), excel.getCellData("password", 5), excel.getCellData("confirm password", 5));
    }
    // Update Info Basic Valid Customer
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5)
    public void TC_UpdateInfoBasicValidProfileCustomer() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateBasicInfo");
        getProfilePage().updateInfoBasicValidProfileCustomer(excel.getCellData("name", 1), excel.getCellData("phone", 1), excel.getCellData("photo", 1), excel.getCellData("password", 1), excel.getCellData("confirm password", 1));
    }

    //Update Email Valid Customer
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6)
    public void updateValidEmailCustomer() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getProfilePage().updateValidEmailCustomer(excel.getCellData("email", 1));
    }

    //Update Email Customer with Current Email
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7)
    public void updateEmailCustomerWithCurrentEmail() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getProfilePage().updateEmailCustomerWithCurrentEmail(excel.getCellData("email", 4));
    }

    //Update Email Customer with Email already exists
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8)
    public void updateEmailCustomerWithEmailExist() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getProfilePage().updateProfileWithEmailExist(excel.getCellData("email", 2));
    }

    //Update Email Customer with Email invalid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9)
    public void updateEmailCustomerWithEmailInvalid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "UpdateEmail");
        getProfilePage().updateProfileWithNewEmailIncorrectFormat(excel.getCellData("email",3));
    }

    //Add New Address Customer Valid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10)
    public void TC_AddNewAddressValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressValid(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }

    //Add New Address Customer without Address
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 11)
    public void TC_AddNewAddressWithoutAddress() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutAddress(excel.getCellData("address", 2), excel.getCellData("country", 2), excel.getCellData("state", 2), excel.getCellData("city", 2), excel.getCellData("postal code", 2), excel.getCellData("phone", 2));
    }

    //Add New Address Customer without Country
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 12)
    public void TC_AddNewAddressWithoutCountry() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutCountry(excel.getCellData("address", 3), excel.getCellData("postal code", 3), excel.getCellData("phone", 3));
    }

    //Add New Address Customer without State
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 13)
    public void TC_AddNewAddressWithoutState() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutState(excel.getCellData("address", 5), excel.getCellData("country", 5), excel.getCellData("postal code", 5), excel.getCellData("phone", 5));
    }

    //Add New Address Customer without City
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 14)
    public void TC_AddNewAddressWithoutCity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutCity(excel.getCellData("address", 5), excel.getCellData("country", 5), excel.getCellData("state", 5), excel.getCellData("postal code", 5), excel.getCellData("phone", 5));
    }

    //Add New Address Customer without Postal Code
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 15)
    public void TC_AddNewAddressWithoutPostalCode() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutPostalCode(excel.getCellData("address", 6), excel.getCellData("country", 6), excel.getCellData("state", 6), excel.getCellData("city", 6), excel.getCellData("postal code", 6), excel.getCellData("phone", 6));
    }

    //Add New Address Customer without Phone
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 16)
    public void TC_AddNewAddressWithoutPhone() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "AddAddress");
        getProfilePage().addNewAddressWithoutPhone(excel.getCellData("address", 7), excel.getCellData("country", 7), excel.getCellData("state", 7), excel.getCellData("city", 7), excel.getCellData("postal code", 7), excel.getCellData("phone", 7));
    }

    // Edit Address Customer Valid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 17)
    public void TC_EditAddressValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressValid(excel.getCellData("address", 1), excel.getCellData("country", 1), excel.getCellData("state", 1), excel.getCellData("city", 1), excel.getCellData("postal code", 1), excel.getCellData("phone", 1));
    }

    // Edit Address Customer without Address
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 18)
    public void TC_EditAddressWithoutAddress() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutAddress(excel.getCellData("address", 2), excel.getCellData("country", 2), excel.getCellData("state", 2), excel.getCellData("city", 2), excel.getCellData("postal code", 2), excel.getCellData("phone", 2));
    }

    // Edit Address Customer without Country
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 19)
    public void TC_EditAddressWithoutCountry() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutCountry(excel.getCellData("address", 3), excel.getCellData("country",3), excel.getCellData("postal code", 3), excel.getCellData("phone", 3));
    }

    // Edit Address Customer without State
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 20)
    public void TC_EditAddressWithoutState() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutState(excel.getCellData("address", 4), excel.getCellData("country", 4), excel.getCellData("state",4), excel.getCellData("postal code", 4), excel.getCellData("phone", 4));
    }

    // Edit Address Customer without City
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 21)
    public void TC_EditAddressWithoutCity() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutCity(excel.getCellData("address", 5), excel.getCellData("country", 5), excel.getCellData("state", 5), excel.getCellData("city", 5), excel.getCellData("postal code", 5), excel.getCellData("phone", 5));
    }

    // Edit Address Customer without Postal Code
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 22)
    public void TC_EditAddressWithoutPostalCode() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));

        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutPostalCode(excel.getCellData("address", 6), excel.getCellData("country", 6), excel.getCellData("state", 6), excel.getCellData("city", 6), excel.getCellData("postal code", 6), excel.getCellData("phone", 6));
    }

    // Edit Address Customer without Phone
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 23)
    public void TC_EditAddressWithoutPhone() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        excel.setExcelFile("DataTest/Profile.xlsx", "EditAddress");
        getProfilePage().editAddressWithoutPhone(excel.getCellData("address", 7), excel.getCellData("country", 7), excel.getCellData("state", 7), excel.getCellData("city", 7), excel.getCellData("postal code", 7), excel.getCellData("phone", 7));
    }

}
