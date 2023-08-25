//package com.edusystem.configuration;
//
//import com.edusystem.services.UserServices;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class BeforeGenerateAppComponent implements ApplicationRunner {
//    private final UserServices userServices;
//    public BeforeGenerateAppComponent(UserServices userServices){
//        this.userServices = userServices;
//    }
//
//    @Override
////    @Transactional
//    public void run(ApplicationArguments args) throws Exception {
//        userServices.generateDefaultUser();
//    }
//}
