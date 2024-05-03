package com.thuydung.pages;

import com.aventstack.extentreports.Status;
import com.thuydung.drivers.DriverManager;
import com.thuydung.keywords.WebUI;
import com.thuydung.reports.ExtentTestManager;
import com.thuydung.requests.Cart;
import com.thuydung.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static com.thuydung.pages.OrderPage.buttonAddToCart;
import static com.thuydung.pages.OrderPage.buttonCart;

public class CartPage extends CommonPage {
    static By messageCartEmptyInCartDetail = By.xpath("//section[@id='cart-summary']//h3[normalize-space()='Your Cart is empty']");
    public static By messageCartEmptyInCart = By.xpath("//div[@id='cart_items']//h3");
    static By inputQuantity = By.xpath("//input[@name='quantity']");
    static By viewProductNameInPopupAddSucceed = By.xpath("//div[@id='addToCart-modal-body']//h6");
    By messageUpdateCart = By.xpath("//span[@data-notify='message']");
    By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    static By totalPriceInDetailProduct = By.xpath("//strong[@id='chosen_price']");
    public static By subTotalPriceInCart = By.xpath("//div[@id='cart_items']//span[text()='Subtotal']/following-sibling::span");
    static By subPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Subtotal']/following-sibling::span");
    public By messageOverQuantityAvailable = By.xpath("//div[@id='addToCart-modal-body']//h3[normalize-space()='This item is out of stock!']");
    public static By elementProductNamesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[1]");
    public static By elementProductQuantitiesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[2]");
    public static By elementProductPricesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[3]");
    public static By elementProductNamesInCartDetail = By.xpath("//section[@id='cart-summary']//img/parent::span/following-sibling::span");
    public static By elementProductPricesInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Price']/following-sibling::span");
    public static By elementProductTaxesInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Tax']/following-sibling::span");
    public static By elementProductQuantitiesInCartDetail = By.xpath("//section[@id='cart-summary']//input[contains(@name,'quantity')]");
    public static By elementTotalPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Total']/following-sibling::span");
    By buttonBuyNow = By.xpath("//button[normalize-space()='Buy Now']");

