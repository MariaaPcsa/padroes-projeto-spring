package com.maria.padroes_projeto_spring.service;

import com.maria.padroes_projeto_spring.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> buscarPorNome(String nome);
    List<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    Cliente inserir(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}