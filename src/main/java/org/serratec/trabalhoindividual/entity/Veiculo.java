package org.serratec.trabalhoindividual.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.trabalhoindividual.model.veiculo.VeiculoCriar;

import java.util.UUID;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "veiculo")
    public class Veiculo {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        @NotNull
        private Cliente cliente;

        @NotBlank
        @Column(nullable = false)
        private String marca;

        @NotBlank
        @Column(nullable = false)
        private String modelo;

        @NotNull
        @Min(1886)
        @Column(nullable = false)
        private Integer ano;

        @NotNull
        @Positive
        @Column(nullable = false)
        private Double valor;

        @NotBlank
        @Size(min = 7, max = 7)
        @Column(nullable = false, unique = true, length = 7)
        private String placa;

        @NotNull
        @PositiveOrZero
        @Column(nullable = false)
        private Double maximoDesconto;

        @NotNull
        @Column(nullable = false)
        private boolean vendido;

        @Min(1)
        private Double valorVenda;

        public Veiculo(VeiculoCriar veiculoCriar, Cliente cliente) {
            this.marca = veiculoCriar.getMarca();
            this.modelo = veiculoCriar.getModelo();
            this.ano = veiculoCriar.getAno();
            this.valor = veiculoCriar.getValor();
            this.placa = veiculoCriar.getPlaca();
            this.maximoDesconto = veiculoCriar.getMaximoDesconto();
            this.vendido = veiculoCriar.isVendido();
            this.valorVenda = veiculoCriar.getValorVenda();
            this.cliente = cliente;
        }
    }
