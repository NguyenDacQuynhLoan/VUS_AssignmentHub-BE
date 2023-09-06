package com.edusystem.configuration;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(Long.valueOf(1),"ADMIN","Administrator",new ArrayList<>());
        Role userRole = new Role(Long.valueOf(2),"USER","User",new ArrayList<>());
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        roleRepository.flush();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        User adminUser = new User(
//            Long.valueOf(1),
//            "001",
//            adminRole,
//            "Ad Teacher",
//            Major.valueOf("Software"),
//            "Female",
//            dateFormat.parse("2000-12-12"),
//            "HCM",
//            "090262528",
//            "admin@gmail",
//            "$2a$12$DJICPRimzm695WS5g.0em.c7EfWeryWb2BaJs3oGi.bvIvgJLKqVm", // admin123
//            new ArrayList<>(),
//            new ArrayList<>()
//        );
//        userRepository.save(adminUser);

        for (int i = 0; i <= 50; i++) {
            User newUser = new User(
                    Long.valueOf(i + 1),
                    "CODE".concat(String.valueOf(i)),
                    adminRole,
                    "USER".concat(String.valueOf(i)),
                    Major.valueOf("Software"),
                    "Female",
                    dateFormat.parse("2000-12-12"),
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