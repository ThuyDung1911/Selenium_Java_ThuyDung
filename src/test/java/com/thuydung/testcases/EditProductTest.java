package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class EditProductTest extends BaseTest {

    //Edit product valid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Edit product valid")
    public void TC_EditProductValid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));

        excel.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getEditProductPage().editProductValid(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("weight", 1), excel.getCellData("tags", 1), excel.getCellData("unitPrice", 1), excel.getCellData("discountDate", 1), excel.getCellData("quantity", 1), excel.getCellData("description", 1), excel.getCellData("discount", 1), excel.getCellData("image", 1), excel.getCellData("vat", 1));
    }
    //Edit product invalid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Edit product invalid")
    public void TC_EditProductInvalid() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));

        excel.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getEditProductPage().editProductInvalid(excel.getCellData("productName", 4), excel.getCellData("category", 4), excel.getCellData("unit", 4), excel.getCellData("weight", 4), excel.getCellData("tags", 4), excel.getCellData("unitPrice", 4), excel.getCellData("discountDate", 4), excel.getCellData("quantity", 4), excel.getCellData("description", 4), excel.getCellData("discount", 4), excel.getCellData("image", 4));
    }

}
