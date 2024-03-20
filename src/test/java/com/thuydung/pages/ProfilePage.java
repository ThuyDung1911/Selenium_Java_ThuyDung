package com.thuydung.pages;

import org.openqa.selenium.Keys;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class ProfilePage extends CommonPage{
    private By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text' and normalize-space()='Manage Profile']");
    private By titleManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By inputName = By.xpath("//input[@placeholder='Your name']");
    private By inputPhone = By.xpath("//input[@placeholder='Your Phone']");
    private By selectAvatar = By.xpath("//div[@class='form-control file-amount']");
    private By inputPassword = By.xpath("//input[@placeholder='New Password']");
    private By inputConfirmPassword = By.xpath("//input[@placeholder='Confirm Password']");
    private By buttonUpdateProfile = By.xpath("//button[normalize-space()='Update Profile']");

    private By messageNotPermitted = By.xpath("//span[@data-notify='message' and normalize-space() = 'Sorry! the action is not permitted in demo']");
    private By titleChangeEmail = By.xpath("//h5[normalize-space()='Change your email']");
    private By inputEmail = By.xpath("//input[@placeholder='Your Email']");
    private By buttonVerifyEmail = By.xpath("//button[@class='btn btn-outline-secondary new-email-verification']");
    private By buttonUpdateEmail = By.xpath("//button[normalize-space()='Update Email']");
    private By messageUpdateSuccess = By.xpath("//span[@data-notify='message']");
    private By titleAddress = By.xpath("//h5[normalize-space()='Address']");
    private By buttonAddNewAddress = By.xpath("//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-light']");
    private By titlePopupNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@id='exampleModalLabel']");
    private By inputYourAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private By selectCountry = By.xpath("//button[@title='Select your country']");
    private By inputSearchCountry = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By selectState = By.xpath("//div[contains(text(),'Select State')]");
    private By inputSearchState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By selectCity = By.xpath("//div[contains(text(),'Select City')]");
    private By inputSearchCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    private By inputPhoneAddress = By.xpath("//input[@placeholder='+880']");
    private By buttonSaveNewAddress = By.xpath("//button[normalize-space()='Save']");


    public void updateInfoBasicProfileCustomer() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuManageProfile);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleManageProfile, "Trang Manage Profile KHÔNG được hiển thị.");
        WebUI.setTextAndClear(inputName, "Customer Updated");
        WebUI.setTextAndClear(inputPhone, "123456");
        WebUI.setTextAndClear(inputPassword, "123456");
        WebUI.setTextAndClear(inputConfirmPassword, "123456");
        WebUI.clickElement(buttonUpdateProfile);
        WebUI.verifyElementVisible(messageUpdateSuccess, "Thông báo cập nhật thành công KHÔNG được hiển thị.");
    }

    public void updateEmail() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuManageProfile);
        WebUI.scrollToElementToBottom(titleChangeEmail);
        WebUI.verifyElementVisible(titleChangeEmail, "Title Change your email is NOT displayed");
        WebUI.setTextAndClear(inputEmail, "dungvu@gmail.com");
        WebUI.clickElement(buttonUpdateEmail);
        WebUI.verifyElementVisible(messageUpdateSuccess, "Update email is failed");
    }

    public void addNewAddress() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuManageProfile);
        WebUI.scrollToElementToBottom(titleAddress);
        WebUI.verifyElementVisible(titleAddress, "Change address block is NOT displayed");
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.verifyElementVisible(titlePopupNewAddress, "Popup New Address is NOT displayed");
        WebUI.setTextAndClear(inputYourAddress, "Hoan Kiem");
        WebUI.clickElement(selectCountry);
        WebUI.setTextAndClear(inputSearchCountry, "Vietnam", Keys.ENTER);
        WebUI.clickElement(selectState);
        WebUI.setTextAndClear(inputSearchState, "Hà Nội", Keys.ENTER);
        WebUI.clickElement(selectCity);
        WebUI.setTextAndClear(inputSearchCity, "Hà Nội", Keys.ENTER);
        WebUI.setTextAndClear(inputPostalCode, "65000");
        WebUI.setTextAndClear(inputPhoneAddress, "0123456789");
        WebUI.clickElement(buttonSaveNewAddress);
        WebUI.scrollToElementToTop(By.xpath("//h5[normalize-space()='Address']"));
        WebUI.verifyElementVisible(By.xpath("//span[normalize-space()='Hà Nội']"), "Không thêm được địa chỉ. Hà Nội không tồn tại.");
    }

}
