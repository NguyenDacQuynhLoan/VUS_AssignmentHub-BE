//package com.edusystem.configuration;
//
//import com.edusystem.entities.Role;
//import com.edusystem.entities.User;
//import com.edusystem.enums.Major;
//import com.edusystem.repositories.RoleRepository;
//import com.edusystem.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//@Component
////@Qualifier("DefaultGenerate")
//public class DefaultGenerate implements CommandLineRunner {
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Role adminRole = new Role(Long.valueOf(1),"ADMIN","Administrator",new ArrayList<>());
//        Role userRole = new Role(Long.valueOf(2),"USER","User",new ArrayList<>());
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//        roleRepository.flush();
//
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        User adminUser = new User(User
////            Long.valueOf(1),
////            "001",
////            adminRole,
////            "Ad Teacher",
////            Major.valueOf("Software"),
////            "Female",
////            dateFormat.parse("2000-12-12"),
////            "HCM",
////            "090262528",
////            "admin@gmail",
////            "$2a$12$DJICPRimzm695WS5g.0em.c7EfWeryWb2BaJs3oGi.bvIvgJLKqVm", // admin123
////            new ArrayList<>(),
////            new ArrayList<>()
////        );
////        userRepository.save(adminUser);
//    }
//}
