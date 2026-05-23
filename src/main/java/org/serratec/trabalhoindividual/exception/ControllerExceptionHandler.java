package org.serratec.trabalhoindividual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleNaoEncontrado(NaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResposta(ex.getMessage()));
    }

    @ExceptionHandler(DadoInvalidoException.class)
    public ResponseEntity<ErroResposta> handleDadoInvalido(DadoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResposta(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidacao(MethodArgumentNotValidException ex) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResposta("Erro de validação: " + mensagens));
    }

    @ExceptionHandler(JaCadastradoException.class)
    public ResponseEntity<ErroResposta> handleJaCadastrado(JaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroResposta(ex.getMessage()));
    }

}