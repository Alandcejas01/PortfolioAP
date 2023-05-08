package com.arg.backend.repositories;

import com.arg.backend.entities.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Interfaz para Project que extiende de JpaRepository para heredar las querys de JPA.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query(value = "select p from Project p WHERE p.active = true")
  List<Project> findActives();

  @Query(value = "select p from Project p WHERE p.active = true and p.nameProject = ?1")
  Optional<Project> findByNameProject(String projectName);

  @Query(value = "select p from Project p WHERE p.active = true and p.id = ?1")
  Optional<Project> findActiveById(Long projectId);
}
