package org.serratec.trabalhoindividual.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.trabalhoindividual.model.cliente.ClienteCriar;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50)
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 12, min = 11)
    @Column(length = 12, nullable = false)
    private String telefone;

    @NotBlank(message = "O cpf é obrigatório")
    @Size(max = 11, min = 11)
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veiculo> veiculos;

    public Cliente(ClienteCriar clienteCriar) {
        this.nome = clienteCriar.getNome();
        this.telefone = clienteCriar.getTelefone();
        this.cpf = clienteCriar.getCpf();
        this.email = clienteCriar.getEmail();
    }
}
