package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.requests.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.thuydung.pages.CartPage.convertCurrencyToBigDecimal;

public class OrderPage {
    private By selectProductNabati = By.xpath("(//a[contains(text(),'Nabati')])[1]");
    private By selectProduct1 = By.xpath("(//a[contains(text(),'Cosy')])[1]");
    public static By buttonAddToCart = By.xpath("//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600']");
    public static By popupAddToCartSucceeded = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    public static By buttonCloseAddToCartMessage = By.xpath("//span[@class = 'la-2x']");
    private By buttonBackToShopping = By.xpath("//button[normalize-space()='Back to shopping']");
    private By selectProduct2 = By.xpath("(//a[@class = 'd-block text-reset' ])[1]");
    private By buttonPlus = By.xpath("//button[contains(@data-type,'plus')]");
    public static By buttonCart = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    public static By viewProductOrderOnCart = By.xpath("//span[@class='fw-600 mb-1 text-truncate-2']");
    private By buttonCheckoutOnCartPopup = By.xpath("//a[normalize-space()='Checkout']");
    private By buttonAddNewAddress = By.xpath("//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']");
    private By verifyCheckedAddress = By.xpath("(//input[@name = 'address_id' ])[2]");
    private By buttonProcessToCheckout = By.xpath("//a[normalize-space()='Proceed to Checkout']");
    private By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");

