package com.maria.padroes_projeto_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.maria.padroes_projeto_spring.model.Cliente;
import com.maria.padroes_projeto_spring.model.Endereco;
import com.maria.padroes_projeto_spring.service.ClienteService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteRestController.class)
class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar todos os clientes")
    void deveRetornarTodosClientes() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEndereco(endereco);

        Mockito.when(clienteService.buscarTodos())
                .thenReturn(List.of(cliente));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    @Test
    @DisplayName("Deve inserir cliente")
    void deveInserirCliente() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEndereco(endereco);

        Mockito.when(clienteService.inserir(Mockito.any()))
                .thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }
}