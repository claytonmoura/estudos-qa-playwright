package com.clayton.qa;

import com.microsoft.playwright.*;

public class PrimeiroTeste {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            
            page.navigate("https://playwright.dev");
            
            System.out.println("Título da página: " + page.title());
            
            page.waitForTimeout(3000); // espera 3 segundos para você ver o navegador
        }
    }
}