package com.maria.padroes_projeto_spring.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.maria.padroes_projeto_spring.model.Cliente;
import com.maria.padroes_projeto_spring.model.Endereco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve criar cliente na integração")
    void deveCriarClienteIntegracao() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEndereco(endereco);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }
}