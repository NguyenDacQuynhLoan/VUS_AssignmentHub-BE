package com.edusystem.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project configuration
 */
@Configuration
public class _ApplicationConfiguration {
    /**
     *  Configuration model mapper transient
     * @return config model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}