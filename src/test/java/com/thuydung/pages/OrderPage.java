package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.helpers.ExcelHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.requests.Address;
import com.thuydung.requests.Cart;
import com.thuydung.requests.OrderAmount;
import com.thuydung.requests.OrderSummary;
import com.thuydung.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.thuydung.pages.CartPage.convertCurrencyToBigDecimal;

public class OrderPage {
    private By buttonAddNewAddress = By.xpath("//div[@onclick='add_new_address()']");
    private By titleNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@id='exampleModalLabel']");
    private By titleNewAddressEdit = By.xpath("//div[@id='edit-address-modal']//h5[@id='exampleModalLabel']");
    public static By buttonAddToCart = By.xpath("//button[@onclick='addToCart()']");
    public static By popupAddToCartSucceeded = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    public static By buttonCloseAddToCartMessage = By.xpath("//span[@class = 'la-2x']");
    public static By buttonCart = By.xpath("//div[@id='cart_items']");
    private By buttonCheckoutOnCartPopup = By.xpath("//div[@id='cart_items']//a[normalize-space()='Checkout']");
    private By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    private By checkAgreeTermAndConditions = By.xpath("//span[@class='aiz-square-check']");
    private By checkboxAgreeTermAndConditions = By.xpath("//input[@id='agree_checkbox']");
    private By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    public By messageNoti = By.xpath("//span[@data-notify='message']");
    private static By subTotalPriceInDisplayPayment = By.xpath("(//th[text()='Subtotal'])/following-sibling::td//span");
    public static By priceTaxInDisplayPayment = By.xpath("(//th[text()='Tax'])/following-sibling::td//span");
    By priceTotalShippingInDisplayPayment = By.xpath("(//th[text()='Total Shipping'])/following-sibling::td//span");
    By priceTotalInDisplayPayment = By.xpath(" //tr[@class='cart-total']//strong");
    By elementProductNamesInDeliveryInfoDisplay = By.xpath("//div[@class='card-body']//img[contains(@class,'img-fit')]/parent::span/following-sibling::span");
    static By elementProductNamesInDisplayPayment = By.xpath("//tbody//td[@class='product-name']");
    static By elementTotalProductPricesInDisplayPayment = By.xpath("//tbody//td[contains(@class,'product-total')]");
    static By elementProductQuantitiesInDisplayPayment = By.xpath("//tbody//strong[@class='product-quantity']");
    By elementProductNamesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[2]/a");
    By elementProductQuantitiesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[4]");
    By elementProductVariationsInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[3]");

    By elementProductPricesInDisplayConfirm = By.xpath("//h5[normalize-space()='Order Details']/following-sibling::div/table//td[6]");
    By elementNameInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Name')]/following-sibling::td");
    By elementEmailInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Email')]/following-sibling::td");
    By elementOrderDateInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Order date')]/following-sibling::td");
    By elementShippingAddressInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Shipping address')]/following-sibling::td");
    By elementPaymentMethodInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Payment method')]/following-sibling::td");
    By elementOrderStatusInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Order status')]/following-sibling::td");
    By elementTotalOrderAmountInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Total order amount')]/following-sibling::td");
    By elementShippingMethodInOrderSummary = By.xpath("//h5[normalize-space()='Order Summary']/following-sibling::div//td[contains(text(),'Shipping:')]/following-sibling::td");
    By elementMenuCheckOut = By.xpath("//div[contains(@class,'text-primary')]");
    public static By valueNewestAddress = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'Address')]/following-sibling::span");
    public static By valueNewestCountry = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'Country')]/following-sibling::span");
    public static By valueNewestState = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'State')]/following-sibling::span");
    public static By valueNewestCity = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'City')]/following-sibling::span");
    public static By valueNewestPostalCode = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'Postal code')]/following-sibling::span");
    public static By valueNewestPhone = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//span[contains(text(),'Phone')]/following-sibling::span");
    public static By iconEllipsisInCardAddressNewest = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//i[@class='la la-ellipsis-v']");
    public static By buttonEditInCardAddressNewest = By.xpath("//div[@onclick='add_new_address()']/parent::div/preceding-sibling::div[1]//i[@class='la la-ellipsis-v']/parent::button/following-sibling::div[contains(@class, 'dropdown-menu')]/a[normalize-space()='Edit']");
    public static By inputAddYourAddress = By.xpath("//textarea[@name='address']");
    public static By inputAddYourAddressEdit = By.xpath("//div[@id='edit-address-modal']//textarea[@name='address']");
    public static By selectAddCountry = By.xpath("//div[@id='new-address-modal']//select[@data-placeholder='Select your country']/parent::div");
    public static By selectAddCountryEdit = By.xpath("//div[@id='edit-address-modal']//select[@data-placeholder='Select your country']/parent::div");
    public static By inputSearchCountry = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By inputSearchCountryEdit = By.xpath("//div[@id='edit-address-modal']//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By selectAddState = By.xpath("//select[@name='state_id']/parent::div");
    public static By selectAddStateEdit = By.xpath("//div[@id='edit-address-modal']//select[@name='state_id']/parent::div");
    public static By inputSearchState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By inputSearchStateEdit = By.xpath("//div[@id='edit-address-modal']//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By selectAddCity = By.xpath("//select[@name='city_id']/parent::div");
    public static By selectAddCityEdit = By.xpath("//div[@id='edit-address-modal']//select[@name='city_id']/parent::div");
    public static By inputSearchCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By inputSearchCityEdit = By.xpath("//div[@id='edit-address-modal']//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public static By inputAddPostalCode = By.xpath("//input[@name='postal_code']");
    public static By inputAddPostalCodeEdit = By.xpath("//div[@id='edit-address-modal']//input[@name='postal_code']");
    public static By inputAddPhoneAddress = By.xpath("//input[@name='phone']");
    public static By inputAddPhoneAddressEdit = By.xpath("//div[@id='edit-address-modal']//input[@name='phone']");
    public static By buttonSaveNewAddress = By.xpath("//button[normalize-space()='Save']");
    public static By buttonSaveNewAddressEdit = By.xpath("//div[@id='edit-address-modal']//button[normalize-space()='Save']");
    By priceCouponDiscountInDisplayPayment = By.xpath("(//th[text()='Coupon Discount'])/following-sibling::td//span");
    By buttonCouponDiscount = By.xpath("//button[contains(@id,'coupon')]");
    By elementAddressInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'Address')]/following-sibling::span");
    By elementPostalCodeInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'Postal code')]/following-sibling::span");
    By elementCityInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'City')]/following-sibling::span");
    By elementStateInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'State')]/following-sibling::span");
    By elementCountryInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'Country')]/following-sibling::span");
    By elementPhoneInShippingInfo = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'Phone')]/following-sibling::span");

    public void openShippingInfoFromURL() {
        WebUI.openURL("https://cms.anhtester.com/checkout");
        //WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
    }


    public void testOpenShippingInfoHaveProductInCart() {
        openShippingInfoFromURL();
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "2. Shipping info", "Trang hiện tại không phải trang Shipping Info");
    }

    public void testOpenShippingInfoWithoutCartEmpty() {
        openShippingInfoFromURL();
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Không có sản phẩm trong giỏ hàng");
        WebUI.verifyAssertTrueEqual(messageNoti, "Your Cart is empty", "Thông báo không có sản phẩm trong giỏ hàng không đúng");
    }

    public void testOpenShippingInfoWithoutLogin() {
        openShippingInfoFromURL();
        if (DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/users/login")) {
            LogUtils.info("Trang hien tai la: " + DriverManager.getDriver().getCurrentUrl());
            LogUtils.info("Không thể truy cập trang Shipping Info khi chưa đăng nhập");
        }
    }

    public void testCheckShippingInfoWithProfile() {
        openShippingInfoFromURL();
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout")) {
            System.out.println("Không thể truy cập trang Shipping Info");
            return;
        }
        //Shipping Info
        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(elementAddressInShippingInfo);
        List<WebElement> postalCodeInShippingInfo = DriverManager.getDriver().findElements(elementPostalCodeInShippingInfo);
        List<WebElement> cityInShippingInfo = DriverManager.getDriver().findElements(elementCityInShippingInfo);
        List<WebElement> stateInShippingInfo = DriverManager.getDriver().findElements(elementStateInShippingInfo);
        List<WebElement> countryInShippingInfo = DriverManager.getDriver().findElements(elementCountryInShippingInfo);
        List<WebElement> phoneInShippingInfo = DriverManager.getDriver().findElements(elementPhoneInShippingInfo);
        List<String> valueAddressInShippingInfo = new ArrayList<>();
        List<String> valuePostalCodeInShippingInfo = new ArrayList<>();
        List<String> valueCityInShippingInfo = new ArrayList<>();
        List<String> valueStateInShippingInfo = new ArrayList<>();
        List<String> valueCountryInShippingInfo = new ArrayList<>();
        List<String> valuePhoneInShippingInfo = new ArrayList<>();
        for (WebElement address : addressInShippingInfo) {
            valueAddressInShippingInfo.add(address.getText());
        }
        for (WebElement postalCode : postalCodeInShippingInfo) {
            valuePostalCodeInShippingInfo.add(postalCode.getText());
        }
        for (WebElement city : cityInShippingInfo) {
            valueCityInShippingInfo.add(city.getText());
        }
        for (WebElement state : stateInShippingInfo) {
            valueStateInShippingInfo.add(state.getText());
        }
        for (WebElement country : countryInShippingInfo) {
            valueCountryInShippingInfo.add(country.getText());
        }
        for (WebElement phone : phoneInShippingInfo) {
            valuePhoneInShippingInfo.add(phone.getText());
        }
        //Profile
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/profile");
        WebUI.waitForPageLoaded();
        List<WebElement> addressInProfile = DriverManager.getDriver().findElements(ProfilePage.elementAddressInProfile);
        List<WebElement> postalCodeInProfile = DriverManager.getDriver().findElements(ProfilePage.elementPostalCodeInProfile);
        List<WebElement> cityInProfile = DriverManager.getDriver().findElements(ProfilePage.elementCityInProfile);
        List<WebElement> stateInProfile = DriverManager.getDriver().findElements(ProfilePage.elementStateInProfile);
        List<WebElement> countryInProfile = DriverManager.getDriver().findElements(ProfilePage.elementCountryInProfile);
        List<WebElement> phoneInProfile = DriverManager.getDriver().findElements(ProfilePage.elementPhoneInProfile);
        List<String> valueAddressInProfile = new ArrayList<>();
        List<String> valuePostalCodeInProfile = new ArrayList<>();
        List<String> valueCityInProfile = new ArrayList<>();
        List<String> valueStateInProfile = new ArrayList<>();
        List<String> valueCountryInProfile = new ArrayList<>();
        List<String> valuePhoneInProfile = new ArrayList<>();
        for (WebElement address : addressInProfile) {
            valueAddressInProfile.add(address.getText());
        }
        for (WebElement postalCode : postalCodeInProfile) {
            valuePostalCodeInProfile.add(postalCode.getText());
        }
        for (WebElement city : cityInProfile) {
            valueCityInProfile.add(city.getText());
        }
        for (WebElement state : stateInProfile) {
            valueStateInProfile.add(state.getText());
        }
        for (WebElement country : countryInProfile) {
            valueCountryInProfile.add(country.getText());
        }
        for (WebElement phone : phoneInProfile) {
            valuePhoneInProfile.add(phone.getText());
        }
        // So sánh thông tin địa chỉ giữa Shipping Info và Profile
        WebUI.verifyAssertEquals(valueAddressInShippingInfo, valueAddressInProfile, "Địa chỉ không khớp");
        WebUI.verifyAssertEquals(valuePostalCodeInShippingInfo, valuePostalCodeInProfile, "Postal Code không khớp");
        WebUI.verifyAssertEquals(valueCityInShippingInfo, valueCityInProfile, "City không khớp");
        WebUI.verifyAssertEquals(valueStateInShippingInfo, valueStateInProfile, "State không khớp");
        WebUI.verifyAssertEquals(valueCountryInShippingInfo, valueCountryInProfile, "Country không khớp");
        WebUI.verifyAssertEquals(valuePhoneInShippingInfo, valuePhoneInProfile, "Phone không khớp");
    }

    public void testAddNewAddressInShippingInfo(String address, String country, String state, String city, String postalCode, String phone) {
        openShippingInfoFromURL();
        WebUI.scrollToElement(buttonAddNewAddress);
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.verifyElementVisible(titleNewAddress, "Popup New Address KHONG hien thi.");

        WebUI.setTextAndClear(inputAddYourAddress, address);
        WebUI.clickElement(selectAddCountry);
        WebUI.setTextAndClear(inputSearchCountry, country, Keys.ENTER);
        WebUI.clickElement(selectAddState);
        WebUI.setTextAndClear(inputSearchState, state, Keys.ENTER);
        WebUI.clickElement(selectAddCity);
        WebUI.setTextAndClear(inputSearchCity, city, Keys.ENTER);
        WebUI.setTextAndClear(inputAddPostalCode, postalCode);
        WebUI.setTextAndClear(inputAddPhoneAddress, phone);
        WebUI.clickElement(buttonSaveNewAddress);
        WebUI.waitForPageLoaded();
        WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'col-xxl-8 col-xl-10 mx-auto')]"));
        WebUI.verifyAssertTrueEqual(valueNewestAddress, address, "Địa chỉ mới không được thêm vào.");
        WebUI.verifyAssertTrueEqual(valueNewestPostalCode, postalCode, "Mã bưu chính mới không được thêm vào.");
        WebUI.verifyAssertTrueEqual(valueNewestCity, city, "Thành phố mới không được thêm vào.");
        WebUI.verifyAssertTrueEqual(valueNewestState, state, "Tỉnh/Thành phố mới không được thêm vào.");
        WebUI.verifyAssertTrueEqual(valueNewestCountry, country, "Quốc gia mới không được thêm vào.");
        WebUI.verifyAssertTrueEqual(valueNewestPhone, phone, "Số điện thoại mới không được thêm vào.");
    }
    public void testAddNewAddressInvalidInShippingInfo(String address, String country, String state, String city, String postalCode, String phone) {
        openShippingInfoFromURL();
        WebUI.scrollToElement(buttonAddNewAddress);
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.verifyElementVisible(titleNewAddress, "Popup New Address KHONG hien thi.");

        WebUI.setTextAndClear(inputAddYourAddress, address);
        WebUI.clickElement(selectAddCountry);
        WebUI.setTextAndClear(inputSearchCountry, country, Keys.ENTER);
        WebUI.clickElement(selectAddState);
        WebUI.setTextAndClear(inputSearchState, state, Keys.ENTER);
        WebUI.clickElement(selectAddCity);
        WebUI.setTextAndClear(inputSearchCity, city, Keys.ENTER);
        WebUI.setTextAndClear(inputAddPostalCode, postalCode);
        WebUI.setTextAndClear(inputAddPhoneAddress, phone);
        WebUI.clickElement(buttonSaveNewAddress);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(inputAddYourAddress, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqualMessageHTML(inputAddYourAddress, "Please fill out this field.", "Thong bao khong chinh xac");
    }

    public void testEditAddressInShippingInfo(String address, String country, String state, String city, String postalCode, String phone) {
        openShippingInfoFromURL();
        WebUI.scrollToElement(buttonAddNewAddress);
        WebUI.clickElement(iconEllipsisInCardAddressNewest);
        WebUI.clickElement(buttonEditInCardAddressNewest);
        WebUI.waitForJQueryLoad();

        WebUI.verifyElementVisible(titleNewAddressEdit, "Popup New Address KHONG hien thi.");

        WebUI.setTextAndClear(inputAddYourAddressEdit, address);
        WebUI.clickElement(selectAddCountryEdit);
        WebUI.setTextAndClear(inputSearchCountryEdit, country, Keys.ENTER);
        WebUI.clickElement(selectAddStateEdit);
        WebUI.setTextAndClear(inputSearchStateEdit, state, Keys.ENTER);
        WebUI.clickElement(selectAddCityEdit);
        WebUI.setTextAndClear(inputSearchCityEdit, city, Keys.ENTER);
        WebUI.setTextAndClear(inputAddPostalCodeEdit, postalCode);
        WebUI.setTextAndClear(inputAddPhoneAddressEdit, phone);
        WebUI.clickElement(buttonSaveNewAddressEdit);
        WebUI.waitForPageLoaded();
        WebUI.scrollToElementToBottom(By.xpath("//div[contains(@class,'col-xxl-8 col-xl-10 mx-auto')]"));
        WebUI.verifyAssertTrueEqual(valueNewestAddress, address, "Địa chỉ mới không được cập nhật.");
        WebUI.verifyAssertTrueEqual(valueNewestPostalCode, postalCode, "Mã bưu chính mới không được cập nhật.");
        WebUI.verifyAssertTrueEqual(valueNewestCity, city, "Thành phố mới không được cập nhật.");
        WebUI.verifyAssertTrueEqual(valueNewestState, state, "Tỉnh/Thành phố mới không được cập nhật.");
        WebUI.verifyAssertTrueEqual(valueNewestCountry, country, "Quốc gia mới không được cập nhật.");
        WebUI.verifyAssertTrueEqual(valueNewestPhone, phone, "Số điện thoại mới không được cập nhật.");
    }
    public void testEditAddressInvalidInShippingInfo(String address, String country, String state, String city, String postalCode, String phone) {
        openShippingInfoFromURL();
        WebUI.scrollToElement(buttonAddNewAddress);
        WebUI.clickElement(iconEllipsisInCardAddressNewest);
        WebUI.clickElement(buttonEditInCardAddressNewest);
        WebUI.waitForJQueryLoad();

        WebUI.verifyElementVisible(titleNewAddressEdit, "Popup New Address KHONG hien thi.");

        WebUI.setTextAndClear(inputAddYourAddressEdit, address);
        WebUI.clickElement(selectAddCountryEdit);
        WebUI.setTextAndClear(inputSearchCountryEdit, country, Keys.ENTER);
        WebUI.clickElement(selectAddStateEdit);
        WebUI.setTextAndClear(inputSearchStateEdit, state, Keys.ENTER);
        WebUI.clickElement(selectAddCityEdit);
        WebUI.setTextAndClear(inputSearchCityEdit, city, Keys.ENTER);
        WebUI.setTextAndClear(inputAddPostalCodeEdit, postalCode);
        WebUI.setTextAndClear(inputAddPhoneAddressEdit, phone);
        WebUI.clickElement(buttonSaveNewAddressEdit);
        WebUI.waitForPageLoaded();
        WebUI.checkHTML5MessageWithValueInvalid(inputAddYourAddressEdit, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqualMessageHTML(inputAddYourAddressEdit, "Please fill out this field.", "Thong bao khong chinh xac");
    }

    public void selectAddressInShippingInfo(String index) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout")) {
            openShippingInfoFromURL();
        }
        //By elementAddress = By.xpath("//input[@name='address_id']/following-sibling::span//span[contains(text(),'Address')]/following-sibling::span");
        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(elementAddressInShippingInfo);
        int size = addressInShippingInfo.size();
        if (Integer.parseInt(index) > size) {
            LogUtils.info("Không tồn tại địa chỉ thứ " + index + " trong Shipping Info");
            return;
        }
        By buttonSelectAddress = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span");
        WebUI.clickElement(buttonSelectAddress);
        WebUI.waitForPageLoaded();
    }

    public Address getInfoAddressSelected(String index) {

        By addressSelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'Address')]/following-sibling::span");
        By postalCodeSelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'Postal code')]/following-sibling::span");
        By citySelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'City')]/following-sibling::span");
        By stateSelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'State')]/following-sibling::span");
        By countrySelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'Country')]/following-sibling::span");
        By phoneSelected = By.xpath("(//input[@name='address_id'])[" + index + "]/following-sibling::span//span[contains(text(),'Phone')]/following-sibling::span");
        Address selectedAddress = new Address(WebUI.getElementText(addressSelected), WebUI.getElementText(citySelected), WebUI.getElementText(stateSelected), WebUI.getElementText(countrySelected), WebUI.getElementText(postalCodeSelected), WebUI.getElementText(phoneSelected));
        return selectedAddress;
    }

    public Address getSelectedAddress() {
        //List<Address> listAddressInShippingInfo = getAddressInShippingInfo();
        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(By.xpath("//input[@type='radio']"));
        for (int i = 1; i <= addressInShippingInfo.size(); i++) {
            if (DriverManager.getDriver().findElement(By.xpath("(//input[@type='radio'])[" + i + "]")).isSelected()) {
                return getInfoAddressSelected(String.valueOf(i));
            }
        }
        return null;
    }


    //test chon dia chi tai man hinh Shipping Info khi co dia chi
    public void testSelectAddressInShippingInfoWithAddress(String index) {
        selectAddressInShippingInfo(index);
        By verifyCheckedAddress = By.xpath("(//input[@type='radio'])[" + index + "]");
        String checkedAddress = String.valueOf(DriverManager.getDriver().findElement(verifyCheckedAddress).isSelected());
        WebUI.verifyAssertEqual(checkedAddress, "true", "Địa chỉ không được chọn");
    }

    //test khi truy cap trang Delivery info ma khong co dia chi trong Shipping Info
    public void testOpenDeliveryInfoWithoutAddress() {
        openShippingInfoFromURL();
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Please add shipping address", "Thong bao khong chinh xac");
    }

    //test khi truy cap trang Delivery info tu URL
    public void testOpenDeliveryInfoWithURL() {
        WebUI.openURL("https://cms.anhtester.com/checkout/delivery_info");
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Please add shipping address", "Thong bao khong chinh xac");
    }

    public void openDeliveryInfoWithShippingInfo() {
        openShippingInfoFromURL();
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
    }

    public void testOpenDeliveryInfoWithShippingInfo() {
        openDeliveryInfoWithShippingInfo();
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "3. Delivery info", "Trang hiện tại không phải trang Delivery Info");
    }

    public List<String> getProductsNameInDeliveryInfoDisplay() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDeliveryInfoDisplay);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        return valueProductNames;
    }

    public void testProductInDeliveryInfoDisplay() {
        if(!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/delivery_info")){
            openDeliveryInfoWithShippingInfo();
        }
        //Check info order in display delivery info
        List<String> productNamesInDisplayDeliveryInfo = getProductsNameInDeliveryInfoDisplay();
        WebUI.clickElement(buttonCart);
        WebUI.waitForJQueryLoad();
        List<String> productNamesInCart = CartPage.getProductsNameInCart();
        WebUI.verifyAssertEquals(productNamesInDisplayDeliveryInfo, productNamesInCart, "Danh sách sản phẩm không khớp với giỏ hàng.");

    }

    public void selectShippingMethod(String shippingMethod) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/delivery_info")) {
            openDeliveryInfoWithShippingInfo();
        }
        By buttonSelectShippingMethod = By.xpath("//span[text()='" + shippingMethod + "']/ancestor::div[@class='col-6']");
        List<WebElement> shippingMethods = DriverManager.getDriver().findElements(buttonSelectShippingMethod);
        for (int i = 1; i <= shippingMethods.size(); i++) {
            WebUI.clickElement(By.xpath("(//span[text()='" + shippingMethod + "']/ancestor::div[@class='col-6'])[" + i + "]"));
        }
        WebUI.waitForPageLoaded();
    }

    public void testSelectShippingMethod(String shippingMethod) {
        selectShippingMethod(shippingMethod);
        By verifyCheckedShippingMethod = By.xpath("//span[text()='" + shippingMethod + "']/ancestor::div[@class='col-6']//input[@type='radio']");
        String checkedShippingMethod = "Shipping Method " + shippingMethod + " da duoc chon: " + DriverManager.getDriver().findElement(verifyCheckedShippingMethod).isSelected();
        WebUI.verifyAssertEqual(checkedShippingMethod, "Shipping Method " + shippingMethod + " da duoc chon: true", "Phương thức vận chuyển không được chọn");
    }

    public void openPaymentInfoFromShippingInfoDisplay() {
        openDeliveryInfoWithShippingInfo();
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
    }

    public void testOpenPaymentInfoFromShippingInfoDisplay() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "4. Payment", "Trang hiện tại không phải trang Payment");
    }

    public void openPaymentInfoFromURL() {
        WebUI.openURL("https://cms.anhtester.com/checkout/payment_select");
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(By.xpath("//h2[normalize-space()='The server returned a \"405 Method Not Allowed\".']"), "Khong xuat hien thong bao khong thể truy cập trang Payment Info tu URL");
    }

    public void testInfoOrderInPaymentInfo() {
        if(!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/payment_select")){
            openPaymentInfoFromShippingInfoDisplay();
        }
        //check quantity item product in summary with cart
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/cart");
        WebUI.waitForPageLoaded();
        List<Cart> cartDetails = CartPage.getCartDetailTemp2();
        String subTotalPriceInCart = WebUI.getElementText(CartPage.subPriceInCartDetail);
        String quantityItemProductInCart = String.valueOf(CartPage.getQuantityItemProductInCartDetail());
        DriverManager.getDriver().switchTo().window(mainWindow);
        String quantityItemProductInDisplayPayment = WebUI.getElementText(By.xpath("//h3[text()='Summary']/following-sibling::div/span")).replaceAll("\\D", "");
        WebUI.verifyAssertEquals(quantityItemProductInCart, quantityItemProductInDisplayPayment, "Số lượng sản phẩm không khớp.");

        //Check info order in display payment with cart

        List<Cart> infoProductsInDisplayPayment = getInfoProductsInDisplayPayment();
        for (int i = 0; i < cartDetails.size(); i++) {
            WebUI.verifyAssertContain( cartDetails.get(i).getNameProduct(), infoProductsInDisplayPayment.get(i).getNameProduct(), "Ten san pham khong khop so voi gio hang.");
            WebUI.verifyAssertEquals(infoProductsInDisplayPayment.get(i).getPrice(), cartDetails.get(i).getPrice(), "Gia san pham khong khop so voi gio hang.");
            WebUI.verifyAssertEquals(infoProductsInDisplayPayment.get(i).getQuantity(), cartDetails.get(i).getQuantity(), "So luong san pham khong khop so voi gio hang.");
        }
        BigDecimal subTotalPriceInDisplayPaymentCheck = BigDecimal.ZERO;
        for (int j = 0; j <infoProductsInDisplayPayment.size(); j++) {
            BigDecimal quantity = new BigDecimal(infoProductsInDisplayPayment.get(j).getQuantity());
            BigDecimal price = infoProductsInDisplayPayment.get(j).getPrice().multiply(quantity);
            subTotalPriceInDisplayPaymentCheck = subTotalPriceInDisplayPaymentCheck.add(price);
        }
        BigDecimal valueSubTotalPriceInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        WebUI.verifyAssertEquals(valueSubTotalPriceInDisplayPayment, subTotalPriceInDisplayPaymentCheck, "Sub total price không khớp so voi gio hang.");
    }

    public BigDecimal calculatorTotalPriceInPaymentInfo() {
        BigDecimal valueSubTotalPriceInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        BigDecimal valuePriceTaxInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTaxInDisplayPayment));
        BigDecimal valuePriceTotalShippingInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalShippingInDisplayPayment));
        BigDecimal valuePriceTotal = valueSubTotalPriceInDisplayPayment.add(valuePriceTaxInDisplayPayment).add(valuePriceTotalShippingInDisplayPayment);
        if (WebUI.getElementText(buttonCouponDiscount).equals("Change Coupon")) {
            BigDecimal valuePriceCouponDiscountInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceCouponDiscountInDisplayPayment));
            BigDecimal valuePriceTotalHaveDiscount = valuePriceTotal.subtract(valuePriceCouponDiscountInDisplayPayment);
            return valuePriceTotalHaveDiscount;
        }
        return valuePriceTotal;
    }

    public void applyCouponDiscount(String couponCode) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/payment_select")) {
            openPaymentInfoFromShippingInfoDisplay();
        }
        By inputCouponDiscount = By.xpath("//input[@name='code']");
        By buttonApplyCoupon = By.xpath("//button[@id='coupon-apply']");
        if (WebUI.checkElementExist(By.xpath("//button[@id='coupon-remove']"))) {
            WebUI.clickElement(buttonCouponDiscount);
            WebUI.waitForElementInvisible(inputCouponDiscount);
        }
        WebUI.setTextAndClear(inputCouponDiscount, couponCode);
        WebUI.clickElement(buttonApplyCoupon);
        WebUI.waitForPageLoaded();
    }

    public void testApplyCouponDiscountValid(String couponCode) {
        applyCouponDiscount(couponCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Coupon has been applied", "Thong bao khong chinh xac");
        WebUI.verifyAssertTrueIsDisplayed(priceCouponDiscountInDisplayPayment, "Khong hien thi gia tri Coupon Discount");
        //check tổng tien o trang payment
        checkTotalPriceInPayment();
    }

    public void testApplyCouponDiscountExpired(String couponCode) {
        applyCouponDiscount(couponCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Coupon expired!", "Thong bao khong chinh xac");
        //check tổng tien o trang payment
        checkTotalPriceInPayment();
    }

    public void testApplyCouponDiscountNotExist(String couponCode) {
        applyCouponDiscount(couponCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Invalid coupon!", "Thong bao khong chinh xac");
        //check tổng tien o trang payment
        checkTotalPriceInPayment();
    }


    public void selectAgreeTerms() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(buttonCompleteOrder);
        WebUI.clickElement(checkAgreeTermAndConditions);
    }

    public void testSelectAgreeTerms() {
        selectAgreeTerms();
        WebUI.sleep(1);
        String verifyCheckedAgreeTerms = "Checkbox dong y dieu khoan da check: " + DriverManager.getDriver().findElement(checkboxAgreeTermAndConditions).isSelected();
        WebUI.verifyAssertEqual(verifyCheckedAgreeTerms, "Checkbox dong y dieu khoan da check: true", "Checkbox dong y dieu khoan chua duoc check");
    }

    public void testNotSelectAgreeTerms() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "You need to agree with our policies", "Thong bao khong chinh xac");
    }

    public void choosePaymentMethodCashOnDelivery() {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/payment_select")) {
            openPaymentInfoFromShippingInfoDisplay();
        }
        if (!DriverManager.getDriver().findElement(checkboxAgreeTermAndConditions).isSelected()) {
            WebUI.scrollToElement(checkAgreeTermAndConditions);
            WebUI.clickElement(checkAgreeTermAndConditions);
        }
        By buttonCashOnDelivery = By.xpath("//span[contains(text(),'Cash on Delivery')]/ancestor::div[@class='col-6 col-md-4']");
        WebUI.clickElement(buttonCashOnDelivery);
        WebUI.waitForPageLoaded();
    }

    public void testChoosePaymentMethodCashOnDelivery() {
        choosePaymentMethodCashOnDelivery();
        By verifyCheckedPaymentMethod = By.xpath("//span[contains(text(),'Cash on Delivery')]/ancestor::div[@class='col-6 col-md-4']//input[@type='radio']");
        String checkedPaymentMethod = "Phuong thuc thanh toan Cash on Delivery da duoc chon: " + DriverManager.getDriver().findElement(verifyCheckedPaymentMethod).isSelected();
        WebUI.verifyAssertEqual(checkedPaymentMethod, "Phuong thuc thanh toan Cash on Delivery da duoc chon: true", "Phuong thuc thanh toan khong duoc chon");
    }

    public void addAdditaionalInfo(String noteForOrder) {
        if (!DriverManager.getDriver().findElement(checkboxAgreeTermAndConditions).isSelected()) {
            WebUI.scrollToElement(checkAgreeTermAndConditions);
            WebUI.clickElement(checkAgreeTermAndConditions);
        }
        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
    }

    public void testClickInTermsAndConditions() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkAgreeTermAndConditions);
        By linkTermsAndConditions = By.xpath("//a[contains(text(),'terms and conditions')]");
        WebUI.clickElement(linkTermsAndConditions);
        WebUI.waitForPageLoaded();
        By elementTermsAndConditions = By.xpath("//h1[normalize-space()='Term Conditions Page']");
        WebUI.verifyAssertTrueIsDisplayed(elementTermsAndConditions, "Khong hien thi trang Terms and Conditions");
    }

    public void testClickInPrivacyPolicy() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkAgreeTermAndConditions);
        By linkPrivacyPolicy = By.xpath("//a[contains(text(),'privacy policy')]");
        WebUI.clickElement(linkPrivacyPolicy);
        WebUI.waitForPageLoaded();
        By elementPrivacyPolicy = By.xpath("//h1[normalize-space()='Privacy Policy Page']");
        WebUI.verifyAssertTrueIsDisplayed(elementPrivacyPolicy, "Khong hien thi trang Privacy Policy");
    }

    public void testOpenConfirmOrderFromURL() {
        WebUI.openURL("https://cms.anhtester.com/checkout/order-confirmed");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(By.xpath("//h1[normalize-space()='Page Not Found!']"), "Khong xuat hien thong bao khong thể truy cập trang Confirm Order tu URL");
    }

    public void openConfirmOrderFromShippingInfo() {
        selectAgreeTerms();
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonCompleteOrder);
    }

    public void testOpenConfirmOrderFromShippingInfo() {
        openConfirmOrderFromShippingInfo();
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "5. Confirmation", "Trang hiện tại không phải trang Confirmation");
    }

    public void testOrderSuccess() {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/order-confirmed")) {
            openConfirmOrderFromShippingInfo();
        }
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Đơn hàng thất bại");
        WebUI.verifyAssertTrueEqual(messageNoti, "Your order has been placed successfully", "Thông báo đơn hàng thành công không đúng");
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "5. Confirmation", "Trang hiện tại không phải trang Confirmation");
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Khong xuat hien message cam on khach hang da dat hang");
    }

    public void testOrderSummaryInConfirmDisplay() {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/order-confirmed")) {
            openConfirmOrderFromShippingInfo();
        }
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        WebUI.openURL("https://cms.anhtester.com/profile");
        WebUI.waitForPageLoaded();
        String nameInCustomerProfile = WebUI.getElementAttribute(ProfilePage.inputName, "value");
        String emailInCustomerProfile = WebUI.getElementAttribute(ProfilePage.inputEmail, "value");

        DriverManager.getDriver().close();

        DriverManager.getDriver().switchTo().window(mainWindow);
        WebUI.scrollToElement(By.xpath("//h5[text()='Order Summary']/parent::div"));
        WebUI.verifyAssertTrueEqual(elementNameInOrderSummary, nameInCustomerProfile, "Tên khách hàng không khớp");
        WebUI.verifyAssertTrueEqual(elementEmailInOrderSummary, emailInCustomerProfile, "Email khách hàng không khớp");
        WebUI.verifyAssertTrueEqual(elementOrderStatusInOrderSummary, "Pending", "Trạng thái đơn hàng không khớp");
        WebUI.verifyAssertTrueEqual(elementShippingMethodInOrderSummary, "Flat shipping rate", "Chính sách van chuyen khong khop");
        WebUI.verifyAssertTrueEqual(elementPaymentMethodInOrderSummary, "Cash on Delivery", "Phuong thuc thanh toan khong khop");

    }

    public OrderSummary getOrderSummaryInConfirmDisplay() {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/checkout/order-confirmed")) {
            openConfirmOrderFromShippingInfo();
        }
        String orderDateInOrderSummary = WebUI.getElementText(elementOrderDateInOrderSummary);
        String nameInOrderSummary = WebUI.getElementText(elementNameInOrderSummary);
        String emailInOrderSummary = WebUI.getElementText(elementEmailInOrderSummary);
        String orderStatusInOrderSummary = WebUI.getElementText(elementOrderStatusInOrderSummary);
        String shippingMethodInOrderSummary = WebUI.getElementText(elementShippingMethodInOrderSummary);
        String paymentMethodInOrderSummary = WebUI.getElementText(elementPaymentMethodInOrderSummary);
        String shippingAddressInOrderSummary = WebUI.getElementText(elementShippingAddressInOrderSummary);
        String shippingAddress = shippingAddressInOrderSummary.split(", ")[0];
        String shippingCity = shippingAddressInOrderSummary.split(", ")[1];
        String shippingState = "";
        String shippingCountry = shippingAddressInOrderSummary.split(", ")[2];
        String shippingPostalCode = "";
        String shippingPhone = "";
        OrderSummary orderSummary = new OrderSummary(orderDateInOrderSummary, nameInOrderSummary, emailInOrderSummary, orderStatusInOrderSummary, shippingMethodInOrderSummary, paymentMethodInOrderSummary, shippingAddress, shippingCity, shippingState, shippingCountry, shippingPostalCode, shippingPhone);
        return orderSummary;
    }

    public OrderSummary getOrderSummaryInHistoryOrder() {
        By elementOrderDateInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Order date:']/following-sibling::td");
        By elementNameInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Customer:']/following-sibling::td");
        By elementEmailInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Email:']/following-sibling::td");
        By elementOrderStatusInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Order status:']/following-sibling::td");
        By elementShippingMethodInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Shipping method:']/following-sibling::td");
        By elementPaymentMethodInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Payment method:']/following-sibling::td");
        By elementShippingAddressInHistoryOrder = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Shipping address:']/following-sibling::td");

        String valueShippingAddress = WebUI.getElementText(elementShippingAddressInHistoryOrder).split(" - ")[0].split(", ")[0];
        String valueShippingCity = WebUI.getElementText(elementShippingAddressInHistoryOrder).split(" - ")[0].split(", ")[1];
        String valueShippingState = WebUI.getElementText(elementShippingAddressInHistoryOrder).split(" - ")[0].split(", ")[2];
        String valueShippingPostalCode = WebUI.getElementText(elementShippingAddressInHistoryOrder).split(" - ")[1].split(", ")[0];
        String valueShippingCountry = WebUI.getElementText(elementShippingAddressInHistoryOrder).split(" - ")[1].split(", ")[1];
        String valueShippingPhone = "";
        String valueOrderDateInHistoryOrder = WebUI.getElementText(elementOrderDateInHistoryOrder);
        String valueNameInHistoryOrder = WebUI.getElementText(elementNameInHistoryOrder);
        String valueEmailInHistoryOrder = WebUI.getElementText(elementEmailInHistoryOrder);
        String valueOrderStatusInHistoryOrder = WebUI.getElementText(elementOrderStatusInHistoryOrder);
        String valueShippingMethodInHistoryOrder = WebUI.getElementText(elementShippingMethodInHistoryOrder);
        String valuePaymentMethodInHistoryOrder = WebUI.getElementText(elementPaymentMethodInHistoryOrder);

        OrderSummary orderSummary = new OrderSummary(valueOrderDateInHistoryOrder, valueNameInHistoryOrder, valueEmailInHistoryOrder, valueOrderStatusInHistoryOrder, valueShippingMethodInHistoryOrder, valuePaymentMethodInHistoryOrder, valueShippingAddress, valueShippingCity, valueShippingState, valueShippingCountry, valueShippingPostalCode, valueShippingPhone);
        return orderSummary;
    }


    public List<Cart> getInfoOrderDetailInDisplayConfirm() {
        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDisplayConfirm);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productVariations = DriverManager.getDriver().findElements(elementProductVariationsInDisplayConfirm);
        List<String> valueProductVariations = new ArrayList<>();
        for (WebElement productVariation : productVariations) {
            valueProductVariations.add(productVariation.getText());
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
        // check product name, quantity, price
        List<Cart> infoProductsInDisplayConfirm = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setNameVariant(valueProductVariations.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayConfirm.add(cart);
        }
        return infoProductsInDisplayConfirm;
    }

    public List<Cart> getInfoOrderDetailInDisplayConfirmWithOrderCode(String orderCode) {
        By elementProductNamesInDisplayConfirm = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//td[2]/a");
        By elementProductVariationsInDisplayConfirm = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//td[3]");
        By elementProductQuantitiesInDisplayConfirm = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//td[4]");
        By elementProductPricesInDisplayConfirm = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//td[6]");

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInDisplayConfirm);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productVariations = DriverManager.getDriver().findElements(elementProductVariationsInDisplayConfirm);
        List<String> valueProductVariations = new ArrayList<>();
        for (WebElement productVariation : productVariations) {
            valueProductVariations.add(productVariation.getText());
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
        // check product name, quantity, price
        List<Cart> infoProductsInDisplayConfirm = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setNameVariant(valueProductVariations.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayConfirm.add(cart);
        }
        return infoProductsInDisplayConfirm;
    }


    public List<Cart> getInfoOrderDetailInHistoryOrder(String orderCode) {

        By elementProductNamesInHistoryOrder = By.xpath("//h1[text()='Order Id: " + orderCode + "']/ancestor::div[contains(@class,'aiz-user-panel')]//h5[text()='Order Details']/ancestor::div[@class='card']//table//td[2]/a");
        By elementProductVariationsInHistoryOrder = By.xpath("//h1[text()='Order Id: " + orderCode + "']/ancestor::div[contains(@class,'aiz-user-panel')]//h5[text()='Order Details']/ancestor::div[@class='card']//table//td[3]");
        By elementProductQuantitiesInHistoryOrder = By.xpath("//h1[text()='Order Id: " + orderCode + "']/ancestor::div[contains(@class,'aiz-user-panel')]//h5[text()='Order Details']/ancestor::div[@class='card']//table//td[4]");
        By elementProductPricesInHistoryOrder = By.xpath("//h1[text()='Order Id: " + orderCode + "']/ancestor::div[contains(@class,'aiz-user-panel')]//h5[text()='Order Details']/ancestor::div[@class='card']//table//td[6]");

        List<WebElement> productNames = DriverManager.getDriver().findElements(elementProductNamesInHistoryOrder);
        List<String> valueProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            valueProductNames.add(productName.getText());
        }
        List<WebElement> productVariations = DriverManager.getDriver().findElements(elementProductVariationsInHistoryOrder);
        List<String> valueProductVariations = new ArrayList<>();
        for (WebElement productVariation : productVariations) {
            valueProductVariations.add(productVariation.getText());
        }
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInHistoryOrder);
        List<Integer> valueProductQuantities = new ArrayList<>();
        for (WebElement productQuantity : productQuantities) {
            valueProductQuantities.add(Integer.parseInt(productQuantity.getText()));
        }
        List<WebElement> productPrices = DriverManager.getDriver().findElements(elementProductPricesInHistoryOrder);
        List<BigDecimal> valueProductPrices = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            valueProductPrices.add(convertCurrencyToBigDecimal(productPrice.getText()));
        }
        // check product name, quantity, price
        List<Cart> infoProductsInDisplayConfirm = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setNameProduct(valueProductNames.get(i));
            cart.setNameVariant(valueProductVariations.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayConfirm.add(cart);
        }
        return infoProductsInDisplayConfirm;
    }


    public void checkInfoPriceInConfirmDisplay() {
        By elementOrderDetailInDisplayConfirm = By.xpath("//div[@class='card-body']");
        List<WebElement> orders = DriverManager.getDriver().findElements(elementOrderDetailInDisplayConfirm);
        BigDecimal totalOrderAmount = BigDecimal.ZERO;
        for (int i = 1; i <= orders.size(); i++) {
            BigDecimal totalOrder = BigDecimal.ZERO;
            By elementOrderCodeInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/span");
            By divOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']");
            WebUI.scrollToElement(divOrderDetail);
            String orderCode = WebUI.getElementText(elementOrderCodeInOrderDetail);
            By elementProductNamesInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//h5[normalize-space()='Order Details']/following-sibling::div/table//td[2]/a");
            By elementSubTotalInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Subtotal']/following-sibling::td/span");
            By elementTaxInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Tax']/following-sibling::td/span");
            By elementShippingInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Shipping']/following-sibling::td/span");
            By elementCouponDiscountInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//th[text()='Coupon Discount']/following-sibling::td/span");
            By elementTotalInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//table//span[text()='Total']/ancestor::th/following-sibling::td//span");
            By elementProductPricesInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']//h5[normalize-space()='Order Details']/following-sibling::div/table//td[6]");

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

            WebUI.verifyAssertEquals(valueSubTotalInDisplayConfirm, valueSubTotal, "Subtotal của order code: " + orderCode + " không đúng tại màn confirm đơn hàng.");
            BigDecimal valueTaxInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTaxInOrderDetail));
            BigDecimal valueShippingInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementShippingInOrderDetail));
            BigDecimal valueCouponDiscountInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementCouponDiscountInOrderDetail));
            BigDecimal valueTotalInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalInOrderDetail));
            totalOrder = totalOrder.add(valueSubTotal).add(valueTaxInOrderDetail).add(valueShippingInOrderDetail).subtract(valueCouponDiscountInOrderDetail);
            WebUI.verifyAssertEquals(valueTotalInOrderDetail, totalOrder, "Tổng giá tiền của order code: " + orderCode + " không đúng tại màn confirm đơn hàng.");
            totalOrderAmount = totalOrderAmount.add(totalOrder);
        }
        WebUI.scrollToElement(By.xpath("//h5[text()='Order Summary']/parent::div"));
        //check total order amount
        WebUI.verifyAssertEquals(convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalOrderAmountInOrderSummary)), totalOrderAmount, "Tổng giá tiền không đúng tại màn confirm đơn hàng.");
    }

    public OrderAmount getOrderAmountInOrderDetail(String orderCode) {
        By elementSubTotalInOrderDetail = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//th[text()='Subtotal']/following-sibling::td/span");
        By elementTaxInOrderDetail = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//th[text()='Tax']/following-sibling::td/span");
        By elementShippingInOrderDetail = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//th[text()='Shipping']/following-sibling::td/span");
        By elementCouponDiscountInOrderDetail = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//th[text()='Coupon Discount']/following-sibling::td/span");
        By elementTotalInOrderDetail = By.xpath("//h2[text()='Order Code: ']/span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//span[text()='Total']/parent::th/following-sibling::td//span");

        BigDecimal valueSubTotalInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementSubTotalInOrderDetail));
        BigDecimal valueTaxInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTaxInOrderDetail));
        BigDecimal valueShippingInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementShippingInOrderDetail));
        BigDecimal valueCouponDiscountInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementCouponDiscountInOrderDetail));
        BigDecimal valueTotalInOrderDetail = convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalInOrderDetail));
        OrderAmount orderAmount = new OrderAmount(valueSubTotalInOrderDetail, valueTaxInOrderDetail, valueShippingInOrderDetail, valueCouponDiscountInOrderDetail, valueTotalInOrderDetail);
        return orderAmount;
    }

    public OrderAmount getOrderAmountInHistoryOrder() {
        By elementSubTotalInHistoryOrder = By.xpath("//td[normalize-space()='Subtotal']/following-sibling::td/span");
        By elementTaxInHistoryOrder = By.xpath("//td[normalize-space()='Tax']/following-sibling::td/span");
        By elementShippingInHistoryOrder = By.xpath("//td[normalize-space()='Shipping']/following-sibling::td/span");
        By elementCouponDiscountInHistoryOrder = By.xpath("//td[normalize-space()='Coupon']/following-sibling::td/span");
        By elementTotalInHistoryOrder = By.xpath("//td[normalize-space()='Total']/following-sibling::td//span");

        BigDecimal valueSubTotalInHistoryOrder = convertCurrencyToBigDecimal(WebUI.getElementText(elementSubTotalInHistoryOrder));
        BigDecimal valueTaxInHistoryOrder = convertCurrencyToBigDecimal(WebUI.getElementText(elementTaxInHistoryOrder));
        BigDecimal valueShippingInHistoryOrder = convertCurrencyToBigDecimal(WebUI.getElementText(elementShippingInHistoryOrder));
        BigDecimal valueCouponDiscountInHistoryOrder = convertCurrencyToBigDecimal(WebUI.getElementText(elementCouponDiscountInHistoryOrder));
        BigDecimal valueTotalInHistoryOrder = convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalInHistoryOrder));
        OrderAmount orderAmount = new OrderAmount(valueSubTotalInHistoryOrder, valueTaxInHistoryOrder, valueShippingInHistoryOrder, valueCouponDiscountInHistoryOrder, valueTotalInHistoryOrder);
        return orderAmount;
    }

    public void cancelOrder(String orderCode) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/purchase_history")) {
            WebUI.openURL("https://cms.anhtester.com/purchase_history");
        }
        By buttonCancelOrder = By.xpath("//a[text()='" + orderCode + "']/ancestor::tr//a[@title='Cancel']");
        WebUI.clickElement(buttonCancelOrder);
        WebUI.waitForJQueryLoad();
        By confirmCancelOrder = By.xpath("//a[@id='delete-link']");
        WebUI.clickElement(confirmCancelOrder);
        WebUI.waitForPageLoaded();
    }

    public void testCancelOrder(String orderCode) {
        cancelOrder(orderCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Order has been canceled successfully", "Thong bao khong chinh xac");
        By elementOrderStatusInHistoryOrder = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr/td[4]");
        WebUI.verifyAssertTrueTextContain(elementOrderStatusInHistoryOrder, "Cancelled", "Trang thai don hang khong chinh xac");
        By viewOrderDetail = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr//a[@title='Order Details']");
        WebUI.clickElement(viewOrderDetail);
        WebUI.waitForPageLoaded();
        By elementOrderStatusInOrderDetail = By.xpath("//h5[text()='Order Summary']/ancestor::div[@class='card']//td[text()='Order status:']/following-sibling::td");
        WebUI.verifyAssertTrueTextContain(elementOrderStatusInOrderDetail, "Cancelled", "Trang thai don hang khong chinh xac");
    }

    public void checkOutOrder(String noteForOrder, String couponCode) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/cart")) {
            WebUI.openURL("https://cms.anhtester.com/cart");
        }
        List<Cart> currentCart = CartPage.getCartDetailTemp2();
        if (currentCart.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(CartPage.messageCartEmptyInCartDetail, "Không có sản phẩm trong giỏ hàng");
            WebUI.verifyAssertTrueEqual(CartPage.messageCartEmptyInCartDetail, "Your Cart is empty", "Thông báo không có sản phẩm trong giỏ hàng không đúng");
            CartPage.addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
            currentCart = CartPage.getCartDetailTemp2();
        }
        WebUI.clickElement(buttonContinueToShipping);
        selectAddressInShippingInfo("3");
        WebUI.sleep(5);
        Address addressSelected = getSelectedAddress();
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
        selectShippingMethod("Home Delivery");
        testProductInDeliveryInfoDisplay();

        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();

        applyCouponDiscount(couponCode);

        //check tổng tien o trang payment
        checkTotalPriceInPayment();

        choosePaymentMethodCashOnDelivery();
        addAdditaionalInfo(noteForOrder);

        WebUI.scrollToElement(buttonCompleteOrder);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        //Check order success
        WebUI.verifyAssertTrueEqual(elementMenuCheckOut, "5. Confirmation", "Trang hiện tại không phải trang Confirmation");
        testOrderSuccess();
        //Check order summary
        testOrderSummaryInConfirmDisplay();

        String valueShippingAddressInOrderSummaryConfirm = WebUI.getElementText(elementShippingAddressInOrderSummary);
        String shippingAddressInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[0];
        String shippingCityInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[1];
        String shippingCountryInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[2];

        WebUI.verifyAssertEquals(shippingAddressInOrderSummaryConfirm, addressSelected.getAddress(), "Địa chỉ giao hàng không khớp.");
        WebUI.verifyAssertEquals(shippingCityInOrderSummaryConfirm, addressSelected.getCity(), "Thành phố giao hàng không khớp.");
        WebUI.verifyAssertEquals(shippingCountryInOrderSummaryConfirm, addressSelected.getCountry(), "Quốc gia giao hàng không khớp.");

        //Check order detail
        WebUI.scrollToElement(By.xpath("//h5[text()='Order Summary']/parent::div"));
        List<Cart> listProduct = getInfoOrderDetailInDisplayConfirm();
        for (int j = 0; j < listProduct.size(); j++) {
            WebUI.verifyAssertContain(currentCart.get(j).getNameProduct(), listProduct.get(j).getNameProduct(), "Tên sản phẩm không khớp.");
            WebUI.verifyAssertEquals(listProduct.get(j).getPrice(), currentCart.get(j).getPrice(), "Giá sản phẩm không khớp.");
            WebUI.verifyAssertEquals(listProduct.get(j).getQuantity(), currentCart.get(j).getQuantity(), "Số lượng sản phẩm không khớp.");
        }
        checkInfoPriceInConfirmDisplay();
