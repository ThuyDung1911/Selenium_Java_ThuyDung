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
    @Test(priority = 2)
    public void testAddProductInvalid() {
        excelLogin = new ExcelHelper();
        excelAddProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getAddProductPage().addProductInvalid(excelAddProduct.getCellData("productName",3), excelAddProduct.getCellData("category",3), excelAddProduct.getCellData("unit",3), excelAddProduct.getCellData("weight",3), excelAddProduct.getCellData("tags",3), excelAddProduct.getCellData("unitPrice",3), excelAddProduct.getCellData("discountDate",3), excelAddProduct.getCellData("quantity",3), excelAddProduct.getCellData("description",3), excelAddProduct.getCellData("discount",3), excelAddProduct.getCellData("image",3));

    }
    @Test(priority = 3)
    public void verifyAddProduct() {
        excelAddProduct = new ExcelHelper();
        excelAddProduct.setExcelFile("DataTest/AddProduct.xlsx", "AddProduct");
        getAddProductPage().verifyNewProduct("Gio qua Tet Thuy Dung SWXRLTRP", excelAddProduct.getCellData("category",2), excelAddProduct.getCellData("unit",2), excelAddProduct.getCellData("unitPrice",2), excelAddProduct.getCellData("discountDate",2), excelAddProduct.getCellData("quantity",2), excelAddProduct.getCellData("description",2), excelAddProduct.getCellData("discount",2));
    }

}


