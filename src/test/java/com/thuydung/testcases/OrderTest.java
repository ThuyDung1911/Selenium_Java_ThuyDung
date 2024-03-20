package com.thuydung.testcases;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.common.BaseTest;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    public ExcelHelper excel;

    // Add order success
    @Test(priority = 1)
    public void testOrderProducts() {
        excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getOrderPage().order("Giao hàng càng sớm càng tốt", excel.getCellData("email", 4), excel.getCellData("password", 4));
    }
}
