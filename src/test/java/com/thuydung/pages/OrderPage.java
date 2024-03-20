package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.List;

public class OrderPage {
    private By selectProductNabati = By.xpath("(//a[contains(text(),'Nabati')])[1]");
    private By selectProduct1 = By.xpath("(//a[contains(text(),'Cosy')])[1]");
    private By buttonAddToCart = By.xpath("//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600']");
    private By popupAddToCartSucceeded = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    private By buttonCloseAddToCartPopup = By.xpath("//span[@class = 'la-2x']");
    private By buttonBackToShopping = By.xpath("//button[normalize-space()='Back to shopping']");
    private By selectProduct2 = By.xpath("(//a[@class = 'd-block text-reset' ])[1]");
    private By buttonPlus = By.xpath("//button[contains(@data-type,'plus')]");
    private By buttonCart = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By viewProductOrderOnCart = By.xpath("//span[@class='fw-600 mb-1 text-truncate-2']");
    private By buttonCheckoutOnCartPopup = By.xpath("//a[normalize-space()='Checkout']");
    private By buttonAddNewAddress = By.xpath("//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']");
    private By verifyCheckedAddress = By.xpath("(//input[@name = 'address_id' ])[2]");
    private By buttonProcessToCheckout = By.xpath("//a[normalize-space()='Proceed to Checkout']");
    private By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By verifyProductNabatiAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='Nabati']");
    private By verifyProductChocoPieAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='ChocoPie']");
    private By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");

    private By checkboxAgreeTermAndConditions = By.xpath("//span[@class='aiz-square-check']");
    private By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    private By quantityProduct = By.xpath("//input[@name='quantity']");
    private By titleNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@id='exampleModalLabel']");
    private By inputYourAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private By selectCountry = By.xpath("//div[contains(text(),'Select your country')]");
    private By buttonSelectAddress = By.xpath("(//span[@class='aiz-rounded-check flex-shrink-0 mt-1'])[1]");
    private By quantity = By.xpath("//input[@name='quantity']");
    private By paymentPage = By.xpath("//h3[normalize-space()='Any additional info?']");

    private By subTotalPrice = By.xpath("(//th[text()='Subtotal'])/following-sibling::td//span");
    private By totalPrice = By.xpath("(//span[text()='Total']/parent::th)/following-sibling::td//span");

    public void order(String noteForOrder, String email, String password) {
        new LoginPage().loginSuccessWithCustomerAccount(email, password);
        WebUI.waitForPageLoaded();
        WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, PropertiesHelper.getValue("product_P01"));
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue("product_P01") + "')]"));
        WebUI.waitForPageLoaded();
        String productPrice1 = WebUI.getElementText(ProductInfoPage.productPrice).trim();
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(popupAddToCartSucceeded, "Add to cart is failed");
        WebUI.clickElement(buttonBackToShopping);
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, PropertiesHelper.getValue("product_P02"));
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue("product_P02") + "')]"));
        String productPrice2 = WebUI.getElementText(ProductInfoPage.productPrice).trim();
        WebUI.clickElement(buttonPlus);
        String quantities = WebUI.getElementAttribute(quantity, "value").trim();
        WebUI.verifyAssertTrueAttribute(quantity, "value", "2", "number of failed products");
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(popupAddToCartSucceeded, "Add to cart is failed");
        WebUI.clickElement(buttonCloseAddToCartPopup);
        WebUI.clickElement(buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(viewProductOrderOnCart, "My product is NOT displayed");
        WebUI.clickElement(buttonCheckoutOnCartPopup);
        WebUI.scrollToElement(buttonSelectAddress);
        WebUI.clickElement(buttonSelectAddress);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.scrollToElement(buttonContinueToPayment);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.verifyAssertTrueIsDisplayed(paymentPage, "Step Payment is NOT displayed");
        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);

        int priceOfProduct1 = Integer.parseInt(productPrice1.replace("$", "").replace(",", "").split("\\.")[0]);

        int quantitiesOfProduct2 = Integer.parseInt(quantities);
        int priceOfProduct2 = (Integer.parseInt(productPrice2.replace("$", "").replace(",", "").split("\\.")[0])) * quantitiesOfProduct2;

        int sumPrice = priceOfProduct1 + priceOfProduct2;

        int subTotal = Integer.parseInt(WebUI.getElementText(subTotalPrice).replace("$", "").replace(",", "").split("\\.")[0]);

        System.out.println("Total of Product 1: " + priceOfProduct1);
        System.out.println("Total of Product 2: " + priceOfProduct2);
        System.out.println("Sum Total of products: " + sumPrice);
        System.out.println("Sub total from Summary Order: " + subTotal);

        WebUI.sleep(2);
        WebUI.verifyEquals(sumPrice, subTotal, "The total price is failed");
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Order is failed");
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
    }

}
