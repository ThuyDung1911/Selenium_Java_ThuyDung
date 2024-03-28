package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class EditProductTest extends BaseTest {
    ExcelHelper excelLogin;
    ExcelHelper excelEditProduct;

    //Edit product valid
    @Test(priority = 2)
    public void testEditProductValid() {
        excelLogin = new ExcelHelper();
        excelEditProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelEditProduct.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getEditProductPage().editProductValid(excelEditProduct.getCellData("productName", 1), excelEditProduct.getCellData("category", 1), excelEditProduct.getCellData("unit", 1), excelEditProduct.getCellData("weight", 1), excelEditProduct.getCellData("tags", 1), excelEditProduct.getCellData("unitPrice", 1), excelEditProduct.getCellData("discountDate", 1), excelEditProduct.getCellData("quantity", 1), excelEditProduct.getCellData("description", 1), excelEditProduct.getCellData("discount", 1), excelEditProduct.getCellData("image", 1));
    }
    //Edit product invalid
    @Test(priority = 1)
    public void testEditProductInvalid() {
        excelLogin = new ExcelHelper();
        excelEditProduct = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelEditProduct.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getLoginPage().loginSuccessAdminPage(excelLogin.getCellData("email", 5), excelLogin.getCellData("password", 5));
        getEditProductPage().editProductInvalid(excelEditProduct.getCellData("productName", 4), excelEditProduct.getCellData("category", 4), excelEditProduct.getCellData("unit", 4), excelEditProduct.getCellData("weight", 4), excelEditProduct.getCellData("tags", 4), excelEditProduct.getCellData("unitPrice", 4), excelEditProduct.getCellData("discountDate", 4), excelEditProduct.getCellData("quantity", 4), excelEditProduct.getCellData("description", 4), excelEditProduct.getCellData("discount", 4), excelEditProduct.getCellData("image", 4));
    }

}
