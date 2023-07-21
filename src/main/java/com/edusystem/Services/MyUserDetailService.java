//package com.edusystem.Services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//    @Value("${spring.security.user.email}")
//    private String adminEmail;
//
//    @Value("${spring.security.user.password}")
//    private String adminPassword;
//
//    @Value("${spring.security.user.role}")
//    private String adminRole;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if(username.equals("admin@gmail.com")){
//            return User.builder()
//                    .username(adminEmail)
//                    .password(adminPassword)
//                    .roles(adminRole).build();
//        }
//        return  null;
//    }
//}
