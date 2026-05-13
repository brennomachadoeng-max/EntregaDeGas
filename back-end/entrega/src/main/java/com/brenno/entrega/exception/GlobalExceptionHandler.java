package com.brenno.entrega.exception;

import com.brenno.entrega.DTO.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDTO> handleRuntimeException(RuntimeException ex) {
        ErroDTO erro = new ErroDTO("ERRO", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDTO> handleException(Exception ex) {
        ErroDTO erro = new ErroDTO("ERRO_INTERNO", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}