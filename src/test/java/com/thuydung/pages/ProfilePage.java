package com.thuydung.pages;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
    private By messageUpdate = By.xpath("//span[@data-notify='message']");
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
    private By inputPhoto = By.xpath("//label[normalize-space()='Photo']/following-sibling::div/descendant::div[normalize-space()='Browse']");
    private By tabUploadNew = By.xpath("//a[normalize-space()='Upload New']");
    private By tabSelectFile = By.xpath("//a[normalize-space()='Select File']");
    private By inputUploadPhoto = By.xpath("//input[@class='uppy-Dashboard-input']");
    private By buttonAddFile = By.xpath("//button[normalize-space()='Add Files']");
    private By inputSearchPhoto = By.xpath("//input[@placeholder='Search your files']");
    private By imageUploaded = By.xpath("(//div[@class='modal-body']//div[contains(@title,'AvatarAccount')]/descendant::img[@class='img-fit'])[1]");

    public void updateInfoBasicProfileCustomer(String name, String phone, String imgName, String password, String confirmPassword) {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuManageProfile);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleManageProfile, "Trang Manage Profile KHÔNG được hiển thị.");
        WebUI.setTextAndClear(inputName, name);
        WebUI.setTextAndClear(inputPhone, phone);
        WebUI.clickElement(inputPhoto);
