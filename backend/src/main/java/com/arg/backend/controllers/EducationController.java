package com.arg.backend.controllers;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Education;
import com.arg.backend.services.EducationService;
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
 * Controller con los endpoints para los metodos del service de Education.
 */
@RestController
@RequestMapping("/api/v1/education")
@RequiredArgsConstructor
public class EducationController {

  private final EducationService educationService;

  @GetMapping
  public ResponseEntity<List<Education.EducationDto>> findAll() {
    return ResponseEntity.ok(educationService.findAll());
  }

  @GetMapping("/{educationId}")
  public ResponseEntity<Education.EducationDto> findById(@PathVariable Long educationId) {
    return ResponseEntity.ok(educationService.findById(educationId));
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<MessageDto> create(
          @RequestBody Education.EducationRequest educationDto,
          @PathVariable Long userId) {
    return ResponseEntity.ok(educationService.create(educationDto, userId));
  }

  @PutMapping("/update/{educationId}")
  public ResponseEntity<MessageDto> updateById(
          @PathVariable Long educationId,
          @RequestBody Education.EducationDto educationDto) {
    return ResponseEntity.ok(educationService.update(educationId, educationDto));
  }

  @DeleteMapping("/delete/{educationId}")
  public ResponseEntity<MessageDto> deleteById(@PathVariable Long educationId) {
    return ResponseEntity.ok(educationService.delete(educationId));
  }
}
