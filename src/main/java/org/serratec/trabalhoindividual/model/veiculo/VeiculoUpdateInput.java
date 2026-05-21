package org.serratec.trabalhoindividual.model.veiculo;

import lombok.Data;

@Data
public class VeiculoUpdateInput {
    private String marca;
    private String modelo;
    private Integer ano;
    private Double valor;
    private Double maximoDesconto;
    private boolean vendido;
    private Double valorVenda;
}