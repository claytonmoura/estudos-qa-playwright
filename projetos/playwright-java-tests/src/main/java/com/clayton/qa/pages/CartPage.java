package com.clayton.qa.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage {
    private final Page page;

    // Seletores
    private final String pageTitle = ".title";
    private final String cartItem = ".cart_item";
    private final String itemName = ".inventory_item_name";
    private final String checkoutButton = "#checkout";

    public CartPage(Page page) {
        this.page = page;
    }

    public void validatePage() {
        assertThat(page.locator(pageTitle)).hasText("Your Cart");
        assertThat(page.locator(cartItem)).isVisible();
    }

    public void validateProductInCart(String productName) {
        assertThat(page.locator(itemName)).hasText(productName);
    }

    public void proceedToCheckout() {
        page.locator(checkoutButton).click();
    }
}