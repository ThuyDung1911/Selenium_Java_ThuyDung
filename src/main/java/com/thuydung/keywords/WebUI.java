package com.thuydung.keywords;

import com.aventstack.extentreports.Status;
import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.reports.ExtentTestManager;
import com.thuydung.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class WebUI {
    private static Actions action = new Actions(DriverManager.getDriver());
    private final static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("TIMEOUT"));
    private final static int STEP_TIME = Integer.parseInt(PropertiesHelper.getValue("STEP_TIME"));
    private final static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
    private static SoftAssert softAssert = new SoftAssert();

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopSoftAssertAll() {
        softAssert.assertAll();
    }

    @Step("Nhấn vào element: {0}")
    public static void clickElement(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        waitForJQueryLoad();
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        DriverManager.getDriver().findElement(by).click();
        ExtentTestManager.logMessage(Status.PASS, "Nhấn vào element: " + by);
        LogUtils.info("Nhấn vào element: " + by.toString());
        //AllureManager.saveTextLog("Nhấn vào element: " + by.toString());
    }

    @Step("Nhấn vào element: {0}")
    public static void clickElement(By by, long timeout) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        getWebElement(by).click();
        LogUtils.info("Nhấn vào element: " + by.toString());
        //AllureManager.saveTextLog("Nhấn vào element: " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Nhấn vào element: " + by);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    @Step("Di chuột đến element {0}")
    public static void scrollToElementToTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Di chuột đến element " + by);
    }

    @Step("Di chuột đến element {0}")
    public static void scrollToElementToBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Di chuột đến element " + by);
    }

    @Step("Di chuột đến element {0}")
    public static void scrollToElementToTop(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Di chuột đến element " + element);
    }

    @Step("Di chuột đến element {0}")
    public static void scrollToElementToBottom(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        LogUtils.info("Di chuột đến element " + element);
    }

    @Step("Xác thực element hiển thị {0}")
    public static boolean verifyElementVisible(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Xác thực element hiển thị " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("Element không hiển thị. " + by);
                softAssert.fail("Element không hiển thị. " + by);
            } else {
                LogUtils.error(message + by);
                softAssert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Xác thực {1} hiển thị đúng với {0}")
    public static void verifySoftAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        boolean result = DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText);
        if (!result) {
            LogUtils.error("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
            ExtentTestManager.logMessage(Status.FAIL, "Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
            ExtentTestManager.addScreenShot(Status.FAIL, message);
//            AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
//            AllureManager.saveScreenshotPNG();
//            softAssert.fail("Sai, Không khớp: " + actual.toString() + " != " + expected.toString());
        } else {
            ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
            //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
            LogUtils.info("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
        }


//        LogUtils.info("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
//        //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
//        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
    }

    @Step("Xác thực {1} hiển thị đúng với {0}")
    public static void verifyAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        //Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        Assert.assertEquals(DriverManager.getDriver().findElement(by).getText().trim(), verifyText, message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
        //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
    }

    @Step("Xác thực {1} hiển thị đúng với {0}")
    public static void verifyAssertTrueEqualMessageHTML(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute("validationMessage").equals(verifyText), message);
        Assert.assertEquals(DriverManager.getDriver().findElement(by).getAttribute("validationMessage"), verifyText, message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
        //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị đúng với " + by.toString());
    }

    @Step("Xác thực {1} hiển thị không đúng với {0}")
    public static void verifySoftAssertFalseEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        softAssert.assertFalse(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
        //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
    }

    @Step("Xác thực {1} hiển thị không đúng với {0}")
    public static void verifyAssertFalseEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        Assert.assertFalse(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        LogUtils.info("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
        //AllureManager.saveTextLog("Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + verifyText + " hiển thị không đúng với " + by.toString());
    }


    @Step("Xác thực attribute {1} chứa {2} trong {0}")
    public static void verifySoftAssertTrueContain(By by, String attribute, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực chứa: " + verifyText);
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực " + attribute + " chứa " + verifyText + " trong " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực " + attribute + " chứa " + verifyText + " trong " + by.toString());
    }

    @Step("Xác thực text chứa {1} trong {0}")
    public static void verifyAssertTrueTextContain(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực chứa: " + verifyText);
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực text chứa " + verifyText + " trong " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực text chứa " + verifyText + " trong " + by.toString());
    }

    public static String removeAccent(String text) {
        // Sử dụng Normalizer với Form.NFD để chuyển chuỗi về dạng chuẩn
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Loại bỏ các ký tự không phải là chữ cái hoặc số và trả về kết quả
        return normalizedText.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    @Step("Xác thực text chứa {1} trong {0} without accent")
    public static void verifyAssertTrueTextContainWithoutAccent(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực text " + by + " chứa: " + verifyText);
        Assert.assertTrue(removeAccent(DriverManager.getDriver().findElement(by).getText()).toLowerCase().contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực text chứa " + verifyText + " trong " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực text chứa " + verifyText + " trong " + by.toString());
    }

    @Step("Xác thực {0} hiển thị")
    public static void verifySoftAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Xác thực " + by + " hiển thị");
            softAssert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureManager.saveTextLog("Xác thực " + by + " hiển thị");
            ExtentTestManager.logMessage("Xác thực " + by + " hiển thị");
        } catch (NoSuchElementException e) {
            softAssert.fail(message);
        }
    }

    @Step("Xác thực {0} hiển thị")
    public static void verifyAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Xác thực " + by + " hiển thị");
            Assert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureManager.saveTextLog("Xác thực " + by + " hiển thị");
            ExtentTestManager.logMessage("Xác thực " + by + " hiển thị");
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }

    @Step("Message HTML với giá trị {0} không hợp lệ hiển thị")
    public static void checkHTML5MessageWithValueInvalid(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Message HTML với giá trị " + by + " không hợp lệ hiển thị");
            Assert.assertFalse((Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", DriverManager.getDriver().findElement(by)), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureManager.saveTextLog("Message HTML với giá trị " + by + " không hợp lệ hiển thị");
            ExtentTestManager.logMessage("Message HTML với giá trị " + by + " không hợp lệ hiển thị");
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }


    @Step("Xác thực {0} không hiển thị")
    public static void verifyAssertFalseIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực " + by + " không hiển thị");
        Assert.assertFalse(DriverManager.getDriver().findElement(by).isDisplayed(), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực " + by + " không hiển thị");
        ExtentTestManager.logMessage("Xác thực " + by + " không hiển thị");

    }

    @Step("Xác thực attribute {1} chứa {2} trong {0}")
    public static void verifySoftAssertTrueAttribute(By by, String attribute, String expectedValue, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).trim().equals(expectedValue), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
        ExtentTestManager.logMessage("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
    }

    @Step("Xác thực attribute {1} chứa {2} trong {0}")
    public static void verifyAssertTrueAttribute(By by, String attribute, String expectedValue, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).trim().equals(expectedValue), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureManager.saveTextLog("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
        ExtentTestManager.logMessage("Xác thực attribute " + attribute + " chứa " + expectedValue + " trong " + by.toString());
    }

    public static void setValue(By by, String value) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("\"" + by + ".setAttribute('value'," + value + ")");
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    @Step("Clear text trên: {0}")
    public static void clearText(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        DriverManager.getDriver().findElement(by).clear();
//        WebUI.clickElement(by);
//        DriverManager.getDriver().findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        //AllureManager.saveTextLog("Clear text trên: " + by.toString());
        LogUtils.info("Clear text trên: " + by.toString());
        ExtentTestManager.logMessage("Clear text trên: " + by.toString());
    }
    @Step("Clear text trên: {0}")
    public static void clearTextWithCtrlA(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
//        DriverManager.getDriver().findElement(by).clear();
        WebUI.clickElement(by);
        DriverManager.getDriver().findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        //AllureManager.saveTextLog("Clear text trên: " + by.toString());
        LogUtils.info("Clear text trên: " + by.toString());
        ExtentTestManager.logMessage("Clear text trên: " + by.toString());
    }

    @Step("Ấn Enter")
    public static void keydownEnter() {
        action.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
        //AllureManager.saveTextLog("Ấn Enter");
        LogUtils.info("Ấn Enter");
        ExtentTestManager.logMessage("Ấn Enter");
    }

    @Step("Nhập text {1} trong element {0} và ấn nút backspace")
    public static void setTextAndBackspace(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, Keys.BACK_SPACE);
        ExtentTestManager.logMessage(Status.PASS, "Nhập text: " + value + " trong element " + by);
        //AllureManager.saveTextLog("Nhập text " + value + " trong " + by.toString() + " và ấn nút backspace");
        LogUtils.info("Nhập text " + value + " trong " + by.toString() + " và ấn nút backspace");
    }

    @Step("Ấn nút backspace")
    public static void keydownBackspace() {
        action.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).build().perform();
        //AllureManager.saveTextLog("Ấn nút backspace");
        LogUtils.info("Ấn nút backspace");
        ExtentTestManager.logMessage("Ấn nút backspace");
    }

    @Step("Nhập text từng từ {1} trong element {0}")
    public static void setTextFromSplitString(By by, String text) {
        WebUI.clearText(by);
        List<String> textSplit = List.of(text.split(" "));
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        for (int i = 0; i < textSplit.size(); i++) {
            if (i > 0) {
                WebUI.setText(by, " " + textSplit.get(i));
            } else {
                WebUI.setText(by, textSplit.get(i));
            }
            WebUI.sleep(1);
        }
    }

    @Step("Nhập text {1} trong element {0}")
    public static void setTextAndClear(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        clearTextWithCtrlA(by);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Nhập text: " + value + " trong element " + by);
        LogUtils.info("Nhập text: " + value + " trong " + by);
        //AllureManager.saveTextLog("Nhập text: " + value + " trong " + by);
    }

    @Step("Nhập text {1} trong element {0}")
    public static void setText(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Nhập text: " + value + " trong element " + by);
        LogUtils.info("Nhập text: " + value + " trong " + by);
        //AllureManager.saveTextLog("Nhập text: " + value + " trong " + by);
    }

    @Step("Nhập text trong textbox và ấn nút")
    public static void setTextAndClear(By by, String value, Keys keys) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, keys);
        //waitForElementVisible(by).sendKeys(value, keys);
        LogUtils.info("Nhập text " + value + " trong " + by + " và ấn nút " + keys.name());

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Nhập text " + value + " trong " + by + " và ấn nút " + keys.name());
        }
        //AllureManager.saveTextLog("Nhập text " + value + " trong " + by + " và ấn nút " + keys.name());
    }

    @Step("Nhập text {1} trong element {0} và ấn nút enter")
    public static void setTextEnter(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, Keys.ENTER);
        ExtentTestManager.logMessage(Status.PASS, "Nhập text: " + value + " trong element " + by);
        //AllureManager.saveTextLog("Nhập text " + value + " trong " + by.toString() + "  và ấn nút enter");
        LogUtils.info("Nhập text " + value + " trong " + by.toString() + " và ấn nút enter");
    }

    public static void waitForElementClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementInVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Step("Chờ cho tới khi element {0} hiển thị")
    public static WebElement waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Chờ cho tới khi element " + by + " hiển thị");
        //AllureManager.saveTextLog("Chờ cho tới khi element " + by + " hiển thị");
        LogUtils.info("Chờ cho tới khi element " + by + " hiển thị");
        return getWebElement(by);
    }

    public static boolean checkElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.isDisplayed();
    }

    @Step("Chờ cho tới khi element {0} không hiển thị")
    public static void waitForElementInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Chờ cho tới khi element " + by + " không hiển thị");
        //AllureManager.saveTextLog("Chờ cho tới khi element " + by + " không hiển thị");
        LogUtils.info("Chờ cho tới khi element " + by + " không hiển thị");
    }

    @Step("Truy cập URL: {0}")
    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        LogUtils.info("Truy cập URL: " + URL);
        ExtentTestManager.logMessage(Status.PASS, "Truy cập URL: " + URL);
        waitForPageLoaded();
        //AllureManager.saveTextLog("Truy cập URL: " + URL);
    }

    @Step("Lấy text của element {0}")
    public static String getElementText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Lấy text của element " + by);
        LogUtils.info("TEXT: " + getWebElement(by).getText());
        return getWebElement(by).getText(); // trả về 1 giá trị String
    }

    @Step("Lấy attribute {1} của element {0}")
    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Lấy attribute của element " + by);
        LogUtils.info("Giá trị attribute: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    @Step("Kiểm tra element tồn tại {0}")
    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            LogUtils.info("Element tồn tại: " + true + " ---" + by);
            return true;
        } else {
            LogUtils.info("Element tồn tại: " + false + " ---" + by);
            return false;
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element không tồn tại. " + by.toString());
            softAssert.fail("Element không tồn tại. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element không tồn tại. " + by.toString());
            softAssert.fail("Element không tồn tại. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Hết thời gian chờ element sẵn sàng để click. " + by.toString());
            softAssert.fail("Hết thời gian chờ element sẵn sàng để click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Hết thời gian chờ element sẵn sàng để click. " + by.toString());
            softAssert.fail("Hết thời gian chờ element sẵn sàng để click. " + by.toString());
        }
    }

    @Step("Xác thực result {1} đúng")
    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        softAssert.assertEquals(actual, expected, "Sai, Không khớp: " + actual.toString() + " != " + expected.toString());
        ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + expected + " đúng");
        //AllureManager.saveTextLog("Xác thực result: " + expected + " đúng");
        LogUtils.info("Xác thực result: " + expected + " đúng");
    }

    @Step("Xác thực result {1} đúng")
    public static void verifySoftAssertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        boolean result = actual.equals(expected);
        softAssert.assertEquals(actual, expected, message);
        if (!result) {
            String message1 = "Sai, Không khớp: " + actual.toString() + " != " + expected.toString();
            LogUtils.error(message1);
            ExtentTestManager.logMessage(Status.FAIL, message1);
            ExtentTestManager.addScreenShot(Status.FAIL, message);
//            AllureManager.saveTextLog("Sai, Không khớp: " + actual.toString() + " != " + expected.toString());
//            AllureManager.saveScreenshotPNG();
            //softAssert.fail("Sai, Không khớp: " + actual.toString() + " != " + expected.toString());
        } else {
            ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + expected + " đúng");
            LogUtils.info("Xác thực result: " + expected + " đúng");
