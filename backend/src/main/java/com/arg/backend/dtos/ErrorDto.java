package com.arg.backend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
  private String message;
}
