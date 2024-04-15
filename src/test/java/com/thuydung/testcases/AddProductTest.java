package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class AddProductTest extends BaseTest {
    ExcelHelper excelLogin;
    ExcelHelper excelAddProduct;

    @Test(priority = 1, description = "Kiem tra them san pham moi khong co variant, co discount hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithHaveDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductNoVariantValidRoleAdmin(excelAddProduct.getCellData("productName",1), excelAddProduct.getCellData("category",1), excelAddProduct.getCellData("unit",1), excelAddProduct.getCellData("weight",1), excelAddProduct.getCellData("tags",1), excelAddProduct.getCellData("unitPrice",1), excelAddProduct.getCellData("discountDate",1), excelAddProduct.getCellData("quantity",1), excelAddProduct.getCellData("description",1), excelAddProduct.getCellData("discount",1), excelAddProduct.getCellData("image",1), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 2, description = "Kiem tra them san pham moi khong co variant, co discount khong hop le voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithInvalidDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductNoVariantValidRoleAdmin(excelAddProduct.getCellData("productName",2), excelAddProduct.getCellData("category",2), excelAddProduct.getCellData("unit",2), excelAddProduct.getCellData("weight",2), excelAddProduct.getCellData("tags",2), excelAddProduct.getCellData("unitPrice",2), excelAddProduct.getCellData("discountDate",2), excelAddProduct.getCellData("quantity",2), excelAddProduct.getCellData("description",2), excelAddProduct.getCellData("discount",2), excelAddProduct.getCellData("image",2), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 3, description = "Kiem tra them san pham moi khong co variant, khong co discount voi role admin")
    public void TC_AddProductNoVariantValidRoleAdminWithNoDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductNoVariantValidRoleAdmin(excelAddProduct.getCellData("productName",4), excelAddProduct.getCellData("category",4), excelAddProduct.getCellData("unit",4), excelAddProduct.getCellData("weight",4), excelAddProduct.getCellData("tags",4), excelAddProduct.getCellData("unitPrice",4), excelAddProduct.getCellData("discountDate",4), excelAddProduct.getCellData("quantity",4), excelAddProduct.getCellData("description",4), excelAddProduct.getCellData("discount",4), excelAddProduct.getCellData("image",4), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 4, description = "Kiem tra them san pham moi khong hop le voi role admin")
    public void TC_AddProductVariantInvalidRoleAdmin() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductInvalidRoleAdmin(excelAddProduct.getCellData("productName",3), excelAddProduct.getCellData("category",3), excelAddProduct.getCellData("unit",3), excelAddProduct.getCellData("weight",3), excelAddProduct.getCellData("tags",3), excelAddProduct.getCellData("unitPrice",3), excelAddProduct.getCellData("discountDate",3), excelAddProduct.getCellData("quantity",3), excelAddProduct.getCellData("description",3), excelAddProduct.getCellData("discount",3), excelAddProduct.getCellData("image",3), excelAddProduct.getCellData("vat",1));
    }

    @Test(priority = 1, description = "Kiem tra them san pham moi co variant, co discount hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithHaveDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductVariantValidRoleAdmin(excelAddProduct.getCellData("productName",1), excelAddProduct.getCellData("category",1), excelAddProduct.getCellData("unit",1), excelAddProduct.getCellData("weight",1), excelAddProduct.getCellData("tags",1), excelAddProduct.getCellData("unitPrice",1), excelAddProduct.getCellData("discountDate",1), excelAddProduct.getCellData("quantity",1), excelAddProduct.getCellData("description",1), excelAddProduct.getCellData("discount",1), excelAddProduct.getCellData("image",1), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 2, description = "Kiem tra them san pham moi co variant, co discount khong hop le voi role admin")
    public void TC_AddProductVariantValidRoleAdminWithInvalidDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductVariantValidRoleAdmin(excelAddProduct.getCellData("productName",2), excelAddProduct.getCellData("category",2), excelAddProduct.getCellData("unit",2), excelAddProduct.getCellData("weight",2), excelAddProduct.getCellData("tags",2), excelAddProduct.getCellData("unitPrice",2), excelAddProduct.getCellData("discountDate",2), excelAddProduct.getCellData("quantity",2), excelAddProduct.getCellData("description",2), excelAddProduct.getCellData("discount",2), excelAddProduct.getCellData("image",2), excelAddProduct.getCellData("vat",1));
    }

    @Test(priority = 5, description = "Kiem tra them san pham moi khong co variant, co discount hop le voi role seller")
    public void TC_AddProductNoVariantValidRoleSellerWithHaveDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductNoVariantValidRoleSeller(excelAddProduct.getCellData("productName",1), excelAddProduct.getCellData("category",1), excelAddProduct.getCellData("unit",1), excelAddProduct.getCellData("weight",1), excelAddProduct.getCellData("tags",1), excelAddProduct.getCellData("unitPrice",1), excelAddProduct.getCellData("discountDate",1), excelAddProduct.getCellData("quantity",1), excelAddProduct.getCellData("description",1), excelAddProduct.getCellData("discount",1), excelAddProduct.getCellData("image",1), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 6, description = "Kiem tra them san pham moi khong co variant, co discount khong hop le voi role seller")
    public void TC_AddProductNoVariantValidRoleSellerWithInvalidDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductNoVariantValidRoleSeller(excelAddProduct.getCellData("productName",2), excelAddProduct.getCellData("category",2), excelAddProduct.getCellData("unit",2), excelAddProduct.getCellData("weight",2), excelAddProduct.getCellData("tags",2), excelAddProduct.getCellData("unitPrice",2), excelAddProduct.getCellData("discountDate",2), excelAddProduct.getCellData("quantity",2), excelAddProduct.getCellData("description",2), excelAddProduct.getCellData("discount",2), excelAddProduct.getCellData("image",2), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 7, description = "Kiem tra them san pham moi khong co variant, khong co discount voi role seller")
    public void TC_AddProductNoVariantValidRoleSellerWithNoDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductNoVariantValidRoleSeller(excelAddProduct.getCellData("productName",4), excelAddProduct.getCellData("category",4), excelAddProduct.getCellData("unit",4), excelAddProduct.getCellData("weight",4), excelAddProduct.getCellData("tags",4), excelAddProduct.getCellData("unitPrice",4), excelAddProduct.getCellData("discountDate",4), excelAddProduct.getCellData("quantity",4), excelAddProduct.getCellData("description",4), excelAddProduct.getCellData("discount",4), excelAddProduct.getCellData("image",4), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 5, description = "Kiem tra them san pham moi co variant, co discount hop le voi role seller")
    public void TC_AddProductVariantValidRoleSellerWithHaveDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductVariantValidRoleSeller(excelAddProduct.getCellData("productName",1), excelAddProduct.getCellData("category",1), excelAddProduct.getCellData("unit",1), excelAddProduct.getCellData("weight",1), excelAddProduct.getCellData("tags",1), excelAddProduct.getCellData("unitPrice",1), excelAddProduct.getCellData("discountDate",1), excelAddProduct.getCellData("quantity",1), excelAddProduct.getCellData("description",1), excelAddProduct.getCellData("discount",1), excelAddProduct.getCellData("image",1), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 6, description = "Kiem tra them san pham moi co variant, co discount khong hop le voi role seller")
    public void TC_AddProductVariantValidRoleSellerWithInvalidDiscount() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductVariantValidRoleSeller(excelAddProduct.getCellData("productName",2), excelAddProduct.getCellData("category",2), excelAddProduct.getCellData("unit",2), excelAddProduct.getCellData("weight",2), excelAddProduct.getCellData("tags",2), excelAddProduct.getCellData("unitPrice",2), excelAddProduct.getCellData("discountDate",2), excelAddProduct.getCellData("quantity",2), excelAddProduct.getCellData("description",2), excelAddProduct.getCellData("discount",2), excelAddProduct.getCellData("image",2), excelAddProduct.getCellData("vat",1));
    }
    @Test(priority = 8, description = "Kiem tra them san pham moi khong hop le voi role seller")
    public void TC_AddProductVariantInvalidRoleSeller() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
        getAddProductPage().addProductInvalidRoleSeller(excelAddProduct.getCellData("productName",3), excelAddProduct.getCellData("category",3), excelAddProduct.getCellData("unit",3), excelAddProduct.getCellData("weight",3), excelAddProduct.getCellData("tags",3), excelAddProduct.getCellData("unitPrice",3), excelAddProduct.getCellData("discountDate",3), excelAddProduct.getCellData("quantity",3), excelAddProduct.getCellData("description",3), excelAddProduct.getCellData("discount",3), excelAddProduct.getCellData("image",3), excelAddProduct.getCellData("vat",1));
    }


    //    @Test(priority = 1, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi khong co variant hop le voi role admin")
