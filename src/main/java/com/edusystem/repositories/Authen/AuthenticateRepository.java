package com.edusystem.repositories.Authen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edusystem.repositories.RoleRepository;
import com.edusystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
         List<UserDetails> newUserDetail = new ArrayList<>();

        _userRepository.findAll().forEach(e -> {
             UserDetails newUserArray = new User(
                 e.getEmail(),
                 e.getPassword(),
                     Collections.singleton(new SimpleGrantedAuthority("AD"))
//                 Collections.singleton(new SimpleGrantedAuthority(e.getUserCode().getName()))
             );
             newUserDetail.add(newUserArray);
         });

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