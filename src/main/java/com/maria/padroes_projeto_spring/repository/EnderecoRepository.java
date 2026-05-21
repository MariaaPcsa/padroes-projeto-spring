package com.maria.padroes_projeto_spring.repository;

import com.maria.padroes_projeto_spring.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}