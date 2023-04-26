package com.arg.backend.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralException extends RuntimeException {
  private HttpStatus status;

  public GeneralException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
