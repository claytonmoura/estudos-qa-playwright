package com.clayton.qa;

import com.clayton.qa.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTeste {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Usa o Page Object
            LoginPage loginPage = new LoginPage(page);
            loginPage.navigate();
            loginPage.login("standard_user", "secret_sauce");

            // ========== ASSERTIONS (Validações) ==========
            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
            assertThat(page.locator(".title")).hasText("Products");
            assertThat(page.locator(".inventory_list")).isVisible();

            System.out.println("Login validado com sucesso!");

            // Espera um pouco para ver o resultado
            page.waitForTimeout(3000);
        }
    }
}