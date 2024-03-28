package com.thuydung.pages;

import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class CartPage extends CommonPage{
    By messageCartEmpty = By.xpath("//h3[normalize-space()='Your Cart is empty']");
    By inputQuantity = By.xpath("//input[@name='quantity']");
    By viewQuantityInCart = By.xpath("(//span[@class='fw-600 mb-1 text-truncate-2']/following-sibling::span)[1]");
    By viewProductNameinPopupAddSucceed = By.xpath("//div[@id='addToCart-modal-body']//h6[contains(@class,'fw-600')]");
    public void testAddProductToCart(String productName, String quantity) {
        getDashboardPage().testSearchProductHaveResult(productName);
        //WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, PropertiesHelper.getValue("product_P01"));
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]"));
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(inputQuantity, quantity);
        String productPrice1 = WebUI.getElementText(ProductInfoPage.productPrice).trim();
        WebUI.scrollToElement(OrderPage.buttonAddToCart);
        WebUI.clickElement(OrderPage.buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Add to cart is failed");
        WebUI.verifyAssertTrueEqual(viewProductNameinPopupAddSucceed, productName, "Product name is NOT correct");
        WebUI.clickElement(OrderPage.buttonCloseAddToCartPopup);
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(OrderPage.viewProductOrderOnCart, "My product is NOT displayed");
        WebUI.verifyAssertTrueTextContain(OrderPage.viewProductOrderOnCart, PropertiesHelper.getValue("product_P01"), "Price of product is NOT correct");
        WebUI.verifyAssertEqual(WebUI.getElementText(viewQuantityInCart).replaceAll("\\D", ""), quantity, "Quantity of product is NOT correct");
    }
    public void testCartEmpty() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(messageCartEmpty, "My product is NOT displayed");
    }
}
