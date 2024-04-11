package com.thuydung.pages;

import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.helpers.SystemHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.utils.LogUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AddProductPage extends CommonPage {
    private String nameProductVerify;
    private By menuProduct = By.xpath("//ul[@id='main-menu']//span[text()='Products']");
    private By submenuAddProduct = By.xpath("//ul[@id='main-menu']//span[normalize-space()='Add New Product']");
    private By submenuProducts = By.xpath("//ul[@id='main-menu']//span[normalize-space()='All products']");
    private By titleAddNewProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    private By blockProductInf = By.xpath("//h5[normalize-space()='Product Information']");
    private By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    private By selectCategory = By.xpath("//button[@data-id= 'category_id']");
    private By inputSearchCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By selectBrand = By.xpath("//button[@data-id='brand_id']");
    private By inputSearchBrand = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By inputUnit = By.xpath("//input[@name='unit']");
    private By inputWeight = By.xpath("//input[@name='weight']");
    private By inputTags = By.xpath("//tags[@role='tagslist']/span");
    private By blockProductImages = By.xpath("//h5[normalize-space()='Product Images']");
    private By selectChooseGalleryImages = By.xpath("//label[contains(text(),'Gallery Images')]/following-sibling::div//div[contains(@class,'file-amount')]");
    private By selectChooseThumbnailImages = By.xpath("//label[contains(text(),'Thumbnail Image')]/following-sibling::div//div[contains(@class,'file-amount')]");
    private By uploadNewImageTab = By.xpath("//a[normalize-space()='Upload New']");
    private By inputGalleryImages = By.xpath("//input[@class = 'uppy-Dashboard-input']");
    private By buttonAddFileImages = By.xpath("//button[normalize-space()='Add Files']");
    private By selectFileTab = By.xpath("//a[normalize-space()='Select File']");
    private By selectGalleryImages = By.xpath("(//img[@class='img-fit'])[1]");
    private By inputSearchImg = By.xpath("//input[@placeholder='Search your files']");
    private By selectThumbnailImages = By.xpath("(//img[@class='img-fit'])[2]");
    private By blockProductPrice = By.xpath("//h5[normalize-space()='Product price + stock']");
    private By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    private By selectDate = By.xpath("//input[@name='date_range']");
    private By buttonSelectDiscountDate = By.xpath("//button[contains(@class,'applyBtn')]");
    private By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    private By selectUnitDiscount = By.xpath("//h5[normalize-space()='Product price + stock']/ancestor::div[@class='card']//select[@name='discount_type']/following-sibling::button");
    private By selectUnitDiscountPercent = By.xpath("//span[normalize-space()='Percent']");
    private By inputQuantity = By.xpath("//input[@placeholder='Quantity']");
    private By inputSKU = By.xpath("//input[@placeholder='SKU']");
    private By blockProductDescription = By.xpath("//h5[normalize-space()='Product Description']");
    private By inputDescription = By.xpath("//textarea[@name='description']/following-sibling::div//div[contains(@class,'note-editable')]");
    private By buttonSavePublish = By.xpath("//button[normalize-space()='Save & Publish']");
    private By messageAddProduct = By.xpath("//span[@data-notify='message']");
    private By allCategoriesTabUI = By.xpath("//a[normalize-space()='All categories']");
    private By unitUI = By.xpath("//div[text()='Price:']/parent::div/following-sibling::div//span");
    private By descriptionUI = By.xpath("//div[@id='tab_default_1']//p");
    int randomNumber = new Random().nextInt(1000000);
    private By newProduct = By.xpath("(//div[@class='card']//img/parent::div/following-sibling::div/span)[1]");
    public static By discountPriceProduct = By.xpath("//div[text()='Discount Price:']/parent::div/following-sibling::div");
    public static By unitPriceProduct = By.xpath("//div[text()='Price:']/parent::div/following-sibling::div");

    public void openAddProductPage() {
        WebUI.clickElement(menuProduct);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(submenuAddProduct);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleAddNewProduct, "Tieu de Add New Product KHONG xuat hien");
    }

    public void addProductWithRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        productName = productName + " " + ConfigData.AUTHOR + " " + RandomStringUtils.randomAlphabetic(8).toUpperCase();
        PropertiesHelper.setValue("product_P01", productName);
        openAddProductPage();
        //Product Information
        WebUI.verifyElementVisible(blockProductInf, "Product Information block KHONG xuat hien");
        WebUI.setTextAndClear(inputProductName, productName);
        WebUI.clickElement(selectCategory);
        WebUI.setTextEnter(inputSearchCategory, category);
        WebUI.clickElement(selectBrand);
        WebUI.setTextEnter(inputSearchBrand, "CMS brand 01");

        WebUI.setTextAndClear(inputUnit, unit);
        WebUI.setTextAndClear(inputWeight, String.valueOf(weight));
        WebUI.setTextAndClear(inputTags, tags);
        //Product Images
        WebUI.verifyAssertTrueIsDisplayed(blockProductImages, "Product Images block KHONG xuat hien");
        WebUI.clickElement(selectChooseGalleryImages);
        WebUI.clickElement(uploadNewImageTab);
        DriverManager.getDriver().findElement(inputGalleryImages).sendKeys(SystemHelper.getCurrentDir() + "DataTest\\" + imgName);
        WebUI.clickElement(selectFileTab);
        LogUtils.info(imgName);
        LogUtils.info(SystemHelper.splitString(imgName, "[.]"));
        String imageName = SystemHelper.splitString(imgName, "[.]").get(0);
        WebUI.setTextEnter(inputSearchImg, imageName);
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        WebUI.clickElement(selectGalleryImages);
        WebUI.clickElement(buttonAddFileImages);
        WebUI.clickElement(selectChooseThumbnailImages);
        WebUI.setTextEnter(inputSearchImg, imageName);
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        WebUI.clickElement(selectThumbnailImages);
        WebUI.clickElement(buttonAddFileImages);
        //Product price + stock
        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
        WebUI.setTextAndClear(inputUnitPrice, String.valueOf(unitPrice));
        WebUI.setTextAndClear(selectDate, discountDate);
        WebUI.clickElement(buttonSelectDiscountDate);
        WebUI.setTextAndClear(inputDiscount, String.valueOf(discount));
        WebUI.clickElement(selectUnitDiscount);
        WebUI.clickElement(selectUnitDiscountPercent);
        WebUI.setTextAndClear(inputQuantity, String.valueOf(quantity));
        WebUI.setTextAndClear(inputSKU, String.valueOf(randomNumber));
        //Product Description  
        WebUI.verifyAssertTrueIsDisplayed(blockProductDescription, "Product description KHONG xuat hien");
        WebUI.setTextAndClear(inputDescription, description);
        WebUI.clickElement(buttonSavePublish); //Click button Save&Public

    }

    public void addProductWithRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        productName = productName + " " + ConfigData.AUTHOR + " " + RandomStringUtils.randomAlphabetic(8).toUpperCase();
        PropertiesHelper.setValue("product_P01", productName);
        WebUI.clickElement(menuProduct);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(submenuProducts);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath("//div[normalize-space()='Add New Product']/ancestor::a/parent::div"));
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleAddNewProduct, "Tieu de Add New Product KHONG xuat hien");
        //Product Information
        WebUI.verifyElementVisible(blockProductInf, "Product Information block KHONG xuat hien");
        WebUI.setTextAndClear(inputProductName, productName);
        WebUI.clickElement(selectCategory);
        WebUI.setTextEnter(inputSearchCategory, category);
        WebUI.clickElement(selectBrand);
        WebUI.setTextEnter(inputSearchBrand, "CMS brand 01");
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        WebUI.setTextAndClear(inputUnit, unit);
        WebUI.setTextAndClear(inputWeight, String.valueOf(weight));
        WebUI.setTextAndClear(inputTags, tags);
        //Product Images
        WebUI.verifyAssertTrueIsDisplayed(blockProductImages, "Product Images block KHONG xuat hien");
        WebUI.clickElement(selectChooseGalleryImages);
        WebUI.clickElement(uploadNewImageTab);
        DriverManager.getDriver().findElement(inputGalleryImages).sendKeys(SystemHelper.getCurrentDir() + "DataTest\\" + imgName);
        WebUI.clickElement(selectFileTab);
        LogUtils.info(imgName);
        LogUtils.info(SystemHelper.splitString(imgName, "[.]"));
        String imageName = SystemHelper.splitString(imgName, "[.]").get(0);
        WebUI.setTextEnter(inputSearchImg, imageName);
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        WebUI.clickElement(selectGalleryImages);
        WebUI.clickElement(buttonAddFileImages);
        WebUI.clickElement(selectChooseThumbnailImages);
        WebUI.setTextEnter(inputSearchImg, imageName);
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        WebUI.clickElement(selectThumbnailImages);
        WebUI.clickElement(buttonAddFileImages);
        //Product price + stock
        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
        WebUI.setTextAndClear(inputUnitPrice, String.valueOf(unitPrice));
        WebUI.setTextAndClear(selectDate, discountDate);
        WebUI.clickElement(buttonSelectDiscountDate);
        WebUI.setTextAndClear(inputDiscount, String.valueOf(discount));
        WebUI.clickElement(selectUnitDiscount);
        WebUI.clickElement(selectUnitDiscountPercent);
        WebUI.setTextAndClear(inputQuantity, String.valueOf(quantity));
        WebUI.setTextAndClear(inputSKU, String.valueOf(randomNumber));
        //Product Description
        WebUI.verifyAssertTrueIsDisplayed(blockProductDescription, "Product description KHONG xuat hien");
        WebUI.setTextAndClear(inputDescription, description);
        WebUI.clickElement(By.xpath("//button[text()='Upload Product']")); //Click button Save&Public

    }

    public void addProductValid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        addProductWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(newProduct).getText();
        verifyNewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount);
    }

    public void addProductInvalid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        addProductWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName);
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.", "Messge Unit hien thi khong dung");
    }

    public void verifyNewProduct(String nameProductVerify, String category, String unit, String unitPrice, String discountDate, String quantity, String description, String discount) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        //WebUI.clickElement(new LoginPage().closeAdvertisementPopup);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(allCategoriesTabUI);
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

        String unitPriceVer1 = WebUI.getElementText(unitPriceProduct);
        String unitPriceVer2 = unitPriceVer1.replaceAll("[^0-9.]", "");
        String unitPriceVer3 = "" + (int) Double.parseDouble(unitPriceVer2);
        Assert.assertEquals(unitPriceVer3, unitPrice, "Unit Price hien thi sai");
        if (discountDateEnd.isAfter(currentDate)) {
            //discountPrice
            String discountPriceVer1 = WebUI.getElementText(discountPriceProduct);
            String discountPriceVer2 = discountPriceVer1.replaceAll("[^0-9.]", "");
            String discountPriceVer3 = "" + (int) Double.parseDouble(discountPriceVer2);
            String comparePrice = "" + (int) (Double.parseDouble(unitPriceVer2) - Double.parseDouble(unitPriceVer2) * Double.parseDouble(discount) / 100 - (int) Double.parseDouble(discountPriceVer2));
            Assert.assertEquals(comparePrice, "0", "Discount Price hien thi sai");
        }
        //unit
        WebUI.verifyAssertTrueEqual(unitUI, "/" + unit, "Unit hien thi sai");
        Assert.assertTrue(DriverManager.getDriver().findElement(unitUI).getText().trim().contains(unit), "Unit hien thi sai");
        //quantity
        WebUI.verifyAssertTrueEqual(ProductInfoPage.quantityProductAvailable, quantity, "Quantity hien thi sai");
        //description
        WebUI.scrollToElement(descriptionUI);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueEqual(descriptionUI, description, "Description hien thi sai");
    }
    public void addProductValidRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        addProductWithRoleSeller(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(By.xpath("(//tr/td[2]/a)[1]")).getText();
        verifyNewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount);
    }

    public void addProductInvalidRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        addProductWithRoleSeller(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName);
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.", "Messge Unit hien thi khong dung");
    }

    public void addProductValidWithDiscount(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
        addProductWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(newProduct).getText();
        verifyNewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount);
    }

}
