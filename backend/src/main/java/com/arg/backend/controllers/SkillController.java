package com.arg.backend.controllers;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Skill;
import com.arg.backend.services.SkillService;
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
 * Controller con los endpoints para los metodos del service de Skill.
 */
@RestController
@RequestMapping("/api/v1/skill")
@RequiredArgsConstructor
public class SkillController {

  private final SkillService skillService;

  @GetMapping
  public ResponseEntity<List<Skill.SkillDto>> findAll() {
    return ResponseEntity.ok(skillService.findAll());
  }

  @GetMapping("/{skillId}")
  public ResponseEntity<Skill.SkillDto> findById(@PathVariable Long skillId) {
    return ResponseEntity.ok(skillService.findById(skillId));
  }

  @PostMapping("/create/{skillId}")
  public ResponseEntity<MessageDto> create(
          @RequestBody Skill.SkillRequest skillDto,
          @PathVariable Long skillId) {
    return ResponseEntity.ok(skillService.create(skillDto, skillId));
  }

  @PutMapping("/update/{skillId}")
  public ResponseEntity<MessageDto> updateById(
          @PathVariable Long skillId,
          @RequestBody Skill.SkillDto skillDto) {
    return ResponseEntity.ok(skillService.update(skillId, skillDto));
  }

  @DeleteMapping("/delete/{skillId}")
  public ResponseEntity<MessageDto> deleteById(@PathVariable Long skillId) {
    return ResponseEntity.ok(skillService.delete(skillId));
  }
}
