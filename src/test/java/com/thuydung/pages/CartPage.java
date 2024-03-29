package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import org.openqa.selenium.By;

public class CartPage extends CommonPage {
    By messageCartEmpty = By.xpath("//h3[normalize-space()='Your Cart is empty']");
    By inputQuantity = By.xpath("//input[@name='quantity']");
    By viewProductNameinPopupAddSucceed = By.xpath("//div[@id='addToCart-modal-body']//h6[contains(@class,'fw-600')]");
    By messageUpdateCart = By.xpath("//span[@data-notify='message']");
    By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    By viewQuantityInCart = By.xpath("//section[@id='cart-summary']//input[@type='number']");

    public void verifyProductInCart(String productName) {
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        WebUI.verifyAssertTrueIsDisplayed(viewProductNameInCart, "Product is NOT displayed");
    }

    public void testAddProductToCart(String productName, String quantity) {
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By resultSearchProduct = By.xpath("//div[@id='search-content']//div[contains(text(),'" + productName + "')]");
        By viewPriceInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/following-sibling::span[contains(text(),'$')]");

        getDashboardPage().testSearchProductHaveResult(productName);
        WebUI.waitForJQueryLoad();
        WebUI.sleep(3);
        WebUI.clickElement(resultSearchProduct);
        WebUI.waitForPageLoaded();
        //Nhập số lượng sản phẩm
        WebUI.setTextAndClear(inputQuantity, quantity);
        WebUI.clickElement(ProductInfoPage.totalProductPrice);

        //Lay giá sản phẩm
        String productPriceVer1;
        if (WebUI.checkElementExist(AddProductPage.discountPriceProduct)) {
            productPriceVer1 = WebUI.getElementText(AddProductPage.discountPriceProduct).trim();
        } else {
            productPriceVer1 = WebUI.getElementText(AddProductPage.unitPriceProduct).trim();
        }
        String productPriceVer2 = getValuePrice(productPriceVer1);
        //Thêm sản phẩm vào giỏ hàng
        WebUI.sleep(3);
        WebUI.scrollToElement(OrderPage.buttonAddToCart);
        WebUI.clickElement(OrderPage.buttonAddToCart);
        WebUI.verifyAssertTrueIsDisplayed(OrderPage.popupAddToCartSucceeded, "Add to cart is failed");
        WebUI.verifyAssertTrueTextContain(viewProductNameinPopupAddSucceed, productName, "Product name is NOT correct");
        WebUI.clickElement(OrderPage.buttonCloseAddToCartPopup);
        //Kiểm tra sản phẩm đã được thêm vào giỏ hàng
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(viewProductNameInCart, "Product is NOT displayed");
        //Lay đơn giá sản phẩm
        String priceInCartVer2 = getValuePrice(WebUI.getElementText(viewPriceInCart));
        //Kiểm tra giá sản phẩm trong giỏ hàng
        WebUI.verifyAssertEqual(priceInCartVer2, productPriceVer2, "Price of product is NOT correct");
    }

    public String getValuePrice(String price) {
        String priceVer1 = price.replaceAll("[^0-9.]", "");
        String priceVer2 = "" + (int) Double.parseDouble(priceVer1);
        return priceVer2;
    }