    public boolean checkProductExistInCart(String productName) {
        WebUI.openURL("https://cms.anhtester.com/cart");
        By viewProductNameInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]");
        //check san pham o cart detail
        if (WebUI.checkElementExist(viewProductNameInCartDetail)) {
            WebUI.scrollToElement(viewProductNameInCartDetail);
            WebUI.hoverElement(viewProductNameInCartDetail);
            System.out.println("San pham " + productName + " co trong gio hang");
        } else {
            System.out.println("San pham " + productName + " khong co trong gio hang");
        }
        return WebUI.checkElementExist(viewProductNameInCartDetail);
    }

    /**
     * Add product to cart from product detail page
     *
     * @param productName Tên sản phẩm
     * @param quantity    Số lượng sản phẩm
     */
    public static void addProductToCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        //Tìm kiếm sản phẩm
        getDashboardPage().testSearchProductHaveResult(productName);
        //Kiem tra kết quả tìm kiếm có sản phẩm cần tìm không
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        if (listResultSearchProduct.isEmpty()) {
            ExtentTestManager.logMessage(Status.FAIL, "Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            LogUtils.info("Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            return;
        }
        productName = WebUI.getElementText(resultSearchProduct);
        WebUI.clickElement(resultSearchProduct);
        WebUI.waitForPageLoaded();
        By elementCheckVariantSize = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Size:')]");
        By elementCheckVariantColor = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Color:')]");
        By elementCheckVariantQuality = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Quality:')]");
        String valueVariantName = "";
        if (WebUI.checkElementExist(elementCheckVariantSize) || WebUI.checkElementExist(elementCheckVariantColor) || WebUI.checkElementExist(elementCheckVariantQuality)) {
            //variant da chon o trang detail product
            valueVariantName = AddProductPage.getVariantNameSelected();
        }

        //Lấy đơn giá bán sản phẩm ở trang chi tiết sản phẩm = tong tien khi mua 1 san pham (bao gom ca tax)
        BigDecimal productPrice = WebUI.convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct));

        //Lay số lượng sản phẩm ton kho
        int quantityAvailable = Integer.parseInt(WebUI.getElementText(ProductInfoPage.quantityProductAvailable));
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/cart");
        WebUI.waitForPageLoaded();
        Map<String, Cart> currentCart = getCartDetail();

        int quantityProductInCart;

        String key;
        if (valueVariantName != "") {
            key = productName + " - " + valueVariantName;
        } else {
            key = productName;
        }
        By viewProductNameInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]");
        //check so luong san pham o cart detail
        if (WebUI.checkElementExist(viewProductNameInCartDetail)) {
            WebUI.scrollToElement(viewProductNameInCartDetail);
            WebUI.hoverElement(viewProductNameInCartDetail);

            quantityProductInCart = currentCart.get(key).getQuantity();

        } else {
            quantityProductInCart = 0;
        }

        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(mainWindow);

        //Nhập số lượng sản phẩm
        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailable) {
            System.out.println("Số lượng sản phẩm tồn kho không đủ. Không thể thêm sản phẩm vào giỏ hàng với số lượng: " + quantity);
            return;
        }
        WebUI.setTextAndClear(inputQuantity, quantity);
        WebUI.clickElement(totalPriceInDetailProduct);
        WebUI.waitForJQueryLoad();
        //Check tổng tiền sản phẩm trong trang chi tiết sản phẩm
        BigDecimal valueTotalPriceInDetailProduct = convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct)); //Tien hien thi tren trang detail product
        BigDecimal checkTotalPriceCheck = productPrice.multiply(WebUI.stringToBigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP); //Tien tinh dua tren don gia va so luong
        WebUI.verifyAssertEquals(valueTotalPriceInDetailProduct, checkTotalPriceCheck, "Tổng tiền sản phẩm không đúng");

        //Thêm sản phẩm vào giỏ hàng
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);

        WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Thêm vào giỏ hàng không thành công");
        WebUI.verifyAssertTrueTextContain(viewProductNameInPopupAddSucceed, productName, "Tên sản phẩm không đúng");
        WebUI.clickElement(OrderPage.buttonCloseAddToCartMessage);

        //Kiểm tra sản phẩm đã được thêm vào giỏ hàng
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/cart");
        WebUI.waitForPageLoaded();

        WebUI.verifyAssertTrueIsDisplayed(viewProductNameInCartDetail, "Sản phẩm KHÔNG có trong giỏ hàng");
        WebUI.scrollToElement(viewProductNameInCartDetail);
        WebUI.hoverElement(viewProductNameInCartDetail);

        if (currentCart.containsKey(key)) {
            currentCart.get(key).setQuantity(currentCart.get(key).getQuantity() + Integer.parseInt(quantity));
        } else {
            currentCart.put(key, new Cart(productName, valueVariantName, productPrice, Integer.parseInt(quantity)));
        }

        Map<String, Cart> afterCart = getCartDetail();
        List<Cart> afterCartTemp = getCartDetailTemp();
        for (int i = 0; i < afterCart.size(); i++) {
            WebUI.verifyAssertEquals(currentCart.get(key).getNameProduct(), afterCart.get(key).getNameProduct(), "Tên sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(key).getNameVariant(), afterCart.get(key).getNameVariant(), "Tên biến thể sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(key).getQuantity(), afterCart.get(key).getQuantity(), "Số lượng sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(key).getPrice(), afterCart.get(key).getPrice(), "Đơn giá sản phẩm trong giỏ hàng không đúng");
        }

        By viewProductPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]/ancestor::li//span[text()='Price']/following-sibling::span");
        By viewProductTaxInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]/ancestor::li//span[text()='Tax']/following-sibling::span");
        //Lay đơn giá sản phẩm trong giỏ hàng (bao gom ca tax)
        BigDecimal priceInCartVer1 = convertCurrencyToBigDecimal(WebUI.getElementText(viewProductPriceInCartDetail));
        BigDecimal taxInCart = convertCurrencyToBigDecimal(WebUI.getElementText(viewProductTaxInCartDetail));
        BigDecimal priceInCartVer2 = priceInCartVer1.add(taxInCart);

        //Kiểm tra giá sản phẩm trong giỏ hàng so voi giá sản phẩm ở trang chi tiết
        WebUI.verifyAssertEquals(priceInCartVer2, productPrice, "Giá sản phẩm trong giỏ hàng không đúng");

        //Kiểm tra tổng tiền sản phẩm trong giỏ hàng
        checkSubTotalPriceInCartDetail();

        //Kiểm tra giỏ hàng dropdown
        WebUI.clickElement(buttonCart);
        WebUI.waitForJQueryLoad();
        List<Cart> cartDropdown = getCartDropdown();
        //Check info product in cart dropdown vs cart detail
        for (int i = 0; i < cartDropdown.size(); i++) {
            WebUI.verifyAssertContain(afterCartTemp.get(i).getNameProduct(), cartDropdown.get(i).getNameProduct(), "Tên sản phẩm trong giỏ hàng dropdown không đúng");
            WebUI.verifyAssertEquals(cartDropdown.get(i).getQuantity(), afterCartTemp.get(i).getQuantity(), "Số lượng sản phẩm trong giỏ hàng dropdown không đúng");
            WebUI.verifyAssertEquals(cartDropdown.get(i).getPrice(), afterCartTemp.get(i).getPrice(), "Đơn giá sản phẩm trong giỏ hàng dropdown không đúng");
        }
        //Check subtotal price in cart dropdown
        checkSubTotalPriceInCart();
        WebUI.clickElement(buttonCart);
        WebUI.sleep(2);
    }

    public void addProductOverQuantityToCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewProductNameInCart = By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]");
        //Tìm kiếm sản phẩm
        getDashboardPage().testSearchProductHaveResult(productName);
        //Kiem tra kết quả tìm kiếm có sản phẩm cần tìm không
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        if (listResultSearchProduct.isEmpty()) {
            ExtentTestManager.logMessage(Status.FAIL, "Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            LogUtils.info("Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            return;
        }
        productName = WebUI.getElementText(resultSearchProduct);
        WebUI.clickElement(resultSearchProduct);
        WebUI.waitForPageLoaded();

        //Lay số lượng sản phẩm ton kho
        int quantityAvailabel = Integer.parseInt(WebUI.getElementText(ProductInfoPage.quantityProductAvailable));
        WebUI.clickElement(OrderPage.buttonCart);
        int quantityProductInCart;
        //Check so luong san pham da co trong gio hang
        if (WebUI.checkElementExist(viewProductNameInCart)) {
            WebUI.scrollToElementToBottom(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));
            WebUI.hoverElement(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));
            quantityProductInCart = getCartDropdownMap().get(productName).getQuantity();
        } else {
            quantityProductInCart = 0;
        }

        //Nhập số lượng sản phẩm
        WebUI.setTextAndClear(inputQuantity, quantity);
        WebUI.clickElement(buttonAddToCart);
        WebUI.waitForJQueryLoad();
        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailabel) {
            WebUI.verifyAssertTrueIsDisplayed(messageOverQuantityAvailable, "Message thong bao qua so luong khong xuat hien");
            WebUI.verifyAssertTrueEqual(messageOverQuantityAvailable, "This item is out of stock!", "Message thong bao qua so luong khong dung");
        } else {
            WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Thêm vào giỏ hàng không thành công");
            WebUI.verifyAssertTrueTextContain(viewProductNameInPopupAddSucceed, productName, "Tên sản phẩm không đúng");
        }
        WebUI.clickElement(OrderPage.buttonCloseAddToCartMessage);
    }

    public void checkQuantityUpdateCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By priceInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//span[text()='Price']/following-sibling::span");
        By viewQuantityInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//input[contains(@name,'quantity')]");
        By viewProductNameInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]");
        //Tìm kiếm sản phẩm
        getDashboardPage().testSearchProductHaveResult(productName);
        //Kiem tra kết quả tìm kiếm có sản phẩm cần tìm không
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        if (listResultSearchProduct.isEmpty()) {
            ExtentTestManager.logMessage(Status.FAIL, "Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            LogUtils.info("Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            return;
        }
        productName = WebUI.getElementText(resultSearchProduct);
        WebUI.clickElement(resultSearchProduct);
        WebUI.waitForPageLoaded();

        //Lay số lượng sản phẩm ton kho
        int quantityAvailabel = Integer.parseInt(WebUI.getElementText(ProductInfoPage.quantityProductAvailable));
        WebUI.clickElement(buttonCart);
        WebUI.waitForJQueryLoad();
        WebUI.clickElement(buttonViewCart);
        WebUI.waitForPageLoaded();
        Map<String, Cart> currentCart = getCartDetail();
        if (!currentCart.containsKey(productName)) {
            System.out.println("Không có sản phẩm trong giỏ hàng");
            return;
        }
        String currentQuantityInCart = WebUI.getElementText(viewQuantityInCart);
        //Nhập số lượng sản phẩm
        WebUI.setTextAndClear(viewQuantityInCart, quantity);
        WebUI.clickElement(priceInCart);
        WebUI.waitForJQueryLoad();
        WebUI.verifyAssertTrueEqual(viewQuantityInCart, currentQuantityInCart, "Hệ thống cho phép cập nhập số lượng sản phẩm trong giỏ hàng nhiều hơn số lượng sản phẩm tồn kho");
    }

    public void checkTotalPriceInDetailProduct(BigDecimal productPrice, String quantity) {
        //Lấy tổng tiền mỗi sản phẩm trong trang chi tiết sản phẩm
        BigDecimal valueTotalPriceInDetailProduct = convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct));
        //Tính tổng tiền sản phẩm dựa theo số lượng và đơn giá
        BigDecimal checkTotalPriceInDetailProduct = productPrice.multiply(WebUI.stringToBigDecimal(quantity));
        WebUI.verifyAssertEquals(valueTotalPriceInDetailProduct.setScale(2, RoundingMode.HALF_UP), checkTotalPriceInDetailProduct.setScale(2, RoundingMode.HALF_UP), "Tổng tiền sản phẩm không đúng");
    }

    /**
     * Lấy đơn giá bán sản phẩm ở trang chi tiết sản phẩm
     *
     * @return Đơn giá bán sản phẩm
     */
    public BigDecimal getPriceInDetailProduct() {
        //Lấy đơn giá bán sản phẩm ở trang chi tiết sản phẩm
        BigDecimal productPrice;
        if (WebUI.checkElementExist(AddProductPage.discountPriceProductInProductDetail)) {
            productPrice = convertCurrencyToBigDecimal(WebUI.getElementText(AddProductPage.discountPriceProductInProductDetail));
            WebUI.clickElement(AddProductPage.discountPriceProductInProductDetail);
        } else {
            productPrice = convertCurrencyToBigDecimal(WebUI.getElementText(AddProductPage.unitPriceProductInProductDetail));
            WebUI.clickElement(AddProductPage.unitPriceProductInProductDetail);
        }
        return productPrice;
    }

    public static void checkSubTotalPriceInCart() {

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDropDown);
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDropDown);
        BigDecimal subTotalPrice = BigDecimal.ZERO;
        if (productNames.isEmpty()) {
            System.out.println("Giỏ hàng trống");
            WebUI.verifyAssertTrueIsDisplayed(messageCartEmptyInCart, "Message khong xuat hien");
            return;
        }
        for (int i = 0; i < productNames.size(); i++) {
            BigDecimal productPrice = convertCurrencyToBigDecimal(productPrices.get(i).getText());
            int productQuantity = Integer.parseInt(productQuantities.get(i).getText().replaceAll("\\D", ""));
            subTotalPrice = subTotalPrice.add(productPrice.multiply(BigDecimal.valueOf(productQuantity))).setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal valueSubTotalPriceInCart = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInCart)).setScale(2, RoundingMode.HALF_UP);
