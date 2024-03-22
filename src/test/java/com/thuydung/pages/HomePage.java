package com.thuydung.pages;

import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class HomePage extends CommonPage{
    public By linkMyPanel = By.xpath("//a[normalize-space()='My Panel']");
    public static By linkLogin = By.xpath("//a[normalize-space()='Registration']/parent::li/preceding-sibling::li/a[normalize-space()='Login']");
    public static By messageRegisterSuccess = By.xpath("//span[@data-notify = 'message']");
    public void openMyPanel() {
        WebUI.verifyAssertTrueIsDisplayed(linkMyPanel,"Không thể truy cập trang My Panel.");
        WebUI.clickElement(linkMyPanel);
    }
}
