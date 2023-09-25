package com.edusystem.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import com.edusystem.entities.Role;
import com.edusystem.entities.User;
import com.edusystem.enums.Major;
import com.edusystem.repositories.RoleRepository;
import com.edusystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project configuration
 */
@Configuration
public class _ApplicationConfiguration implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

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

    /**
     *  Default data
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().size() == 0 && roleRepository.findAll().size() == 0)
        {
            Role adminRole = new Role(Long.valueOf(1),"ADMIN","Administrator",new ArrayList<>());
            Role userRole = new Role(Long.valueOf(2),"USER","User",new ArrayList<>());
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            roleRepository.flush();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Random random = new Random();
            String[] genders = {"Female" ,"Male"};
            String[] sampleDates = {"1999-10-09" ,"1997-8-25","1996-12-08","2003-3-21","1999-12-12"};

            for (int i = 0; i <= 50; i++) {
                User newUser = new User(
                    Long.valueOf(i + 1),
                    "CODE".concat(String.valueOf(i)),
                    adminRole,
                    "USER".concat(String.valueOf(i)),
                    Major.randomMajor(),
                    genders[random.nextInt(genders.length)],
                    dateFormat.parse(sampleDates[random.nextInt(sampleDates.length)]),
                    "HCM",
                    "09000".concat(String.valueOf(i)),
                    i == 0 ?"admin@gmail":"admin".concat(String.valueOf(i + 1)) +"@gmail",
                    "$2a$12$DJICPRimzm695WS5g.0em.c7EfWeryWb2BaJs3oGi.bvIvgJLKqVm", // admin123
                    new ArrayList<>(),
                    new ArrayList<>()
                );
                userRepository.save(newUser);
            }
            userRepository.flush();
        }
    }
}