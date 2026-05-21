package com.maria.padroes_projeto_spring.controller;

import com.maria.padroes_projeto_spring.model.Cliente;
import com.maria.padroes_projeto_spring.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")

@Tag(
        name = "Clientes",
        description = "API para gerenciamento de clientes"
)
public class ClienteRestController {

    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping

    @Operation(
            summary = "Buscar todos os clientes",
            description = "Retorna todos os clientes cadastrados"
    )

    public ResponseEntity<List<Cliente>> buscarTodos() {

        return ResponseEntity.ok(
                clienteService.buscarTodos()
        );
    }

    @GetMapping("/{id}")

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna um cliente específico"
    )

    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")

    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.buscarPorId(id)
        );
    }

    @PostMapping

    @Operation(
            summary = "Cadastrar cliente",
            description = "Cadastra um novo cliente"
    )

    @ApiResponse(responseCode = "201", description = "Cliente criado")

    public ResponseEntity<Cliente> inserir(
            @Valid @RequestBody Cliente cliente
    ) {

        Cliente clienteSalvo = clienteService.inserir(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteSalvo);
    }

    @PutMapping("/{id}")

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza um cliente existente"
    )

    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente
    ) {

        return ResponseEntity.ok(
                clienteService.atualizar(id, cliente)
        );
    }

    @DeleteMapping("/{id}")

    @Operation(
            summary = "Deletar cliente",
            description = "Remove um cliente"
    )

    @ApiResponse(responseCode = "204", description = "Cliente removido")

    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")

    public ResponseEntity<List<Cliente>> buscarPorNome(
            @RequestParam String nome
    ) {

        return ResponseEntity.ok(
                clienteService.buscarPorNome(nome)
        );
    }
}