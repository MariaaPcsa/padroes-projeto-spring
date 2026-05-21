package com.maria.padroes_projeto_spring.client;

import com.maria.padroes_projeto_spring.dto.ViaCepResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "viacep-client",
        url = "${viacep.url}"
)
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponseDTO consultarCep(
            @PathVariable("cep") String cep
    );
}