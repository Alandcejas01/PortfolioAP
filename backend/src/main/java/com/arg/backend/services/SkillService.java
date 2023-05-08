package com.arg.backend.services;

import com.arg.backend.config.ModelMapperConfig;
import com.arg.backend.dtos.MessageDto;
import com.arg.backend.entities.Skill;
import com.arg.backend.entities.User;
import com.arg.backend.exceptions.GeneralException;
import com.arg.backend.repositories.SkillRepository;
import com.arg.backend.repositories.UserRepository;
import com.arg.backend.services.interfaces.SkillServiceInterface;
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
public class SkillService implements SkillServiceInterface {

  private final SkillRepository skillRepository;
  private final UserRepository userRepository;

  ModelMapper mapper = new ModelMapperConfig().modelMapper();

  public SkillService(SkillRepository skillRepository, UserRepository userRepository) {
    this.skillRepository = skillRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageDto create(Skill.SkillRequest skillDto, Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException("El usuario no existe",
                    HttpStatus.BAD_REQUEST));

    if (skillRepository.findByNameSkill(
            skillDto.getNameSkill()).isPresent()) {
      throw new GeneralException("La Skill ya existe", HttpStatus.BAD_REQUEST);
    }
    Skill skill = mapper.map(skillDto, Skill.class);
    skill.setUserId(user);
    skillRepository.save(skill);
    return new MessageDto("Skill agregada con éxito.");
  }

  @Override
  public Skill.SkillDto findById(Long skillId) {
    Skill skill = skillRepository.findActiveById(skillId)
            .orElseThrow(() -> new GeneralException("La skill no existe",
                    HttpStatus.BAD_REQUEST));
    return mapper.map(skill, Skill.SkillDto.class);
  }

  @Override
  public MessageDto update(Long skillId, Skill.SkillDto skillDto) {
    Skill existingSkill = skillRepository.findActiveById(skillId)
            .orElseThrow(() -> new GeneralException("La skill no existe",
                    HttpStatus.BAD_REQUEST));

    Optional<Skill> skill = skillRepository
            .findByNameSkill(skillDto.getNameSkill());
    skill.ifPresent(skillName -> {
      if (!skillId.equals(skillName.getId())) {
        throw new GeneralException("El nombre de la skill ya existe", HttpStatus.BAD_REQUEST);
      }
    });

    mapper.map(skillDto, existingSkill);
    skillRepository.save(existingSkill);
    return new MessageDto("Skill actualizada con éxito");
  }

  @Override
  public MessageDto delete(Long skillId) {
    Skill skill = skillRepository.findActiveById(skillId)
            .orElseThrow(() -> new GeneralException("No existe la skill",
                    HttpStatus.BAD_REQUEST));

    skill.setActive(false);
    skill.setUserId(null);
    skillRepository.save(skill);
    return new MessageDto("Skill eliminada con éxito.");
  }

  @Override
  public List<Skill.SkillDto> findAll() {
    List<Skill> skills = skillRepository.findActives();

    return skills.stream()
            .map(skill -> mapper.map(skill, Skill.SkillDto.class))
            .collect(Collectors.toList());
  }
}
