package com.arg.backend.repositories;

import com.arg.backend.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz para User que extiende de JpaRepository para las querys de JPA.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