//        WebUI.verifySoftAssertEquals(valueSubTotalPriceInCart, subTotalPrice, "Tổng tiền sản phẩm trong giỏ hàng không đúng");
        WebUI.verifyAssertEquals(valueSubTotalPriceInCart, subTotalPrice, "Tổng tiền sản phẩm trong giỏ hàng không đúng");

    }

    public static Map<String, Cart> getCartDropdownMap() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        if (productNames.isEmpty()) {
            return new HashMap<>();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDropDown);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText().replaceAll("\\D", "")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDropDown);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        Map<String, Cart> cart = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart1 = new Cart();
            cart1.setNameProduct(valueProductNames.get(i));
            cart1.setQuantity(valueProductQuantities.get(i));
            cart1.setPrice(valueProductPrices.get(i));
            cart.put(valueProductNames.get(i), cart1);
        }
        return cart;
    }

    public static List<Cart> getCartDropdown() {

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        if (productNames.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDropDown);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText().replaceAll("\\D", "")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDropDown);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        List<Cart> cart = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart1 = new Cart();
            cart1.setNameProduct(valueProductNames.get(i));
            cart1.setQuantity(valueProductQuantities.get(i));
            cart1.setPrice(valueProductPrices.get(i));
            cart.add(cart1);
        }
        return cart;
    }

    public static List<Cart> getCartDetailTemp() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        if (productNames.isEmpty()) {
            return new ArrayList<>();
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDetail);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getAttribute("value")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDetail);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        List<WebElement> productTaxes = DriverManager.getDriver().findElements(elementProductTaxesInCartDetail);
        List<BigDecimal> valueProductTaxes = new ArrayList<>();
        for (WebElement productTax : productTaxes) {
            valueProductTaxes.add(convertCurrencyToBigDecimal(productTax.getText()));
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<Cart> cartDetailTemp = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).add(valueProductTaxes.get(i)));
            cartDetailTemp.add(cart);
        }
        return cartDetailTemp;
    }

    public static List<Cart> getCartDetailTemp2() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        if (productNames.isEmpty()) {
            return new ArrayList<>();
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDetail);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            WebUI.hoverElement2(productQuantity);
            valueProductQuantities.add(Integer.parseInt(productQuantity.getAttribute("value")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDetail);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            WebUI.hoverElement(productPrice);
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        List<WebElement> productTaxes = DriverManager.getDriver().findElements(elementProductTaxesInCartDetail);
        List<BigDecimal> valueProductTaxes = new ArrayList<>();
        for (WebElement productTax : productTaxes) {
            WebUI.hoverElement(productTax);
            valueProductTaxes.add(convertCurrencyToBigDecimal(productTax.getText()));
        }
        List<String> valueProductNames = new ArrayList<>();
        List<String> valueVariantNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            WebUI.hoverElement(productName);
            if (!productName.getText().contains(" - ")) {
                valueProductNames.add(productName.getText());
                valueVariantNames.add("");
                continue;
            }
            String productName1 = productName.getText().split(" - ")[0].trim();
            String variantName = productName.getText().split(" - ")[1].trim();
            valueVariantNames.add(variantName);
            valueProductNames.add(productName1);
        }
        List<Cart> cartDetailTemp = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setNameVariant(valueVariantNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i));
            cart.setVat(valueProductTaxes.get(i));
            cartDetailTemp.add(cart);
        }
        return cartDetailTemp;
    }

    public static List<String> getProductsNameInCart() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames;
    }
    public static List<String> getProductsNameInCartDetail() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText().split(" - ")[0].trim());
        }
        return valueProductNames;
    }

    public static int getQuantityItemProductInCart() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames.size();
    }
    public static int getQuantityItemProductInCartDetail() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames.size();
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

    public void removeProductFromCart(String productName) {
        By viewProductNameInCart = By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]");
        By buttonRemoveProduct = By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::a/following-sibling::span");
        WebUI.clickElement(OrderPage.buttonCart);
        List<WebElement> productNames = WebUI.getWebElements(viewProductNameInCart);
        if (productNames.isEmpty()) {
            System.out.println("San pham nay khong xoa duoc khoi gio hang vi khong co san pham nay trong gio hang");
            return;
        }

        WebUI.clickElement(buttonRemoveProduct);
        WebUI.verifyAssertTrueIsDisplayed(messageUpdateCart, "Message thong bao san pham da duoc xoa khoi gio hang khong xuat hien");
        WebUI.verifyAssertTrueEqual(messageUpdateCart, "Item has been removed from cart", "Message khong dung");
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.waitForPageLoaded();
        checkSubTotalPriceInCart();

    }


    public static void checkSubTotalPriceInCartDetail() {

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDetail);
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDetail);
        List<WebElement> totalPriceInCartDetail = DriverManager.getDriver().findElements(elementTotalPriceInCartDetail);
        List<WebElement> productTaxes = DriverManager.getDriver().findElements(elementProductTaxesInCartDetail);
        BigDecimal subPrice = BigDecimal.ZERO;
        if (productNames.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(messageCartEmptyInCartDetail, "Message khong xuat hien");
            return;
        }
        for (int i = 0; i < productNames.size(); i++) {
            BigDecimal totalPrice = BigDecimal.ZERO;
            WebUI.hoverElement(productPrices.get(i));
            BigDecimal productPrice = convertCurrencyToBigDecimal(productPrices.get(i).getText());
            WebUI.hoverElement(productTaxes.get(i));
            BigDecimal productTax = convertCurrencyToBigDecimal(productTaxes.get(i).getText());
            WebUI.hoverElement2(productQuantities.get(i));
            int productQuantity = Integer.parseInt(productQuantities.get(i).getAttribute("value"));
            totalPrice = totalPrice.add((productPrice.add(productTax)).multiply(BigDecimal.valueOf(productQuantity)));
//            WebUI.verifySoftAssertEquals(convertCurrencyToBigDecimal(totalPriceInCartDetail.get(i).getText()).setScale(2, RoundingMode.HALF_UP), totalPrice.setScale(2, RoundingMode.HALF_UP), "Tổng tiền sản phẩm trong giỏ hàng chi tiết không đúng");
            WebUI.verifyAssertEquals(convertCurrencyToBigDecimal(totalPriceInCartDetail.get(i).getText()).setScale(2, RoundingMode.HALF_UP), totalPrice.setScale(2, RoundingMode.HALF_UP), "Tổng tiền sản phẩm trong giỏ hàng chi tiết không đúng");
            subPrice = subPrice.add(totalPrice);
        }
        BigDecimal valueSubTotalPriceInCart = convertCurrencyToBigDecimal(WebUI.getElementText(subPriceInCartDetail));
        WebUI.scrollToElementToBottom(subPriceInCartDetail);
