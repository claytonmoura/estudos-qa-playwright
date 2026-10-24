package com.clayton.qa.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutPage {
    private final Page page;

    private final String firstNameInput = "#first-name";
    private final String lastNameInput = "#last-name";
    private final String postalCodeInput = "#postal-code";
    private final String continueButton = "#continue";
    private final String finishButton = "#finish";
    private final String completeHeader = ".complete-header";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        page.locator(firstNameInput).fill(firstName);
        page.locator(lastNameInput).fill(lastName);
        page.locator(postalCodeInput).fill(postalCode);
        page.locator(continueButton).click();
    }

    public void finishOrder() {
        page.locator(finishButton).click();
    }

    public void validateOrderCompleted() {
        assertThat(page.locator(completeHeader)).hasText("Thank you for your order!");
    }
}