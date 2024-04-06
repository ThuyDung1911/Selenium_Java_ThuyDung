package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.requests.Cart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.math.BigDecimal;
import java.util.*;

import static com.thuydung.pages.CartPage.convertCurrencyToBigDecimal;

public class OrderPage {
    private By buttonAddNewAddress = By.xpath("//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']");
    private By verifyCheckedAddress = By.xpath("(//input[@name = 'address_id' ])[2]");
    private By buttonProcessToCheckout = By.xpath("//a[normalize-space()='Proceed to Checkout']");
    private By selectProduct2 = By.xpath("(//a[@class = 'd-block text-reset' ])[1]");
    private By selectProductNabati = By.xpath("(//a[contains(text(),'Nabati')])[1]");
    private By selectProduct1 = By.xpath("(//a[contains(text(),'Cosy')])[1]");
    private By verifyProductNabatiAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='Nabati']");
    private By verifyProductChocoPieAtStepCheckout = By.xpath("//span[@class='fs-14 opacity-60'][normalize-space()='ChocoPie']");
    private By quantityProduct = By.xpath("//input[@name='quantity']");
    private By titleNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@id='exampleModalLabel']");
    private By inputYourAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private By selectCountry = By.xpath("//div[contains(text(),'Select your country')]");

    public static By buttonAddToCart = By.xpath("//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600']");
    public static By popupAddToCartSucceeded = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    public static By buttonCloseAddToCartMessage = By.xpath("//span[@class = 'la-2x']");
    private By buttonBackToShopping = By.xpath("//button[normalize-space()='Back to shopping']");
    private By buttonPlus = By.xpath("//button[contains(@data-type,'plus')]");
    public static By buttonCart = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    public static By viewProductOrderOnCart = By.xpath("//span[@class='fw-600 mb-1 text-truncate-2']");
    private By buttonCheckoutOnCartPopup = By.xpath("//a[normalize-space()='Checkout']");
    private By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    private By checkboxAgreeTermAndConditions = By.xpath("//span[@class='aiz-square-check']");
    private By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    public By messageOrder = By.xpath("//span[@data-notify='message']");
    private By buttonSelectAddress = By.xpath("(//span[@class='aiz-rounded-check flex-shrink-0 mt-1'])[1]");
    private By quantity = By.xpath("//input[@name='quantity']");
    private By paymentPage = By.xpath("//h3[normalize-space()='Any additional info?']");
    private static By subTotalPriceInDisplayPayment = By.xpath("(//th[text()='Subtotal'])/following-sibling::td//span");
    public static By priceTaxInDisplayPayment = By.xpath("(//th[text()='Tax'])/following-sibling::td//span");
    By priceTotalShippingInDisplayPayment = By.xpath("(//th[text()='Total Shipping'])/following-sibling::td//span");
    By priceTotalInDisplayPayment = By.xpath(" //tr[@class='cart-total']//strong");
    By elementProductNamesInCartDropdown = By.xpath("//div[contains(text(),'Cart Items')]/following-sibling::ul/li//span[contains(@class,'text-truncate')]");
    By elementProductNamesInDeliveryInfoDisplay = By.xpath("//div[@class='card-body']//ul[@class='list-group list-group-flush']//span[contains(@class,'opacity-60')]");
    static By elementProductNamesInDisplayPayment = By.xpath("//tbody//td[@class='product-name']");
    static By elementTotalProductPricesInDisplayPayment = By.xpath("//tbody//td[contains(@class,'product-total')]");
    static By elementProductQuantitiesInDisplayPayment = By.xpath("//tbody//strong[@class='product-quantity']");
    By elementProductNamesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[2]/a");
    By elementProductQuantitiesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[4]");
    By elementProductPricesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[6]");
    By elementSubTotalInDisplayConfirm = By.xpath("//table//th[text()='Subtotal']/following-sibling::td/span");
    By elementPriceShippingInDisplayConfirm = By.xpath("//table//th[text()='Shipping']/following-sibling::td/span");
    By elementPriceTaxInDisplayConfirm = By.xpath("//table//th[text()='Tax']/following-sibling::td/span");
    By elementCouponDiscountInDisplayConfirm = By.xpath("//table//th[text()='Coupon Discount']/following-sibling::td/span");
    By elementTotalInDisplayConfirm = By.xpath("//table//span[text()='Total']/ancestor::th/following-sibling::td//span");
    By elementNameInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Name')]/following-sibling::td");
    By elementEmailInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Email')]/following-sibling::td");
    By elementOrderDateInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Order date')]/following-sibling::td");
    By elementShippingAddressInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Shipping address')]/following-sibling::td");
    By elementPaymentMethodInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Payment method')]/following-sibling::td");
    By elementOrderStatusInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Order status')]/following-sibling::td");
    By elementTotalOrderAmountInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Total order amount')]/following-sibling::td");
    By elementShippingInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Shipping')]/following-sibling::td");

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
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDeliveryInfoDisplay);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames;
    }

    public void checkOutOrder(String noteForOrder) {
        WebUI.clickElement(buttonCart);
        Map<String, Cart> currentCart = CartPage.getCartDropdown();
        WebUI.openURL("https://cms.anhtester.com/checkout");
        WebUI.waitForPageLoaded();
        if (currentCart.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(messageOrder, "Không có sản phẩm trong giỏ hàng");
            WebUI.verifyAssertTrueEqual(messageOrder, "Your Cart is empty", "Thông báo không có sản phẩm trong giỏ hàng không đúng");
            return;
        }
        //WebUI.clickElement(buttonContinueToShipping);
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
        Map<String, Cart> infoProductsInDisplayPayment = getInfoProductsInDisplayPayment();
        WebUI.clickElement(buttonCart);

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInCartDropdown);
        if (productNames.isEmpty()) {
            return;
        }
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        WebUI.verifyAssertEquals(infoProductsInDisplayPayment.size(), valueProductNames.size(), "Số lượng sản phẩm không khớp.");
        Map<String, Cart> infoProductsInCart = CartPage.getCartDropdown();
        WebUI.verifyAssertEquals(infoProductsInCart.size(), valueProductNames.size(), "Số lượng sản phẩm không khớp.");

        for (int i = 0; i < infoProductsInCart.size(); i++) {
            WebUI.verifyAssertEquals(infoProductsInDisplayPayment.get(valueProductNames.get(i)).getName(), infoProductsInCart.get(valueProductNames.get(i)).getName(), "Tên sản phẩm không khớp.");
            WebUI.verifyAssertEquals(infoProductsInDisplayPayment.get(valueProductNames.get(i)).getQuantity(), infoProductsInCart.get(valueProductNames.get(i)).getQuantity(), "Số lượng sản phẩm không khớp.");
            WebUI.verifyAssertEquals(infoProductsInDisplayPayment.get(valueProductNames.get(i)).getPrice(), infoProductsInCart.get(valueProductNames.get(i)).getPrice(), "Giá sản phẩm không khớp.");
        }

        checkSubTotalPriceInDisplayPayment();
        //Check totalPrice in display payment
        checkTotalPriceInDisplayPayment();

        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
        WebUI.scrollToElement(buttonCompleteOrder);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        //Check order success
        WebUI.verifyAssertTrueIsDisplayed(messageOrder, "Đơn hàng thất bại");
        WebUI.verifyAssertTrueEqual(messageOrder, "Your order has been placed successfully", "Thông báo đơn hàng thành công không đúng");
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Đơn hàng thất bại");
        //Check order Summary in display confirmation order
        checkOrderSummary();
        //Check order Detail  in display confirmation order
        Map<String, Cart> infoProductsInDisplayConfirm = getInfoOrderDetailInDisplayConfirm();
        for (int i = 0; i < infoProductsInDisplayConfirm.size(); i++) {
            WebUI.verifyAssertEquals(infoProductsInDisplayConfirm.get(valueProductNames.get(i)).getName(), infoProductsInCart.get(valueProductNames.get(i)).getName(), "Tên sản phẩm không khớp.");
            WebUI.verifyAssertEquals(infoProductsInDisplayConfirm.get(valueProductNames.get(i)).getQuantity(), infoProductsInCart.get(valueProductNames.get(i)).getQuantity(), "Số lượng sản phẩm không khớp.");
            WebUI.verifyAssertEquals(infoProductsInDisplayConfirm.get(valueProductNames.get(i)).getPrice(), infoProductsInCart.get(valueProductNames.get(i)).getPrice(), "Giá sản phẩm không khớp.");
        }
        checkSubTotalPriceInDisplayConfirm(); //check cả subtotal, total, history order
        //checkTotalPriceInDisplayConfirm();

        //Check history order
