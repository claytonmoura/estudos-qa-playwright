package com.clayton.qa.tests;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ApiTest {

    static Playwright playwright;
    static APIRequestContext request;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://reqres.in"));
    }

    @AfterAll
    static void teardown() {
        request.dispose();
        playwright.close();
    }

    @Test
    void deveListarUsuariosComSucesso() {
        APIResponse response = request.get("/api/users?page=2");

        assertEquals(200, response.status());
        assertTrue(response.text().contains("michael.lawson"));
        System.out.println("Status: " + response.status());
        System.out.println("Body: " + response.text());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        APIResponse response = request.get("/api/users/2");

        assertEquals(200, response.status());
        assertTrue(response.text().contains("janet.weaver"));
        System.out.println("Status: " + response.status());
        System.out.println("Body: " + response.text());
    }

    @Test
    void deveRetornarErroQuandoUsuarioNaoExistir() {
        APIResponse response = request.get("/api/users/23");

        assertEquals(404, response.status());
        System.out.println("Status: " + response.status());
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        APIResponse response = request.post("/api/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData("{\"name\": \"Clayton\", \"job\": \"QA\"}")
        );

        System.out.println("Status: " + response.status());
        System.out.println("Body: " + response.text());

        assertEquals(201, response.status());
        assertTrue(response.text().contains("Clayton") || response.text().contains("id"));
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        APIResponse response = request.put("/api/users/2",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData("{\"name\": \"Clayton\", \"job\": \"QA Automation\"}")
        );

        System.out.println("Status: " + response.status());
        System.out.println("Body: " + response.text());

        assertEquals(200, response.status());
        assertTrue(response.text().contains("Clayton") || response.text().contains("updatedAt"));
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        APIResponse response = request.delete("/api/users/2");

        assertEquals(204, response.status());
        System.out.println("Status: " + response.status());
    }
}
