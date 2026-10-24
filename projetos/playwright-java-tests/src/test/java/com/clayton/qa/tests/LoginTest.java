package com.clayton.qa.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.clayton.qa.pages.CartPage;
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

    @Test
    void deveExibirErroQuandoUsuarioForInvalido() {
    LoginPage loginPage = new LoginPage(page);
    loginPage.navigate();
    loginPage.login("usuario_invalido", "secret_sauce");
    loginPage.validateErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    void deveExibirErroQuandoSenhaForInvalida() {
    LoginPage loginPage = new LoginPage(page);
    loginPage.navigate();
    loginPage.login("standard_user", "senha_errada");
    loginPage.validateErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    void deveExibirErroQuandoCamposEstiveremVazios() {
    LoginPage loginPage = new LoginPage(page);
    loginPage.navigate();
    loginPage.login("", "");
    loginPage.validateErrorMessage("Epic sadface: Username is required");
    }
}