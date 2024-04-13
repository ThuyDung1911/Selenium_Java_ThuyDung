package com.thuydung.pages;

import com.aventstack.extentreports.Status;
import com.thuydung.drivers.DriverManager;
import com.thuydung.keywords.WebUI;
import com.thuydung.reports.ExtentTestManager;
import com.thuydung.requests.Cart;
import com.thuydung.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    By inputQuantity = By.xpath("//input[@name='quantity']");
    By viewProductNameInPopupAddSucceed = By.xpath("//div[@id='addToCart-modal-body']//h6");
    By messageUpdateCart = By.xpath("//span[@data-notify='message']");
    By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    By totalPriceInDetailProduct = By.xpath("//strong[@id='chosen_price']");
    public static By subTotalPriceInCart = By.xpath("//div[@id='cart_items']//span[text()='Subtotal']/following-sibling::span");
    static By subPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Subtotal']/following-sibling::span");
    public By messageOverQuantityAvailable = By.xpath("//div[@id='addToCart-modal-body']//h3[normalize-space()='This item is out of stock!']");
    public static By elementProductNamesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[1]");
    public static By elementProductQuantitiesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[2]");
    public static By elementProductPricesInCartDropDown = By.xpath("//div[@id='cart_items']//img/following-sibling::span/span[3]");
    public static By elementProductNamesInCartDetail = By.xpath("//section[@id='cart-summary']//img/parent::span/following-sibling::span");
    public static By elementProductPricesInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Price']/following-sibling::span");
    public static By elementProductQuantitiesInCartDetail = By.xpath("//section[@id='cart-summary']//input[contains(@name,'quantity')]");
    public static By elementTotalPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Total']/following-sibling::span");
    By buttonBuyNow = By.xpath("//button[normalize-space()='Buy Now']");
    /**
     * Add product to cart from product detail page
     *
     * @param productName Tên sản phẩm
     * @param quantity    Số lượng sản phẩm
     */
    public void addProductToCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewProductNameInCart = By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]");
        By viewPriceInCart = By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/following-sibling::span[2]");
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
        int quantityAvailable = Integer.parseInt(WebUI.getElementText(ProductInfoPage.quantityProductAvailable));
        WebUI.clickElement(OrderPage.buttonCart);
        Map<String,Cart> currentCart = getCartDropdownMap();
        int quantityProductInCart;
        //Check so luong san pham da co trong gio hang
        if (WebUI.checkElementExist(viewProductNameInCart)) {
            WebUI.scrollToElementToBottom(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));
            WebUI.hoverElement(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));

            quantityProductInCart = currentCart.get(productName).getQuantity();

        } else {
            quantityProductInCart = 0;
        }
        //Nhập số lượng sản phẩm
        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailable) {
            System.out.println("Số lượng sản phẩm tồn kho không đủ. Không thể thêm sản phẩm vào giỏ hàng với số lượng: " + quantity);
            return;
        }
        WebUI.setTextAndClear(inputQuantity, quantity);
        //Lấy đơn giá bán sản phẩm ở trang chi tiết sản phẩm
        BigDecimal productPrice = getPriceInDetailProduct();
        WebUI.waitForJQueryLoad();
        //Check tổng tiền sản phẩm trong trang chi tiết sản phẩm
        checkTotalPriceInDetailProduct(productPrice, quantity);

        //Thêm sản phẩm vào giỏ hàng
        WebUI.sleep(3);
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);

        WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Thêm vào giỏ hàng không thành công");
        WebUI.verifyAssertTrueTextContain(viewProductNameInPopupAddSucceed, productName, "Tên sản phẩm không đúng");
        WebUI.clickElement(OrderPage.buttonCloseAddToCartMessage);

        //Kiểm tra sản phẩm đã được thêm vào giỏ hàng
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.waitForJQueryLoad();
        WebUI.verifyAssertTrueIsDisplayed(viewProductNameInCart, "Sản phẩm KHÔNG có trong giỏ hàng");
        WebUI.scrollToElementToBottom(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));
        WebUI.hoverElement(By.xpath("//div[@id='cart_items']//span[contains(text(),'" + productName + "')]/ancestor::li"));

        if(currentCart.containsKey(productName)) {
            currentCart.get(productName).setQuantity(currentCart.get(productName).getQuantity() + Integer.parseInt(quantity));
        }
        else {
            currentCart.put(productName, new Cart(productName, productPrice, Integer.parseInt(quantity)));
        }

        Map<String,Cart> afterCartDropdown = getCartDropdownMap();

        for (int i = 0; i < afterCartDropdown.size(); i++) {
            WebUI.verifyAssertEquals(currentCart.get(productName).getName(), afterCartDropdown.get(productName).getName(), "Tên sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(productName).getQuantity(), afterCartDropdown.get(productName).getQuantity(), "Số lượng sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(productName).getPrice(), afterCartDropdown.get(productName).getPrice(), "Đơn giá sản phẩm trong giỏ hàng không đúng");

        }

        //Lay đơn giá sản phẩm trong giỏ hàng
        BigDecimal priceInCartVer2 = convertCurrencyToBigDecimal(WebUI.getElementText(viewPriceInCart));

        //Kiểm tra giá sản phẩm trong giỏ hàng
        WebUI.verifyAssertEquals(priceInCartVer2, productPrice, "Giá sản phẩm trong giỏ hàng không đúng");

        //Kiểm tra tổng tiền sản phẩm trong giỏ hàng
        checkSubTotalPriceInCart();

        //Kiểm tra giỏ hàng chi tiết
        WebUI.clickElement(buttonViewCart);
        WebUI.waitForPageLoaded();