//            AllureManager.saveTextLog("Xác thực result: " + expected + " đúng");
        }

//        softAssert.assertEquals(actual, expected, message);
//        ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + expected + " đúng");
//        LogUtils.info("Xác thực result: " + expected + " đúng");
    }

    @Step("Xác thực result {1} đúng")
    public static void verifyAssertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertEquals(actual, expected, message);
        ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + expected + " đúng");
        LogUtils.info("Xác thực result: " + expected + " đúng");
        //AllureManager.saveTextLog("Xác thực result: " + expected + " đúng");
    }

    @Step("Xác thực result {0} chứa {1}")
    public static void verifyAssertContain(String actual, String key, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertTrue(actual.contains(key), message);
        //Assert.assertEquals(actual, key, message);
        ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + actual + " chứa " + key);
        LogUtils.info("Xác thực result: " + actual + " chứa " + key);
//        AllureManager.saveTextLog("Xác thực result: " + actual + " chứa " + key);
    }

    @Step("Xác thực result {0} bằng {1}")
    public static void verifyAssertEqual(String actual, String key, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        //Assert.assertTrue(actual.equals(key), message);
        Assert.assertEquals(actual, key, message);
        ExtentTestManager.logMessage(Status.PASS, "Xác thực result: " + actual + " bằng " + key);
        LogUtils.info("Xác thực result: " + actual + " bằng " + key);
//        AllureManager.saveTextLog("Xác thực result: " + actual + " bằng " + key);
    }


    /**
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                if (WebUI.checkElementExist(By.xpath("//*[contains(text(),'too long to response')]"))) {
                    JavascriptExecutor js2 = (JavascriptExecutor) DriverManager.getDriver();
                    js2.executeScript("location.reload()");
                }
                softAssert.fail("Hết thời gian cho trang load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            //LogUtils.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                softAssert.fail("Hết thời gian cho JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            LogUtils.info("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                softAssert.fail("Hết thời gian cho Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }

    }

    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Step("Press ENTER from keyboard")
    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            LogUtils.info("Press ENTER from keyboard");
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Step("Press ESC from keyboard")
    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            LogUtils.info("Press ESC from keyboard");
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Step("Press F11 from keyboard")
    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            LogUtils.info("Press F11 from keyboard");
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static WebElement highLightElementFull(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.backgroundColor='skyblue'", getWebElement(by));
            sleep(ConfigData.HIGHLIGHT_TIMEOUT);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElementFull2(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.backgroundColor='green'", getWebElement(by));
            sleep(ConfigData.HIGHLIGHT_TIMEOUT);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='5px solid blue'", getWebElement(by));
            sleep(ConfigData.HIGHLIGHT_TIMEOUT);
        }
        return getWebElement(by);
    }

    public static BigDecimal stringToBigDecimal(String numberString) {
        return new BigDecimal(numberString);
    }

    public static long randomNumber(int minNumDigits, int maxNumDigits) {
        long min = (long) Math.pow(10, minNumDigits - 1);
        long max = (long) Math.pow(10, maxNumDigits) - 1;
        Random random = new Random();
        long randomNumber = min + random.nextInt((int) (max - min + 1));
        return randomNumber;
    }

    /**
     * Chuyển đổi tiền tệ sang BigDecimal
     *
     * @param amount String: Số tiền
     * @return BigDecimal: Số tiền
     */
    public static BigDecimal convertCurrencyToBigDecimal(String amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        try {
            Number number = format.parse(amount);
            return new BigDecimal(number.toString()).setScale(2, RoundingMode.HALF_UP);
        } catch (ParseException e) {
            e.printStackTrace();
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }


}
