package com.edusystem.repositories.Authen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.edusystem.entities.Role;
import com.edusystem.repositories.RoleRepository;
import com.edusystem.repositories.UserRepository;
import com.edusystem.services.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *  Authentication Repository
 */
@Repository
public class AuthenticateRepository {

    @Autowired
    UserRepository _userRepository;

    @Autowired
    RoleRepository _roleRepository;

    public void getUserRoleName(com.edusystem.entities.User user){
        var temp =_roleRepository.findAll().stream()
                .filter(e -> e.getCode().equals(user.getUserCode())).findFirst();
    }

    /**
     *  Find email in Database
     * @param email User email from User entity
     * @return founded user
     */
    public UserDetails findEmail(String email){
         List<UserDetails> newUserDetail = _userRepository.findAll().stream().map(e -> {
//            if(e.getUserRole() == null){
//                return null;
//            }
//            String roleCode  = e.getUserRole().getCode();
            return new User(
                 e.getEmail(),
                 e.getPassword(),
                    Collections.singleton(
                            new SimpleGrantedAuthority("ADMIN")
                    )
             );
         }).collect(Collectors.toList());

        UserDetails userDetails = null;
        if(newUserDetail.size() > 0){
            for (UserDetails user : newUserDetail) {
                if(user.getUsername().equals(email)) {
                    userDetails = new User(user.getUsername(),
                            user.getPassword(),
                            user.getAuthorities());
                }
            }
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("not found User");
        }
        return userDetails;
    }
}