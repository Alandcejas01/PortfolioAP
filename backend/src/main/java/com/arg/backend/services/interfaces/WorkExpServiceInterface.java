package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.WorkExperience;
import java.util.List;


/**
 * Interfaz con los metodos a implementar en el WorkExpService.
 */
public interface WorkExpServiceInterface {

  MessageDto create(WorkExperience.WorkExperienceRequest workExperienceDto, Long userId);

  WorkExperience.WorkExperienceDto findById(Long workExpId);

  MessageDto update(Long workExpId, WorkExperience.WorkExperienceDto workExperienceDto);

  MessageDto delete(Long workExpId);

  List<WorkExperience.WorkExperienceDto> findAll();

}
