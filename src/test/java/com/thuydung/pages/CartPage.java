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
    By messageCartEmptyInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//div[contains(@class,'dropdown-menu')]//h3[normalize-space()='Your Cart is empty']");
    By inputQuantity = By.xpath("//input[@name='quantity']");
    By viewProductNameInPopupAddSucceed = By.xpath("//div[@id='addToCart-modal-body']//h6[contains(@class,'fw-600')]");
    By messageUpdateCart = By.xpath("//span[@data-notify='message']");
    By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    By viewQuantityInCart = By.xpath("//section[@id='cart-summary']//input[@type='number']");
    By totalPriceInDetailProduct = By.xpath("//strong[@id='chosen_price']");
    By subTotalPriceInCart = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::div/span[contains(text(),'$')]");
    static By subPriceInCartDetail = By.xpath("//section[@id='cart-summary']//span[text()='Subtotal']/following-sibling::span");
    public By messageOverQuantityAvailable = By.xpath("//div[@id='addToCart-modal-body']//h3[normalize-space()='This item is out of stock!']");


    /**
     * Add product to cart from product detail page
     *
     * @param productName Tên sản phẩm
     * @param quantity    Số lượng sản phẩm
     */
    public void addProductToCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By viewPriceInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/following-sibling::span[contains(text(),'$')]");
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
            WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            WebUI.hoverElement(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            //quantityProductInCart = getCart().get(productName);
            quantityProductInCart = getCartDropdown().getQuantity();
        } else {
            quantityProductInCart = 0;
        }
        //Map<String, Integer> currentCart = getCart();
        Cart currentCart = getCartDropdown();
        //Nhập số lượng sản phẩm
        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailabel) {
            System.out.println("Số lượng sản phẩm tồn kho không đủ. Không thể thêm sản phẩm vào giỏ hàng với số lượng: " + quantity);
            return;
        }
        WebUI.setTextAndClear(inputQuantity, quantity);
        //Lấy đơn giá bán sản phẩm ở trang chi tiết sản phẩm
        BigDecimal productPrice = getPriceInDetailProduct();

        //Lấy tổng tiền sản phẩm trong trang chi tiết sản phẩm
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
        WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
//        boolean checkProductNameExsist = false;
//        for itemProductName : getCartDropdown().getName() {
//
//        }
        if (currentCart.getName() == productName) {
            currentCart.setQuantity(currentCart.getQuantity() + Integer.parseInt(quantity));
        } else {
            currentCart.setQuantity(Integer.parseInt(quantity));
        }
//        if (currentCart.containsKey(productName)) {
//            currentCart.put(productName, currentCart.get(productName) + Integer.parseInt(quantity));
//        } else {
//            currentCart.put(productName, Integer.parseInt(quantity));
//        }
//        Map<String, Integer> afterCart = getCart();
        Cart afterCartDropdown = getCartDropdown();
