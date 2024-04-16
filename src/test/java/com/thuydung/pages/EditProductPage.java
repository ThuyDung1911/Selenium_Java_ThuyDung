package com.thuydung.pages;

import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditProductPage extends CommonPage{
    private String nameProductVerify;
    private By menuProduct = By.xpath("//ul[@id='main-menu']//span[text()='Products']");
    private By submenuAllProducts = By.xpath("//ul[@id='main-menu']//span[normalize-space()='All products']");
    private By inputSearchProduct = By.xpath("//input[@id='search']");
    private By btnEditProduct = By.xpath("(//a[@title='Edit'])[1]");
    private By titleEditProduct = By.xpath("//h1[normalize-space()='Edit Product']");
    private By inputProductName = By.name("name");
    private By selectCategory = By.xpath("//button[@data-id='category_id']");
    private By inputSearchDropdown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By selectBrand = By.xpath("//button[@data-id='brand_id']");
    private By inputWeight = By.xpath("//input[@name='weight']");
    private By inputUnit = By.xpath("//input[@name='unit']");
    private By inputTags = By.xpath("//tags[@role='tagslist']/span");
    private By messageEditProduct = By.xpath("//span[@data-notify='message']");
    private By buttonUpdateProduct = By.xpath("//button[normalize-space()='Update Product']");
    private By newProductName = By.xpath("//input[@placeholder='Product Name']");

    public void editProduct(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image) {
        WebUI.clickElement(menuProduct);
        WebUI.waitForJQueryLoad();
        WebUI.clickElement(submenuAllProducts);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(btnEditProduct);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleEditProduct, "Tieu de Edit Product KHONG xuat hien");
        //Product Information
        String nameProductNeedEdit = productName + " " + ConfigData.AUTHOR + " Update " + RandomStringUtils.randomAlphabetic(8).toUpperCase();

        WebUI.setTextAndClear(inputProductName, nameProductNeedEdit);
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
    public void editProductValid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image,String vat) {
        editProduct(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, image);
        //Verify message
        WebUI.verifyAssertTrueIsDisplayed(messageEditProduct, "Message Edit Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageEditProduct, "Product has been updated successfully", "Message Edit Product KHONG chinh xac");
        nameProductVerify = DriverManager.getDriver().findElement(newProductName).getAttribute("value");
        AddProductPage.verifyNewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount, vat);
    }
    //Edit product invalid
    public void editProductInvalid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String image) {
        editProduct(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, image);
        //Verify
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.","Messge Product name hien thi khong dung");
    }
    public static void verifyNewProduct(String nameProductVerify, String category, String unit, String unitPrice, String discountDate, String quantity, String description, String discount, String vat) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        //WebUI.clickElement(new LoginPage().closeAdvertisementPopup);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(AddProductPage.allCategoriesTabUI);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("//a[contains(text(),'" + category + "')]"));
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(By.xpath("(//a[normalize-space()='" + nameProductVerify + "'])"), "Product KHONG xuat hien");
        WebUI.sleep(2);
        WebUI.clickElement(By.xpath("(//a[normalize-space()='" + nameProductVerify + "'])[1]"));
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        //nameProduct
        WebUI.verifyAssertTrueEqual(By.xpath("//h1[normalize-space()='" + nameProductVerify + "']"), nameProductVerify, "Product name hien thi sai");
        //unitPrice
        //discountDateEnd
        String[] discountDates = discountDate.split(" to ");
        String dataDiscountDateEnd = discountDates[discountDates.length - 1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime discountDateEnd = LocalDateTime.parse(dataDiscountDateEnd, formatter);
        //currentDate
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime currentDate = currentDateTime;

        String unitPriceVer1 = WebUI.getElementText(AddProductPage.unitPriceProductInProductDetail); //Hien thi tren trang view product
        BigDecimal unitPriceVer2 = WebUI.convertCurrencyToBigDecimal(unitPriceVer1);
        //BigDecimal unitPriceCheck = WebUI.stringToBigDecimal(unitPrice).add(WebUI.stringToBigDecimal(unitPrice).multiply(WebUI.stringToBigDecimal(vat)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal unitPriceCheck = WebUI.stringToBigDecimal(unitPrice).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(unitPriceVer2, unitPriceCheck, "Unit Price hien thi sai");
        if (discountDateEnd.isAfter(currentDate)) {
            //discountPrice
            String discountPriceVer1 = WebUI.getElementText(AddProductPage.discountPriceProductInProductDetail);
            BigDecimal discountPriceVer2 = WebUI.convertCurrencyToBigDecimal(discountPriceVer1);
//            BigDecimal discountPriceCheck = unitPriceCheck.subtract(unitPriceCheck.multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
            BigDecimal discountPriceCheck = WebUI.stringToBigDecimal(unitPrice).subtract(WebUI.stringToBigDecimal(unitPrice).multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
            WebUI.verifyAssertEquals(discountPriceVer2, discountPriceCheck, "Discount Price hien thi sai");
        }
        //unit
        WebUI.verifyAssertTrueEqual(AddProductPage.unitUI, "/" + unit, "Unit hien thi sai");
        Assert.assertTrue(DriverManager.getDriver().findElement(AddProductPage.unitUI).getText().trim().contains(unit), "Unit hien thi sai");
        //quantity
        WebUI.verifyAssertTrueEqual(ProductInfoPage.quantityProductAvailable, quantity, "Quantity hien thi sai");
        //description
        WebUI.scrollToElement(AddProductPage.descriptionUI);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueEqual(AddProductPage.descriptionUI, description, "Description hien thi sai");
    }




}
