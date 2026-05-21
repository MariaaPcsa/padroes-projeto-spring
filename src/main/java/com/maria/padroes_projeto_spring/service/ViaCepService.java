package com.maria.padroes_projeto_spring.service;

import com.maria.padroes_projeto_spring.model.Endereco;

public interface ViaCepService {

    Endereco consultarCep(String cep);
}