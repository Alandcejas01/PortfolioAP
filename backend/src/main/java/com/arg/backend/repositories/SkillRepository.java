package com.arg.backend.repositories;


import com.arg.backend.entities.Education;
import com.arg.backend.entities.Skill;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Interfaz para Skill que extiende de JpaRepository para heredar las querys de JPA.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

  @Query(value = "select s from Skill s WHERE s.active = true")
  List<Skill> findActives();

  @Query(value = "select s from Skill s WHERE s.active = true and s.nameSkill = ?1")
  Optional<Skill> findByNameSkill(String skillName);

  @Query(value = "select s from Skill s WHERE s.active = true and s.id = ?1")
  Optional<Skill> findActiveById(Long skillId);
}
