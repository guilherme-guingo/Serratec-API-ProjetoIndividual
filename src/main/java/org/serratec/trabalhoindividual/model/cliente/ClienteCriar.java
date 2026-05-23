package org.serratec.trabalhoindividual.model.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteCriar {

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 12)
    private String telefone;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email
    private String email;
}