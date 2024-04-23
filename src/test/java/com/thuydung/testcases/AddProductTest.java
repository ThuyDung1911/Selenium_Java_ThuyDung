package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.utils.JiraCreateIssue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class AddProductTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiem tra them san pham moi khong co variant, co discount hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithHaveDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleAdmin(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiem tra them san pham moi khong co variant, co discount khong hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleAdmin(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3, description = "Kiem tra them san pham moi co variant, co discount hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithHaveDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductVariantValidRoleAdmin(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4, description = "Kiem tra them san pham moi co variant, co discount khong hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductVariantValidRoleAdmin(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 5, description = "Kiem tra them san pham moi khong hop le voi role admin")
    public void TC_AddProductVariantInvalidRoleAdmin() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductInvalidRoleAdmin(excel.getCellData("productName", 3), excel.getCellData("category", 3), excel.getCellData("unit", 3), excel.getCellData("weight", 3), excel.getCellData("tags", 3), excel.getCellData("unitPrice", 3), excel.getCellData("discountDate", 3), excel.getCellData("quantity", 3), excel.getCellData("description", 3), excel.getCellData("discount", 3), excel.getCellData("image", 3), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 6, description = "Kiem tra them san pham moi khong co variant, co discount hop le voi role seller")
    public void TC_AddProductNoVariantValidRoleSellerWithHaveDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleSeller(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 7, description = "Kiem tra them san pham moi khong co variant, co discount khong hop le voi role seller")
    public void TC_AddProductNoVariantValidRoleSellerWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductNoVariantValidRoleSeller(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 8, description = "Kiem tra them san pham moi co variant, co discount hop le voi role seller")
    public void TC_AddProductVariantValidRoleSellerWithHaveDiscount() {
        try {
            ExcelHelper excel = new ExcelHelper();
            excel.setExcelFile("DataTest/Login.xlsx", "Login");
            getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
            excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
            getAddProductPage().addProductVariantValidRoleSeller(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
        } catch (Exception e) {
            e.printStackTrace();
            if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
                JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                js.executeScript("location.reload()");
            }
        }
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 9, description = "Kiem tra them san pham moi co variant, co discount khong hop le voi role seller")
    public void TC_AddProductVariantValidRoleSellerWithInvalidDiscount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductVariantValidRoleSeller(excel.getCellData("productName", 2), excel.getCellData("category", 2), excel.getCellData("unit", 2), excel.getCellData("weight", 2), excel.getCellData("tags", 2), excel.getCellData("unitPrice", 2), excel.getCellData("discountDate", 2), excel.getCellData("quantity", 2), excel.getCellData("description", 2), excel.getCellData("discount", 2), excel.getCellData("image", 2), excel.getCellData("vat", 1));
    }

    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 10, description = "Kiem tra them san pham moi khong hop le voi role seller")
    public void TC_AddProductVariantInvalidRoleSeller() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().addProductInvalidRoleSeller(excel.getCellData("productName", 3), excel.getCellData("category", 3), excel.getCellData("unit", 3), excel.getCellData("weight", 3), excel.getCellData("tags", 3), excel.getCellData("unitPrice", 3), excel.getCellData("discountDate", 3), excel.getCellData("quantity", 3), excel.getCellData("description", 3), excel.getCellData("discount", 3), excel.getCellData("image", 3), excel.getCellData("vat", 1));
    }


//        @Test(priority = 1, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi khong co variant hop le voi role admin")
//    public void TC_AddProductNoVariantValidRoleAdmin(Hashtable<String, String> data) {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
//        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getAddProductPage().addProductNoVariantValidRoleAdmin(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 1, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi co variant hop le voi role admin")
//    public void TC_AddProductVariantValidRoleAdmin(Hashtable<String, String> data) {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
//        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getAddProductPage().addProductVariantValidRoleAdmin(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 5, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi khong co variant hop le voi role seller")
//    public void TC_AddProductNoVariantValidRoleSeller(Hashtable<String, String> data) {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
//        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getAddProductPage().addProductNoVariantValidRoleSeller(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 5, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi co variant hop le voi role seller")
//    public void TC_AddProductVariantValidRoleSeller(Hashtable<String, String> data) {
//        ExcelHelper excel = new ExcelHelper();
//        excel.setExcelFile("DataTest/Login.xlsx", "Login");
//        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
//        excel.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getAddProductPage().addProductVariantValidRoleSeller(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }

}


