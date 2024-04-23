package com.thuydung.pages;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

public class CouponPage extends CommonPage {
    public By buttonCouponType = By.xpath("//button[@data-id='coupon_type']");
    public By optionCouponType = By.xpath("//a[@class='dropdown-item']//span[text()='For Total Orders']");
    public By inputCouponCode = By.xpath("//input[@id='code']");
    public By inputMinOrderAmount = By.xpath("//input[@name='min_buy']");
    public By inputDiscountAmount = By.xpath("//input[@name='discount']");
    public By dropdownDiscountType = By.xpath("//select[@name='discount_type']/following-sibling::button");
    public By optionDiscountType = By.xpath("//a[@role='option']/span[text()='Amount']");

    public By inputMaxDiscountAmount = By.xpath("//input[@name='max_discount']");
    public By inputRangeDate = By.xpath("//input[@name='date_range']");
    public By buttonSubmit = By.xpath("//button[@type='submit']");
    ExcelHelper excelLogin;

    public void addCouponValid(String couponCode, String minOrderAmount, String discountAmount, String maxDiscountAmount, String rangeDate) {
        excelLogin = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        couponCode = couponCode + RandomStringUtils.randomAlphabetic(3).toUpperCase();
        PropertiesHelper.setValue("COUPON_VALID", couponCode);
        WebUI.openURL("https://cms.anhtester.com/admin/coupon/create");
        WebUI.clickElement(buttonCouponType);
        WebUI.clickElement(optionCouponType);
        WebUI.waitForJQueryLoad();
        WebUI.setText(inputCouponCode, couponCode);
        WebUI.setText(inputMinOrderAmount, minOrderAmount);
        WebUI.setText(inputDiscountAmount, discountAmount);
        WebUI.clickElement(dropdownDiscountType);
        WebUI.clickElement(optionDiscountType);
        WebUI.setText(inputMaxDiscountAmount, maxDiscountAmount);
        WebUI.setTextAndClear(inputRangeDate, rangeDate);
        WebUI.waitForJQueryLoad();
        WebUI.clickElement(buttonSubmit);

    }


}
