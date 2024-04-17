package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ProductInfoTest extends BaseTest {
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1)
    public void TC_GetProductInfo() {
        getLoginPage().openHomePage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("DataTest/GetProductInfo.xlsx", "ProductInfo");

        ArrayList<String> productInfo = getProductInfoPage().productInfo(PropertiesHelper.getValue("product_P01"));
        int lastRow = excelHelper.getLastRowNum();
        int newRow = lastRow + 1;
        excelHelper.setCellData(String.valueOf(newRow), newRow, 0);
        for (int i = 0; i < productInfo.size(); i++) {
            excelHelper.setCellData(productInfo.get(i), newRow, i + 1);
        }
    }
}
