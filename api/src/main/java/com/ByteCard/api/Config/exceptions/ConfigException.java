package com.ByteCard.api.Config.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ExceptionErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ExceptionValid(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body( erro.stream().map(MessageErro::new).toList());
    }

    private record MessageErro(String campo, String mensagem){

        public MessageErro(FieldError erro) {
            this(erro.getField(),erro.getDefaultMessage());

        }
    }
}
