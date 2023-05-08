package com.arg.backend.entities;


import com.arg.backend.enums.LevelSkill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Esta clase hace referencia a la entidad skill de la bases de datos.
 * Su proposito es hacer relación a las skills del usuario.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "skills")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nameSkill;

  private LevelSkill levelSkill;

  private Boolean active = true;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User userId;

  /**
   * Dto para la creacion de skills.
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SkillRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSkill;
    private LevelSkill levelSkill;
    private Long userId;
  }

  /**
   * Dto para la manipulacion de datos en el CRUD.
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SkillDto {
    private Long id;
    private String nameSkill;
    private LevelSkill levelSkill;
    private Long userId;
  }
}
