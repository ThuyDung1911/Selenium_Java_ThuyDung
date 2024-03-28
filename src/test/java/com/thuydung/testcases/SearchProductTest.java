package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {
    //Search product have result
    @Test(priority = 1)
    public void testSearchProductHaveResult() {
        getDashboardPage().testSearchProductHaveResult("Gio qua Tet Thuy Dung CZRFANYB");
    }
    //Search product have not result
    @Test(priority = 2)
    public void testSearchProductHaveNotResult() {
        getDashboardPage().testSearchProductHaveNotResult("Ấbcd");
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
