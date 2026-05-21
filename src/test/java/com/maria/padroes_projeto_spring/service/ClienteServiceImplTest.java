package com.maria.padroes_projeto_spring.service;

import com.maria.padroes_projeto_spring.model.Cliente;
import com.maria.padroes_projeto_spring.model.Endereco;
import com.maria.padroes_projeto_spring.repository.ClienteRepository;
import com.maria.padroes_projeto_spring.repository.EnderecoRepository;
import com.maria.padroes_projeto_spring.service.impl.ClienteServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ViaCepService viaCepService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private Endereco endereco;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        endereco = new Endereco();
        endereco.setCep("01001000");
        endereco.setLogradouro("Praça da Sé");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEndereco(endereco);
    }

    @Test
    @DisplayName("Deve buscar todos os clientes")
    void deveBuscarTodosClientes() {

        when(clienteRepository.findAll())
                .thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.buscarTodos();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar cliente por ID")
    void deveBuscarClientePorId() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.buscarPorId(1L);

        assertNotNull(clienteEncontrado);
        assertEquals("Maria", clienteEncontrado.getNome());

        verify(clienteRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve inserir cliente")
    void deveInserirCliente() {

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente clienteSalvo = clienteService.inserir(cliente);

        assertNotNull(clienteSalvo);
        assertEquals("Maria", clienteSalvo.getNome());

        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Deve deletar cliente")
    void deveDeletarCliente() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deletar(1L);

        verify(clienteRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não existir")
    void deveLancarExcecaoQuandoClienteNaoExistir() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> clienteService.buscarPorId(1L)
        );

        assertEquals(
                "Cliente não encontrado",
                exception.getMessage()
        );
    }
}