package com.arg.backend.controllers;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Project;
import com.arg.backend.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Controller con los endpoints para los metodos del service de Proyecto.
 */
@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping
  public ResponseEntity<List<Project.ProjectDto>> findAll() {
    return ResponseEntity.ok(projectService.findAll());
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<Project.ProjectDto> findById(@PathVariable Long projectId) {
    return ResponseEntity.ok(projectService.findById(projectId));
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<MessageDto> create(
          @RequestBody Project.ProjectRequest projectDto,
          @PathVariable Long userId) {
    return ResponseEntity.ok(projectService.create(projectDto, userId));
  }

  @PutMapping("/update/{projectId}")
  public ResponseEntity<MessageDto> updateById(
          @PathVariable Long projectId,
          @RequestBody Project.ProjectDto projectDto) {
    return ResponseEntity.ok(projectService.update(projectId, projectDto));
  }

  @DeleteMapping("/delete/{projectId}")
  public ResponseEntity<MessageDto> deleteById(@PathVariable Long projectId) {
    return ResponseEntity.ok(projectService.delete(projectId));
  }
}