//        WebUI.verifyAssertEquals(currentCart, afterCart, "Thông tin tên, số lượng sản phẩm trong giỏ hàng không đúng");
        WebUI.verifyAssertEquals(currentCart.getName(), afterCartDropdown.getName(), "Thông tin tên sản phẩm trong giỏ hàng không đúng");
        //WebUI.verifyAssertEquals(currentCart.getQuantity(), afterCartDropdown.getQuantity(), "Thông tin số lượng sản phẩm trong giỏ hàng không đúng");
        WebUI.verifyAssertEquals(currentCart.getPrice(), afterCartDropdown.getPrice(), "Thông tin đơn giá sản phẩm trong giỏ hàng không đúng");
        //WebUI.verifyAssertEquals(currentCart, afterCartDropdown, "Thông tin tên, số lượng, đơn giá sản phẩm trong giỏ hàng không đúng");
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
        Cart cartDetail = getCartDetail1();

        WebUI.verifyAssertEquals(cartDetail.getName(), afterCartDropdown.getName(), "Thông tin tên, số lượng, đơn giá sản phẩm trong giỏ hàng chi tiết không đúng");
        WebUI.verifyAssertEquals(cartDetail.getQuantity(), afterCartDropdown.getQuantity(), "Thông tin tên, số lượng, đơn giá sản phẩm trong giỏ hàng chi tiết không đúng");
        WebUI.verifyAssertEquals(cartDetail.getPrice(), afterCartDropdown.getPrice(), "Thông tin tên, số lượng, đơn giá sản phẩm trong giỏ hàng chi tiết không đúng");

        //Kiểm tra giá sản phẩm trong giỏ hàng chi tiết
        checkSubTotalPriceInCartDetail();
        WebUI.sleep(2);
    }

    public void checkQuantityAddToCart(String productName, String quantity) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By viewPriceInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/following-sibling::span[contains(text(),'$')]");
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
            WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            WebUI.hoverElement(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            quantityProductInCart = getCart().get(productName);
        } else {
            quantityProductInCart = 0;
        }
        //Nhập số lượng sản phẩm
        WebUI.setTextAndClear(inputQuantity, quantity);
        WebUI.clickElement(buttonAddToCart);
        WebUI.waitForJQueryLoad();
        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailabel) {
            //Check total price in detail product (over quantity available = max quantity available * price)
            //checkTotalPriceInDetailProduct(convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct)), String.valueOf(quantityAvailabel));
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
        String currentQuantityInCart = WebUI.getElementText(viewQuantityInCart);
        //Nhập số lượng sản phẩm
        WebUI.setTextAndClear(viewQuantityInCart, quantity);
        WebUI.clickElement(priceInCart);
        WebUI.waitForJQueryLoad();
        WebUI.verifyAssertTrueEqual(viewQuantityInCart, currentQuantityInCart, "Hệ thống cho phép cập nhập số lượng sản phẩm trong giỏ hàng nhiều hơn số lượng sản phẩm tồn kho");
    }

    public void checkQuantityAvailabelFail(String productName) {
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By viewPriceInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/following-sibling::span[contains(text(),'$')]");
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
            WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            WebUI.hoverElement(By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::li[@class='list-group-item']"));
            quantityProductInCart = getCart().get(productName) == null ? 0 : getCart().get(productName);
        } else {
            quantityProductInCart = 0;
        }
        //Nhập số lượng sản phẩm
        String quantity = String.valueOf(quantityAvailabel + 1);
        WebUI.setTextAndClear(inputQuantity, quantity);
        WebUI.clickElement(buttonAddToCart);
        WebUI.waitForJQueryLoad();
        WebUI.verifyAssertTrueIsDisplayed(messageOverQuantityAvailable, "Message thong bao qua so luong khong xuat hien");
        WebUI.verifyAssertTrueEqual(messageOverQuantityAvailable, "This item is out of stock!", "Message thong bao qua so luong khong dung");

//        if (Integer.parseInt(quantity) + quantityProductInCart > quantityAvailabel) {
//            //Check total price in detail product (over quantity available = max quantity available * price)
//            //checkTotalPriceInDetailProduct(convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct)), String.valueOf(quantityAvailabel));
//            WebUI.verifyAssertTrueIsDisplayed(messageOverQuantityAvailable,"Message thong bao qua so luong khong xuat hien");
//            WebUI.verifyAssertTrueEqual(messageOverQuantityAvailable,"This item is out of stock!","Message thong bao qua so luong khong dung");
//            //WebUI.verifyElementVisible(OrderPage.popupAddToCartSucceeded, "Message thong bao them vao gio hang voi so luong san pham qua luong ton kho khong xuat hien");
//
//            //WebUI.verifyAssertFalseIsDisplayed(OrderPage.popupAddToCartSucceeded, "Message thong bao them vao gio hang voi so luong san pham qua luong ton kho thanh cong xuat hien");
//        }
//        else {
//            WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Thêm vào giỏ hàng không thành công");
//            WebUI.verifyAssertTrueTextContain(viewProductNameInPopupAddSucceed, productName, "Tên sản phẩm không đúng");
//            //WebUI.verifyAssertFalseIsDisplayed(messageOverQuantityAvailable,"Message thong bao qua so luong xuat hien du so luong them vao gio hang hop le");
//        }
        WebUI.clickElement(OrderPage.buttonCloseAddToCartMessage);
        String quantity2 = String.valueOf(quantityAvailabel - quantityProductInCart);
        WebUI.setTextAndClear(inputQuantity, quantity2);
        WebUI.clickElement(buttonAddToCart);
        WebUI.waitForJQueryLoad();
        if (Integer.parseInt(quantity2) + quantityProductInCart > quantityAvailabel) {
            //Check total price in detail product (over quantity available = max quantity available * price)
            //checkTotalPriceInDetailProduct(convertCurrencyToBigDecimal(WebUI.getElementText(totalPriceInDetailProduct)), String.valueOf(quantityAvailabel));

            WebUI.verifyAssertFalseIsDisplayed(OrderPage.popupAddToCartSucceeded, "Message thong bao them vao gio hang voi so luong san pham qua luong ton kho thanh cong xuat hien");
            WebUI.verifyAssertTrueIsDisplayed(messageOverQuantityAvailable, "Message thong bao qua so luong khong xuat hien");
            WebUI.verifyAssertTrueEqual(messageOverQuantityAvailable, "This item is out of stock!", "Message thong bao qua so luong khong dung");
            //WebUI.verifyElementVisible(OrderPage.popupAddToCartSucceeded, "Message thong bao them vao gio hang voi so luong san pham qua luong ton kho khong xuat hien");

        } else {

            WebUI.verifyAssertFalseIsDisplayed(messageOverQuantityAvailable, "Message thong bao qua so luong xuat hien du so luong them vao gio hang hop le");

            WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Thêm vào giỏ hàng không thành công");
            WebUI.verifyAssertTrueTextContain(viewProductNameInPopupAddSucceed, productName, "Tên sản phẩm không đúng");
        }
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
        if (WebUI.checkElementExist(AddProductPage.discountPriceProduct)) {
            productPrice = convertCurrencyToBigDecimal(WebUI.getElementText(AddProductPage.discountPriceProduct));
            WebUI.clickElement(AddProductPage.discountPriceProduct);
        } else {
            productPrice = convertCurrencyToBigDecimal(WebUI.getElementText(AddProductPage.unitPriceProduct));
            WebUI.clickElement(AddProductPage.unitPriceProduct);
        }
        return productPrice;
    }

    public void checkSubTotalPriceInCart() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]"));
        List<WebElement> productPrices = DriverManager.getDriver().findElements(By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]/following-sibling::span[contains(text(),'$')]"));
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]/following-sibling::span[contains(text(),'x')]"));
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

    public static Cart getCartDropdown() {
        By elementProductNames = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]");
        By elementProductQuantities = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]/following-sibling::span[contains(text(),'x')]");
        By elementProductPrices = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]/following-sibling::span[contains(text(),'$')]");

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);
        if (productNames.size() == 0) {
            return new Cart();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantities);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText().replaceAll("\\D", "")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPrices);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        Cart cart = new Cart();
        for (int i = 0; i < valueProductNames.size(); i++) {
            cart.setName(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i));
        }
        return cart;
    }

    public static Map<String, Integer> getCart() {
        By elementProductNames = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]");
        By elementProductQuantities = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]/following-sibling::span[contains(text(),'x')]");
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);
        if (productNames.size() == 0) {
            return new HashMap<>();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantities);
        List<String> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(productQuantity.getText().replaceAll("\\D", ""));
        }
        Map<String, Integer> cart = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            cart.put(valueProductNames.get(i), Integer.parseInt(valueProductQuantities.get(i)));
        }
        return cart;
    }

    public static List<String> getProductsNameInCart() {
        By elementProductNames = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]");
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames;
    }

    public static int getQuantityItemProductInCart() {
        By elementProductNames = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]");
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);
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
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By buttonRemoveProduct = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::a/following-sibling::span");
        WebUI.clickElement(OrderPage.buttonCart);
