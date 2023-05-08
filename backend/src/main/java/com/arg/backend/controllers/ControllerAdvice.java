package com.arg.backend.controllers;

import com.arg.backend.dtos.ErrorDto;
import com.arg.backend.exceptions.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controlador de excepciones para los controllers Rest para el manejo de errores en la aplicación.
 */
@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(value = GeneralException.class)
  public ResponseEntity<ErrorDto> requestExceptionHandler(GeneralException ex) {
    ErrorDto error = ErrorDto.builder().message(ex.getMessage()).build();
    return new ResponseEntity<>(error, ex.getStatus());
  }
}
