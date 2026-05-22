package org.serratec.trabalhoindividual.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErroResposta(
        @Schema(description = "Campo com a mensagem de erro detalhada do sistema")
        String mensagem
) {
}