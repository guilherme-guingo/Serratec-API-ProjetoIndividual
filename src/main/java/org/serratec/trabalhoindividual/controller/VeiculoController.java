package org.serratec.trabalhoindividual.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cadastrar um novo veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados informados inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados"),
            @ApiResponse(responseCode = "409", description = "Dados já cadastrados")
    })
    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody VeiculoCriar veiculoCriar) {
        veiculoService.inserir(veiculoCriar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar veículos ou buscar por placa/marca/modelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos retornada"),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    @GetMapping
    public ResponseEntity<List<VeiculoBuscaId>> buscar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {
        return ResponseEntity.ok(veiculoService.buscar(placa, marca, modelo));
    }

    @Operation(summary = "Atualizar dados de um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados informados inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @Valid @RequestBody VeiculoUpdateInput veiculoUpdate) {
        veiculoService.atualizar(id, veiculoUpdate);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remover um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados informados inválidos"),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}