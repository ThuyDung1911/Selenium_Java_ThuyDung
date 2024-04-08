package com.thuydung.pages;

import com.thuydung.drivers.DriverManager;
import com.thuydung.keywords.WebUI;
import com.thuydung.requests.Address;
import com.thuydung.requests.Cart;
import com.thuydung.utils.LogUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private By titleNewAddressEdit = By.xpath("//div[@id='edit-address-modal']//h5[@id='exampleModalLabel']");
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
    public By messageNoti = By.xpath("//span[@data-notify='message']");
    private By buttonSelectAddressTest = By.xpath("(//span[@class='aiz-rounded-check flex-shrink-0 mt-1'])[1]");
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
    By elementMenuCheckOut = By.xpath("//div[contains(@class,'text-primary')]");
    public static By valueNewestAddress = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'Address')]/following-sibling::span");
    public static By valueNewestCountry = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'Country')]/following-sibling::span");
    public static By valueNewestState = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'State')]/following-sibling::span");
    public static By valueNewestCity = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'City')]/following-sibling::span");
    public static By valueNewestPostalCode = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'Postal code')]/following-sibling::span");
    public static By valueNewestPhone = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//span[contains(text(),'Phone')]/following-sibling::span");
    public static By iconEllipsisInCardAddressNewest = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//i[@class='la la-ellipsis-v']");
    public static By buttonEditInCardAddressNewest = By.xpath("(//div[@class='border p-3 rounded mb-3 c-pointer text-center bg-white h-100 d-flex flex-column justify-content-center']/preceding::div[@class='col-md-6 mb-3'][1])//i[@class='la la-ellipsis-v']/parent::button/following-sibling::div[contains(@class, 'dropdown-menu')]/a[normalize-space()='Edit']");

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
    By elementAddress = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Address')]/following-sibling::span");
    By elementPostalCode = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Postal code')]/following-sibling::span");
    By elementCity = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'City')]/following-sibling::span");
    By elementState = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'State')]/following-sibling::span");
    By elementCountry = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Country')]/following-sibling::span");
    By elementPhone = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Phone')]/following-sibling::span");
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

