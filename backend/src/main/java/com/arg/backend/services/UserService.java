package com.arg.backend.services;

import com.arg.backend.dtos.UserDto;
import com.arg.backend.entities.User;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.services.interfaces.UserServiceInterface;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserRepository userRepository;

  ModelMapper mapper = new ModelMapper();

  @Override
  public UserDto save(UserDto userDto) {
    User userEntity = mapper.map(userDto, User.class);
    userRepository.save(userEntity);

    return mapper.map(userEntity, UserDto.class);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
