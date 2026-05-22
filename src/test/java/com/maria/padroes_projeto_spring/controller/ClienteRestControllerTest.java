package com.maria.padroes_projeto_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maria.padroes_projeto_spring.exception.ClienteNotFoundException;
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

    // =========================
    // GET TODOS
    // =========================
    @Test
    @DisplayName("Deve retornar todos os clientes")
    void deveRetornarTodosClientes() throws Exception {

        Mockito.when(clienteService.buscarTodos())
                .thenReturn(List.of(criarCliente()));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    // =========================
    // GET POR ID
    // =========================
    @Test
    @DisplayName("Deve buscar cliente por ID")
    void deveBuscarClientePorId() throws Exception {

        Mockito.when(clienteService.buscarPorId(1L))
                .thenReturn(criarCliente());

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    // =========================
    // POST
    // =========================
    @Test
    @DisplayName("Deve inserir cliente")
    void deveInserirCliente() throws Exception {

        Cliente cliente = criarCliente();

        Mockito.when(clienteService.inserir(Mockito.any(Cliente.class)))
                .thenAnswer(invocation -> {
                    Cliente c = invocation.getArgument(0);
                    c.setId(1L);
                    return c;
                });

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    // =========================
    // PUT
    // =========================
    @Test
    @DisplayName("Deve atualizar cliente")
    void deveAtualizarCliente() throws Exception {

        Cliente cliente = criarCliente();
        cliente.setNome("Maria Atualizada");

        Mockito.when(clienteService.atualizar(Mockito.eq(1L), Mockito.any(Cliente.class)))
                .thenReturn(cliente);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Atualizada"));
    }

    // =========================
    // DELETE
    // =========================
    @Test
    @DisplayName("Deve deletar cliente")
    void deveDeletarCliente() throws Exception {

        Mockito.doNothing()
                .when(clienteService)
                .deletar(1L);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }



    // =========================
    // ERRO 404 CORRIGIDO
    // =========================
    @Test
    @DisplayName("Deve retornar 404 corretamente")
    void deveRetornar404() throws Exception {

        Mockito.when(clienteService.buscarPorId(99L))
                .thenThrow(new ClienteNotFoundException("Cliente não encontrado"));

        mockMvc.perform(get("/clientes/99"))
                .andExpect(status().isNotFound());
    }

    // =========================
    // UTIL
    // =========================
    private Cliente criarCliente() {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEndereco(endereco);

        return cliente;
    }


}