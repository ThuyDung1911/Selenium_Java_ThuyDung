package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {
    ExcelHelper excelLogin;
    //Search product have result
    @Test(priority = 1)
    public void testSearchProductHaveResult() {
        getDashboardPage().testSearchProductHaveResult("a");
    }
    //Search product have not result
    @Test(priority = 2)
    public void testSearchProductHaveNotResult() {
        getDashboardPage().testSearchProductHaveNotResult("abcd");
    }
    //Search tag contain keySearchProduct
    @Test(priority = 3)
    public void testSearchSuggestionTagContainKeySearchProduct() {
        getDashboardPage().testSearchSuggestionTagContainKeySearchProduct("a");
    }
    //Search category contain keySearchProduct
    @Test(priority = 4)
    public void testSearchSuggestionCategoryContainKeySearchProduct() {
        getDashboardPage().testSearchSuggestionCategoryContainKeySearchProduct("a");
    }

}
