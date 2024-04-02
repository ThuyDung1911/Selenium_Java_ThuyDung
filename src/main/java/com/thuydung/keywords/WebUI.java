package com.thuydung.keywords;

import com.aventstack.extentreports.Status;
import com.thuydung.constants.ConfigData;
import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.reports.AllureManager;
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
import java.text.Normalizer;
import java.time.Duration;
import java.util.List;

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

    @Step("Click element: {0}")
    public static void clickElement(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        DriverManager.getDriver().findElement(by).click();
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        LogUtils.info("Click element: " + by.toString());
        //AllureReportManager.saveTextLog("Click element: " + by.toString());
    }

    @Step("Click element: {0}")
    public static void clickElement(By by, long timeout) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        getWebElement(by).click();
        LogUtils.info("Click element: " + by.toString());
        //AllureReportManager.saveTextLog("Click element: " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                softAssert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                softAssert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify {1} is display correct on {0}")
    public static void verifySoftAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display correct on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display correct on " + by.toString());
    }

    @Step("Verify {1} is display correct on {0}")
    public static void verifyAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display correct on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display correct on " + by.toString());
    }

    @Step("Verify {1} is display correct on {0}")
    public static void verifyAssertTrueEqualMessageHTML(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute("validationMessage").equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display correct on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display correct on " + by.toString());
    }

    @Step("Verify {1} is display incorrect on {0}")
    public static void verifySoftAssertFalseEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        softAssert.assertFalse(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display incorrect on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display incorrect on " + by.toString());
    }

    @Step("Verify {1} is display incorrect on {0}")
    public static void verifyAssertFalseEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        Assert.assertFalse(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display incorrect on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display incorrect on " + by.toString());
    }


    @Step("Verify attribute {1} is contains {2} on {0}")
    public static void verifySoftAssertTrueContain(By by, String attribute, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify contain: " + verifyText);
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify " + attribute + " is contains " + verifyText + " on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + attribute + " is contains " + verifyText + " on " + by.toString());
    }

    @Step("Verify text is contains {1} on {0}")
    public static void verifyAssertTrueTextContain(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify contain: " + verifyText);
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify text is contains " + verifyText + " on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify text is contains " + verifyText + " on " + by.toString());
    }

    public static String removeAccent(String text) {
        // Sử dụng Normalizer với Form.NFD để chuyển chuỗi về dạng chuẩn
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Loại bỏ các ký tự không phải là chữ cái hoặc số và trả về kết quả
        return normalizedText.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    @Step("Verify text is contains {1} on {0} without accent")
    public static void verifyAssertTrueTextContainWithoutAccent(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify text " + by + " is contain: " + verifyText);
        Assert.assertTrue(removeAccent(DriverManager.getDriver().findElement(by).getText()).toLowerCase().contains(verifyText), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify text is contains " + verifyText + " on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify text is contains " + verifyText + " on " + by.toString());
    }

    @Step("Verify {0} is displayed")
    public static void verifySoftAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify " + by + " is displayed");
            softAssert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureReportManager.saveTextLog("Verify " + by + " is displayed");
            ExtentTestManager.logMessage("Verify " + by + " is displayed");
        } catch (NoSuchElementException e) {
            softAssert.fail(message);
        }
    }

    @Step("Verify {0} is displayed")
    public static void verifyAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify " + by + " is displayed");
            Assert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureReportManager.saveTextLog("Verify " + by + " is displayed");
            ExtentTestManager.logMessage("Verify " + by + " is displayed");
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }

    @Step("Message HTML with value {0} invalid is displayed")
    public static void checkHTML5MessageWithValueInvalid(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Message HTML with value " + by + " invalid is displayed");
            Assert.assertFalse((Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", DriverManager.getDriver().findElement(by)), message);
            if (ConfigData.HIGHLIGHT_ELEMENT == true) {
                highLightElement(by);
            }
            //AllureReportManager.saveTextLog("Message HTML with value " + by + " invalid is displayed");
            ExtentTestManager.logMessage("Message HTML with value " + by + " invalid is displayed");
        } catch (NoSuchElementException e) {
            Assert.fail(message);
        }
    }


    @Step("Verify {0} is not displayed")
    public static void verifyAssertFalseIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify " + by + " is not displayed");
        Assert.assertFalse(DriverManager.getDriver().findElement(by).isDisplayed(), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify " + by + " is not displayed");
        ExtentTestManager.logMessage("Verify " + by + " is not displayed");

    }

    @Step("Verify attribute {1} is contains {2} on {0}")
    public static void verifySoftAssertTrueAttribute(By by, String attribute, String expectedValue, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        softAssert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).trim().equals(expectedValue), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        ExtentTestManager.logMessage("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
    }

    @Step("Verify attribute {1} is contains {2} on {0}")
    public static void verifyAssertTrueAttribute(By by, String attribute, String expectedValue, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).trim().equals(expectedValue), message);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        //AllureReportManager.saveTextLog("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        ExtentTestManager.logMessage("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
    }

    public static void setValue(By by, String value) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("\"" + by + ".setAttribute('value'," + value + ")");
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    @Step("Clear text on: {0}")
    public static void clearText(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        WebUI.clickElement(by);
        DriverManager.getDriver().findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        //AllureReportManager.saveTextLog("Clear text on: " + by.toString());
        LogUtils.info("Clear text on: " + by.toString());
        ExtentTestManager.logMessage("Clear text on: " + by.toString());
    }

    @Step("Key down Enter")
    public static void keydownEnter() {
        action.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
        //AllureReportManager.saveTextLog("Key down Enter");
        LogUtils.info("Key down Enter");
        ExtentTestManager.logMessage("Key down Enter");
    }
    @Step("Set text {1} on element {0} and key down backspace")
    public static void setTextAndBackspace(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, Keys.BACK_SPACE);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        //AllureReportManager.saveTextLog("Set text " + value + " on " + by.toString() + " and key down backspace");
        LogUtils.info("Set text " + value + " on " + by.toString() + " and key down backspace");
    }

    @Step("Key down Backspace")
    public static void keydownBackspace() {
        action.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).build().perform();
        //AllureReportManager.saveTextLog("Key down Backspace");
        LogUtils.info("Key down Backspace");
        ExtentTestManager.logMessage("Key down Backspace");
    }

    @Step("Set text from split {1} on element {0}")
    public static void setTextFromSplitString(By by, String text) {
        WebUI.clearText(by);
        List<String> textSplit = List.of(text.split(" "));
        System.out.println("Length of text: " + textSplit.size());
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

    @Step("Set text {1} on element {0}")
    public static void setTextAndClear(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        clearText(by);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        LogUtils.info("Set text: " + value + " on " + by);
    }

    @Step("Set text {1} on element {0}")
    public static void setText(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        LogUtils.info("Set text: " + value + " on " + by);
    }

    @Step("Set text on text box and press key")
    public static void setTextAndClear(By by, String value, Keys keys) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, keys);
        //waitForElementVisible(by).sendKeys(value, keys);
        LogUtils.info("Set text " + value + " on " + by + " and press key " + keys.name());

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Set text " + value + " on " + by + " and press key " + keys.name());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by + " and press key " + keys.name());
    }

    @Step("Set text {1} on element {0} and key down enter")
    public static void setTextEnter(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        getWebElement(by).sendKeys(value, Keys.ENTER);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        //AllureReportManager.saveTextLog("Set text " + value + " on " + by.toString() + " and key down enter");
        LogUtils.info("Set text " + value + " on " + by.toString() + " and key down enter");
    }

    public static void waitForElementClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementInVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Step("Wait until the element {0} is visible")
    public static WebElement waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Wait until the element " + by + " is visible");
        //AllureReportManager.saveTextLog("Wait until the element " + by + " is visible");
        LogUtils.info("Wait until the element " + by + " is visible");
        return getWebElement(by);
    }
    public static boolean checkElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.isDisplayed();
    }

    @Step("Wait until the element {0} is invisible")
    public static void waitForElementInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Wait until the element " + by + " is invisible");
        //AllureReportManager.saveTextLog("Wait until the element " + by + " is invisible");
        LogUtils.info("Wait until the element " + by + " is invisible");
    }

    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        LogUtils.info("Open URL: " + URL);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + URL);
        waitForPageLoaded();
        //AllureReportManager.saveTextLog("Open URL: " + URL);
    }

    @Step("Get text of element {0}")
    public static String getElementText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElement(by);
        }
        LogUtils.info("Get text of element " + by);
        LogUtils.info("TEXT: " + getWebElement(by).getText());
        return getWebElement(by).getText(); // trả về 1 giá trị  String
    }

    @Step("Get attribute {1} of element {0}")
    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        if (ConfigData.HIGHLIGHT_ELEMENT == true) {
            highLightElementFull(by);
        }
        LogUtils.info("Get attribute of element " + by);
        LogUtils.info("Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    @Step("Check element exist {0}")
    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        sleep(3);
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            LogUtils.info("checkElementExist: " + true + " ---" + by);
            return true;
        } else {
            LogUtils.info("checkElementExist: " + false + " ---" + by);
            return false;
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            softAssert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("Element not exist. " + by.toString());
            softAssert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            softAssert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
            softAssert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    @Step("Verify result {1} is correct")
    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        softAssert.assertEquals(actual, expected, "Fail, NOT match: " + actual.toString() + " != " + expected.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + expected + " is correct");
        LogUtils.info("Verify result: " + expected + " is correct");
    }

    @Step("Verify result {1} is correct")
    public static void verifySoftAssertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        softAssert.assertEquals(actual, expected, message);
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + expected + " is correct");
        LogUtils.info("Verify result: " + expected + " is correct");
    }

    @Step("Verify result {1} is correct")
    public static void verifyAssertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertEquals(actual, expected, message);
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + expected + " is correct");
        LogUtils.info("Verify result: " + expected + " is correct");
    }

    @Step("Verify result {0} is contain {1}")
    public static void verifyAssertContain(String actual, String key, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertTrue(actual.contains(key), message);
        //Assert.assertEquals(actual, key, message);
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + actual + " is contain " + key);
        LogUtils.info("Verify result: " + actual + " is contain " + key);
    }

    @Step("Verify result {0} is equal {1}")
    public static void verifyAssertEqual(String actual, String key, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        //Assert.assertTrue(actual.equals(key), message);
        Assert.assertEquals(actual, key, message);
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + actual + " is equal " + key);
        LogUtils.info("Verify result: " + actual + " is equal " + key);
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
                softAssert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
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
                softAssert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
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
                softAssert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
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

}
