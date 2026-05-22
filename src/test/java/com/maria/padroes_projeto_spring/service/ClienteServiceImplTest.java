package com.maria.padroes_projeto_spring.service;

import com.maria.padroes_projeto_spring.exception.ClienteNotFoundException;
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

    // =========================
    // FIND ALL
    // =========================
    @Test
    @DisplayName("Deve buscar todos os clientes")
    void deveBuscarTodosClientes() {

        when(clienteRepository.findAll())
                .thenReturn(List.of(cliente));

        List<Cliente> result = clienteService.buscarTodos();

        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    // =========================
    // FIND BY ID OK
    // =========================
    @Test
    @DisplayName("Deve buscar cliente por ID")
    void deveBuscarClientePorId() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        Cliente result = clienteService.buscarPorId(1L);

        assertEquals("Maria", result.getNome());
        verify(clienteRepository).findById(1L);
    }

    // =========================
    // FIND BY ID NOT FOUND
    // =========================
    @Test
    @DisplayName("Deve lançar exceção quando cliente não existir")
    void deveLancarExcecaoQuandoClienteNaoExistir() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.empty());

        ClienteNotFoundException ex = assertThrows(
                ClienteNotFoundException.class,
                () -> clienteService.buscarPorId(1L)
        );

        assertTrue(ex.getMessage().contains("Cliente não encontrado"));
    }

    // =========================
    // INSERT - CEP EXISTE NO BANCO
    // =========================
    @Test
    @DisplayName("Deve inserir cliente com CEP já existente")
    void deveInserirClienteCepExistente() {

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente result = clienteService.inserir(cliente);

        assertNotNull(result);

        verify(enderecoRepository, never()).save(any());
        verify(viaCepService, never()).consultarCep(any());
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando endereço for nulo")
    void deveLancarExcecaoEnderecoNulo() {

        cliente.setEndereco(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.inserir(cliente)
        );
    }
    @Test
    @DisplayName("Deve usar endereço do banco sem chamar ViaCep")
    void deveUsarEnderecoDoBanco() {

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any()))
                .thenReturn(cliente);

        Cliente result = clienteService.inserir(cliente);

        assertNotNull(result);

        verify(viaCepService, never()).consultarCep(any());
        verify(enderecoRepository, never()).save(any());
    }


    // =========================
    // INSERT - CEP NÃO EXISTE (USA VIA CEP)
    // =========================
    @Test
    @DisplayName("Deve buscar endereço via API quando não existir no banco")
    void deveBuscarEnderecoViaApi() {

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.empty());

        when(viaCepService.consultarCep("01001000"))
                .thenReturn(endereco);

        when(enderecoRepository.save(any(Endereco.class)))
                .thenReturn(endereco);

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente result = clienteService.inserir(cliente);

        assertNotNull(result);

        verify(viaCepService).consultarCep("01001000");
        verify(enderecoRepository).save(any(Endereco.class));
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve limpar CEP e salvar corretamente")
    void deveLimparCepEManterFluxo() {

        cliente.getEndereco().setCep("01001-000");

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any()))
                .thenReturn(cliente);

        Cliente result = clienteService.inserir(cliente);

        assertNotNull(result);

        verify(enderecoRepository).findById("01001000");
    }

    @Test
    @DisplayName("Deve limpar CEP com caracteres especiais")
    void deveLimparCepComCaracteres() {

        cliente.getEndereco().setCep("01001-000");

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any()))
                .thenReturn(cliente);

        Cliente result = clienteService.inserir(cliente);

        assertNotNull(result);

        verify(enderecoRepository).findById("01001000");
    }

    // =========================
    // UPDATE
    // =========================
    @Test
    @DisplayName("Deve atualizar cliente")
    void deveAtualizarCliente() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(enderecoRepository.findById("01001000"))
                .thenReturn(Optional.of(endereco));

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente result = clienteService.atualizar(1L, cliente);

        assertNotNull(result);
        verify(clienteRepository).save(any(Cliente.class));
    }


    // =========================
    // DELETE NOT FOUND
    // =========================
    @Test
    @DisplayName("Deve lançar erro ao deletar cliente inexistente")
    void deveErroAoDeletarClienteInexistente() {

        when(clienteRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ClienteNotFoundException.class,
                () -> clienteService.deletar(99L)
        );
    }

    // =========================
    // BUSCAR POR NOME
    // =========================
    @Test
    @DisplayName("Deve buscar cliente por nome")
    void deveBuscarPorNome() {

        when(clienteRepository.findByNomeContainingIgnoreCase("Maria"))
                .thenReturn(List.of(cliente));

        List<Cliente> result = clienteService.buscarPorNome("Maria");

        assertEquals(1, result.size());
        verify(clienteRepository).findByNomeContainingIgnoreCase("Maria");
    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar cliente por nome")
    void deveLancarExcecaoQuandoNaoEncontrarClientePorNome() {

        when(clienteRepository.findByNomeContainingIgnoreCase(anyString()))
                .thenReturn(List.of());

        ClienteNotFoundException exception = assertThrows(
                ClienteNotFoundException.class,
                () -> clienteService.buscarPorNome("Inexistente")
        );

        assertTrue(exception.getMessage().contains("Cliente não encontrado"));

        verify(clienteRepository).findByNomeContainingIgnoreCase("Inexistente");
    }
    @Test
    @DisplayName("Deve buscar clientes por nome com sucesso")
    void deveBuscarClientesPorNome() {

        when(clienteRepository.findByNomeContainingIgnoreCase("Maria"))
                .thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.buscarPorNome("Maria");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());

        verify(clienteRepository).findByNomeContainingIgnoreCase("Maria");
    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar clientes por nome")
    void deveLancarExcecaoNomeNaoEncontrado() {

        when(clienteRepository.findByNomeContainingIgnoreCase(anyString()))
                .thenReturn(List.of());

        assertThrows(
                ClienteNotFoundException.class,
                () -> clienteService.buscarPorNome("Nada")
        );
    }


}