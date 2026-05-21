package com.maria.padroes_projeto_spring.service.impl;

import com.maria.padroes_projeto_spring.client.ViaCepClient;

import com.maria.padroes_projeto_spring.dto.ViaCepResponseDTO;

import com.maria.padroes_projeto_spring.model.Endereco;

import com.maria.padroes_projeto_spring.service.ViaCepService;

import org.springframework.stereotype.Service;

@Service
public class ViaCepServiceImpl implements ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepServiceImpl(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Override
    public Endereco consultarCep(String cep) {

        ViaCepResponseDTO dto =
                viaCepClient.consultarCep(cep);

        if (dto == null || Boolean.TRUE.equals(dto.getErro())) {

            throw new RuntimeException(
                    "CEP não encontrado"
            );
        }

        Endereco endereco = new Endereco();

        endereco.setCep(
                dto.getCep().replaceAll("\\D", "")
        );

        endereco.setLogradouro(dto.getLogradouro());

        endereco.setComplemento(dto.getComplemento());

        endereco.setBairro(dto.getBairro());

        endereco.setLocalidade(dto.getLocalidade());

        endereco.setUf(dto.getUf());

        endereco.setIbge(dto.getIbge());

        endereco.setGia(dto.getGia());

        endereco.setDdd(dto.getDdd());

        endereco.setSiafi(dto.getSiafi());

        return endereco;
    }
}