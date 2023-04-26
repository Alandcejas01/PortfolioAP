package com.arg.backend.services;

import com.arg.backend.dtos.UserDto;
import com.arg.backend.dtos.auth.AuthenticationRequestDto;
import com.arg.backend.dtos.auth.AuthenticationResponseDto;
import com.arg.backend.dtos.auth.RegisterRequestDto;
import com.arg.backend.entities.User;
import com.arg.backend.enums.Role;
import com.arg.backend.exceptions.GeneralException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  ModelMapper mapper = new ModelMapper();

  public AuthenticationResponseDto register(RegisterRequestDto request) {

    Optional<User> userExist = userService.findByEmail(request.getEmail());

    if (userExist.isPresent()) {
      throw new GeneralException("El usuario ya existe", HttpStatus.BAD_REQUEST);
    }
    User user = User.builder()
        .fullName(request.getFullName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ADMIN)
        .build();

    userService.save(mapper.map(user, UserDto.class));

    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponseDto.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        ));

    User user = userService.findByEmail(request.getEmail()).orElseThrow();
    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponseDto.builder()
        .token(jwtToken)
        .build();
  }
}
