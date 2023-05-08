package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.User;
import java.util.Optional;


/**
 * Interfaz con los metodos a implementar en el Userservice.
 */
public interface UserServiceInterface {

  void save(User.UserAuthentication userDto);

  MessageDto update(Long userId, User.UserDto userDto);

  Optional<User> findByEmail(String email);

  User.UserDto findById(Long userId);
}
