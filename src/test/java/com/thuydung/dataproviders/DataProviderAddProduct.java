package com.thuydung.dataproviders;

import com.thuydung.helpers.ExcelHelper;
import com.thuydung.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderAddProduct {
    @DataProvider(name = "data_provider_add_product")
    public Object[][] dataAddProduct() {
        ExcelHelper excelHelpers = new ExcelHelper();
//        Object[][] data = excelHelpers.getExcelData(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct");
        Object[][] data = excelHelpers.getDataHashTable(SystemHelper.getCurrentDir() + "DataTest/AddProduct.xlsx", "AddProduct", 1, 2);
        return data;
    }


}
