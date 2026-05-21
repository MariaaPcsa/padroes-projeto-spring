package com.maria.padroes_projeto_spring.swagger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerDocumentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve carregar Swagger UI")
    void deveCarregarSwaggerUI() throws Exception {

        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve carregar OpenAPI JSON")
    void deveCarregarOpenApiJson() throws Exception {

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith("application/json"));
    }

    @Test
    @DisplayName("Deve conter endpoint clientes")
    void deveConterEndpointClientes() throws Exception {

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/clientes']").exists());
    }

    @Test
    @DisplayName("Deve conter método GET documentado")
    void deveConterMetodoGetDocumentado() throws Exception {

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/clientes'].get").exists());
    }

    @Test
    @DisplayName("Deve conter método POST documentado")
    void deveConterMetodoPostDocumentado() throws Exception {

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/clientes'].post").exists());
    }

    @Test
    @DisplayName("Deve conter schema Cliente")
    void deveConterSchemaCliente() throws Exception {

        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.components.schemas.Cliente").exists());
    }
}