//        Map<String, Integer> cartDetail = getCartDetail();
        Map<String,Cart> cartDetail = getCartDetail();
        for (int i = 0; i < cartDetail.size(); i++) {
            WebUI.verifyAssertEquals(currentCart.get(productName).getName(), afterCartDropdown.get(productName).getName(), "Tên sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(productName).getQuantity(), afterCartDropdown.get(productName).getQuantity(), "Số lượng sản phẩm trong giỏ hàng không đúng");
            WebUI.verifyAssertEquals(currentCart.get(productName).getPrice(), afterCartDropdown.get(productName).getPrice(), "Đơn giá sản phẩm trong giỏ hàng không đúng");
        }

        //Kiểm tra giá sản phẩm trong giỏ hàng chi tiết
        checkSubTotalPriceInCartDetail();
        WebUI.sleep(2);
    }

    public void checkQuantityAddToCart(String productName, String quantity) {
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
        Map<String,Cart> currentCart = getCartDetail();
        if(!currentCart.containsKey(productName)){
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

    public void checkSubTotalPriceInCart() {

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInCartDropDown);
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInCartDropDown);
        BigDecimal subTotalPrice = BigDecimal.ZERO;
        if (productNames.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(messageCartEmptyInCart, "Message khong xuat hien");
            return;
        }
        for (int i = 0; i < productNames.size(); i++) {
            BigDecimal productPrice = convertCurrencyToBigDecimal(productPrices.get(i).getText());
            int productQuantity = Integer.parseInt(productQuantities.get(i).getText().replaceAll("\\D", ""));
            subTotalPrice = subTotalPrice.add(productPrice.multiply(BigDecimal.valueOf(productQuantity)));
        }
        BigDecimal valueSubTotalPriceInCart = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInCart));
        WebUI.verifyAssertEquals(subTotalPrice, valueSubTotalPriceInCart.setScale(2, RoundingMode.HALF_UP), "Tổng tiền sản phẩm trong giỏ hàng không đúng");
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
            cart1.setName(valueProductNames.get(i));
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
            cart1.setName(valueProductNames.get(i));
            cart1.setQuantity(valueProductQuantities.get(i));
            cart1.setPrice(valueProductPrices.get(i));
            cart.add(cart1);
        }
        return cart;
    }

    public static List<String> getProductsNameInCart() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropDown);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
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
        BigDecimal subPrice = BigDecimal.ZERO;
        if (productNames.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(messageCartEmptyInCartDetail, "Message khong xuat hien");
            return;
        }
        for (int i = 0; i < productNames.size(); i++) {
            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal productPrice = convertCurrencyToBigDecimal(productPrices.get(i).getText());
            int productQuantity = Integer.parseInt(productQuantities.get(i).getAttribute("value"));
            totalPrice = totalPrice.add(productPrice.multiply(BigDecimal.valueOf(productQuantity)));
            WebUI.verifyAssertEquals(convertCurrencyToBigDecimal(totalPriceInCartDetail.get(i).getText()).setScale(2, RoundingMode.HALF_UP), totalPrice.setScale(2, RoundingMode.HALF_UP), "Tổng tiền sản phẩm trong giỏ hàng chi tiết không đúng");
            subPrice = subPrice.add(totalPrice);
        }
        BigDecimal valueSubTotalPriceInCart = convertCurrencyToBigDecimal(WebUI.getElementText(subPriceInCartDetail));
        WebUI.scrollToElementToBottom(subPriceInCartDetail);
        WebUI.verifyAssertEquals(subPrice, valueSubTotalPriceInCart.setScale(2, RoundingMode.HALF_UP), "Tổng tien tất cả sản phẩm chi tiết trong giỏ hàng không đúng");

    }

    public Map<String,Cart> getCartDetail() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDetail);
        if (productNames.isEmpty()) {
            return new HashMap<>();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
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
        Map<String, Cart> cartDetail = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setName(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i));
            cartDetail.put(valueProductNames.get(i), cart);
        }
        return cartDetail;
    }


    public void updateQuantityProductInCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By priceInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//span[text()='Price']/following-sibling::span");
        By viewQuantityInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//input[contains(@name,'quantity')]");
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
        if (Integer.parseInt(quantity) > quantityAvailabel) {
            System.out.println("Số lượng sản phẩm tồn kho không đủ. Không thể thêm sản phẩm vào giỏ hàng với số lượng: " + quantity);
            return;
        }

        WebUI.clickElement(buttonBuyNow);
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(viewQuantityInCart, quantity);
        WebUI.clickElement(priceInCart);
        WebUI.waitForJQueryLoad();
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
