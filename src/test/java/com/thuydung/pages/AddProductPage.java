package com.thuydung.pages;

import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.helpers.SystemHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.requests.ProductVariant;
import com.thuydung.utils.LogUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AddProductPage extends CommonPage {
    public String nameProductVerify;
    public By menuProduct = By.xpath("(//ul[@id='main-menu']//span[text()='Products'])[1]");
    public By submenuAddProduct = By.xpath("//ul[@id='main-menu']//span[normalize-space()='Add New Product']");
    public By submenuAllProducts = By.xpath("//ul[@id='main-menu']//span[normalize-space()='All products']");
    public By submenuProducts = By.xpath("(//ul[@id='main-menu']//span[text()='Products'])[2]");
    public By titleAddNewProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    public By blockProductInf = By.xpath("//h5[normalize-space()='Product Information']");
    public By divProductVariation = By.xpath("//h5[normalize-space()='Product Variation']/ancestor::div[@class='card']");
    public By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    public By selectCategory = By.xpath("//button[@data-id= 'category_id']");
    public By inputSearchCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By selectBrand = By.xpath("//button[@data-id='brand_id']");
    public By inputSearchBrand = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By inputUnit = By.xpath("//input[@name='unit']");
    public By inputWeight = By.xpath("//input[@name='weight']");
    public By inputTags = By.xpath("//tags[@role='tagslist']/span");
    public By blockProductImages = By.xpath("//h5[normalize-space()='Product Images']");
    public By selectChooseGalleryImages = By.xpath("//label[contains(text(),'Gallery Images')]/following-sibling::div//div[contains(@class,'file-amount')]");
    public By selectChooseThumbnailImages = By.xpath("//label[contains(text(),'Thumbnail Image')]/following-sibling::div//div[contains(@class,'file-amount')]");
    public By uploadNewImageTab = By.xpath("//a[normalize-space()='Upload New']");
    public By inputGalleryImages = By.xpath("//input[@class = 'uppy-Dashboard-input']");
    public By buttonAddFileImages = By.xpath("//button[normalize-space()='Add Files']");
    public By selectFileTab = By.xpath("//a[normalize-space()='Select File']");
    public By selectGalleryImages = By.xpath("(//img[@class='img-fit'])[1]");
    public By inputSearchImg = By.xpath("//input[@placeholder='Search your files']");
    public By selectThumbnailImages = By.xpath("(//img[@class='img-fit'])[2]");
    public By blockProductPrice = By.xpath("//h5[normalize-space()='Product price + stock']");
    public By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    public By selectDate = By.xpath("//input[@name='date_range']");
    public By buttonSelectDiscountDate = By.xpath("//button[contains(@class,'applyBtn')]");
    public By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    public By selectUnitDiscount = By.xpath("//h5[normalize-space()='Product price + stock']/ancestor::div[@class='card']//select[@name='discount_type']/following-sibling::button");
    public By selectUnitDiscountPercent = By.xpath("//span[normalize-space()='Percent']");
    public By inputQuantity = By.xpath("//input[@placeholder='Quantity']");
    public By inputSKU = By.xpath("//input[@placeholder='SKU']");
    By inputVat = By.xpath("//input[@placeholder='Tax']");
    public By selectUnitVat = By.xpath("//h5[normalize-space()='Vat & TAX']/ancestor::div[@class='card']//select[@name='tax_type[]']/following-sibling::button");
    public By selectUnitVatFlat = By.xpath("//h5[text()='Vat & TAX']/parent::div/following-sibling::div//span[normalize-space()='Flat']");
    public By blockProductDescription = By.xpath("//h5[normalize-space()='Product Description']");
    public By inputDescription = By.xpath("//textarea[@name='description']/following-sibling::div//div[contains(@class,'note-editable')]");
    public By buttonSavePublish = By.xpath("//button[normalize-space()='Save & Publish']");
    public By messageAddProduct = By.xpath("//span[@data-notify='message']");
    public static By allCategoriesTabUI = By.xpath("//a[normalize-space()='All categories']");
    public static By unitUI = By.xpath("//div[text()='Price:']/parent::div/following-sibling::div//span");
    public static By descriptionUI = By.xpath("//div[@id='tab_default_1']//p");
    int randomNumber = new Random().nextInt(1000000);
    public static By newProduct = By.xpath("(//div[@class='card']//img/parent::div/following-sibling::div/span)[1]");
    public static By discountPriceProductInProductDetail = By.xpath("//div[text()='Discount Price:']/parent::div/following-sibling::div");
    public static By unitPriceProductInProductDetail = By.xpath("//div[text()='Price:']/parent::div/following-sibling::div");
    public static By totalPriceInDetailProduct = By.xpath("//strong[@id='chosen_price']");
    public static By blockProductVariation = By.xpath("//h5[normalize-space()='Product Variation']");
    public static By valueVariant = By.xpath("//tr[@class='variant']//label");
    public static By valueVariantPrice = By.xpath("//tr[@class='variant']//input[contains(@name,'price')]");
    public static By valueVariantSKU = By.xpath("//tr[@class='variant']//input[contains(@name,'sku')]");
    public static By valueVariantQuantity = By.xpath("//tr[@class='variant']//input[contains(@name,'qty')]");
    By toggleInputColor = By.xpath("//input[@name='colors_active']");
    By toggleColor = By.xpath("//input[@name='colors_active']/parent::label");
    By selectColor = By.xpath("//button[@data-id='colors']");
    By inputSearchDropDown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    public void openAddProductPage() {
        WebUI.clickElement(menuProduct);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(submenuAddProduct);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(titleAddNewProduct, "Tieu de Add New Product KHONG xuat hien");
    }

    public void addProductNoVariantWithRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
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
        ////No Product Variation
        //Product price + stock
        addProductPriceAndStockNoVariation(unitPrice, discountDate, discount, quantity);
        //Product Description  
        WebUI.verifyAssertTrueIsDisplayed(blockProductDescription, "Product description KHONG xuat hien");
        WebUI.setTextAndClear(inputDescription, description);
        //vat
        WebUI.setTextAndClear(inputVat, vat);
        WebUI.clickElement(selectUnitVat);
        WebUI.waitForElementVisible(selectUnitVatFlat);
        WebUI.clickElement(selectUnitVatFlat);
        WebUI.clickElement(buttonSavePublish); //Click button Save&Public

    }

    public void addProductVariantWithRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
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
        //Product Variation
        WebUI.scrollToElement(divProductVariation);
        addProductVariationColor(List.of("AliceBlue", "Amethyst"));
        addProductVariationAttribute(List.of("Size"));
        addProductVariationAttribute(List.of("Quality"));
        addProductVariationAttributeValue("Size", List.of("22", "23"));
        addProductVariationAttributeValue("Quality", List.of("Real", "Remake"));
        //Product price + stock
        addProductPriceAndStockVariation(unitPrice, discountDate, discount);
        //Product Description
        WebUI.verifyAssertTrueIsDisplayed(blockProductDescription, "Product description KHONG xuat hien");
        WebUI.setTextAndClear(inputDescription, description);
        //vat
        WebUI.scrollToElement(inputVat);
        WebUI.setTextAndClear(inputVat, vat);
        WebUI.clickElement(selectUnitVat);
        WebUI.clickElement(selectUnitVatFlat);
        WebUI.clickElement(buttonSavePublish); //Click button Save&Public

    }

    public void addProductVariationColor(List<String> color) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductVariation, "Product variation block KHONG xuat hien");
        WebUI.clickElement(toggleColor);
        WebUI.clickElement(selectColor);
        for (String colorName : color) {
            WebUI.setTextAndClear(inputSearchDropDown, colorName);
            WebUI.waitForJQueryLoad();
            WebUI.sleep(2);
            WebUI.clickElement(By.xpath("//div[@class='dropdown-menu show']//span[text()='" + colorName + "']"));
        }
        WebUI.clickElement(selectColor);

    }

    public void addProductVariationAttribute(List<String> attribute) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductVariation, "Product variation block KHONG xuat hien");

        By inputSearchDropDown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
        By selectAttribute = By.xpath("//button[@data-id='choice_attributes']");
        for (String attributeName : attribute) {
            WebUI.clickElement(selectAttribute);
            WebUI.setTextAndClear(inputSearchDropDown, attributeName);
            WebUI.waitForJQueryLoad();
            WebUI.sleep(2);
            WebUI.clickElement(By.xpath("//div[@class='dropdown-menu show']//span[text()='" + attributeName + "']"));
            WebUI.waitForJQueryLoad();
            WebUI.clickElement(selectAttribute);
        }
    }

    public void addProductVariationAttributeValue(String attributeName, List<String> attributeValue) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductVariation, "Product variation block KHONG xuat hien");

        By inputSearchDropDown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

        By selectAttributeValue = By.xpath("//input[@name='choice[]'][@value='" + attributeName + "']/parent::div/following-sibling::div//button");
        WebUI.clickElement(selectAttributeValue);
        for (String attributeNameValue : attributeValue) {
            WebUI.setTextAndClear(inputSearchDropDown, attributeNameValue);
            WebUI.waitForJQueryLoad();
            WebUI.sleep(2);
            WebUI.clickElement(By.xpath("//div[@class='dropdown-menu show']//span[text()='" + attributeNameValue + "']"));
        }
        WebUI.clickElement(selectAttributeValue);
    }

    public void addProductPriceAndStockNoVariation(String unitPrice, String discountDate, String discount, String quantity) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
        WebUI.setTextAndClear(inputUnitPrice, unitPrice);
        WebUI.setTextAndClear(selectDate, discountDate);
        WebUI.clickElement(buttonSelectDiscountDate);
        WebUI.setTextAndClear(inputDiscount, discount);
        WebUI.clickElement(selectUnitDiscount);
        WebUI.clickElement(selectUnitDiscountPercent);
        WebUI.setTextAndClear(inputQuantity, String.valueOf(quantity));
        WebUI.setTextAndClear(inputSKU, String.valueOf(randomNumber));

    }

    public void addInfoCommonProductPriceAndStockVariation(String unitPrice, String discountDate, String discount) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
        WebUI.setTextAndClear(inputUnitPrice, String.valueOf(unitPrice));
        WebUI.setTextAndClear(selectDate, discountDate);
        WebUI.clickElement(buttonSelectDiscountDate);
        WebUI.setTextAndClear(inputDiscount, String.valueOf(discount));
        WebUI.clickElement(selectUnitDiscount);
        WebUI.clickElement(selectUnitDiscountPercent);
    }

    public void addProductPriceAndStockVariation(String unitPrice, String discountDate, String discount) {
        WebUI.verifyAssertTrueIsDisplayed(blockProductPrice, "Product price block KHONG xuat hien");
        WebUI.setTextAndClear(inputUnitPrice, String.valueOf(unitPrice));
        WebUI.setTextAndClear(selectDate, discountDate);
        WebUI.clickElement(buttonSelectDiscountDate);
        WebUI.setTextAndClear(inputDiscount, String.valueOf(discount));
        WebUI.clickElement(selectUnitDiscount);
        WebUI.clickElement(selectUnitDiscountPercent);
        //Variant
        By valueVariant = By.xpath("//tr[@class='variant']//label");
        List<WebElement> elementValueVariant = DriverManager.getDriver().findElements(valueVariant);
        for (int i = 1; i <= elementValueVariant.size(); i++) {
            By elementVariantPrice = By.xpath("(//tr[@class='variant']//input[contains(@name,'price')])[" + i + "]");
            String valueVariantPrice = String.valueOf(WebUI.randomNumber(6, 7));
            WebUI.setTextAndClear(elementVariantPrice, valueVariantPrice);
            By elementVariantQuantity = By.xpath("(//tr[@class='variant']//input[contains(@name,'qty')])[" + i + "]");
            String valueVariantQuantity = String.valueOf(WebUI.randomNumber(2, 3));
            WebUI.setTextAndClear(elementVariantQuantity, valueVariantQuantity);
        }
    }

    public void addProductNoVariantWithRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
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
        //Vat
        WebUI.scrollToElement(inputVat);
        WebUI.setTextAndClear(inputVat, vat);
        WebUI.clickElement(selectUnitVat);
        WebUI.waitForElementVisible(selectUnitVatFlat);
        WebUI.clickElement(By.xpath("//button[text()='Upload Product']")); //Click button Save&Public

    }
    public void addProductVariantWithRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
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
        //Product Variation
        WebUI.scrollToElement(divProductVariation);
        addProductVariationColor(List.of("AliceBlue", "Amethyst"));
        addProductVariationAttribute(List.of("Size"));
        WebUI.sleep(2);
        addProductVariationAttribute(List.of("Quality"));
        addProductVariationAttributeValue("Size", List.of("22", "23"));
        addProductVariationAttributeValue("Quality", List.of("Real", "Remake"));
        //Product price + stock
        addProductPriceAndStockVariation(unitPrice, discountDate, discount);
        //Product Description
        WebUI.verifyAssertTrueIsDisplayed(blockProductDescription, "Product description KHONG xuat hien");
        WebUI.setTextAndClear(inputDescription, description);
        //Vat
        WebUI.scrollToElement(inputVat);
        WebUI.setTextAndClear(inputVat, vat);
        WebUI.clickElement(selectUnitVat);
        WebUI.waitForElementVisible(selectUnitVatFlat);
        WebUI.clickElement(By.xpath("//button[text()='Upload Product']")); //Click button Save&Public

    }
    public void addProductNoVariantValidRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductNoVariantWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(newProduct).getText();
        EditProductPage.verifyNewProductNoVariantInEditDisplay(nameProductVerify, category, unit, weight, unitPrice, discountDate, quantity, description, discount, vat);
        verifyNewProductInViewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount, vat);
    }

    public void addProductVariantValidRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductVariantWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(newProduct).getText();
        verifyNewProductVariant(nameProductVerify, category, unit, discountDate, description, discount, vat);
    }

    public void verifyNewProductVariant(String nameProductVerify, String category, String unit, String discountDate, String description, String discount, String vat) {
        WebUI.clickElement(EditProductPage.btnEditProductNewest);
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(valueVariant);
        WebUI.waitForElementVisible(valueVariantPrice);
        WebUI.waitForElementVisible(valueVariantSKU);
        WebUI.waitForElementVisible(valueVariantQuantity);
        WebUI.sleep(5);
        List<WebElement> elementValueVariants = DriverManager.getDriver().findElements(valueVariant);
        List<WebElement> elementValueVariantPrices = DriverManager.getDriver().findElements(valueVariantPrice);
        List<WebElement> elementValueVariantSKUs = DriverManager.getDriver().findElements(valueVariantSKU);
        List<WebElement> elementValueVariantQuantities = DriverManager.getDriver().findElements(valueVariantQuantity);

        List<String> valueElementValueVariants = new ArrayList<>();
        for (WebElement elementValueVariant : elementValueVariants) {
            valueElementValueVariants.add(elementValueVariant.getText());
        }
        List<BigDecimal> valueElementValueVariantPrices = new ArrayList<>();
        for (WebElement elementValueVariantPrice : elementValueVariantPrices) {
            valueElementValueVariantPrices.add(new BigDecimal(elementValueVariantPrice.getAttribute("value")));
        }
        List<String> valueElementValueVariantSKUs = new ArrayList<>();
        for (WebElement elementValueVariantSKU : elementValueVariantSKUs) {
            valueElementValueVariantSKUs.add(elementValueVariantSKU.getAttribute("value"));
        }
        List<String> valueElementValueVariantQuantities = new ArrayList<>();
        for (WebElement elementValueVariantQuantity : elementValueVariantQuantities) {
            valueElementValueVariantQuantities.add(elementValueVariantQuantity.getAttribute("value"));
        }
        List<ProductVariant> infoProductVariant = new ArrayList<>();
        for (int i = 0; i < valueElementValueVariants.size(); i++) {
            ProductVariant productVariant = new ProductVariant();
            String variantName = valueElementValueVariants.get(i);
            BigDecimal variantPrice = valueElementValueVariantPrices.get(i);
            String variantSKU = valueElementValueVariantSKUs.get(i);
            String variantQuantity = valueElementValueVariantQuantities.get(i);
            productVariant.setVariantName(variantName);
            productVariant.setVariantPrice(variantPrice);
            productVariant.setVariantSKU(variantSKU);
            productVariant.setVariantQuantity(variantQuantity);
            infoProductVariant.add(productVariant);
        }

        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
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
        //unit
        WebUI.verifyAssertTrueEqual(unitUI, "/" + unit, "Unit hien thi sai");
        Assert.assertTrue(DriverManager.getDriver().findElement(unitUI).getText().trim().contains(unit), "Unit hien thi sai");
        //unitPrice
        //discountDateEnd
        if (!discountDate.contains("to")) {

        }
        String[] discountDates = discountDate.split(" to ");
        String dataDiscountDateEnd = discountDates[discountDates.length - 1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime discountDateEnd = LocalDateTime.parse(dataDiscountDateEnd, formatter);
        //currentDate
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime currentDate = currentDateTime;

        //get value variant selected
        String valueVariantName = getVariantNameSelected();
        //get price variant
        BigDecimal infoVariantPrice = BigDecimal.ZERO;
        String infoVariantQuantity = "";
        BigDecimal minValueInfoVariantPrice = infoProductVariant.get(0).getVariantPrice();
        BigDecimal maxValueInfoVariantPrice = infoProductVariant.get(0).getVariantPrice();
        for (int j = 0; j < infoProductVariant.size(); j++) {
            if (infoProductVariant.get(j).getVariantPrice().compareTo(minValueInfoVariantPrice) < 0) {
                minValueInfoVariantPrice = infoProductVariant.get(j).getVariantPrice();
            }
            if (infoProductVariant.get(j).getVariantPrice().compareTo(maxValueInfoVariantPrice) > 0) {
                maxValueInfoVariantPrice = infoProductVariant.get(j).getVariantPrice();
            }
            if (infoProductVariant.get(j).getVariantName().equals(valueVariantName)) {
                infoVariantPrice = infoProductVariant.get(j).getVariantPrice();
                //quantity
                infoVariantQuantity = infoProductVariant.get(j).getVariantQuantity();
            }
        }
        //unitPrice hien thi o trang product detail (hien thi khoang tu min - max) + tax
        String unitPriceInProductDetail = WebUI.getElementText(unitPriceProductInProductDetail);
        String minUnitPriceInProductDetail = unitPriceInProductDetail.split("-")[0].trim();
        String maxUnitPriceInProductDetail = unitPriceInProductDetail.split("-")[1].split("/g")[0].trim();
        BigDecimal minValueUnitPriceInProductDetail = WebUI.convertCurrencyToBigDecimal(minUnitPriceInProductDetail);
        BigDecimal maxValueUnitPriceInProductDetail = WebUI.convertCurrencyToBigDecimal(maxUnitPriceInProductDetail);
//        BigDecimal minValueInfoVariantPriceCheck = minValueInfoVariantPrice.add(minValueInfoVariantPrice.multiply(WebUI.stringToBigDecimal(vat)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
//        BigDecimal maxValueInfoVariantPriceCheck = maxValueInfoVariantPrice.add(maxValueInfoVariantPrice.multiply(WebUI.stringToBigDecimal(vat)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal minValueInfoVariantPriceCheck = minValueInfoVariantPrice.add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal maxValueInfoVariantPriceCheck = maxValueInfoVariantPrice.add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifySoftAssertEquals(minValueUnitPriceInProductDetail, minValueInfoVariantPriceCheck, "Min Unit Price hien thi sai");
        WebUI.verifySoftAssertEquals(maxValueUnitPriceInProductDetail, maxValueInfoVariantPriceCheck, "Max Unit Price hien thi sai");

        if (discountDateEnd.isAfter(currentDate)) {
            //discountPrice
            String discountPriceInProductDetail = WebUI.getElementText(discountPriceProductInProductDetail);
            String minDiscountPriceInProductDetail = discountPriceInProductDetail.split("-")[0].trim();
            String maxDiscountPriceInProductDetail = discountPriceInProductDetail.split("-")[1].split("/g")[0].trim();
            BigDecimal minDiscountPriceCheck = minValueInfoVariantPrice.subtract(minValueInfoVariantPrice.multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal maxDiscountPriceCheck = maxValueInfoVariantPrice.subtract(maxValueInfoVariantPrice.multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);

            WebUI.verifySoftAssertEquals(WebUI.convertCurrencyToBigDecimal(minDiscountPriceInProductDetail), minDiscountPriceCheck, "Min Discount Price hien thi sai");
            WebUI.verifySoftAssertEquals(WebUI.convertCurrencyToBigDecimal(maxDiscountPriceInProductDetail), maxDiscountPriceCheck, "Max Discount Price hien thi sai");
        }

        //quantity
        WebUI.verifyAssertEquals(WebUI.getElementText(ProductInfoPage.quantityProductAvailable), infoVariantQuantity, "Quantity hien thi sai");
        //description
        WebUI.scrollToElement(descriptionUI);
        WebUI.sleep(1);
        WebUI.verifySoftAssertTrueEqual(descriptionUI, description, "Description hien thi sai");
        //Total price in detail product
        String totalPriceInProductDetail = WebUI.getElementText(totalPriceInDetailProduct);
        BigDecimal valueUnitPriceInProductDetail = WebUI.convertCurrencyToBigDecimal(totalPriceInProductDetail);
//        BigDecimal valueUnitPriceCheck = infoVariantPrice.add(infoVariantPrice.multiply(WebUI.stringToBigDecimal(vat)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal valueUnitPriceCheck = infoVariantPrice.add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);

        if (discountDateEnd.isAfter(currentDate)) {
            BigDecimal discountPrice = infoVariantPrice.subtract(infoVariantPrice.multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).add(WebUI.stringToBigDecimal(vat));
            valueUnitPriceCheck = discountPrice;
        }
        WebUI.verifySoftAssertEquals(valueUnitPriceInProductDetail, valueUnitPriceCheck, "Unit Price hien thi sai");
    }

    public static String getVariantNameSelected() {
        //get value variant selected
        By optionSize = By.xpath("//div[contains(normalize-space(),'Size:')]/parent::div[contains(@class,'col')]/following-sibling::div//input[@type='radio'][contains(@name,'attribute')]");
        By optionQuality = By.xpath("//div[contains(normalize-space(),'Quality:')]/parent::div[contains(@class,'col')]/following-sibling::div//input[@type='radio'][contains(@name,'attribute')]");
        By optionColor = By.xpath("//input[@type='radio'][contains(@name,'color')]");
        String valueVariantName = "";
        if (WebUI.checkElementExist(optionColor)) {
            List<WebElement> elementOptionColor = DriverManager.getDriver().findElements(optionColor);
            for (WebElement elementOption : elementOptionColor) {
                if (elementOption.isSelected()) {
                    String valueSelectedColor = elementOption.getAttribute("value");
                    valueVariantName += valueSelectedColor;
                }
            }
        }
        if (WebUI.checkElementExist(optionSize)) {
            List<WebElement> elementOptionSize = DriverManager.getDriver().findElements(optionSize);
            for (WebElement elementOption : elementOptionSize) {
                if (elementOption.isSelected()) {
                    String valueSelectedSize = elementOption.getAttribute("value");
                    valueVariantName += "-" + valueSelectedSize;
                }
            }
        }
        if (WebUI.checkElementExist(optionQuality)) {
            List<WebElement> elementOptionQuality = DriverManager.getDriver().findElements(optionQuality);
            for (WebElement elementOption : elementOptionQuality) {
                if (elementOption.isSelected()) {
                    String valueSelectedQuality = elementOption.getAttribute("value");
                    valueVariantName += "-" + valueSelectedQuality;
                }
            }
        }

        return valueVariantName;
    }

    public void addProductInvalidRoleAdmin(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductNoVariantWithRoleAdmin(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.", "Message Unit hien thi khong dung");
    }

    public static void verifyNewProductInViewProduct(String nameProductVerify, String category, String unit, String unitPrice, String discountDate, String quantity, String description, String discount, String vat) {
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

        String unitPriceVer1 = WebUI.getElementText(unitPriceProductInProductDetail); //Hien thi tren trang view product
        BigDecimal unitPriceVer2 = WebUI.convertCurrencyToBigDecimal(unitPriceVer1);
        //BigDecimal unitPriceCheck = WebUI.stringToBigDecimal(unitPrice).add(WebUI.stringToBigDecimal(unitPrice).multiply(WebUI.stringToBigDecimal(vat)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal unitPriceCheck = WebUI.stringToBigDecimal(unitPrice).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(unitPriceVer2, unitPriceCheck, "Unit Price hien thi sai");
        if (discountDateEnd.isAfter(currentDate)) {
            //discountPrice
            String discountPriceVer1 = WebUI.getElementText(discountPriceProductInProductDetail);
            BigDecimal discountPriceVer2 = WebUI.convertCurrencyToBigDecimal(discountPriceVer1);
//            BigDecimal discountPriceCheck = unitPriceCheck.subtract(unitPriceCheck.multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
            BigDecimal discountPriceCheck = WebUI.stringToBigDecimal(unitPrice).subtract(WebUI.stringToBigDecimal(unitPrice).multiply(WebUI.stringToBigDecimal(discount)).divide(new BigDecimal(100))).add(WebUI.stringToBigDecimal(vat)).setScale(2, RoundingMode.HALF_UP);
            WebUI.verifyAssertEquals(discountPriceVer2, discountPriceCheck, "Discount Price hien thi sai");
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

    public void addProductNoVariantValidRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductNoVariantWithRoleSeller(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(By.xpath("(//tr/td[2]/a)[1]")).getText();
        verifyNewProductInViewProduct(nameProductVerify, category, unit, unitPrice, discountDate, quantity, description, discount, vat);
    }
    public void addProductVariantValidRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductVariantWithRoleSeller(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.verifyAssertTrueIsDisplayed(messageAddProduct, "Message Add Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageAddProduct, "Product has been inserted successfully", "Message Add Product thanh cong KHONG xuat hien");
        nameProductVerify = DriverManager.getDriver().findElement(By.xpath("(//tr/td[2]/a)[1]")).getText();
        verifyNewProductVariant(nameProductVerify, category, unit, discountDate, description, discount, vat);
    }

    public void addProductInvalidRoleSeller(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName, String vat) {
        addProductNoVariantWithRoleSeller(productName, category, unit, weight, tags, unitPrice, discountDate, quantity, description, discount, imgName, vat);
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.", "Messge Unit hien thi khong dung");
    }





}
