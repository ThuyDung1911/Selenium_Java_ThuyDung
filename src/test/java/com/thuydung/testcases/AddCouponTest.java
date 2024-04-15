package com.thuydung.testcases;

import com.thuydung.common.BaseTest;
import org.testng.annotations.Test;

public class AddCouponTest extends BaseTest {

    @Test(priority = 1)
    public void TC_AddCouponValid() {
        getCouponPage().addCouponValid("COUPON2024", "100000", "10000", "5000000", "04/09/2024 - 06/09/2024");
    }
}
