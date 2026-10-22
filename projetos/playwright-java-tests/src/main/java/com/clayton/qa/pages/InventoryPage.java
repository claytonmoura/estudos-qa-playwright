package com.clayton.qa.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InventoryPage {
    private final Page page;

    // Seletores
    private final String pageTitle = ".title";
    private final String inventoryList = ".inventory_list";
    private final String addBackpackButton = "#add-to-cart-sauce-labs-backpack";
    private final String cartBadge = ".shopping_cart_badge";
    private final String cartIcon = ".shopping_cart_link";

    public InventoryPage(Page page) {
        this.page = page;
    }

    public void validatePage() {
        assertThat(page.locator(pageTitle)).hasText("Products");
        assertThat(page.locator(inventoryList)).isVisible();
    }

    public void addBackpackToCart() {
        page.locator(addBackpackButton).click();
    }

    public void validateProductAddedToCart() {
        assertThat(page.locator(cartBadge)).hasText("1");
    }

    public void goToCart() {
        page.locator(cartIcon).click();
    }
}