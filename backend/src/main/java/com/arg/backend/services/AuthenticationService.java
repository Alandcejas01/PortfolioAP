package com.arg.backend.services;

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

/**
 * Servicio para el registro y autenticación de la aplicación.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  ModelMapper mapper = new ModelMapper();

  /**
   * Este método se encarga de registrar al usuario en la bases de datos
   * y returnar el token jwt.
   *
   * @param request con datos del registro del usuario.
   * @return JWT token.
   */
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

    userService.save(mapper.map(user, User.UserAuthentication.class));

    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponseDto.builder()
        .token(jwtToken)
        .build();
  }

  /**
   * Este método se encarga de autenticar el usuario obtenido
   * y devolver el jwt si la autenticacion es correcta.
   *
   * @param request contiene datos del login del Usuario.
   * @return JWT token.
   */
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
