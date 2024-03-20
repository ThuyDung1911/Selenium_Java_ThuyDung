package com.thuydung.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // khởi tạo drivers nạp nhiều luồng với dataType = Webdriver, ThreadLoal chỉ là nơi lưu thôi

    private DriverManager() {
        super();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }
}

