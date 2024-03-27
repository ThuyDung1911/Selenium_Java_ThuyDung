package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends CommonPage {
    public static By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
    public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
    public static By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By buttonSubmitSearch = By.xpath("//div[@class='input-group-append d-none d-lg-block']//button[@type='submit']");
    public static By fullNameAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::h4");
    public static By emailAccount = By.xpath("//span[contains(@class,'avatar')]/following-sibling::div");
    public static By buttonLogout = By.xpath("//a[normalize-space()='My Panel']/parent::li/following-sibling::li/a[normalize-space()='Logout']");
    public static By divSearchResult = By.xpath("//div[contains(@class,'typed-search-box')]");
    public static By resultSearchProduct = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Products']/following-sibling::ul//div[contains(@class,'product-name')]");
    public static By resultCategorySuggestions = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Category Suggestions']/following-sibling::ul//a");
    public static By resultTagSuggestions = By.xpath("//div[contains(@class,'typed-search-box')]//div[text()='Popular Suggestions']/following-sibling::ul//a");
    public static By messageSearchNothing = By.xpath("//div[contains(@class,'search-nothing')]");

    public void testSearchProduct(String keySearchProduct) {
        String keyTempSearchProduct = keySearchProduct + " ";
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.setTextAndClear(inputSearchProduct, keyTempSearchProduct);
        WebUI.clickElement(inputSearchProduct);
        WebUI.keydownBackspace();
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(divSearchResult, "Khối Search result KHONG xuat hien");
        keySearchProduct = keySearchProduct.toLowerCase();
    }

    public void testSearchProductHaveResult(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        List<String> listResultSearchProductValues = new ArrayList<>();
        int i = 0;
        for (WebElement resultWebElementSearchProduct : listResultSearchProduct) {
            listResultSearchProductValues.add(resultWebElementSearchProduct.getText().toLowerCase());
            i++;
            WebUI.verifyAssertTrueIsDisplayed(By.xpath("(//div[contains(@class,'typed-search-box')]//div[text()='Products']/following-sibling::ul//div[contains(@class,'product-name')])[" + i + "]"), "Ket qua tim kiem KHONG xuat hien");
        }
        for (String resultSearchProductValue : listResultSearchProductValues) {
            WebUI.verifyAssertContain(resultSearchProductValue, keySearchProduct, "Ket qua tim kiem KHONG dung");
        }
    }

    public void testSearchProductHaveNotResult(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        WebUI.verifyAssertTrueIsDisplayed(messageSearchNothing, "Ket qua tim kiem van xuat hien");
        WebUI.verifyAssertTrueEqual(messageSearchNothing, "Sorry, nothing found for \"" + keySearchProduct + "\"", "Message khong tim thay KHONG dung");

    }

    public void testSearchSuggestionCategoryContainKeySearchProduct(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        List<WebElement> listResultSearchCategory = DriverManager.getDriver().findElements(resultCategorySuggestions);
        List<String> listResultSearchCategoryValues = new ArrayList<>();
        int i = 0;
        for (WebElement resultWebElementSearchCategory : listResultSearchCategory) {
            listResultSearchCategoryValues.add(resultWebElementSearchCategory.getText().toLowerCase());
            i++;
            WebUI.verifyAssertTrueIsDisplayed(By.xpath("(//div[contains(@class,'typed-search-box')]//div[text()='Category Suggestions']/following-sibling::ul//a)[" + i + "]"), "Ket qua tim kiem KHONG xuat hien");
        }
        for (String resultSearchCategoryValue : listResultSearchCategoryValues) {
            WebUI.verifyAssertContain(resultSearchCategoryValue, keySearchProduct, "Ket qua tim kiem KHONG dung");
        }

//        testSearchProduct(keySearchProduct);
//        WebUI.verifyAssertTrueIsDisplayed(resultCategorySuggestions, "Ket qua tim kiem KHONG xuat hien");
//        //Check result contain keySearchProduct
//        WebUI.verifyAssertTrueTextContain(resultCategorySuggestions, keySearchProduct, "Ket qua tim kiem KHONG dung");
    }

    public void testSearchSuggestionTagContainKeySearchProduct(String keySearchProduct) {
        testSearchProduct(keySearchProduct);
        List<WebElement> listResultSearchTag = DriverManager.getDriver().findElements(resultTagSuggestions);
        List<String> listResultSearchTagValues = new ArrayList<>();
        int i = 0;
        for (WebElement resultWebElementSearchTag : listResultSearchTag) {
            listResultSearchTagValues.add(resultWebElementSearchTag.getText().toLowerCase());
            i++;
            WebUI.verifyAssertTrueIsDisplayed(By.xpath("(//div[contains(@class,'typed-search-box')]//div[text()='Popular Suggestions']/following-sibling::ul//a)[" + i + "]"), "Ket qua tim kiem KHONG xuat hien");
        }
        for (String resultSearchTagValue : listResultSearchTagValues) {
            WebUI.verifyAssertContain(resultSearchTagValue, keySearchProduct, "Ket qua tim kiem KHONG dung");
        }

//        testSearchProduct(keySearchProduct);
//        WebUI.verifyAssertTrueIsDisplayed(resultTagSuggestions, "Ket qua tim kiem KHONG xuat hien");
//        WebUI.verifyAssertTrueTextContain(resultTagSuggestions, keySearchProduct, "Ket qua tim kiem KHONG dung");

    }

}