//        WebUI.clickElement(buttonRemoveProduct);
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
        List<WebElement> productNames = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[@class='fs-14 opacity-60']"));
        List<WebElement> productPrices = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[text()='Price']/following-sibling::span"));
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//input[contains(@name,'quantity')]"));
        List<WebElement> totalPriceInCartDetail = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[text()='Total']/following-sibling::span"));
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

    public Map<String, Integer> getCartDetail() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[@class='fs-14 opacity-60']"));
        if (productNames.size() == 0) {
            return new HashMap<>();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//input[contains(@name,'quantity')]"));
        List<String> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(productQuantity.getAttribute("value"));
        }
        Map<String, Integer> cartDetail = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            cartDetail.put(valueProductNames.get(i), Integer.parseInt(valueProductQuantities.get(i)));
        }
        return cartDetail;
    }
    public Cart getCartDetail1() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[@class='fs-14 opacity-60']"));
        if (productNames.size() == 0) {
            return new Cart();
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//input[contains(@name,'quantity')]"));
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText().replaceAll("\\D", "")));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(By.xpath("//section[@id='cart-summary']//li//span[text()='Price']/following-sibling::span"));
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        Cart cartDetail = new Cart();
        for (int i = 0; i < valueProductNames.size(); i++) {
            cartDetail.setName(valueProductNames.get(i));
            cartDetail.setQuantity(valueProductQuantities.get(i));
            cartDetail.setPrice(valueProductPrices.get(i));
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
        WebUI.clickElement(By.xpath("//button[normalize-space()='Buy Now']"));
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
