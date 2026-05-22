package org.serratec.trabalhoindividual.controller;

import jakarta.validation.Valid;
import org.serratec.trabalhoindividual.model.cliente.ClienteBuscaId;
import org.serratec.trabalhoindividual.model.cliente.ClienteCriar;
import org.serratec.trabalhoindividual.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteCriar clienteCriar) {
        clienteService.inserir(clienteCriar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteBuscaId>> buscar(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome) {

        return ResponseEntity.ok(clienteService.buscar(cpf, nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}