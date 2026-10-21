package com.clayton.qa;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LoginTeste {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Acessa o site
            page.navigate("https://www.saucedemo.com");

            // Preenche usuário e senha
            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");

            // Clica no botão de login
            page.locator("#login-button").click();

            // Espera um pouco para ver o resultado
            page.waitForTimeout(4000);

            System.out.println("Login realizado! Título da página: " + page.title());
        }
    }
}