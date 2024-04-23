package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends CommonPage {
    public By linkMyPanel = By.xpath("//a[normalize-space()='My Panel']");
    public static By linkLogin = By.xpath("//a[normalize-space()='Registration']/parent::li/preceding-sibling::li/a[normalize-space()='Login']");
    public static By messageRegisterSuccess = By.xpath("//span[@data-notify = 'message']");
    public static By buttonLogoutWithRoleAdmin = By.xpath("//div[contains(@class,'dropdown-menu')]//span[text()='Logout']");

    public void openMyPanel() {
        try {
            WebUI.verifyAssertTrueIsDisplayed(linkMyPanel, "Không thể truy cập trang My Panel.");
            WebUI.clickElement(linkMyPanel);
            WebUI.waitForPageLoaded();
        } catch (Exception e) {
            e.printStackTrace();
            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                js.executeScript("location.reload()");
            }
        }
    }

}
