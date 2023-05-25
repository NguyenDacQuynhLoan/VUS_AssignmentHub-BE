package com.edusystem.Repositories;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {

    private final static List<UserDetails> list = new ArrayList<>(Arrays.asList(
            new User(
                    "loannguyen123@gmail.com",
                    "$2a$10$EOeoExqNirHcUY//9HBy9e5MCkEXkeBAUaAMdp8Z9RZVEJpoXG1vq",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            )
    ));

    public UserDetails findEmail(String email){
        UserDetails userDetails = null;
        for (UserDetails user : list) {
            if(user.getUsername().equals(email)) {
                userDetails = new User(user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()); // clone cai nay ra. xai chung Object thi thang Spring security no set null pass sau moi lan authenticate o controller roi
            }
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("not found User");
        }
        return userDetails;
    }
}
