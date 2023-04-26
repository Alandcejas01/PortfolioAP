package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.UserDto;
import com.arg.backend.entities.User;
import java.util.Optional;


public interface UserServiceInterface {

  UserDto save(UserDto userDto);

  Optional<User> findByEmail(String email);
}
