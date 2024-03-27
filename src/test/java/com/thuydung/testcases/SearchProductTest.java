package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {
    //Search product have result
    @Test(priority = 1)
    public void testSearchProductHaveResult() {
        getDashboardPage().testSearchProductHaveResult("Apple");
        //getDashboardPage().verifySearchProduct("Apple");
    }
}