//        WebUI.verifySoftAssertEquals(subPrice, valueSubTotalPriceInCart.setScale(2, RoundingMode.HALF_UP), "Tổng tien tất cả sản phẩm chi tiết trong giỏ hàng không đúng");
        WebUI.verifyAssertEquals(subPrice, valueSubTotalPriceInCart.setScale(2, RoundingMode.HALF_UP), "Tổng tien tất cả sản phẩm chi tiết trong giỏ hàng không đúng");

    }

    public static Map<String, Cart> getCartDetail() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        if (productNames.isEmpty()) {
            return new HashMap<>();
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDetail);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getAttribute("value")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDetail);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        List<WebElement> productTaxes = DriverManager.getDriver().findElements(elementProductTaxesInCartDetail);
        List<BigDecimal> valueProductTaxes = new ArrayList<>();
        for (WebElement productTax : productTaxes) {
            valueProductTaxes.add(convertCurrencyToBigDecimal(productTax.getText()));
        }
        List<String> valueProductNames = new ArrayList<>();
        List<String> valueVariantNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            if (!productName.getText().contains(" - ")) {
                valueProductNames.add(productName.getText());
                valueVariantNames.add("");
                continue;
            }
            valueProductNames.add(productName.getText().split(" - ")[0].trim());
            valueVariantNames.add(productName.getText().split(" - ")[1].trim());
        }
        Map<String, Cart> cartDetail = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setNameVariant(valueVariantNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).add(valueProductTaxes.get(i)).setScale(2, RoundingMode.HALF_UP));
            cart.setVat(valueProductTaxes.get(i));
            if (valueVariantNames.get(i) == "") {
                String key = valueProductNames.get(i);
                cartDetail.put(key, cart);
                continue;
            }
            String key = valueProductNames.get(i) + " - " + valueVariantNames.get(i);
            cartDetail.put(key, cart);
        }
        return cartDetail;
    }


    public void updateQuantityProductInCart(String productName, String quantity) {

        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        //Tìm kiếm sản phẩm
        getDashboardPage().testSearchProductHaveResult(productName);
        //Kiem tra kết quả tìm kiếm có sản phẩm cần tìm không
        List<WebElement> listResultSearchProduct = DriverManager.getDriver().findElements(resultSearchProduct);
        if (listResultSearchProduct.isEmpty()) {
            ExtentTestManager.logMessage(Status.FAIL, "Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            LogUtils.info("Sản phẩm: " + productName + " không có trong kết quả tìm kiếm.");
            return;
        }
        productName = WebUI.getElementText(resultSearchProduct);
        WebUI.clickElement(resultSearchProduct);
        WebUI.waitForPageLoaded();
        By elementCheckVariantSize = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Size:')]");
        By elementCheckVariantColor = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Color:')]");
        By elementCheckVariantQuality = By.xpath("//form[@id='option-choice-form']//div[text()='Quantity:']/ancestor::div[contains(@class,'row')]/preceding-sibling::div//div[contains(text(),'Quality:')]");
        String valueVariantName = "";
        if (WebUI.checkElementExist(elementCheckVariantSize) || WebUI.checkElementExist(elementCheckVariantColor) || WebUI.checkElementExist(elementCheckVariantQuality)) {
            //variant da chon o trang detail product
            valueVariantName = AddProductPage.getVariantNameSelected();
        }

        //Lay số lượng sản phẩm ton kho
        int quantityAvailable = Integer.parseInt(WebUI.getElementText(ProductInfoPage.quantityProductAvailable));

        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/cart");
        WebUI.waitForPageLoaded();
        Map<String, Cart> currentCart = getCartDetail();


        String key;
        if (valueVariantName != "") {
            key = productName + " - " + valueVariantName;
        } else {
            key = productName;
        }
        By viewProductNameInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]");
        //lay so luong san pham o cart detail
        int quantityProductInCart;
        if (WebUI.checkElementExist(viewProductNameInCartDetail)) {
            WebUI.scrollToElement(viewProductNameInCartDetail);
            WebUI.hoverElement(viewProductNameInCartDetail);

            quantityProductInCart = currentCart.get(key).getQuantity();
        } else {
            System.out.println("Không có sản phẩm trong giỏ hàng");
            return;
        }
        String valueQuantityProductInCart = String.valueOf(quantityProductInCart);
        By viewQuantityInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]/ancestor::li//input[contains(@name,'quantity')]");
        By priceInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + key + "')]/ancestor::li//span[text()='Price']/following-sibling::span");
        WebUI.setTextAndClear(viewQuantityInCart, quantity);
        WebUI.clickElement(priceInCart);
        WebUI.sleep(2);
        String valueQuantityInCart = WebUI.getElementAttribute(viewQuantityInCart, "value");
        if (Integer.parseInt(quantity) > quantityAvailable) {
            System.out.println("Số lượng sản phẩm tồn kho không đủ. Không thể cập nhập số lượng sản phẩm trong giỏ hàng với số lượng: " + quantity);
            WebUI.verifyAssertEquals(valueQuantityInCart, valueQuantityProductInCart, "Số lượng sản phẩm trong giỏ hàng không đúng");
        } else {
            WebUI.verifyAssertEquals(valueQuantityInCart, quantity, "Số lượng sản phẩm trong giỏ hàng không đúng");
        }
        checkSubTotalPriceInCartDetail();
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.waitForJQueryLoad();
        checkSubTotalPriceInCart();
    }

    public void removeProductFromCartDetail(String productName) {
        By removeInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::div[contains(@class,'row ')]//a[contains(@onclick,'removeFromCartView')]");
        List<WebElement> iconRemoveProduct = WebUI.getWebElements(removeInCart);
        if (iconRemoveProduct.isEmpty()) {
            System.out.println("San pham nay khong xoa duoc khoi gio hang vi khong co san pham nay trong gio hang");
            return;
        }
        WebUI.clickElement(removeInCart);
        WebUI.verifyAssertTrueIsDisplayed(messageUpdateCart, "Message khong xuat hien");
        WebUI.verifyAssertTrueEqual(messageUpdateCart, "Item has been removed from cart", "Message khong dung");
        WebUI.waitForJQueryLoad();
        checkSubTotalPriceInCartDetail();
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.waitForJQueryLoad();
        checkSubTotalPriceInCart();
        WebUI.clickElement(OrderPage.buttonCart);

        WebUI.sleep(2);
    }


}
