package com.thuydung.dataproviders;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderAddProductToCart {
    @DataProvider(name = "data_provider_add_product_to_cart")
    public Object[][] dataAddProductToCart() {
        ExcelHelper excelHelpers = new ExcelHelper();
//        Object[][] data = excelHelpers.getExcelData(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct");
        Object[][] data = excelHelpers.getDataHashTable(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct", 1, 2);
        return data;
    }
}