//    public void TC_AddProductNoVariantValidRoleAdmin(Hashtable<String, String> data) {
//        excelLogin = new ExcelHelper();
//        excelAddProduct = new ExcelHelper();
//        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
//        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
//        getAddProductPage().addProductNoVariantValidRoleAdmin(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 1, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi co variant hop le voi role admin")
//    public void TC_AddProductVariantValidRoleAdmin(Hashtable<String, String> data) {
//        excelLogin = new ExcelHelper();
//        excelAddProduct = new ExcelHelper();
//        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
//        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
//        getAddProductPage().addProductVariantValidRoleAdmin(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 5, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi khong co variant hop le voi role seller")
//    public void TC_AddProductNoVariantValidRoleSeller(Hashtable<String, String> data) {
//        excelLogin = new ExcelHelper();
//        excelAddProduct = new ExcelHelper();
//        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
//        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
//        getAddProductPage().addProductNoVariantValidRoleSeller(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }
//    @Test(priority = 5, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class, description = "Kiem tra them san pham moi co variant hop le voi role seller")
//    public void TC_AddProductVariantValidRoleSeller(Hashtable<String, String> data) {
//        excelLogin = new ExcelHelper();
//        excelAddProduct = new ExcelHelper();
//        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
//        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getLoginPage().loginSuccessWithSellerAccount("dungtest@yopmail.com", "123456");
//        getAddProductPage().addProductVariantValidRoleSeller(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"), data.get("vat"));
//    }

}


