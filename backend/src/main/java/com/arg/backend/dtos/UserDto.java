package com.arg.backend.dtos;

import com.arg.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {
  private Long id;
  private String fullName;
  private String password;
  private String email;
  private Role role;
}
