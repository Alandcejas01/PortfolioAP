package com.arg.backend.controllers;

import com.arg.backend.dtos.auth.AuthenticationRequestDto;
import com.arg.backend.dtos.auth.AuthenticationResponseDto;
import com.arg.backend.dtos.auth.RegisterRequestDto;
import com.arg.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDto> register(
      @RequestBody RegisterRequestDto request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @RequestBody AuthenticationRequestDto request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

}
