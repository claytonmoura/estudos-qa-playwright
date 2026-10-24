package com.clayton.qa.tests;

import com.clayton.qa.pages.CartPage;
import com.clayton.qa.pages.InventoryPage;
import com.clayton.qa.pages.LoginPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class LoginTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(page);
        inventoryPage.validatePage();
        inventoryPage.addBackpackToCart();
        inventoryPage.validateProductAddedToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(page);
        cartPage.validatePage();
        cartPage.validateProductInCart("Sauce Labs Backpack");
    }
}