package com.arg.backend.services;

import com.arg.backend.config.ModelMapperConfig;
import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Education;
import com.arg.backend.entities.User;
import com.arg.backend.exceptions.GeneralException;
import com.arg.backend.repositories.EducationRepository;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.services.interfaces.EducationServiceInterface;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene metodos CRUD para las educaciones del usuario.
 */
@Service
public class EducationService implements EducationServiceInterface {

  private final EducationRepository educationRepository;
  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapperConfig().modelMapper();

  public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
    this.educationRepository = educationRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageDto create(Education.EducationRequest educationDto, Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
                    HttpStatus.BAD_REQUEST));

    if (educationRepository.findByNameEducation(
            educationDto.getNameEducation()).isPresent()) {
      throw new GeneralException("La educación ya existe", HttpStatus.BAD_REQUEST);
    }
    Education education = mapper.map(educationDto, Education.class);
    education.setUserId(user);
    educationRepository.save(education);
    return new MessageDto("Educación agregada con éxito.");
  }

  @Override
  public Education.EducationDto findById(Long educationId) {
    Education education = educationRepository.findActiveById(educationId)
            .orElseThrow(() -> new GeneralException("La educación no existe",
                    HttpStatus.BAD_REQUEST));
    return mapper.map(education, Education.EducationDto.class);
  }

  @Override
  public MessageDto update(Long educationId, Education.EducationDto educationDto) {
    Education existingEducation = educationRepository.findActiveById(educationId)
            .orElseThrow(() -> new GeneralException("La educación no existe",
                    HttpStatus.BAD_REQUEST));

    Optional<Education> education = educationRepository
            .findByNameEducation(educationDto.getNameEducation());
    education.ifPresent(educationName -> {
      if (!educationId.equals(educationName.getId())) {
        throw new GeneralException("El nombre de la educación ya existe", HttpStatus.BAD_REQUEST);
      }
    });

    mapper.map(educationDto, existingEducation);
    educationRepository.save(existingEducation);
    return new MessageDto("Educación actualizada con éxito");
  }

  @Override
  public MessageDto delete(Long educationId) {
    Education education = educationRepository.findActiveById(educationId)
            .orElseThrow(() -> new GeneralException("No existe la educación",
                    HttpStatus.BAD_REQUEST));

    education.setActive(false);
    education.setUserId(null);
    educationRepository.save(education);
    return new MessageDto("Educación eliminada con éxito.");
  }

  @Override
  public List<Education.EducationDto> findAll() {
    List<Education> educations = educationRepository.findActives();

    return educations.stream()
            .map(education -> mapper.map(education, Education.EducationDto.class))
            .collect(Collectors.toList());
  }
}
