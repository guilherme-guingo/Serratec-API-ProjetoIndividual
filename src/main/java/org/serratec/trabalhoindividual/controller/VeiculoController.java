package org.serratec.trabalhoindividual.controller;

import jakarta.validation.Valid;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoBuscaId;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoCriar;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoUpdateInput;
import org.serratec.trabalhoindividual.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody VeiculoCriar veiculoCriar) {
        veiculoService.inserir(veiculoCriar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<VeiculoBuscaId>> buscar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {

        return ResponseEntity.ok(veiculoService.buscar(placa, marca, modelo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @Valid @RequestBody VeiculoUpdateInput veiculoUpdate) {
        veiculoService.atualizar(id, veiculoUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}