//        WebUI.clickElement(tabUploadNew);
//        DriverManager.getDriver().findElement(inputUploadPhoto).sendKeys(SystemHelper.getCurrentDir() + "DataTest\\" + imgName + ".png");
        WebUI.clickElement(tabSelectFile);
        WebUI.setTextEnter(inputSearchPhoto, imgName);
        WebUI.waitForJQueryLoad();
        WebUI.clickElement(imageUploaded);
        WebUI.clickElement(buttonAddFile);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.setTextAndClear(inputConfirmPassword, confirmPassword);
        WebUI.clickElement(buttonUpdateProfile);
    }
    public void updateInfoBasicValidProfileCustomer(String name, String phone, String imgName, String password, String confirmPassword) {
        updateInfoBasicProfileCustomer(name, phone, imgName, password, confirmPassword);
        WebUI.verifyAssertTrueEqual(messageUpdate, "Your Profile has been updated successfully!", "Thông báo cập nhật thành công không đúng");
    }
    public void updateInfoBasicProfileCustomerWithoutName(String name, String phone, String imgName, String password, String confirmPassword) {
        updateInfoBasicProfileCustomer(name, phone, imgName, password, confirmPassword);
        WebUI.verifyAssertFalseEqual(messageUpdate, "Your Profile has been updated successfully!", "Thông báo cập nhật thành công VẪN được hiển thị, tên không bắt buộc.");
    }
    public void updateInfoBasicProfileCustomerWithoutPhone(String name, String phone, String imgName, String password, String confirmPassword) {
        updateInfoBasicProfileCustomer(name, phone, imgName, password, confirmPassword);
        WebUI.verifyAssertFalseEqual(messageUpdate, "Your Profile has been updated successfully!", "Thông báo cập nhật thành công VẪN được hiển thị, số điện thoại không bắt buộc.");
    }
    public void verifyUpdatePasswordFail(String password, String confirmPassword) {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        if(password!= confirmPassword) {
            //
            WebUI.clickElement(DashboardPage.buttonLogout);
            WebUI.waitForPageLoaded();
            WebUI.clickElement(HomePage.linkLogin);
            WebUI.waitForPageLoaded();
            WebUI.setTextAndClear(LoginPage.inputEmail, excel.getCellData("email", 4));
            WebUI.setTextAndClear(LoginPage.inputPassword, password);
            WebUI.clickElement(LoginPage.buttonSubmitLogin);
            WebUI.verifyAssertTrueIsDisplayed(LoginPage.messageAccDoesNotExist, "Đăng nhập vào hệ thống thành công với password mới: " + password);
            //
            WebUI.waitForPageLoaded();
            WebUI.setTextAndClear(LoginPage.inputEmail, excel.getCellData("email", 4));
            WebUI.setTextAndClear(LoginPage.inputPassword, password);
            WebUI.clickElement(LoginPage.buttonSubmitLogin);
            WebUI.verifyAssertTrueIsDisplayed(LoginPage.messageAccDoesNotExist, "Đăng nhập vào hệ thống thành công với password mới: " + confirmPassword);
        }
        else {
            WebUI.clickElement(DashboardPage.buttonLogout);
            WebUI.waitForPageLoaded();
            WebUI.clickElement(HomePage.linkLogin);
            WebUI.waitForPageLoaded();
            WebUI.setTextAndClear(LoginPage.inputEmail, excel.getCellData("email", 4));
            WebUI.setTextAndClear(LoginPage.inputPassword, password);
            WebUI.clickElement(LoginPage.buttonSubmitLogin);
            WebUI.verifyAssertTrueIsDisplayed(LoginPage.messageAccDoesNotExist, "Đăng nhập vào hệ thống thành công với password mới: " + password);
        }
        //
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(LoginPage.inputEmail, excel.getCellData("email", 4));
        WebUI.setTextAndClear(LoginPage.inputPassword, excel.getCellData("password", 4));
        WebUI.clickElement(LoginPage.buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(DashboardPage.titleDashboard, "Đăng nhập vào hệ thống thành công với password cũ: " + excel.getCellData("password", 4));

    }
    public void updateInfoBasicProfileCustomerNoMatchPassword(String name, String phone, String imgName, String password, String confirmPassword) {
        updateInfoBasicProfileCustomer(name, phone, imgName, password, confirmPassword);
        WebUI.verifyAssertTrueIsDisplayed(messageUpdate, "Thông báo không xuất hiện");
        WebUI.verifyAssertFalseEqual(messageUpdate, "Your Profile has been updated successfully!", "Thông báo cập nhật thành công VẪN được hiển thị, mật khẩu không trùng khớp.");
        WebUI.sleep(2);
        //Đăng nhập lại để kiểm tra thông tin đã được cập nhật
        //verifyUpdatePasswordFail(password, confirmPassword);
    }
    public void updateInfoBasicProfileCustomerPasswordLessCharacter(String name, String phone, String imgName, String password, String confirmPassword) {
        updateInfoBasicProfileCustomer(name, phone, imgName, password, confirmPassword);
        WebUI.verifySoftAssertTrueIsDisplayed(messageUpdate, "Thông báo không xuất hiện");
        WebUI.verifySoftAssertFalseEqual(messageUpdate, "Your Profile has been updated successfully!", "Thông báo cập nhật thành công VẪN được hiển thị, mật khẩu ít hơn 6 ký tự.");
        WebUI.sleep(2);
        //Đăng nhập lại để kiểm tra thông tin đã được cập nhật
        //verifyUpdatePasswordFail(password, confirmPassword);
    }
    public void updateEmail(String email) {
        WebUI.waitForPageLoaded();
        WebUI.scrollToElementToBottom(titleChangeEmail);
        WebUI.clickElement(menuManageProfile);
        WebUI.verifyElementVisible(titleChangeEmail, "Tieu de Change your email KHONG xuat hien.");
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(buttonUpdateEmail);
    }

    public void updateValidEmailCustomer(String email) {
        updateEmail(email);
        WebUI.verifyElementVisible(messageUpdate, "Cap nhat email that bai");
        WebUI.verifyAssertTrueEqual(messageUpdate, "A verification mail has been sent to the mail you provided us with.", "Thông báo da gui email không đúng");
    }
    public void updateEmailCustomerWithCurrentEmail(String email) {
        updateEmail(email);
        WebUI.verifyElementVisible(messageUpdate, "Cap nhat email that bai");
        WebUI.verifyAssertTrueEqual(messageUpdate, "Email already exists!", "Thông báo cập nhật email không đúng");
    }
    public void updateProfileWithEmailExist(String email) {
        updateEmail(email);
        WebUI.verifyElementVisible(messageUpdate, "Cap nhat email that bai");
        WebUI.verifyAssertTrueEqual(messageUpdate, "Email already exists!", "Thông báo cập nhật email không đúng");
    }
    public void updateProfileWithNewEmailIncorrectFormat(String email) {
        updateEmail(email);
        WebUI.verifyElementVisible(messageUpdate, "Cap nhat email that bai");
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
