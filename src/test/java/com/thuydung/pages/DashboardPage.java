package com.thuydung.pages;

import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class DashboardPage extends CommonPage{
    public static By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
    public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
    public static By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By buttonSubmitSearch = By.xpath("//div[@class='input-group-append d-none d-lg-block']//button[@type='submit']");
    public static By fullNameAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::h4");
    public static By emailAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::div");
    public static By buttonLogout = By.xpath("//a[normalize-space()='My Panel']/parent::li/following-sibling::li/a[normalize-space()='Logout']");
    public static By divSearchResult = By.xpath("//div[contains(@class,'typed-search-box')]");
    public static By resultSearchProduct = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Products']/following-sibling::ul//div[contains(@class,'product-name')]");
    public void testSearchProductHaveResult(String keySearchProduct) {
        WebUI.setTextAndClear(inputSearchProduct, keySearchProduct);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(divSearchResult, "Khối Search result KHONG xuat hien");
        WebUI.verifyAssertTrueIsDisplayed(resultSearchProduct, "Ket qua tim kiem KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(resultSearchProduct, keySearchProduct, "Ket qua tim kiem KHONG dung");
        //Check result contain keySearchProduct

    }
}
