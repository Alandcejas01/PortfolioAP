package com.arg.backend.controllers;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.WorkExperience;
import com.arg.backend.services.WorkExpService;
import java.util.List;
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

/**
 * Controller con los endpoints para los metodos del service de WorkExperience.
 */
@RestController
@RequestMapping("/api/v1/workExp")
@RequiredArgsConstructor
public class WorkExpController {

  private final WorkExpService workExpService;

  @GetMapping
  public ResponseEntity<List<WorkExperience.WorkExperienceDto>> findAll() {
    return ResponseEntity.ok(workExpService.findAll());
  }

  @GetMapping("/{expId}")
  public ResponseEntity<WorkExperience.WorkExperienceDto> findById(@PathVariable Long expId) {
    return ResponseEntity.ok(workExpService.findById(expId));
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<MessageDto> create(
          @RequestBody WorkExperience.WorkExperienceRequest workExperienceDto,
          @PathVariable Long userId) {
    return ResponseEntity.ok(workExpService.create(workExperienceDto, userId));
  }

  @PutMapping("/update/{workExpId}")
  public ResponseEntity<MessageDto> updateById(
          @PathVariable Long workExpId,
          @RequestBody WorkExperience.WorkExperienceDto workExperienceDto) {
    return ResponseEntity.ok(workExpService.update(workExpId, workExperienceDto));
  }

  @DeleteMapping("/delete/{expId}")
  public ResponseEntity<MessageDto> deleteById(@PathVariable Long expId) {
    return ResponseEntity.ok(workExpService.delete(expId));
  }
}
