package com.clayton.qa.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;

    // Seletores
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginButton = "#login-button";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://www.saucedemo.com");
    }

    public void login(String username, String password) {
        page.locator(usernameInput).fill(username);
        page.locator(passwordInput).fill(password);
        page.locator(loginButton).click();
    }

    public void validateErrorMessage(String expectedMessage) {
    assertThat(page.locator("[data-test='error']")).hasText(expectedMessage);
    }
}