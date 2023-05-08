package com.arg.backend.services;

import com.arg.backend.config.ModelMapperConfig;
import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Project;
import com.arg.backend.entities.User;
import com.arg.backend.exceptions.GeneralException;
import com.arg.backend.repositories.ProjectRepository;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.services.interfaces.ProjectServiceInterface;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


/**
 * Servicio que contiene metodos CRUD para los proyectos del usuario.
 */
@Service
public class ProjectService implements ProjectServiceInterface {

  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapperConfig().modelMapper();

  public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageDto create(Project.ProjectRequest projectDto, Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
                    HttpStatus.BAD_REQUEST));

    if (projectRepository.findByNameProject(
            projectDto.getNameProject()).isPresent()) {
      throw new GeneralException("El proyecto ya existe", HttpStatus.BAD_REQUEST);
    }
    Project project = mapper.map(projectDto, Project.class);
    project.setUserId(user);
    projectRepository.save(project);
    return new MessageDto("Proyecto agregado con éxito.");
  }

  @Override
  public Project.ProjectDto findById(Long projectId) {
    Project project = projectRepository.findActiveById(projectId)
            .orElseThrow(() -> new GeneralException("El proyecto no existe",
                    HttpStatus.BAD_REQUEST));
    return mapper.map(project, Project.ProjectDto.class);
  }

  @Override
  public MessageDto update(Long projectId, Project.ProjectDto projectDto) {
    Project existingProject = projectRepository.findActiveById(projectId)
            .orElseThrow(() -> new GeneralException("El proyecto no existe",
                    HttpStatus.BAD_REQUEST));

    Optional<Project> project = projectRepository
            .findByNameProject(projectDto.getNameProject());
    project.ifPresent(projectName -> {
      if (!projectId.equals(projectName.getId())) {
        throw new GeneralException("El nombre del proyecto ya existe", HttpStatus.BAD_REQUEST);
      }
    });

    mapper.map(projectDto, existingProject);
    projectRepository.save(existingProject);
    return new MessageDto("Proyecto actualizado con éxito");
  }

  @Override
  public MessageDto delete(Long projectId) {
    Project project = projectRepository.findActiveById(projectId)
            .orElseThrow(() -> new GeneralException("No existe el proyecto",
                    HttpStatus.BAD_REQUEST));

    project.setActive(false);
    project.setUserId(null);
    projectRepository.save(project);
    return new MessageDto("Proyecto eliminado con éxito.");
  }

  @Override
  public List<Project.ProjectDto> findAll() {
    List<Project> projects = projectRepository.findActives();

    return projects.stream()
            .map(project -> mapper.map(project, Project.ProjectDto.class))
            .collect(Collectors.toList());
  }
}
