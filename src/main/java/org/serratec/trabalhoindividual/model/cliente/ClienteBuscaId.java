package org.serratec.trabalhoindividual.model.cliente;

import lombok.Data;
import org.serratec.trabalhoindividual.entity.Cliente;

import java.util.UUID;

@Data
public class ClienteBuscaId {

    private UUID id;
    private String nome;
    private String telefone;
    private String email;

    public ClienteBuscaId(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
    }
}