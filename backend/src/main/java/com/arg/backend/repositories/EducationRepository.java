package com.arg.backend.repositories;


import com.arg.backend.entities.Education;
import com.arg.backend.entities.WorkExperience;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para Education que extiende de JpaRepository para heredar las querys de JPA.
 */
@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

  @Query(value = "select e from Education e WHERE e.active = true")
  List<Education> findActives();

  @Query(value = "select e from Education e WHERE e.active = true and e.nameEducation = ?1")
  Optional<Education> findByNameEducation(String educationName);

  @Query(value = "select e from Education e WHERE e.active = true and e.id = ?1")
  Optional<Education> findActiveById(Long educationId);
}
