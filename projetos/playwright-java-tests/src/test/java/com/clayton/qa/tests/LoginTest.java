package com.clayton.qa.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.clayton.qa.data.TestData;
import com.clayton.qa.pages.CartPage;
import com.clayton.qa.pages.CheckoutPage;
import com.clayton.qa.pages.InventoryPage;
import com.clayton.qa.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LoginTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        boolean isCI = System.getenv("CI") != null;
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isCI));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void deveRealizarLoginEAdicionarProdutoNoCarrinho() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(TestData.USER_VALID, TestData.PASSWORD_VALID);

        InventoryPage inventoryPage = new InventoryPage(page);
        inventoryPage.validatePage();
        inventoryPage.addBackpackToCart();
        inventoryPage.validateProductAddedToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(page);
        cartPage.validatePage();
        cartPage.validateProductInCart(TestData.PRODUCT_BACKPACK);
    }

    @Test
    void deveExibirErroQuandoUsuarioForInvalido() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(TestData.USER_INVALID, TestData.PASSWORD_VALID);
        loginPage.validateErrorMessage(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    void deveExibirErroQuandoSenhaForInvalida() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(TestData.USER_VALID, TestData.PASSWORD_INVALID);
        loginPage.validateErrorMessage(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    void deveExibirErroQuandoCamposEstiveremVazios() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("", "");
        loginPage.validateErrorMessage(TestData.ERROR_USERNAME_REQUIRED);
    }

    @Test
    void deveFinalizarCompraComSucesso() {
        // Login
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(TestData.USER_VALID, TestData.PASSWORD_VALID);

        // Produtos
        InventoryPage inventoryPage = new InventoryPage(page);
        inventoryPage.validatePage();
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        // Carrinho
        CartPage cartPage = new CartPage(page);
        cartPage.validatePage();
        cartPage.proceedToCheckout();

        // Checkout
        CheckoutPage checkoutPage = new CheckoutPage(page);
        checkoutPage.fillCustomerInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPage.finishOrder();
        checkoutPage.validateOrderCompleted();
    }
}