    private By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By verifyProductNabatiAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='Nabati']");
    private By verifyProductChocoPieAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='ChocoPie']");
    private By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");

    private By checkboxAgreeTermAndConditions = By.xpath("//span[@class='aiz-square-check']");
    private By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    public By messageOrder = By.xpath("//span[@data-notify='message']");
    private By quantityProduct = By.xpath("//input[@name='quantity']");
    private By titleNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@id='exampleModalLabel']");
    private By inputYourAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private By selectCountry = By.xpath("//div[contains(text(),'Select your country')]");
    private By buttonSelectAddress = By.xpath("(//span[@class='aiz-rounded-check flex-shrink-0 mt-1'])[1]");
    private By quantity = By.xpath("//input[@name='quantity']");
    private By paymentPage = By.xpath("//h3[normalize-space()='Any additional info?']");

    private static By subTotalPriceInDisplayPayment = By.xpath("(//th[text()='Subtotal'])/following-sibling::td//span");
    private By totalPrice = By.xpath("(//span[text()='Total']/parent::th)/following-sibling::td//span");

    public void order(String noteForOrder, String email, String password) {
        new LoginPage().loginSuccessWithCustomerAccount(email, password);
        WebUI.waitForPageLoaded();
        //Add product 1 to cart
        WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, PropertiesHelper.getValue("product_P01"));
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue("product_P01") + "')]"));
        WebUI.waitForPageLoaded();
        String productPrice1 = WebUI.getElementText(ProductInfoPage.totalProductPrice).trim();
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(popupAddToCartSucceeded, "Add to cart is failed");
        //Add product 2 to cart
        WebUI.clickElement(buttonBackToShopping);
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.setTextFromSplitString(DashboardPage.inputSearchProduct, PropertiesHelper.getValue("product_P02"));
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue("product_P02") + "')]"));
        String productPrice2 = WebUI.getElementText(ProductInfoPage.totalProductPrice).trim();
        WebUI.clickElement(buttonPlus);
        String quantities = WebUI.getElementAttribute(quantity, "value").trim();
        WebUI.verifyAssertTrueAttribute(quantity, "value", "2", "number of failed products");
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(popupAddToCartSucceeded, "Add to cart is failed");
        WebUI.clickElement(buttonCloseAddToCartMessage);
        //View cart demo
        WebUI.clickElement(buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(viewProductOrderOnCart, "My product is NOT displayed");
        //Check out
        WebUI.clickElement(buttonCheckoutOnCartPopup);
        WebUI.scrollToElement(buttonSelectAddress);
        WebUI.clickElement(buttonSelectAddress);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.scrollToElement(buttonContinueToPayment);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.verifyAssertTrueIsDisplayed(paymentPage, "Step Payment is NOT displayed");
        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);

        int priceOfProduct1 = Integer.parseInt(productPrice1.replace("$", "").replace(",", "").split("\\.")[0]);

        int quantitiesOfProduct2 = Integer.parseInt(quantities);
        int priceOfProduct2 = (Integer.parseInt(productPrice2.replace("$", "").replace(",", "").split("\\.")[0])) * quantitiesOfProduct2;

        int sumPrice = priceOfProduct1 + priceOfProduct2;

        int subTotal = Integer.parseInt(WebUI.getElementText(subTotalPriceInDisplayPayment).replace("$", "").replace(",", "").split("\\.")[0]);

        System.out.println("Total of Product 1: " + priceOfProduct1);
        System.out.println("Total of Product 2: " + priceOfProduct2);
        System.out.println("Sum Total of products: " + sumPrice);
        System.out.println("Sub total from Summary Order: " + subTotal);

        WebUI.sleep(2);
        WebUI.verifyAssertEquals(sumPrice, subTotal, "The total price is failed");
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Order is failed");
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
    }
    public List<String> getProductsNameInDeliveryInfoDisplay() {
        By elementProductNames = By.xpath("//div[@class='card-body']//ul[@class='list-group list-group-flush']//span[contains(@class,'opacity-60')]");
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames;
    }
    public void checkOutOrder(String noteForOrder) {
        WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
        //Check info order in display delivery info
        List<String> productNamesInDisplayDeliveryInfo = getProductsNameInDeliveryInfoDisplay();
        WebUI.clickElement(buttonCart);
        WebUI.waitForJQueryLoad();
        List<String> productNamesInCart = CartPage.getProductsNameInCart();
        WebUI.verifyAssertEquals(productNamesInDisplayDeliveryInfo, productNamesInCart, "Danh sách sản phẩm không khớp với giỏ hàng.");
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
        //Check info order in display payment
        String quantityItemProductInCart = String.valueOf(CartPage.getQuantityItemProductInCart());
        String quantityItemProductInDisplayPayment = WebUI.getElementText(By.xpath("//span[@class='badge badge-inline badge-primary']")).replaceAll("\\D", "");
        WebUI.verifyAssertEquals(quantityItemProductInCart, quantityItemProductInDisplayPayment, "Số lượng sản phẩm không khớp.");
        Cart infoProductsInDisplayPayment = getInfoProductsInDisplayPayment();
        WebUI.clickElement(buttonCart);
        Cart infoProductsInCart = CartPage.getCartDropdown();
        WebUI.verifyAssertEquals(infoProductsInDisplayPayment.getName(), infoProductsInCart.getName(), "Tên sản phẩm không khớp.");
        WebUI.verifyAssertEquals(infoProductsInDisplayPayment.getQuantity(), infoProductsInCart.getQuantity(), "Số lượng sản phẩm không khớp.");
        WebUI.verifyAssertEquals(infoProductsInDisplayPayment.getPrice(), infoProductsInCart.getPrice(), "Giá sản phẩm không khớp.");
        checkSubTotalPriceInDisplayPayment();
        //Check totalPrice in display payment

        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
        WebUI.scrollToElement(buttonCompleteOrder);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(messageOrder, "Đơn hàng thất bại");
        WebUI.verifyAssertTrueEqual(messageOrder, "Your order has been placed successfully", "Thông báo đơn hàng thành công không đúng");
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Đơn hàng thất bại");
        //Check order Summary in display confirmation order
        //Check order Detail  in display confirmation order

        //Check history order
    }

    /**
     * Check subtotal price in display payment
     */
    public static void checkSubTotalPriceInDisplayPayment() {
        By elementProductNames = By.xpath("//tbody//td[@class='product-name']");
        By elementTotalProductPrices = By.xpath("//tbody//td[contains(@class,'product-total')]");
        By elementProductQuantities = By.xpath("//tbody//strong[@class='product-quantity']");

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNames);

        List<WebElement> totalProductPrices = DriverManager.getDriver().findElements(elementTotalProductPrices);
        List<BigDecimal> valueTotalProductPrices = new ArrayList<>();
        for (WebElement totalProductPrice : totalProductPrices) {
            valueTotalProductPrices.add(convertCurrencyToBigDecimal(totalProductPrice.getText()));
        }
        BigDecimal subTotalPrice = BigDecimal.ZERO;
        for (int i = 0; i < valueTotalProductPrices.size(); i++) {
               subTotalPrice = subTotalPrice.add(valueTotalProductPrices.get(i));
        }
        BigDecimal valueSubTotalPrice = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        WebUI.verifyAssertEquals(subTotalPrice, valueSubTotalPrice, "Subtotal price không đúng");

    }

    /**
     * Get info products in display payment
     * @return
     */
    public static Cart getInfoProductsInDisplayPayment() {
        By elementProductNames = By.xpath("//tbody//td[@class='product-name']");
        By elementTotalProductPrices = By.xpath("//tbody//td[contains(@class,'product-total')]");
        By elementProductQuantities = By.xpath("//tbody//strong[@class='product-quantity']");

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
        List<WebElement> totalProductPrices = DriverManager.getDriver().findElements(elementTotalProductPrices);
        List<BigDecimal> valueTotalProductPrices = new ArrayList<>();
        for (WebElement totalProductPrice : totalProductPrices) {
            valueTotalProductPrices.add(convertCurrencyToBigDecimal(totalProductPrice.getText()));
        }
        Cart cart = new Cart();
        for (int i = 0; i < valueProductNames.size(); i++) {
            cart.setName(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueTotalProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
        }
        return cart;
    }




}
