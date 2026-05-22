package com.maria.padroes_projeto_spring.service.impl;

import com.maria.padroes_projeto_spring.exception.ClienteNotFoundException;
import com.maria.padroes_projeto_spring.model.Cliente;
import com.maria.padroes_projeto_spring.model.Endereco;
import com.maria.padroes_projeto_spring.repository.ClienteRepository;
import com.maria.padroes_projeto_spring.repository.EnderecoRepository;
import com.maria.padroes_projeto_spring.service.ClienteService;
import com.maria.padroes_projeto_spring.service.ViaCepService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            ViaCepService viaCepService
    ) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNotFoundException("Cliente não encontrado com id: " + id)
                );
    }

    @Override
    @Transactional
    public Cliente inserir(Cliente cliente) {
        return salvarClienteComCep(cliente);
    }

    @Override
    @Transactional
    public Cliente atualizar(Long id, Cliente cliente) {
        buscarPorId(id);
        cliente.setId(id);
        return salvarClienteComCep(cliente);
    }

    @Override
    @Transactional
    public void deletar(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente não encontrado com id: " + id);
        }

        clienteRepository.deleteById(id);
    }

    private Cliente salvarClienteComCep(Cliente cliente) {

        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("CEP inválido");
        }

        String cep = cliente.getEndereco().getCep().replaceAll("\\D", "");

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novo = viaCepService.consultarCep(cep);
                    return enderecoRepository.save(novo);
                });

        cliente.setEndereco(endereco);

        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {

        List<Cliente> clientes =
                clienteRepository.findByNomeContainingIgnoreCase(nome);

        if (clientes == null || clientes.isEmpty()) {
            throw new ClienteNotFoundException(
                    "Cliente não encontrado com nome: " + nome
            );
        }

        return clientes;
    }
}