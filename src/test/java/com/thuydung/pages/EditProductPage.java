package com.thuydung.pages;

import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProductPage extends CommonPage{
    private String nameProductVerify;
    private By menuProduct = By.xpath("//ul[@id='main-menu']//span[text()='Products']");
    private By submenuAllProducts = By.xpath("//ul[@id='main-menu']//span[normalize-space()='All products']");
    private By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By btnEditProductNewest = By.xpath("(//a[@title='Edit'])[1]");
    private By titleEditProduct = By.xpath("//h1[normalize-space()='Edit Product']");
    private static By inputProductName = By.name("name");
    private static By selectCategory = By.xpath("//button[@data-id='category_id']");
    private By inputSearchDropdown = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private static By selectBrand = By.xpath("//button[@data-id='brand_id']");
    private static By inputWeight = By.xpath("//input[@name='weight']");
    private static By inputUnit = By.xpath("//input[@name='unit']");
    private static By inputTags = By.xpath("//tags[@role='tagslist']/span");
    private static By valueInputTags = By.xpath("//tags[@role='tagslist']/tag");
    public static By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    public static By selectDate = By.xpath("//input[@name='date_range']");
    public By buttonSelectDiscountDate = By.xpath("//button[contains(@class,'applyBtn')]");
    public static By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    public By selectUnitDiscount = By.xpath("//h5[normalize-space()='Product price + stock']/ancestor::div[@class='card']//select[@name='discount_type']/following-sibling::button");
    public By selectUnitDiscountPercent = By.xpath("//span[normalize-space()='Percent']");
    public static By inputQuantity = By.xpath("//input[@placeholder='Quantity']");
    public static By inputSKU = By.xpath("//input[@placeholder='SKU']");
    static By inputVat = By.xpath("//input[@placeholder='Tax']");
    public By selectUnitVat = By.xpath("//h5[normalize-space()='Vat & TAX']/ancestor::div[@class='card']//select[@name='tax_type[]']/following-sibling::button");
    public By selectUnitVatFlat = By.xpath("//h5[text()='Vat & TAX']/parent::div/following-sibling::div//span[normalize-space()='Flat']");
    public static By inputDescription = By.xpath("//textarea[@name='description']/following-sibling::div//div[contains(@class,'note-editable')]");

    private By messageEditProduct = By.xpath("//span[@data-notify='message']");
    private By buttonUpdateProduct = By.xpath("//button[normalize-space()='Update Product']");
    private By newProductName = By.xpath("//input[@placeholder='Product Name']");
    public static By valueVariant = By.xpath("//tr[@class='variant']//label");
    public static By valueVariantPrice = By.xpath("//tr[@class='variant']//input[contains(@name,'price')]");
    public static By valueVariantSKU = By.xpath("//tr[@class='variant']//input[contains(@name,'sku')]");
    public static By valueVariantQuantity = By.xpath("//tr[@class='variant']//input[contains(@name,'qty')]");
    public void editProduct(String productName, String category, String unit, String description) {
        WebUI.clickElement(menuProduct);
        WebUI.waitForJQueryLoad();
        WebUI.clickElement(submenuAllProducts);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(btnEditProductNewest);
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
//        WebUI.clickElement(By.xpath("//tags[@role='tagslist']"));
//        WebUI.keydownBackspace();
//        WebUI.keydownBackspace();
//        WebUI.setTextEnter(inputTags, tags);
        WebUI.setTextAndClear(inputDescription, description);
        WebUI.clickElement(buttonUpdateProduct);
    }
    //Edit product valid
    public void editProductValid(String productName, String category, String unit, String description) {
        editProduct(productName, category, unit, description);
        //Verify message
        WebUI.verifyAssertTrueIsDisplayed(messageEditProduct, "Message Edit Product KHONG xuat hien");
        WebUI.verifyAssertTrueEqual(messageEditProduct, "Product has been updated successfully", "Message Edit Product KHONG chinh xac");
        nameProductVerify = DriverManager.getDriver().findElement(newProductName).getAttribute("value");
        //verifyNewProductNoVariantInEditDisplay(nameProductVerify, category, unit, weight, unitPrice, discountDate, quantity, description, discount, vat);
        verifyNewProductInViewProductWithCommonInfo(nameProductVerify, category, unit, description);
    }
    public static void verifyNewProductInViewProductWithCommonInfo(String nameProductVerify, String category, String unit, String description) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        //WebUI.clickElement(new LoginPage().closeAdvertisementPopup);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
        WebUI.clickElement(AddProductPage.allCategoriesTabUI);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
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
        WebUI.verifyAssertTrueEqual(AddProductPage.unitUI, "/" + unit, "Unit hien thi sai");
        Assert.assertTrue(DriverManager.getDriver().findElement(AddProductPage.unitUI).getText().trim().contains(unit), "Unit hien thi sai");
        //description
        WebUI.scrollToElement(AddProductPage.descriptionUI);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueEqual(AddProductPage.descriptionUI, description, "Description hien thi sai");
    }
    public static void verifyNewProductNoVariantInEditDisplay(String nameProductNew, String category, String unit, String weight, String unitPrice, String discountDate, String quantity,  String description, String discount, String vat) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/admin/products/admin")) {
            WebUI.openURL("https://cms.anhtester.com/admin/products/admin");
        }
        WebUI.clickElement(EditProductPage.btnEditProductNewest);
        WebUI.waitForPageLoaded();
        Map<String,String> dataAddSuccessProduct = EditProductPage.getProductNoVariantDataInEditDisplay();
        WebUI.verifyAssertEquals(nameProductNew, dataAddSuccessProduct.get("productName"), "Product name khong duoc chinh sua dung");

        WebUI.verifyAssertEquals(category, dataAddSuccessProduct.get("category"), "Category khong duoc chinh sua dung");

        WebUI.verifyAssertEquals(unit, dataAddSuccessProduct.get("unit"), "Unit khong duoc chinh sua dung");

        BigDecimal weightCheck = WebUI.stringToBigDecimal(weight).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(String.valueOf(weightCheck), dataAddSuccessProduct.get("weight"), "Weight khong duoc chinh sua dung");

        BigDecimal unitPriceCheck = WebUI.stringToBigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(String.valueOf(unitPriceCheck), dataAddSuccessProduct.get("unitPrice"), "Unit Price khong duoc chinh sua dung");

        BigDecimal discountFormat = WebUI.stringToBigDecimal(discount).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(String.valueOf(discountFormat), dataAddSuccessProduct.get("discount"), "Discount khong duoc chinh sua dung");

        WebUI.verifyAssertEquals(discountDate, dataAddSuccessProduct.get("discountDate"), "Discount Date khong duoc chinh sua dung");

        WebUI.verifyAssertEquals(quantity, dataAddSuccessProduct.get("quantity"), "Quantity khong duoc chinh sua dung");

        WebUI.verifyAssertEquals(description, dataAddSuccessProduct.get("description"), "Description khong duoc chinh sua dung");

        BigDecimal vatCheck = WebUI.stringToBigDecimal(vat).setScale(2, RoundingMode.HALF_UP);
        WebUI.verifyAssertEquals(String.valueOf(vatCheck), dataAddSuccessProduct.get("vat"), "Vat khong duoc chinh sua dung");
    }
    //Edit product invalid
    public void editProductInvalid(String productName, String category, String unit, String description) {
        editProduct(productName, category, unit, description);
        //Verify
        WebUI.checkHTML5MessageWithValueInvalid(inputUnit, "Unit la truong bat buoc");
        WebUI.verifyAssertTrueEqualMessageHTML(inputUnit, "Please fill out this field.","Messge Product name hien thi khong dung");
    }
    public static Map<String,String> getProductNoVariantDataInEditDisplay() {
        String productName = WebUI.getElementAttribute(inputProductName,"value");
        String category = WebUI.getElementAttribute(selectCategory,"title").replace("-","").trim();
        String unit = WebUI.getElementAttribute(inputUnit,"value");
        String brand = WebUI.getElementAttribute(selectBrand,"title");
        String weight = WebUI.getElementAttribute(inputWeight,"value");
        List<WebElement> listTag= DriverManager.getDriver().findElements(valueInputTags);
        List<String> tags = new ArrayList<>();
        for (WebElement tag : listTag) {
            tags.add(tag.getAttribute("value"));
        }
        String unitPrice = WebUI.getElementAttribute(inputUnitPrice,"value");
        String discountDate = WebUI.getElementAttribute(selectDate,"value");
        String quantity = WebUI.getElementAttribute(inputQuantity,"value");
        String description = WebUI.getElementText(inputDescription);
        String discount = WebUI.getElementAttribute(inputDiscount,"value");
        String vat = WebUI.getElementAttribute(inputVat,"value");
        String sku = WebUI.getElementAttribute(inputSKU,"value");
        Map<String,String> productData = new HashMap<>();
        productData.put("productName",productName);
        productData.put("category",category);
        productData.put("brand",brand);
        productData.put("unit",unit);
        productData.put("weight",weight);
        productData.put("tags", tags.toString());
        productData.put("unitPrice",unitPrice);
        productData.put("discountDate",discountDate);
        productData.put("discount",discount);
        productData.put("sku",sku);
        productData.put("quantity",quantity);
        productData.put("description",description);
        productData.put("vat",vat);
        return productData;
    }
    public Map<String,String> getProductVariantDataInEditDisplay() {
        String productName = WebUI.getElementText(inputProductName);
        String category = WebUI.getElementText(selectCategory);
        String unit = WebUI.getElementText(inputUnit);
        String brand = WebUI.getElementText(selectBrand);
        String weight = WebUI.getElementText(inputWeight);
        String tags = WebUI.getElementText(inputTags);
        String unitPrice = WebUI.getElementText(inputUnit);
        String discountDate = WebUI.getElementText(selectDate);
        String description = WebUI.getElementText(inputDescription);
        String discount = WebUI.getElementText(inputDiscount);
        String vat = WebUI.getElementText(inputVat);
        String sku = WebUI.getElementText(inputSKU);
        List<WebElement> variantList = DriverManager.getDriver().findElements(valueVariant);
        List<WebElement> variantPriceList = DriverManager.getDriver().findElements(valueVariantPrice);
        List<WebElement> variantSKUList = DriverManager.getDriver().findElements(valueVariantSKU);
        List<WebElement> variantQuantityList = DriverManager.getDriver().findElements(valueVariantQuantity);
        List<String> variantListData = new ArrayList<>();
        List<String> variantPriceListData = new ArrayList<>();
        List<String> variantSKUListData = new ArrayList<>();
        List<String> variantQuantityListData = new ArrayList<>();
        for (WebElement variant : variantList) {
            variantListData.add(variant.getText());
        }
        for (WebElement variantPrice : variantPriceList) {
            variantPriceListData.add(variantPrice.getAttribute("value"));
        }
        for (WebElement variantSKU : variantSKUList) {
            variantSKUListData.add(variantSKU.getAttribute("value"));
        }
        for (WebElement variantQuantity : variantQuantityList) {
            variantQuantityListData.add(variantQuantity.getAttribute("value"));
        }
        Map<String,String> productData = new HashMap<>();
        productData.put("productName",productName);
        productData.put("category",category);
        productData.put("brand",brand);
        productData.put("unit",unit);
        productData.put("weight",weight);
        productData.put("tags",tags);
        productData.put("unitPrice",unitPrice);
        productData.put("discountDate",discountDate);
        productData.put("discount",discount);
        productData.put("sku",sku);
        productData.put("description",description);
        productData.put("vat",vat);
        productData.put("variantList",variantListData.toString());
        productData.put("variantPriceList",variantPriceListData.toString());
        productData.put("variantSKUList",variantSKUListData.toString());
        productData.put("variantQuantityList",variantQuantityListData.toString());
        return productData;
    }




}
