package com.thuydung.pages;

import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class LoginPage extends CommonPage {

    public static By closeAdvertisementPopup = By.xpath("//i[@class='la la-close fs-20']");
    public static By buttonLogin = By.xpath("//a[normalize-space() = 'Login' and @class = 'text-reset d-inline-block opacity-60 py-2']");
    public static By buttonCopyAdminAcc = By.xpath("//button[normalize-space()='Copy']");
    public static By buttonSubmitLogin = By.xpath("//button[normalize-space()='Login']");
    public static By titleLoginPage = By.xpath("//h1[normalize-space() = 'Login to your account.']");
    public static By messageRequiredEmail = By.xpath("//strong[contains(text(),'The email field is required when phone is not present.')]");
    public static By inputEmail = By.xpath("//input[@id='email']");
    public static By inputPassword = By.xpath("//input[@id='password']");
    public static By messageInvalidEmailFormat = By.xpath("//input[contains(@class, 'is-invalid') and @id = 'email']");
    public static By messageAccDoesNotExist = By.xpath("//span[@data-notify='message']");
    public static By messageRequiredPassword = By.xpath("//input[contains(@class, 'is-invalid') and @id = 'password']");
    public static By logoAnhTesterPage = By.xpath("//img[@alt='Active eCommerce CMS']");
    public static By roleUser = By.xpath("//span[contains(@class,'avatar')]/following-sibling::span//descendant::span[contains(@class,'small')]");
    public static By buttonOkCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");

    public void openLoginPage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(closeAdvertisementPopup);
        WebUI.clickElement(buttonOkCookies);
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(titleLoginPage, "Trang đăng nhập KHÔNG được hiển thị");
    }

    public void openHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(closeAdvertisementPopup);
        WebUI.clickElement(buttonOkCookies);
        WebUI.waitForPageLoaded();
    }
    
    public void loginFailWithEmailNull() {
        openLoginPage();
        WebUI.sleep(1);
        WebUI.setText(inputPassword, "123456");
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageInvalidEmailFormat, "Email không bắt buộc.");
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Thông báo bắt buộc nhập email không xuất hiện.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageRequiredEmail), "The email field is required when phone is not present.", "Nội dung của thông báo không đúng.");
        WebUI.sleep(2);
    }

    public void loginFailWithEmailDoesNotExist(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Thông báo tài khoản không hợp lệ không xuất hiện.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageAccDoesNotExist), "Invalid login credentials", "Nội dung của thông báo không đúng.");
    }

    public void loginFailWithNullPassword(String email) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPassword, "Mật khẩu không bắt buộc.");
        WebUI.sleep(2);
    }

    public void loginFailWithIncorrectPassword(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clearText(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Không hiển thị thông báo lỗi.");
        WebUI.verifyAssertEquals(WebUI.getElementText(messageAccDoesNotExist), "Invalid login credentials", "Nội dung của thông báo không đúng.");
        WebUI.sleep(2);
    }

    public void loginSuccessWithCustomerAccount(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clearText(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.waitForElementVisible(DashboardPage.titleDashboard);
        WebUI.verifyAssertTrueIsDisplayed(DashboardPage.titleDashboard, "Trang Dashboard không được hiển thị.");
        WebUI.verifyAssertEquals(WebUI.getElementText(DashboardPage.titleDashboard), "Dashboard","Tiêu đề trang Dashboard không đúng.");
        WebUI.sleep(2);
    }

    public void loginSuccessWithSellerAccount(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clearText(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.waitForElementVisible(DashboardPage.titleDashboard);
        WebUI.verifyAssertTrueIsDisplayed(DashboardPage.titleDashboard, "Trang Dashboard không được hiển thị.");
        WebUI.verifyAssertEquals(WebUI.getElementText(DashboardPage.titleDashboard), "Dashboard","Tiêu đề trang Dashboard không đúng.");
        WebUI.verifyAssertTrueEqual(roleUser,"seller","Tài khoản đăng nhập không phải seller.");
        WebUI.sleep(2);
    }

    public void loginSuccessAdminPage(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.waitForElementVisible(logoAnhTesterPage);
        WebUI.verifyAssertTrueIsDisplayed(logoAnhTesterPage, "Đăng nhập vào hệ thống không thành công.");
        WebUI.verifyAssertTrueEqual(roleUser,"admin","Tài khoản đăng nhập không phải admin.");
        WebUI.sleep(2);
    }
    public void loginFailWithInvalidEmailFormat(String email, String password) {
        openLoginPage();
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        //WebUI.verifyAssertTrueIsDisplayed(titleLoginPage, "Trang hiện tại không phải là trang đăng nhập.");
        WebUI.checkHTML5MessageWithValueInvalid(inputEmail,"Email sai dinh dang");
        WebUI.sleep(2);
    }
}


