package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.utils.JiraCreateIssue;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {
    //Search product have result
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 1)
    public void TC_SearchProductHaveResult() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "SearchProduct");
        getDashboardPage().testSearchProductHaveResult(excel.getCellData("keySearchProduct", 1));
    }
    //Search product have not result
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 2)
    public void TC_SearchProductHaveNotResult() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "SearchProduct");
        getDashboardPage().testSearchProductHaveNotResult(excel.getCellData("keySearchProduct", 2));
    }
    //Search tag contain keySearchProduct
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 3)
    public void TC_SearchSuggestionTagContainKeySearchProduct() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "SearchProduct");
        getDashboardPage().testSearchSuggestionTagContainKeySearchProduct(excel.getCellData("keySearchProduct", 3));
    }
    //Search category contain keySearchProduct
    @JiraCreateIssue(isCreateIssue = false)
    @Test(priority = 4)
    public void TC_SearchSuggestionCategoryContainKeySearchProduct() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/DataTestCMS.xlsx", "SearchProduct");
        getDashboardPage().testSearchSuggestionCategoryContainKeySearchProduct(excel.getCellData("keySearchProduct", 4));
    }

}
