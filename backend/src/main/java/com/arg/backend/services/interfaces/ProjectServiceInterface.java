package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Project;
import java.util.List;


/**
 * Interfaz con los metodos a implementar en el WorkExpService.
 */
public interface ProjectServiceInterface {

  MessageDto create(Project.ProjectRequest projectDto, Long userId);

  Project.ProjectDto findById(Long projectId);

  MessageDto update(Long projectId, Project.ProjectDto projectDto);

  MessageDto delete(Long projectId);

  List<Project.ProjectDto> findAll();

}
