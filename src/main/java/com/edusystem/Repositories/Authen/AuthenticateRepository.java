package com.edusystem.Repositories.Authen;

import com.edusystem.Assets.Enum.Role;
import com.edusystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AuthenticateRepository {
    @Autowired
    UserRepository _userRepository;

    String getRoleName (com.edusystem.Entities.User user){
        com.edusystem.Entities.User detectedUser = _userRepository.findByIdAndUserRoles_Code(user.getId(), user.getRoleCode());
        String result =  Role.getByIndex(detectedUser.getRoleCode()).getName();
//        return  result;
        return "";
    }

    /**
     *  Find email in Database
     * @param email
     * @return
     */
    public UserDetails findEmail(String email){
         List<UserDetails> newUserDetail = new ArrayList<UserDetails>();

        _userRepository.findAll().forEach(e -> {
             UserDetails newUserArray = new User(
                 e.getUsername(),
                 e.getPassword(),
//                     Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
                 Collections.singleton(new SimpleGrantedAuthority(getRoleName(e)))
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
