package com.arg.backend.services;

import com.arg.backend.config.ModelMapperConfig;
import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.User;
import com.arg.backend.entities.WorkExperience;
import com.arg.backend.exceptions.GeneralException;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.repositories.WorkExpRepository;
import com.arg.backend.services.interfaces.WorkExpServiceInterface;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene metodos CRUD para las experiencias laborales del usuario.
 */
@Service
public class WorkExpService implements WorkExpServiceInterface {

  private final WorkExpRepository workExpRepository;
  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapperConfig().modelMapper();

  public WorkExpService(WorkExpRepository workExpRepository, UserRepository userRepository) {
    this.workExpRepository = workExpRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageDto create(WorkExperience.WorkExperienceRequest workExperienceDto, Long userId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
                    HttpStatus.BAD_REQUEST));

    if (workExpRepository.findByNameExperience(
            workExperienceDto.getNameExperience()).isPresent()) {
      throw new GeneralException("La experiencia ya existe", HttpStatus.BAD_REQUEST);
    }
    WorkExperience workExperience = mapper.map(workExperienceDto, WorkExperience.class);
    workExperience.setUserId(user);
    workExpRepository.save(workExperience);
    return new MessageDto("Experiencia agregada con éxito.");
  }

  @Override
  public WorkExperience.WorkExperienceDto findById(Long workExpId) {
    WorkExperience workExperience = workExpRepository.findActiveById(workExpId)
            .orElseThrow(() -> new GeneralException("La experiencia no existe",
                    HttpStatus.BAD_REQUEST));
    return mapper.map(workExperience, WorkExperience.WorkExperienceDto.class);
  }

  @Override
  public MessageDto update(Long workExpId, WorkExperience.WorkExperienceDto workExperienceDto) {
    WorkExperience existingExperience = workExpRepository.findActiveById(workExpId)
            .orElseThrow(() -> new GeneralException("La experiencia no existe",
                    HttpStatus.BAD_REQUEST));

    Optional<WorkExperience> experience = workExpRepository
            .findByNameExperience(workExperienceDto.getNameExperience());
    experience.ifPresent(expName -> {
      if (!workExpId.equals(expName.getId())) {
        throw new GeneralException("El nombre de la experiencia ya existe", HttpStatus.BAD_REQUEST);
      }
    });

    mapper.map(workExperienceDto, existingExperience);
    workExpRepository.save(existingExperience);
    return new MessageDto("Experiencia actualizada con éxito");
  }

  @Override
  public MessageDto delete(Long workExpId) {
    WorkExperience workExp = workExpRepository.findActiveById(workExpId)
            .orElseThrow(() -> new GeneralException("No existe la experiencia",
                    HttpStatus.BAD_REQUEST));

    workExp.setActive(false);
    workExp.setUserId(null);
    workExpRepository.save(workExp);
    return new MessageDto("Experiencia eliminada con éxito.");
  }

  @Override
  public List<WorkExperience.WorkExperienceDto> findAll() {
    List<WorkExperience> experiences = workExpRepository.findActives();

    return experiences.stream()
            .map(experience -> mapper.map(experience, WorkExperience.WorkExperienceDto.class))
            .collect(Collectors.toList());
  }
}
