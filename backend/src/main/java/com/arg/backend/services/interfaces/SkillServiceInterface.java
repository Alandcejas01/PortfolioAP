package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Skill;
import java.util.List;




/**
 * Interfaz con los metodos a implementar en el WorkExpService.
 */
public interface SkillServiceInterface {

  MessageDto create(Skill.SkillRequest skillDto, Long userId);

  Skill.SkillDto findById(Long skillId);

  MessageDto update(Long skillId, Skill.SkillDto skillDto);

  MessageDto delete(Long skillId);

  List<Skill.SkillDto> findAll();

}
