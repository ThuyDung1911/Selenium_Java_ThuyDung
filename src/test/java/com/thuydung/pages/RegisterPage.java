package com.thuydung.pages;

import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;


public class RegisterPage extends CommonPage {
    public By titleRegisterPage = By.xpath("//h1[normalize-space()='Create an account.']");
    public By inputFullName = By.xpath("//input[@placeholder='Full Name']");
    public By inputEmail = By.xpath("//input[@placeholder='Email']");
    public By inputPassword = By.xpath("//input[@placeholder='Password']");
    public By inputConfirmPassword = By.xpath("//input[@placeholder='Confirm Password']");
    public By checkboxAgreeCondition = By.xpath("//span[contains(text(),'By signing up you agree to our terms and condition')]/parent::label");
    public By buttonRegister = By.xpath("//button[normalize-space()='Create Account']");
    public By messageRequiredPassword = By.xpath("//strong[contains(text(),'The password field is required.')]");
    public By messageRequiredFullName = By.xpath("//strong[normalize-space()='The name field is required.']");
    public By messageRequiredCheckbox = By.xpath("//input[@type='checkbox' and @required]");
    public By messageRequiredEmail = By.xpath("input[@placeholder='Email' and @required]");
    public By messageRequiredPasswordCharacter = By.xpath("//strong[normalize-space()='The password must be at least 6 characters.']");
    public By messageRequiredConfirmPasswordMatch = By.xpath("//strong[normalize-space()='The password confirmation does not match.']");
    public By linkLogin = By.xpath("//a[normalize-space()='Log In']");
    public void registerSuccessCustomerAccount(String fullname, String email, String password, String confirm_password) {
        WebUI.openURL(PropertiesHelper.getValue("URL_REGISTER"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(LoginPage.buttonOkCookies);
        WebUI.waitForPageLoaded();
        WebUI.setText(inputFullName,fullname);
        WebUI.setText(inputEmail,email);
        WebUI.setText(inputPassword,password);
        WebUI.setText(inputConfirmPassword,confirm_password);
        WebUI.clickElement(checkboxAgreeCondition);
        WebUI.clickElement(buttonRegister);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(HomePage.messageRegisterSuccess,"Khong xuat hien thong bao dang ky thanh cong.");
        WebUI.verifyAssertTrueEqual(HomePage.messageRegisterSuccess,"Registration successful.","Thong bao dang ky thanh cong khong dung.");
        verifyNewCustomerAccount(fullname,email);
        WebUI.sleep(2);
    }
    public void verifyNewCustomerAccount(String fullname, String email) {
        getHomePage().openMyPanel();
        WebUI.verifyAssertTrueEqual(DashboardPage.fullNameAccount,fullname,"Tên tài khoản không đúng.");
        WebUI.verifyAssertTrueEqual(DashboardPage.emailAccount,email,"Email tài khoản không đúng.");
    }
    public void registerFailWithoutFullname(String email, String password, String confirm_password) {
        WebUI.openURL(PropertiesHelper.getValue("URL_REGISTER"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(LoginPage.buttonOkCookies);
        WebUI.waitForPageLoaded();
        WebUI.setText(inputEmail,email);
        WebUI.setText(inputPassword,password);
        WebUI.setText(inputConfirmPassword,confirm_password);
        WebUI.clickElement(checkboxAgreeCondition);
        WebUI.clickElement(buttonRegister);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredFullName,"Khong xuat hien thong bao bat buoc nhap ten.");
        WebUI.verifyAssertTrueEqual(messageRequiredFullName,"The name field is required.","Thong bao bat buoc nhap ten khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailWithoutEmail(String fullname, String password, String confirm_password) {
        WebUI.openURL(PropertiesHelper.getValue("URL_REGISTER"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(LoginPage.buttonOkCookies);
        WebUI.waitForPageLoaded();
        WebUI.setText(inputFullName,fullname);
        WebUI.setText(inputPassword,password);
        WebUI.setText(inputConfirmPassword,confirm_password);
        WebUI.clickElement(checkboxAgreeCondition);
        WebUI.clickElement(buttonRegister);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail,"Email khong bat buoc nhap.");
        WebUI.verifyAssertTrueIsDisplayed(titleRegisterPage,"He thong chuyen huong sang trang khac.");
        WebUI.sleep(2);
    }


}
