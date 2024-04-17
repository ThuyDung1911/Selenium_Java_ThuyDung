package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {
    //Search product have result
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1)
    public void TC_SearchProductHaveResult() {
        getDashboardPage().testSearchProductHaveResult("gio qua tet hdlvt");
    }
    //Search product have not result
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2)
    public void TC_SearchProductHaveNotResult() {
        getDashboardPage().testSearchProductHaveNotResult("Ấbcd");
    }
    //Search tag contain keySearchProduct
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3)
    public void TC_SearchSuggestionTagContainKeySearchProduct() {
        getDashboardPage().testSearchSuggestionTagContainKeySearchProduct("a");
    }
    //Search category contain keySearchProduct
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4)
    public void TC_SearchSuggestionCategoryContainKeySearchProduct() {
        getDashboardPage().testSearchSuggestionCategoryContainKeySearchProduct("a");
    }

}
