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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // CREATE
    // =========================
    @Test
    @DisplayName("Deve criar cliente na integração")
    void deveCriarClienteIntegracao() throws Exception {

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    // =========================
    // LIST ALL
    // =========================
    @Test
    @DisplayName("Deve listar clientes")
    void deveListarClientes() throws Exception {

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente()))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    // =========================
    // GET BY ID
    // =========================
    @Test
    @DisplayName("Deve buscar cliente por id")
    void deveBuscarClientePorId() throws Exception {

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Cliente cliente = objectMapper.readValue(response, Cliente.class);

        mockMvc.perform(get("/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    // =========================
    // UPDATE
    // =========================
    @Test
    @DisplayName("Deve atualizar cliente")
    void deveAtualizarCliente() throws Exception {

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Cliente cliente = objectMapper.readValue(response, Cliente.class);
        cliente.setNome("Maria Atualizada");

        mockMvc.perform(put("/clientes/" + cliente.getId())
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

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Cliente cliente = objectMapper.readValue(response, Cliente.class);

        mockMvc.perform(delete("/clientes/" + cliente.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/clientes/" + cliente.getId()))
                .andExpect(status().isNotFound());
    }

    // =========================
    // ERRO - ID INVÁLIDO
    // =========================
    @Test
    @DisplayName("Deve retornar 404 para cliente inexistente")
    void deveRetornar404QuandoNaoEncontrarCliente() throws Exception {

        mockMvc.perform(get("/clientes/999999"))
                .andExpect(status().isNotFound());
    }

    // =========================
    // ERRO - BAD REQUEST
    // =========================

    @DisplayName("Deve retornar erro ao enviar JSON inválido")
    @Test
    void deveRetornarErroJsonInvalido() throws Exception {

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    // =========================
    // UTIL JSON
    // =========================
    private String jsonCliente() {
        return """
        {
          "nome": "Maria",
          "endereco": {
            "cep": "01001000"
          }
        }
        """;
    }

    // =========================
    // COVERAGE - equals/hashCode
    // =========================
    @Test
    void deveValidarEqualsEHashCode() {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setNome("Maria");
        c1.setEndereco(endereco);

        Cliente c2 = new Cliente();
        c2.setId(1L);
        c2.setNome("Maria");
        c2.setEndereco(endereco);

        assert c1.equals(c2);
        assert c1.hashCode() == c2.hashCode();
    }

    // =========================
    // COVERAGE - construtor
    // =========================
    @Test
    void deveCriarClienteComConstrutor() {

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");

        Cliente cliente = new Cliente(1L, "Maria", endereco);

        org.junit.jupiter.api.Assertions.assertEquals(1L, cliente.getId());
        org.junit.jupiter.api.Assertions.assertEquals("Maria", cliente.getNome());
    }

}