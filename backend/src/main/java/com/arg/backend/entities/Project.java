package com.arg.backend.entities;


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
 * Esta clase hace referencia a la entidad project de la bases de datos.
 * Su proposito es hacer relación a los proyectos del usuario.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nameProject;

  private String description;

  private String imgUrl;

  private String repoUrl;

  private String deployUrl;

  private Boolean active = true;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User userId;

  /**
   * Dto para la creacion de proyectos.
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ProjectRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProject;
    private String description;
    private String imgUrl;
    private String repoUrl;
    private String deployUrl;
    private Long userId;
  }

  /**
   * Dto para la manipulacion de datos en el CRUD.
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ProjectDto {
    private Long id;
    private String nameProject;
    private String description;
    private String imgUrl;
    private String repoUrl;
    private String deployUrl;
    private Long userId;
  }
}
