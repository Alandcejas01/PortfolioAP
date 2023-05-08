package com.arg.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase para la configuración del modelmapper.
 */
@Configuration
public class ModelMapperConfig {

  /**
   * Crea un model mapper con las configuraciones establecidas.
   *
   * @return modelMapper ya configurado.
   *
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(true);

    return modelMapper;
  }
}