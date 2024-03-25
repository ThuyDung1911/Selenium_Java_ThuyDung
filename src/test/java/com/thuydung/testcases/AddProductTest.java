package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.dataproviders.DataProviderAddProduct;
import com.thuydung.helpers.ExcelHelper;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class AddProductTest extends BaseTest {
    ExcelHelper excelLogin;
    ExcelHelper excelAddProduct;

    //Add new product valid in Admin page (Admin role)
    @Test(priority = 1, dataProvider = "data_provider_add_product", dataProviderClass = DataProviderAddProduct.class)
    public void testAddProductValid(Hashtable<String, String> data) {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductValid(data.get("productName"), data.get("category"), data.get("unit"), data.get("weight"), data.get("tags"), data.get("unitPrice"), data.get("discountDate"), data.get("quantity"), data.get("description"), data.get("discount"), data.get("image"));
    }
    //Add new product invalid
//    public void testAddProductInvalid(String productName, String category, String unit, String weight, String tags, String unitPrice, String discountDate, String quantity, String description, String discount, String imgName) {
//        excelLogin = new ExcelHelper();
//        excelAddProduct = new ExcelHelper();
//        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
//        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
//        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
//        getAddProductPage().addProductInvalid(excelAddProduct.getCellData("productName"));
//
//    }

}


