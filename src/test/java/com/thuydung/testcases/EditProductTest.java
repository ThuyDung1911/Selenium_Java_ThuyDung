package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class EditProductTest extends BaseTest {

    //Edit product valid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1, description = "Kiem tra sua san pham thanh cong voi role admin")
    public void TC_EditProductValidWithRoleAdmin() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));
        excel.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getEditProductPage().editProductValid(excel.getCellData("productName", 1), excel.getCellData("category", 1), excel.getCellData("unit", 1), excel.getCellData("description", 1));
    }

    //Edit product invalid
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2, description = "Kiem tra sua san pham that bai voi role admin")
    public void TC_EditProductInvalidWithRoleAdmin() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessAdminPage(excel.getCellData("email", 5), excel.getCellData("password", 5));

        excel.setExcelFile("DataTest/EditProduct.xlsx", "EditProduct");
        getEditProductPage().editProductInvalid(excel.getCellData("productName", 4), excel.getCellData("category", 4), excel.getCellData("unit", 4), excel.getCellData("description", 4));
    }

}
