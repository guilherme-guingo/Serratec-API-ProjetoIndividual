package org.serratec.trabalhoindividual.model.veiculo;

import lombok.Data;
import org.serratec.trabalhoindividual.entity.Veiculo;

import java.util.UUID;

@Data
public class VeiculoBuscaId {

    private UUID id;
    private String clienteNome;
    private String marca;
    private String modelo;
    private Integer ano;
    private Double valor;
    private String placa;
    private boolean vendido;
    private Double valorVenda;

    public VeiculoBuscaId(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.clienteNome = veiculo.getCliente().getNome();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
        this.placa = veiculo.getPlaca();
        this.vendido = veiculo.isVendido();
        this.valorVenda = veiculo.getValorVenda();
    }
}