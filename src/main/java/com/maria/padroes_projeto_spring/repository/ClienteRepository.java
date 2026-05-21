package com.maria.padroes_projeto_spring.repository;

import com.maria.padroes_projeto_spring.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(
            String nome  );
}