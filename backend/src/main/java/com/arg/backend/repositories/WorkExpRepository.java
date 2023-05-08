package com.arg.backend.repositories;


import com.arg.backend.entities.WorkExperience;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para WorkExperience que extiende de JpaRepository para heredar las querys de JPA.
 */
@Repository
public interface WorkExpRepository extends JpaRepository<WorkExperience, Long> {

  @Query(value = "select w from WorkExperience w WHERE w.active = true")
  List<WorkExperience> findActives();

  @Query(value = "select w from WorkExperience w where w.active = true and w.nameExperience = ?1")
  Optional<WorkExperience> findByNameExperience(String expName);

  @Query(value = "select w from WorkExperience w where w.active = true and w.id = ?1")
  Optional<WorkExperience> findActiveById(Long expId);
}