    public void testCartEmpty() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.clickElement(LoginPage.closeAdvertisementPopup);
        WebUI.clickElement(OrderPage.buttonCart);
        WebUI.verifyAssertTrueIsDisplayed(messageCartEmpty, "My product is NOT displayed");
    }

    public void testAddOneProductToCart(String productName, String quantity) {
        testAddProductToCart(productName, quantity);
        By viewQuantityInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/following-sibling::span[contains(text(),'x')]");
        //Check quantity of product
        WebUI.verifyAssertEqual(WebUI.getElementText(viewQuantityInCart).replaceAll("\\D", ""), quantity, "Quantity of product is NOT correct");
        //Check price of product

    }

    public void testAddManyProductToCart(String productName1, String quantity1, String productName2, String quantity2) {
        if (!productName1.equals(productName2)) {
            By viewQuantityInCart1 = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName1 + "')]/following-sibling::span[contains(text(),'x')]");
            By viewQuantityInCart2 = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName2 + "')]/following-sibling::span[contains(text(),'x')]");
            testAddProductToCart(productName1, quantity1);
            WebUI.verifyAssertEqual(WebUI.getElementText(viewQuantityInCart1).replaceAll("\\D", ""), quantity1, "Quantity of product is NOT correct");
            testAddProductToCart(productName2, quantity2);
            WebUI.verifyAssertEqual(WebUI.getElementText(viewQuantityInCart2).replaceAll("\\D", ""), quantity2, "Quantity of product is NOT correct");
        } else {
            By viewQuantityInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName1 + "')]/following-sibling::span[contains(text(),'x')]");
            testAddProductToCart(productName1, quantity1);
            testAddProductToCart(productName2, quantity2);
            int quantity = Integer.parseInt(quantity1) + Integer.parseInt(quantity2);
            String sumQuantity = String.valueOf(quantity);
            WebUI.verifyAssertEqual(WebUI.getElementText(viewQuantityInCart).replaceAll("\\D", ""), sumQuantity, "Quantity of product is NOT correct");
        }

    }

    public void testCartDetail(String productName, String quantity) {
        By viewProductNameInCartDetail = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]");
        testAddOneProductToCart(productName, quantity);
        WebUI.clickElement(buttonViewCart);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(viewProductNameInCartDetail, "Product is NOT displayed");
        WebUI.verifyAssertContain(WebUI.getElementText(viewProductNameInCartDetail), productName, "Product name is NOT correct");

        WebUI.verifyAssertTrueIsDisplayed(viewQuantityInCart, "Quantity of product is NOT displayed");
        String valueQuantity = WebUI.getElementAttribute(viewQuantityInCart, "value");
        WebUI.verifyAssertEqual(valueQuantity, quantity, "Quantity of product is NOT correct");
    }

    public void testUpdateQuantityProductInCart(String productName, String quantity) {
        By totalPriceInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//span[text()='Total']/following-sibling::span");
        By priceInCart = By.xpath("//section[@id='cart-summary']//span[contains(text(),'" + productName + "')]/ancestor::li//span[text()='Price']/following-sibling::span");
        testCartDetail(productName, "1");
        WebUI.setTextAndClear(viewQuantityInCart, quantity);
        WebUI.clickElement(priceInCart);
        int valuePriceInCart = (int) Double.parseDouble(WebUI.getElementText(priceInCart).replaceAll("[^0-9.]", ""));
        //Double valueTotalPriceInCart = Double.parseDouble(WebUI.getElementText(totalPriceInCart).replaceAll("[^0-9.]", ""));
        String caculatorPrice = valuePriceInCart * Integer.parseInt(quantity) + ".00";
        WebUI.verifyAssertEqual(WebUI.getElementText(totalPriceInCart).replaceAll("[^0-9.]", ""), caculatorPrice, "Price of product is NOT correct");
    }
    public void testRemoveProductFromCart(String productName) {

        testAddOneProductToCart(productName, "1");

    }

    public void checkSubTotalPrice() {

    }

    public void removeProductFromCart(String productName) {
        By viewProductNameInCart = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]");
        By buttonRemoveProduct = By.xpath("//div[contains(@class,'nav-cart-box dropdown')]//span[contains(text(),'" + productName + "')]/ancestor::a/following-sibling::span");
        WebUI.clickElement(buttonRemoveProduct);
        if (!WebUI.checkElementExist(viewProductNameInCart)) {
            System.out.println("Product is removed");
            WebUI.verifyAssertTrueIsDisplayed(messageUpdateCart, "Message khong xuat hien");
            WebUI.verifyAssertTrueEqual(messageUpdateCart, "Item has been removed from cart", "Message khong dung");
        } else {
            System.out.println("Product is NOT removed");
        }
    }

}
