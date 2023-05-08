package com.arg.backend.services.interfaces;

import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Education;
import java.util.List;



/**
 * Interfaz con los metodos a implementar en el WorkExpService.
 */
public interface EducationServiceInterface {

  MessageDto create(Education.EducationRequest educationDto, Long userId);

  Education.EducationDto findById(Long educationId);

  MessageDto update(Long educationId, Education.EducationDto educationDto);

  MessageDto delete(Long educationId);

  List<Education.EducationDto> findAll();

}
