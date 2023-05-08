package com.arg.backend.controllers;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.User;
import com.arg.backend.services.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller con los endpoints para los metodos del service del Usuario.
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{userEmail}")
  public ResponseEntity<Optional<User>> user(@PathVariable String userEmail) {
    return ResponseEntity.ok(userService.findByEmail(userEmail));
  }

  @GetMapping("/find/{userId}")
  public ResponseEntity<User.UserDto> findById(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.findById(userId));
  }

  @PutMapping("update/{userId}")
  public ResponseEntity<MessageDto> update(@PathVariable Long userId,
                                           @RequestBody User.UserDto userDto) {
    return ResponseEntity.ok(userService.update(userId, userDto));
  }
}
