package com.clayton.qa;

import com.clayton.qa.pages.CartPage;
import com.clayton.qa.pages.InventoryPage;
import com.clayton.qa.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LoginTeste {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // 1. Login
            LoginPage loginPage = new LoginPage(page);
            loginPage.navigate();
            loginPage.login("standard_user", "secret_sauce");

            // 2, Página de produtos
            InventoryPage inventoryPage = new InventoryPage(page);
            inventoryPage.validatePage();
            inventoryPage.addBackpackToCart();
            inventoryPage.validateProductAddedToCart();
            inventoryPage.goToCart();

            // 3. Carrinho
            CartPage cartPage = new CartPage(page);
            cartPage.validatePage();
            cartPage.validateProductInCart("Sauce Labs Backpack");

            System.out.println("Fluxo completo (Login -> Carrinho) validado com sucesso!");

            page.waitForTimeout(3000);
        }
    }
}