//        By elementAddress = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Address')]/following-sibling::span");
//        By elementPostalCode = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Postal code')]/following-sibling::span");
//        By elementCity = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'City')]/following-sibling::span");
//        By elementState = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'State')]/following-sibling::span");
//        By elementCountry = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Country')]/following-sibling::span");
//        By elementPhone = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Phone')]/following-sibling::span");

        //Shipping Info
        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(elementAddress);
        List<WebElement> postalCodeInShippingInfo = DriverManager.getDriver().findElements(elementPostalCode);
        List<WebElement> cityInShippingInfo = DriverManager.getDriver().findElements(elementCity);
        List<WebElement> stateInShippingInfo = DriverManager.getDriver().findElements(elementState);
        List<WebElement> countryInShippingInfo = DriverManager.getDriver().findElements(elementCountry);
        List<WebElement> phoneInShippingInfo = DriverManager.getDriver().findElements(elementPhone);
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
        List<WebElement> addressInProfile = DriverManager.getDriver().findElements(elementAddress);
        List<WebElement> postalCodeInProfile = DriverManager.getDriver().findElements(elementPostalCode);
        List<WebElement> cityInProfile = DriverManager.getDriver().findElements(elementCity);
        List<WebElement> stateInProfile = DriverManager.getDriver().findElements(elementState);
        List<WebElement> countryInProfile = DriverManager.getDriver().findElements(elementCountry);
        List<WebElement> phoneInProfile = DriverManager.getDriver().findElements(elementPhone);
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

    public void selectAddressInShippingInfo(String index) {
        openShippingInfoFromURL();
        //By elementAddress = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Address')]/following-sibling::span");
        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(elementAddress);
        int size = addressInShippingInfo.size();
        if (Integer.parseInt(index) > size) {
            LogUtils.info("Không tồn tại địa chỉ thứ " + index + " trong Shipping Info");
            return;
        }
        By buttonSelectAddress = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']");
        WebUI.clickElement(buttonSelectAddress);
        WebUI.waitForPageLoaded();
        getSelectedAddress();
    }
    public Address getInfoAddressSelected(String index) {
        By addressSelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'Address')]/following-sibling::span");
        By postalCodeSelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'Postal code')]/following-sibling::span");
        By citySelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'City')]/following-sibling::span");
        By stateSelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'State')]/following-sibling::span");
        By countrySelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'Country')]/following-sibling::span");
        By phoneSelected = By.xpath("(//input[@type='radio'])[" + index + "]/ancestor::div[@class='col-md-6 mb-3']//span[contains(text(),'Phone')]/following-sibling::span");
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
        openDeliveryInfoWithShippingInfo();
        //Check info order in display delivery info
        List<String> productNamesInDisplayDeliveryInfo = getProductsNameInDeliveryInfoDisplay();
        WebUI.clickElement(buttonCart);
        WebUI.waitForJQueryLoad();
        List<String> productNamesInCart = CartPage.getProductsNameInCart();
        WebUI.verifyAssertEquals(productNamesInDisplayDeliveryInfo, productNamesInCart, "Danh sách sản phẩm không khớp với giỏ hàng.");

    }

    public void selectShippingMethod(String shippingMethod) {
        openDeliveryInfoWithShippingInfo();
        WebUI.clickElement(By.xpath("//span[text()='" + shippingMethod + "']/ancestor::div[@class='col-6']"));
        WebUI.waitForPageLoaded();
    }

    public void testSelectShippingMethod(String shippingMethod) {
        selectShippingMethod(shippingMethod);
        By verifyCheckedShippingMethod = By.xpath("//span[text()='" + shippingMethod + "']/ancestor::div[@class='col-6']//input[@type='radio']");
        String checkedShippingMethod = "Shipping Method " + shippingMethod + " da duoc chon: " + DriverManager.getDriver().findElement(verifyCheckedShippingMethod).isSelected();
        WebUI.verifyAssertEqual(checkedShippingMethod, "Shipping Method " + shippingMethod + " da duoc chon: true", "Phương thức vận chuyển không được chọn");
    }



    public List<WebElement> getAddressInShippingInfo() {
//        By elementAddress = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Address')]/following-sibling::span");
//        By elementPostalCode = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Postal code')]/following-sibling::span");
//        By elementCity = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'City')]/following-sibling::span");
//        By elementState = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'State')]/following-sibling::span");
//        By elementCountry = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Country')]/following-sibling::span");
//        By elementPhone = By.xpath("//div[contains(@class,'row gutters')]//span[contains(text(),'Phone')]/following-sibling::span");

        List<WebElement> addressInShippingInfo = DriverManager.getDriver().findElements(By);
        List<WebElement> postalCodeInShippingInfo = DriverManager.getDriver().findElements(elementPostalCode);
        List<WebElement> cityInShippingInfo = DriverManager.getDriver().findElements(elementCity);
        List<WebElement> stateInShippingInfo = DriverManager.getDriver().findElements(elementState);
        List<WebElement> countryInShippingInfo = DriverManager.getDriver().findElements(elementCountry);
        List<WebElement> phoneInShippingInfo = DriverManager.getDriver().findElements(elementPhone);
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
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < addressInShippingInfo.size(); i++) {
            Address address = new Address(valueAddressInShippingInfo.get(i), valueCityInShippingInfo.get(i), valueStateInShippingInfo.get(i), valueCountryInShippingInfo.get(i), valuePostalCodeInShippingInfo.get(i), valuePhoneInShippingInfo.get(i));
            addresses.add(address);
        }
        return addresses;
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
        openPaymentInfoFromShippingInfoDisplay();
        //check quantity item product in summary with cart
        WebUI.clickElement(buttonCart);
        String quantityItemProductInCart = String.valueOf(CartPage.getQuantityItemProductInCart());
        WebUI.clickElement(buttonCart);
        String quantityItemProductInDisplayPayment = WebUI.getElementText(By.xpath("//span[@class='badge badge-inline badge-primary']")).replaceAll("\\D", "");
        WebUI.verifyAssertEquals(quantityItemProductInCart, quantityItemProductInDisplayPayment, "Số lượng sản phẩm không khớp.");

        //Check info order in display payment with cart
        WebUI.clickElement(buttonCart);
        List<Cart> infoProductsInCart = CartPage.getCartDropdown();
        String subTotalPriceInCart = WebUI.getElementText(CartPage.subTotalPriceInCart);
        WebUI.clickElement(buttonCart);
        List<Cart> infoProductsInDisplayPayment = getInfoProductsInDisplayPayment();
        WebUI.verifyAssertEquals(infoProductsInDisplayPayment, infoProductsInCart, "Thong tin sản phẩm không khớp so voi gio hang.");
        WebUI.verifyAssertTrueEqual(subTotalPriceInDisplayPayment, subTotalPriceInCart, "Sub total price không khớp so voi gio hang.");
    }
    public BigDecimal calculatorTotalPriceInPaymentInfo() {
        BigDecimal valueSubTotalPriceInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        BigDecimal valuePriceTaxInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTaxInDisplayPayment));
        BigDecimal valuePriceTotalShippingInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalShippingInDisplayPayment));
        BigDecimal valuePriceTotal = valueSubTotalPriceInDisplayPayment.add(valuePriceTaxInDisplayPayment).add(valuePriceTotalShippingInDisplayPayment);
        if(WebUI.getElementText(buttonCouponDiscount).equals("Change Coupon")) {
            BigDecimal valuePriceCouponDiscountInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceCouponDiscountInDisplayPayment));
            BigDecimal valuePriceTotalHaveDiscount = valuePriceTotal.subtract(valuePriceCouponDiscountInDisplayPayment);
            return valuePriceTotalHaveDiscount;
        }
        return valuePriceTotal;
    }
    public void applyCouponDiscount(String couponCode) {
        By inputCouponDiscount = By.xpath("//input[@name='code']");
        By buttonApplyCoupon = By.xpath("//button[@id='coupon-apply']");
        WebUI.setTextAndClear(inputCouponDiscount, couponCode);
        WebUI.clickElement(buttonApplyCoupon);
        WebUI.waitForPageLoaded();
    }
    public void testApplyCouponDiscountValid(String couponCode) {
        applyCouponDiscount(couponCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Coupon has been applied", "Thong bao khong chinh xac");
        WebUI.verifyAssertTrueIsDisplayed(priceCouponDiscountInDisplayPayment, "Khong hien thi gia tri Coupon Discount");
    }
    public void testApplyCouponDiscountInvalid(String couponCode) {
        applyCouponDiscount(couponCode);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "Coupon code is invalid", "Thong bao khong chinh xac");
    }
    public void testTotalPriceInPaymentInfoWithNoDiscountCoupon() {
        openPaymentInfoFromShippingInfoDisplay();
        BigDecimal valuePriceTotal = calculatorTotalPriceInPaymentInfo();
        BigDecimal valuePriceTotalInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalInDisplayPayment));
        WebUI.verifyAssertEquals(valuePriceTotalInDisplayPayment, valuePriceTotal, "Tổng giá tiền ở trang thanh toán không khớp.");
    }
    public void testTotalPriceInPaymentInfoWithDiscountCoupon() {
        openPaymentInfoFromShippingInfoDisplay();
        applyCouponDiscount("DUNG1");
        BigDecimal valuePriceTotal = calculatorTotalPriceInPaymentInfo();
        BigDecimal valuePriceTotalInDisplayPayment = convertCurrencyToBigDecimal(WebUI.getElementText(priceTotalInDisplayPayment));
        WebUI.verifyAssertEquals(valuePriceTotalInDisplayPayment, valuePriceTotal, "Tổng giá tiền ở trang thanh toán không khớp.");
    }

    public void selectAgreeTerms() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        }
    public void testSelectAgreeTerms() {
        selectAgreeTerms();
        String verifyCheckedAgreeTerms = "Checkbox dong y dieu khoan da check: " + DriverManager.getDriver().findElement(checkboxAgreeTermAndConditions).isSelected();
        WebUI.verifyAssertEqual(verifyCheckedAgreeTerms, "Checkbox dong y dieu khoan da check: true", "Checkbox dong y dieu khoan chua duoc check");
    }
    public void testNotSelectAgreeTerms() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Khong xuat hien thong bao");
        WebUI.verifyAssertTrueEqual(messageNoti, "You need to agree with our policies", "Thong bao khong chinh xac");
    }
    public void choosePaymentMethodCashOnDelivery() {
        //openPaymentInfoFromShippingInfoDisplay();
        selectAgreeTerms();
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
        selectAgreeTerms();
        WebUI.setTextAndClear(inputAdditionalInfo, noteForOrder);
    }
    public void testClickInTermsAndConditions() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        By linkTermsAndConditions = By.xpath("//a[contains(text(),'terms and conditions')]");
        WebUI.clickElement(linkTermsAndConditions);
        WebUI.waitForPageLoaded();
        By elementTermsAndConditions = By.xpath("//h1[normalize-space()='Term Conditions Page']");
        WebUI.verifyAssertTrueIsDisplayed(elementTermsAndConditions, "Khong hien thi trang Terms and Conditions");
    }
    public void testClickInPrivacyPolicy() {
        openPaymentInfoFromShippingInfoDisplay();
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        By linkPrivacyPolicy= By.xpath("//a[contains(text(),'privacy policy')]");
        WebUI.clickElement(linkPrivacyPolicy);
        WebUI.waitForPageLoaded();
        By elementPrivacyPolicy = By.xpath("//h1[normalize-space()='Privacy Policy Page']");
        WebUI.verifyAssertTrueIsDisplayed(elementPrivacyPolicy, "Khong hien thi trang Privacy Policy");
    }
    public void testOpenConfirmOrderFromURL() {
        WebUI.openURL("https://cms.anhtester.com/checkout/order-confirmed");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(By.xpath("//h1[normalize-space()='Page Not Found!']"),"Khong xuat hien thong bao khong thể truy cập trang Confirm Order tu URL");
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
    public void testAddOrderSuccess() {
        openConfirmOrderFromShippingInfo();
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Đơn hàng thất bại");
        WebUI.verifyAssertTrueEqual(messageNoti, "Your order has been placed successfully", "Thông báo đơn hàng thành công không đúng");
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Khong xuat hien message cam on khach hang da dat hang");
    }

    public void testOrderSummaryInConfirmDisplay() {
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
        WebUI.verifyAssertTrueEqual(elementOrderStatusInOrderSummary, "Pending", "Trạng thái đơn hàng không khớp");
        WebUI.verifyAssertTrueEqual(elementShippingInOrderSummary,"Flat shipping rate", "Chính sách van chuyen khong khop");
        WebUI.verifyAssertTrueEqual(elementPaymentMethodInOrderSummary, "Cash on Delivery", "Phuong thuc thanh toan khong khop");


    }

    public List<Cart> getInfoOrderDetailInDisplayConfirm() {
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
        // check product name, quantity, price
        List<Cart> infoProductsInDisplayConfirm = new ArrayList<>();
        for (int i = 0; i < valueProductNames.size(); i++) {
            Cart cart = new Cart();
            cart.setName(valueProductNames.get(i));
            cart.setQuantity(valueProductQuantities.get(i));
            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
            infoProductsInDisplayConfirm.add(cart);
        }
//        Map<String, Cart> infoProductsInDisplayConfirm = new HashMap<>();
//        for (int i = 0; i < valueProductNames.size(); i++) {
//            Cart cart = new Cart();
//            cart.setName(valueProductNames.get(i));
//            cart.setQuantity(valueProductQuantities.get(i));
//            cart.setPrice(valueProductPrices.get(i).divide(BigDecimal.valueOf(valueProductQuantities.get(i))));
//            infoProductsInDisplayConfirm.put(valueProductNames.get(i), cart);
//        }
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
            //check history order
            checkHistoryOrder(orderCode);
        }
        //check total order amount
        WebUI.verifyAssertEquals(convertCurrencyToBigDecimal(WebUI.getElementText(elementTotalOrderAmountInOrderSummary)), totalOrderAmount, "Tổng giá tiền không đúng tại màn confirm đơn hàng.");
    }
    public void checkOutOrder(String noteForOrder) {

        WebUI.clickElement(buttonCart);
        List<Cart> currentCart = CartPage.getCartDropdown();
        if (currentCart.isEmpty()) {
            WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Không có sản phẩm trong giỏ hàng");
            WebUI.verifyAssertTrueEqual(messageNoti, "Your Cart is empty", "Thông báo không có sản phẩm trong giỏ hàng không đúng");
            return;
        }
        openDeliveryInfoWithShippingInfo();
        WebUI.waitForPageLoaded();
        //Check info order in display delivery info
        testProductInDeliveryInfoDisplay();

        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
        //Check info order in display payment
        testInfoOrderInPaymentInfo();

        applyCouponDiscount("DUNG1");
        //Check totalPrice in display payment
        testTotalPriceInPaymentInfoWithDiscountCoupon();

        choosePaymentMethodCashOnDelivery();
        addAdditaionalInfo(noteForOrder);

        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        //Check order success
        WebUI.verifyAssertTrueIsDisplayed(messageNoti, "Đơn hàng thất bại");
        WebUI.verifyAssertTrueEqual(messageNoti, "Your order has been placed successfully", "Thông báo đơn hàng thành công không đúng");
        WebUI.verifyAssertTrueIsDisplayed(messageOrderSuccess, "Khong xuat hien message cam on khach hang da dat hang");

        //Check order summary
        testOrderSummaryInConfirmDisplay();
        //Check order detail
        checkOrderDetail();
        //checkTotalAmountInConfirmOrder


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
//        LogUtils.info(WebUI.getElementText(elementOrderStatusInHistoryOrder));
        WebUI.verifyAssertTrueEqual(elementOrderStatusInHistoryOrder, orderStatusInOrderSummary, "Trạng thái đơn hàng không khớp.");


        //Check order detail in history order
        WebUI.clickElement(elementOrderCodeInHistoryOrder);
        WebUI.waitForPageLoaded();
        By elementOrderIDInOrderHistoryDetail = By.xpath("//h1[contains(text(), 'Order Id')]");
        WebUI.verifyAssertTrueIsDisplayed(elementOrderIDInOrderHistoryDetail, "Không hiển thị chi tiết đơn hàng ứng với order code: " + orderCode + " trong lịch sử đơn hàng");
        WebUI.verifyAssertTrueEqual(elementOrderIDInOrderHistoryDetail, "Order Id: " + orderCode, "Mã đơn hàng không khớp.");

        DriverManager.getDriver().switchTo().window(mainWindow);

    }
    public void checkOrderDetail(String orderCode) {
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
        BigDecimal valueSubTotalPrice = convertCurrencyToBigDecimal(WebUI.getElementText(subTotalPriceInDisplayPayment));
        WebUI.verifyAssertEquals(subTotalPrice, valueSubTotalPrice, "Subtotal price không đúng");

    }

    /**
     * Get info products in display payment
     *
     * @return
     */
    public static Map<String, Cart> getInfoProductsInDisplayPaymentMap() {
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

        List<Cart> infoProductsInDisplayPayment = new ArrayList<>();
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
            infoProductsInDisplayPayment.add(cart);
        }
        return infoProductsInDisplayPayment;
    }


}
