package com.arg.backend.services;

import com.arg.backend.config.ModelMapperConfig;
import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.User;
import com.arg.backend.exceptions.GeneralException;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.services.interfaces.UserServiceInterface;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


/**
 * Servicio que contiene toda la logica del negocio relacionada al Usuario.
 */
@Service
public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapperConfig().modelMapper();

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(User.UserAuthentication userDto) {
    User userEntity = mapper.map(userDto, User.class);
    userRepository.save(userEntity);
  }

  @Override
  public MessageDto update(Long userId, User.UserDto userDto) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
                    HttpStatus.BAD_REQUEST));

    mapper.map(userDto, user);
    userRepository.save(user);
    return new MessageDto("Usuario actualizado con éxito");
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User.UserDto findById(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
            HttpStatus.BAD_REQUEST));

    return mapper.map(user, User.UserDto.class);
  }

}