//        checkHistoryOrder(noteForOrder);

    }
    public void checkTotalPriceInPayment() {
        BigDecimal valuePriceTotal = calculatorTotalPriceInPaymentInfo();
        BigDecimal valuePriceTotalInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalInDisplayPayment));
        WebUI.verifyAssertEquals(valuePriceTotalInDisplayPayment, valuePriceTotal, "Tổng giá tiền ở trang thanh toán không khớp.");

    }
    public void checkOutOrder(String noteForOrder) {
        if (!DriverManager.getDriver().getCurrentUrl().equals("https://cms.anhtester.com/cart")) {
            WebUI.openURL("https://cms.anhtester.com/cart");
        }
        List<Cart> currentCart = CartPage.getCartDetailTemp2();
        if (currentCart.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(CartPage.messageCartEmptyInCartDetail, "Không có sản phẩm trong giỏ hàng");
            WebUI.verifyAssertTrueEqual(CartPage.messageCartEmptyInCartDetail, "Your Cart is empty", "Thông báo không có sản phẩm trong giỏ hàng không đúng");
            CartPage.addProductToCart("Cosy Thuy Dung OOTVUJLN", "1");
            currentCart = CartPage.getCartDetailTemp2();
        }
        WebUI.clickElement(buttonContinueToShipping);
        selectAddressInShippingInfo("3");
        Address addressSelected = getSelectedAddress();
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
        selectShippingMethod("Home Delivery");

        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();

        //check tổng tien o trang payment
        checkTotalPriceInPayment();

        choosePaymentMethodCashOnDelivery();
        addAdditaionalInfo(noteForOrder);

        WebUI.scrollToElement(buttonCompleteOrder);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        //Check order success
        testOrderSuccess();
        //Check order summary
        testOrderSummaryInConfirmDisplay();

        String valueShippingAddressInOrderSummaryConfirm = WebUI.getElementText(elementShippingAddressInOrderSummary);
        String shippingAddressInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[0];
        String shippingCityInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[1];
        String shippingCountryInOrderSummaryConfirm = valueShippingAddressInOrderSummaryConfirm.split(", ")[2];

        WebUI.verifyAssertEquals(shippingAddressInOrderSummaryConfirm, addressSelected.getAddress(), "Địa chỉ giao hàng không khớp.");
        WebUI.verifyAssertEquals(shippingCityInOrderSummaryConfirm, addressSelected.getCity(), "Thành phố giao hàng không khớp.");
        WebUI.verifyAssertEquals(shippingCountryInOrderSummaryConfirm, addressSelected.getCountry(), "Quốc gia giao hàng không khớp.");

//        String shippingAddressInOrderSummary = addressSelected.getAddress() + ", " + addressSelected.getCity() + ", " + addressSelected.getCountry();
//        WebUI.verifyAssertTrueEqual(elementShippingAddressInOrderSummary, shippingAddressInOrderSummary, "Địa chỉ giao hàng không khớp.");

        //Check order detail
        WebUI.scrollToElement(By.xpath("//h5[text()='Order Summary']/parent::div"));
        List<Cart> listProduct = getInfoOrderDetailInDisplayConfirm();
        for (int j = 0; j < listProduct.size(); j++) {
            WebUI.verifyAssertContain(currentCart.get(j).getNameProduct(), listProduct.get(j).getNameProduct(), "Tên sản phẩm không khớp.");
            WebUI.verifyAssertEquals(listProduct.get(j).getPrice(), currentCart.get(j).getPrice(), "Giá sản phẩm không khớp.");
            WebUI.verifyAssertEquals(listProduct.get(j).getQuantity(), currentCart.get(j).getQuantity(), "Số lượng sản phẩm không khớp.");
        }
        checkInfoPriceInConfirmDisplay();
//        checkHistoryOrder(noteForOrder);

    }

    public void checkOrderExistInAdmin(String orderCode) {
        WebUI.openURL("https://cms.anhtester.com/admin/all_orders");
        WebUI.waitForPageLoaded();
        By elementSearchOrderCode = By.xpath("//input[@id='search']");
        By buttonFilter = By.xpath("//button[normalize-space()='Filter']");
        WebUI.setTextAndClear(elementSearchOrderCode, orderCode);
        WebUI.clickElement(buttonFilter);
        WebUI.waitForPageLoaded();
        By elementOrderCodeInAdmin = By.xpath("//td[2]");
        WebUI.verifyAssertTrueIsDisplayed(elementOrderCodeInAdmin, "Không xuất hiện đơn hàng trong admin.");
        WebUI.verifyAssertTrueTextContain(elementOrderCodeInAdmin, orderCode, "Mã đơn hàng không khớp.");
    }

    public void checkHistoryOrder(String noteForOrder) {
        By elementOrderDetailInDisplayConfirm = By.xpath("//div[@class='card-body']");
        List<WebElement> orders = DriverManager.getDriver().findElements(elementOrderDetailInDisplayConfirm);
        List<String> valueOrderCode = new ArrayList<>();
        for (int i = 1; i <= orders.size(); i++) {
            By elementOrderCodeInOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/span");
            String orderCode = WebUI.getElementText(elementOrderCodeInOrderDetail);
            valueOrderCode.add(orderCode);
            List<Cart> listProduct = getInfoOrderDetailInDisplayConfirmWithOrderCode(orderCode);
            OrderAmount orderAmountInOrderDetail = getOrderAmountInOrderDetail(orderCode);
            OrderSummary orderSummaryInConfirmDisplay = getOrderSummaryInConfirmDisplay();

            String orderDateInOrderSummary = WebUI.getElementText(elementOrderDateInOrderSummary);
            String orderDateInOrderSummaryFormat = orderDateInOrderSummary.split(" ")[0];
            String orderStatusInOrderSummary = WebUI.getElementText(elementOrderStatusInOrderSummary);
            By elementTotalOrderInOrderDetail = By.xpath("//span[text()='" + orderCode + "']/ancestor::div[@class='card-body']//table//span[text()='Total']/ancestor::th/following-sibling::td//span");
            String totalOrderInOrderDetail = WebUI.getElementText(elementTotalOrderInOrderDetail);
            String mainWindow = DriverManager.getDriver().getWindowHandle();
            By divOrderDetail = By.xpath("(//h2[contains(text(),'Order Code')])[" + i + "]/ancestor::div[@class='card-body']");
            WebUI.scrollToElement(divOrderDetail);
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
            WebUI.verifyAssertTrueTextContain(elementOrderStatusInHistoryOrder, orderStatusInOrderSummary, "Trạng thái đơn hàng không khớp.");

            //Check order detail in history order
            By viewOrderDetail = By.xpath("//a[normalize-space()='" + orderCode + "']/ancestor::tr//a[@title='Order Details']");
            WebUI.clickElement(viewOrderDetail);
            WebUI.waitForPageLoaded();

//            WebUI.clickElement(elementOrderCodeInHistoryOrder);
//            WebUI.waitForPageLoaded();
            By elementOrderIDInOrderHistoryDetail = By.xpath("//h1[contains(text(), 'Order Id')]");
            WebUI.verifyAssertTrueIsDisplayed(elementOrderIDInOrderHistoryDetail, "Không hiển thị chi tiết đơn hàng ứng với order code: " + orderCode + " trong lịch sử đơn hàng");
            WebUI.verifyAssertTrueEqual(elementOrderIDInOrderHistoryDetail, "Order Id: " + orderCode, "Mã đơn hàng không khớp.");
            WebUI.scrollToElement(By.xpath("//td[normalize-space()='Total']/following-sibling::td//span"));

            List<Cart> listProductInHistoryOrder = getInfoOrderDetailInHistoryOrder(orderCode);
            OrderAmount orderAmountInHistoryOrder = getOrderAmountInHistoryOrder();
            OrderSummary orderSummaryInHistoryOrder = getOrderSummaryInHistoryOrder();

            for (int j = 0; j < listProduct.size(); j++) {
                WebUI.verifyAssertEquals(listProductInHistoryOrder.get(j).getNameProduct(), listProduct.get(j).getNameProduct(), "Tên sản phẩm không khớp.");
                WebUI.verifyAssertEquals(listProductInHistoryOrder.get(j).getNameVariant(), listProduct.get(j).getNameVariant(), "Tên biến thể sản phẩm không khớp.");
                WebUI.verifyAssertEquals(listProductInHistoryOrder.get(j).getPrice(), listProduct.get(j).getPrice(), "Giá sản phẩm không khớp.");
                WebUI.verifyAssertEquals(listProductInHistoryOrder.get(j).getQuantity(), listProduct.get(j).getQuantity(), "Số lượng sản phẩm không khớp.");
            }
            WebUI.verifyAssertEquals(orderAmountInHistoryOrder.getSubTotal(), orderAmountInOrderDetail.getSubTotal(), "Subtotal không khớp.");
            WebUI.verifyAssertEquals(orderAmountInHistoryOrder.getTax(), orderAmountInOrderDetail.getTax(), "Tax không khớp.");
            WebUI.verifyAssertEquals(orderAmountInHistoryOrder.getShipping(), orderAmountInOrderDetail.getShipping(), "Shipping không khớp.");
            WebUI.verifyAssertEquals(orderAmountInHistoryOrder.getCouponDiscount(), orderAmountInOrderDetail.getCouponDiscount(), "Coupon Discount không khớp.");
            WebUI.verifyAssertEquals(orderAmountInHistoryOrder.getTotal(), orderAmountInOrderDetail.getTotal(), "Total không khớp.");

            By elementOrderCodeInOrderSummary = By.xpath("//td[text()='Order Code::']/following-sibling::td");
            WebUI.verifyAssertEquals(WebUI.getElementText(elementOrderCodeInOrderSummary), orderCode, "Mã đơn hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getOrderDate(), orderSummaryInConfirmDisplay.getOrderDate(), "Ngày tạo đơn hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getName(), orderSummaryInConfirmDisplay.getName(), "Tên khách hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getEmail(), orderSummaryInConfirmDisplay.getEmail(), "Email khách hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getOrderStatus(), orderSummaryInConfirmDisplay.getOrderStatus(), "Trạng thái đơn hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getShippingMethod(), orderSummaryInConfirmDisplay.getShippingMethod(), "Phương thức vận chuyển không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getPaymentMethod(), orderSummaryInConfirmDisplay.getPaymentMethod(), "Phương thức thanh toán không khớp.");

            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getAddress(), orderSummaryInConfirmDisplay.getAddress(), "Địa chỉ Address giao hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getCity(), orderSummaryInConfirmDisplay.getCity(), "Địa chỉ City giao hàng không khớp.");
            WebUI.verifyAssertEquals(orderSummaryInHistoryOrder.getCountry(), orderSummaryInConfirmDisplay.getCountry(), "Địa chỉ Country giao hàng không khớp.");

            //check additional info in order summary
            By elementAdditionalInfoInOrderSummary = By.xpath("//td[text()='Additional Info']/following-sibling::td");
            WebUI.verifyAssertTrueEqual(elementAdditionalInfoInOrderSummary, noteForOrder, "Thông tin thêm không khớp.");

//            check total order amount
            By elementTotalOrderAmountInHistoryOrderDetail = By.xpath("//td[text()='Total order amount:']/following-sibling::td");
            String valueTotalOrderAmount = WebUI.getElementText(elementTotalOrderAmountInHistoryOrderDetail);
            valueTotalOrderAmount = convertCurrencyToBigDecimal(valueTotalOrderAmount).toString();
            WebUI.verifyAssertEquals(valueTotalOrderAmount, orderAmountInHistoryOrder.getTotal(), "Tổng giá tiền không đúng tại phần order summary trong trang lich sử đơn hàng.");

            DriverManager.getDriver().switchTo().window(mainWindow);

        }

        WebUI.clickElement(DashboardPage.buttonLogoutRoleCustomer);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(LoginPage.buttonLogin);
        WebUI.waitForPageLoaded();
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        WebUI.setTextAndClear(LoginPage.inputEmail, excel.getCellData("email", 5));
        WebUI.setTextAndClear(LoginPage.inputPassword, excel.getCellData("password", 5));
        WebUI.clickElement(LoginPage.buttonSubmitLogin);
        WebUI.waitForPageLoaded();
        for (int j = 0; j < valueOrderCode.size(); j++) {
            checkOrderExistInAdmin(valueOrderCode.get(j));
        }

    }

    public static List<Cart> getInfoProductsInDisplayPayment() {
        List<WebElement> productQuantities = DriverManager.getDriver().findElements(elementProductQuantitiesInDisplayPayment);
        if (productQuantities.size() == 0) {
            return new ArrayList<>();
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

        List<Cart> infoProductsInDisplayPayment = getCartsInDisplayPayment(valueProductNames, valueProductQuantities, valueTotalProductPrices);
        return infoProductsInDisplayPayment;
    }

    private static List<Cart> getCartsInDisplayPayment(List<String> valueProductNames, List<Integer> valueProductQuantities, List<BigDecimal> valueTotalProductPrices) {
        List<Cart> infoProductsInDisplayPayment = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            String name = valueProductNames.get(i);
            String quantity = " × " + valueProductQuantities.get(i);
            int position = name.indexOf(quantity);
            if (position != -1) {
                name = name.substring(0, position);
            }
            cart.setNameProduct(name);
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueTotalProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayPayment.add(cart);
        }
        return infoProductsInDisplayPayment;
    }


}
