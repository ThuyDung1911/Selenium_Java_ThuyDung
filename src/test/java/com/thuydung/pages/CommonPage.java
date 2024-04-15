package com.thuydung.pages;

public class CommonPage {
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public OrderPage orderPage;
    public AddProductPage addProductPage;
    public EditProductPage editProductPage;
    public ProductInfoPage productInfoPage;
    public RegisterPage registerPage;
    public HomePage homePage;
    public static DashboardPage dashboardPage;
    public CartPage cartPage;
    public CouponPage couponPage;

    public CouponPage getCouponPage() {
        if (couponPage == null) {
            couponPage = new CouponPage();
        }
        return couponPage;
    }
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ProfilePage getProfilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage();
        }
        return profilePage;
    }

    public OrderPage getOrderPage() {
        if (orderPage == null) {
            orderPage = new OrderPage();
        }
        return orderPage;
    }

    public AddProductPage getAddProductPage() {
        if (addProductPage == null) {
            addProductPage = new AddProductPage();
        }
        return addProductPage;
    }

    public ProductInfoPage getProductInfoPage() {
        if (productInfoPage == null) {
            productInfoPage = new ProductInfoPage();
        }
        return productInfoPage;
    }
    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
    public EditProductPage getEditProductPage() {
        if (editProductPage == null) {
            editProductPage = new EditProductPage();
        }
        return editProductPage;
    }
    public static DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}
