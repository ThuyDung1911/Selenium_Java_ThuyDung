package com.thuydung.pages;

import org.openqa.selenium.By;

public class DashboardPage extends CommonPage{
    public static By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
    public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
    public static By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By buttonSubmitSearch = By.xpath("//div[@class='input-group-append d-none d-lg-block']//button[@type='submit']");
    public static By fullNameAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::h4");
    public static By emailAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::div");
    public static By buttonLogout = By.xpath("//a[normalize-space()='My Panel']/parent::li/following-sibling::li/a[normalize-space()='Logout']");
}