//        By elementOrderCodeInDisplayConfirm = By.xpath("//h2[contains(text(),'Order Code')]/span");
//        String orderCode = WebUI.getElementText(elementOrderCodeInDisplayConfirm);
//        checkHistoryOrder(orderCode);


    }

    public void checkHistoryOrder(String orderCode) {
        String orderDateInOrderSummary = WebUI.getElementText(elementOrderDateInOrderSummary);
        String orderDateInOrderSummaryFormat = orderDateInOrderSummary.split(" ")[0];
        String orderStatusInOrderSummary = WebUI.getElementText(elementOrderStatusInOrderSummary) + " *";
        By elementTotalOrderInOrderDetail = By.xpath("//span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//span[text()='Total']/ancestor::th/following-sibling::td//span");
        String totalOrderInOrderDetail = WebUI.getElementText(elementTotalOrderInOrderDetail);
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/purchase_history");
        WebUI.waitForPageLoaded();
        By elementOrderCodeInHistoryOrder = By.xpath("//a[normalize-space()='" + orderCode + "']");
        By elementOrderDateInHistoryOrder = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr/td[2]");
        By elementTotalOrderAmountInHistoryOrder = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr/td[3]");
        By elementOrderStatusInHistoryOrder = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr/td[4]");
        WebUI.verifyAssertTrueIsDisplayed(elementOrderCodeInHistoryOrder, "Không xuất hiện đơn hàng vừa tạo trong lịch sử đơn hàng.");
        WebUI.verifyAssertTrueEqual(elementOrderDateInHistoryOrder, orderDateInOrderSummaryFormat, "Ngày tạo đơn hàng không khớp.");

        WebUI.verifyAssertTrueEqual(elementTotalOrderAmountInHistoryOrder, totalOrderInOrderDetail, "Tổng giá trị đơn hàng không khớp.");
//        System.out.println(WebUI.getElementText(elementOrderStatusInHistoryOrder));
        WebUI.verifyAssertTrueEqual(elementOrderStatusInHistoryOrder, orderStatusInOrderSummary, "Trạng thái đơn hàng không khớp.");


        //Check order detail in history order
        WebUI.clickElement(elementOrderCodeInHistoryOrder);



        DriverManager.getDriver().switchTo().window(mainWindow);

    }

    public void checkOrderDetail(String orderCode) {

    }

    public void checkOrderSummary() {
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/profile");
        WebUI.waitForPageLoaded();
        String nameInCustomerProfile = WebUI.getElementAttribute(ProfilePage.inputName, "value");
        String emailInCustomerProfile = WebUI.getElementAttribute(ProfilePage.inputEmail, "value");

        DriverManager.getDriver().close();

        DriverManager.getDriver().switchTo().window(mainWindow);
        WebUI.verifyAssertTrueEqual(elementNameInOrderSummary, nameInCustomerProfile, "Tên khách hàng không khớp");
        WebUI.verifyAssertTrueEqual(elementEmailInOrderSummary, emailInCustomerProfile, "Email khách hàng không khớp");


    }

    public Map<String, Cart> getInfoOrderDetailInDisplayConfirm() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDisplayConfirm);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInDisplayConfirm);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText()));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInDisplayConfirm);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        //check product name, quantity, price
        Map<String, Cart> infoProductsInDisplayConfirm = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setName(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayConfirm.put(valueProductNames.get(i), cart);
        }
        return infoProductsInDisplayConfirm;
    }

    public void checkSubTotalPriceInDisplayConfirm() {
        By elementOrderDetailInDisplayConfirm = By.xpath("//div[@class='card-body']");
        List<WebElement> orders = DriverManager.getDriver().findElements(elementOrderDetailInDisplayConfirm);
        //Map<String, Cart> infoProductsInDisplayConfirm = getInfoOrderDetailInDisplayConfirm();
        BigDecimal totalOrderAmount = BigDecimal.ZERO;
        for (int i = 1; i <= orders.size(); i++) {
            BigDecimal totalOrder = BigDecimal.ZERO;
            By elementOrderCodeInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/span");
            String orderCode = WebUI.getElementText(elementOrderCodeInOrderDetail);
            By elementProductNamesInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//h5[normalize-space()='Order Details']/following-sibling::div/table//td[2]/a");
            By elementSubTotalInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Subtotal']/following-sibling::td/span");
            By elementTaxInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Tax']/following-sibling::td/span");
            By elementShippingInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Shipping']/following-sibling::td/span");
            By elementCouponDiscountInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Coupon Discount']/following-sibling::td/span");
            By elementTotalInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//span[text()='Total']/ancestor::th/following-sibling::td//span");
            By elementProductPricesInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//h5[normalize-space()='Order Details']/following-sibling::div/table//td[6]");
//            By elementProductQuantitiesInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//h5[normalize-space()='Order Details']/following-sibling::div/table//td[4]");
//            List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInOrderDetail);
//            List<Integer> valueProductQuantities = new ArrayList<>();
//            for (WebElement productQuantity : productQuantities) {
//                valueProductQuantities.add(Integer.parseInt(productQuantity.getText()));
//            }

            List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInOrderDetail);
            List<BigDecimal> valueProductPrices = new ArrayList<>();
            for (WebElement productPrice : productPrices) {
                valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
            }

            List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInOrderDetail);
            List<String> valueProductNames = new ArrayList<>();
            for (WebElement productName : productNames) {
                valueProductNames.add(productName.getText());
            }
            BigDecimal valueSubTotalInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementSubTotalInOrderDetail));

            BigDecimal valueSubTotal = BigDecimal.ZERO;
            for (int j = 0; j < valueProductNames.size(); j++) {
                valueSubTotal = valueSubTotal.add(valueProductPrices.get(j));
            }

