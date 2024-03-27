package com.thuydung.pages;

import com.thuydung.constants.ConfigData;
import com.thuydung.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class EditProductPage extends CommonPage{
    private By menuProduct = By.xpath("//span[normalize-space()='Products']");
    private By menuAllProducts = By.xpath("//span[normalize-space()='All products']");
    private By inputSearchProduct = By.xpath("//input[@id='search']");
    private By btnEditProduct = By.xpath("(//a[@title='Edit'])[1]");
    private By titleEditProduct = By.xpath("//h1[normalize-space()='Edit Product']");
    private By inputProductName = By.name("name");
    private By selectCategory = By.xpath("//button[@data-id='category_id']");
    private By inputSearchDropdown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By selectBrand = By.xpath("//button[@data-id='brand_id']");
    private By inputWeight = By.xpath("//input[@name='weight']");
    private By inputUnit = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    private By inputTags = By.xpath("//span[@data-placeholder='Type to add a tag']");
    private By messageEditProduct = By.xpath("//span[@data-notify='message']");
    private By blockProductPrice = By.xpath("//h5[normalize-space()='Product price + stock']");
    private By inputUnitPrice = By.xpath("//input[@placeholder='Unit price']");
    private By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    private By selectDate = By.xpath("//input[@placeholder='Select Date']");
    private By buttonSelectDiscountDate = By.xpath("//button[@class='applyBtn btn btn-sm btn-primary']");
    private By selectUnitDiscount = By.xpath("(//div[@class='filter-option-inner-inner'][normalize-space()='Flat'])[1]");
    private By selectUnitDiscountPercent = By.xpath("//span[normalize-space()='Percent']");
    private By inputQuantity = By.xpath("//input[@placeholder='Quantity']");
    private By inputSKU = By.xpath("//input[@placeholder='SKU']");
    private By buttonUpdateProduct = By.xpath("//button[normalize-space()='Update Product']");
    public void editProduct(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image) {
        String nameProductNeedEdit = productName;
        WebUI.clickElement(menuProduct);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuAllProducts);
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(inputSearchProduct, nameProductNeedEdit, Keys.ENTER);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(btnEditProduct);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleEditProduct, "Tieu de Edit Product KHONG xuat hien");
        //Product Information
        WebUI.setTextAndClear(inputProductName, productName);
        WebUI.clickElement(selectCategory);
        WebUI.setTextEnter(inputSearchDropdown, category);
        WebUI.clickElement(selectBrand);
        WebUI.setTextEnter(inputSearchDropdown, "Cosy");
        WebUI.setTextAndClear(inputUnit, unit);
        WebUI.setTextAndClear(inputWeight, String.valueOf(weight));
        WebUI.clickElement(By.xpath("//tags[@role='tagslist']"));
        WebUI.keydownBackspace();
        WebUI.keydownBackspace();
        WebUI.setTextEnter(inputTags, tags);
        //DriverManager.getDriver().findElement(inputTags).sendKeys(tags, Keys.ENTER);
        //Product price + stock
//        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
//        WebUI.setTextAndClear(inputUnitPrice, String.valueOf(unitPrice));
//        WebUI.setTextAndClear(selectDate, discountDate);
//        WebUI.clickElement(buttonSelectDiscountDate);
//        WebUI.setTextAndClear(inputDiscount, String.valueOf(discount));
//        WebUI.clickElement(selectUnitDiscount);
//        WebUI.clickElement(selectUnitDiscountPercent);
//        WebUI.setTextAndClear(inputQuantity, String.valueOf(quantity));
        //WebUI.setTextAndClear(inputSKU, String.valueOf(randomNumber));
        WebUI.clickElement(buttonUpdateProduct);

    }
    //Edit product valid
    public void editProductValid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image) {
        editProduct(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, image);
        productName = productName + " " + ConfigData.AUTHOR + " Update " + RandomStringUtils.randomAlphabetic(8).toUpperCase();
        editProduct(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, image);
        //Verify message
        WebUI.verifyAssertTrueIsDisplayed(messageEditProduct, "Message Edit Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageEditProduct, "Product has been updated successfully", "Message Edit Product KHONG chinh xac");
    }
    //Edit product invalid
    public void editProductInvalid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image) {
        editProduct(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, image);
        //Verify
        WebUI.checkHTML5MessageWithValueInvalid(inputProductName, "Product name la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputProductName, "Please fill out this field.","Messge Product name hien thi khong dung");
    }



}
