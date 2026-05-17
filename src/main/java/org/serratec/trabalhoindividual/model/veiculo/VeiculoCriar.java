package org.serratec.trabalhoindividual.model.veiculo;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class VeiculoCriar {

    @NotNull
    private UUID clienteId;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(1886)
    private Integer ano;

    @NotNull
    @Positive
    private Double valor;

    @NotBlank
    @Size(min = 7, max = 7)
    private String placa;

    @NotNull
    @PositiveOrZero
    private Double maximoDesconto;

    @NotNull
    private boolean vendido;

    @Positive
    private Double valorVenda;
}