//            for (int j = 0; j < infoProductsInDisplayConfirm.size(); j++) {
//                valueSubTotal = valueSubTotal.add(infoProductsInDisplayConfirm.get(valueProductNames.get(j)).getPrice().multiply(BigDecimal.valueOf(infoProductsInDisplayConfirm.get(valueProductNames.get(j)).getQuantity())));
//            }

            WebUI.verifyAssertEquals(valueSubTotalInDisplayConfirm, valueSubTotal, "Subtotal của order code: " + orderCode + " không đúng tại màn confirm đơn hàng.");
            BigDecimal valueTaxInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTaxInOrderDetail));
            BigDecimal valueShippingInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementShippingInOrderDetail));
            BigDecimal valueCouponDiscountInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementCouponDiscountInOrderDetail));
            BigDecimal valueTotalInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalInOrderDetail));
            totalOrder = totalOrder.add(valueSubTotal).add(valueTaxInOrderDetail).add(valueShippingInOrderDetail).subtract(valueCouponDiscountInOrderDetail);
            WebUI.verifyAssertEquals(valueTotalInOrderDetail, totalOrder, "Tổng giá tiền của order code: " + orderCode + " không đúng tại màn confirm đơn hàng.");
            totalOrderAmount = totalOrderAmount.add(totalOrder);
            //check history order
            checkHistoryOrder(orderCode);
        }
        //check total order amount
        WebUI.verifyAssertEquals(convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalOrderAmountInOrderSummary)), totalOrderAmount, "Tổng giá tiền không đúng tại màn confirm đơn hàng.");
    }

    public void checkTotalPriceInDisplayConfirm() {
        BigDecimal valueSubTotalInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementSubTotalInDisplayConfirm));
        BigDecimal valuePriceShippingInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementPriceShippingInDisplayConfirm));
        BigDecimal valuePriceTaxInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementPriceTaxInDisplayConfirm));
        BigDecimal valueCouponDiscountInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementCouponDiscountInDisplayConfirm));
        BigDecimal valueTotalInDisplayConfirm = convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalInDisplayConfirm));
        BigDecimal valueTotal = valueSubTotalInDisplayConfirm.add(valuePriceShippingInDisplayConfirm).add(valuePriceTaxInDisplayConfirm).subtract(valueCouponDiscountInDisplayConfirm);
        WebUI.verifyAssertEquals(valueTotalInDisplayConfirm, valueTotal, "Tổng giá tiền không đúng tại màn confirm đơn hàng.");
    }

    public void checkTotalPriceInDisplayPayment() {
        BigDecimal valueSubTotalPriceInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        BigDecimal valuePriceTaxInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTaxInDisplayPayment));
        BigDecimal valuePriceTotalShippingInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalShippingInDisplayPayment));
        BigDecimal valuePriceTotalInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalInDisplayPayment));
        BigDecimal valuePriceTotal = valueSubTotalPriceInDisplayPayment.add(valuePriceTaxInDisplayPayment).add(valuePriceTotalShippingInDisplayPayment);
        WebUI.verifyAssertEquals(valuePriceTotalInDisplayPayment, valuePriceTotal, "Tổng giá tiền ở trang thanh toán không khớp.");
    }

    /**
     * Check subtotal price in display payment
     */
    public static void checkSubTotalPriceInDisplayPayment() {
        List<WebElement> totalProductPrices = DriverManager.getDriver().findElements(elementTotalProductPricesInDisplayPayment);
        List<BigDecimal> valueTotalProductPrices = new ArrayList<>();
        for (WebElement totalProductPrice : totalProductPrices) {
            valueTotalProductPrices.add(convertCurrencyToBigDecimal(totalProductPrice.getText()));
        }
        BigDecimal subTotalPrice = BigDecimal.ZERO;
        for (int i = 0; i < valueTotalProductPrices.size(); i++) {
            subTotalPrice = subTotalPrice.add(valueTotalProductPrices.get(i));
        }
//        Map<String, Cart> infoProductsInDisplayPayment = getInfoProductsInDisplayPayment();
//        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDisplayPayment);
//        if (productNames.size() == 0) {
//            return;
//        }
//        List<String> valueProductNames = new ArrayList<>();
//        for (WebElement productName : productNames) {
//            valueProductNames.add(productName.getText());
//        }
//
//        BigDecimal subTotalPrice = BigDecimal.ZERO;
//        for (int i = 0; i < infoProductsInDisplayPayment.size(); i++) {
//            subTotalPrice = subTotalPrice.add(infoProductsInDisplayPayment.get(valueProductNames.get(i)).getPrice().multiply(BigDecimal.valueOf(infoProductsInDisplayPayment.get(valueProductNames.get(i)).getQuantity())));
//        }
        BigDecimal valueSubTotalPrice = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        WebUI.verifyAssertEquals(subTotalPrice, valueSubTotalPrice, "Subtotal price không đúng");

    }

    /**
     * Get info products in display payment
     *
     * @return
     */
    public static Map<String, Cart> getInfoProductsInDisplayPayment() {
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInDisplayPayment);
        if (productQuantities.size() == 0) {
            return new HashMap<>();
        }
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText().replaceAll("\\D", "")));
        }

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDisplayPayment);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }

        List<WebElement> totalProductPrices = DriverManager.getDriver().findElements(elementTotalProductPricesInDisplayPayment);
        List<BigDecimal> valueTotalProductPrices = new ArrayList<>();
        for (WebElement totalProductPrice : totalProductPrices) {
            valueTotalProductPrices.add(convertCurrencyToBigDecimal(totalProductPrice.getText()));
        }

        Map<String, Cart> infoProductsInDisplayPayment = new HashMap<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            String name = valueProductNames.get(i);
            String quantity = " × " + valueProductQuantities.get(i);
            int position = name.indexOf(quantity);
            if (position != -1) {
                name = name.substring(0, position);
            }
            cart.setName(name);
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueTotalProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayPayment.put(name, cart);
        }
        return infoProductsInDisplayPayment;
    }


}
