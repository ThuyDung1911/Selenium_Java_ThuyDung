package com.thuydung.pages;

import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
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
    public By messageRequiredPasswordCharacter = By.xpath("//strong[normalize-space()='The password must be at least 6 characters.']");
    public By messageRequiredConfirmPasswordMatch = By.xpath("//strong[normalize-space()='The password confirmation does not match.']");
    public By messageNotiRegister= By.xpath("//span[@data-notify='message']");
    private By errorMessage = By.xpath("//h1[normalize-space()='Something went wrong!']");
    public void registerAccount(String fullname, String email, String password, String confirm_password) {
        WebUI.openURL(PropertiesHelper.getValue("URL_REGISTER"));
        if (WebUI.getWebElement(LoginPage.closeAdvertisementPopup).isDisplayed()) {
            WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        }
        if (WebUI.getWebElement(LoginPage.buttonOkCookies).isDisplayed()) {
            WebUI.clickElement(LoginPage.buttonOkCookies);
        }
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.setText(inputFullName,fullname);
        WebUI.setText(inputEmail,email);
        WebUI.setText(inputPassword,password);
        WebUI.setText(inputConfirmPassword,confirm_password);
        WebUI.clickElement(checkboxAgreeCondition);
        WebUI.clickElement(buttonRegister);
        WebUI.waitForPageLoaded();
    }
    public void registerSuccessCustomerAccount(String fullname, String email, String password, String confirm_password) {
        email = email + RandomStringUtils.randomAlphabetic(8).toUpperCase() + "@gmail.com";
        registerAccount(fullname,email,password,confirm_password);
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
    public void registerFailWithoutFullname(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredFullName,"Khong xuat hien thong bao bat buoc nhap ten.");
        WebUI.verifyAssertTrueEqual(messageRequiredFullName,"The name field is required.","Thong bao bat buoc nhap ten khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailWithoutEmail(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(errorMessage,"He thong khong bao loi khi email de trong.");
        WebUI.sleep(2);
    }
    public void registerFailWithExistEmail(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(messageNotiRegister,"Khong xuat hien thong bao email da ton tai.");
        WebUI.verifyAssertTrueEqual(messageNotiRegister,"Email or Phone already exists.","Thong bao email da ton tai khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailWithInvalidEmail(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(titleRegisterPage,"Email khong dung dinh dang.");
        WebUI.sleep(2);
    }
    public void registerFailWithoutPassword(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPassword,"Khong xuat hien thong bao bat buoc nhap mat khau.");
        WebUI.verifyAssertTrueEqual(messageRequiredPassword,"The password field is required.","Thong bao bat buoc nhap mat khau khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailCustomerWithPasswordLessCharacter(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPasswordCharacter,"Khong xuat hien thong bao mat khau phai co it nhat 6 ky tu.");
        WebUI.verifyAssertTrueEqual(messageRequiredPasswordCharacter,"The password must be at least 6 characters.","Thong bao mat khau phai co it nhat 6 ky tu khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailCustomerWithPasswordNotMatch(String fullname, String email, String password, String confirm_password) {
        registerAccount(fullname,email,password,confirm_password);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredConfirmPasswordMatch,"Khong xuat hien thong bao mat khau khong trung khop.");
        WebUI.verifyAssertTrueEqual(messageRequiredConfirmPasswordMatch,"The password confirmation does not match.","Thong bao mat khau khong trung khop khong dung.");
        WebUI.sleep(2);
    }
    public void registerFailCustomerWithoutAcceptTerm(String fullname, String email, String password, String confirm_password) {
        email = email + RandomStringUtils.randomAlphabetic(8).toUpperCase() + "@gmail.com";
        WebUI.openURL(PropertiesHelper.getValue("URL_REGISTER"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(LoginPage.buttonOkCookies);
        WebUI.waitForPageLoaded();
        WebUI.setText(inputFullName,fullname);
        WebUI.setText(inputEmail,email);
        WebUI.setText(inputPassword,password);
        WebUI.setText(inputConfirmPassword,confirm_password);
        WebUI.clickElement(buttonRegister);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(titleRegisterPage,"Cho phep dang ky ma chua dong y dieu khoan.");
        WebUI.sleep(2);
    }


}
