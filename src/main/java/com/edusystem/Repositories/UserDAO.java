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
        return  list
                .stream()
                .filter(e -> e.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("not found User"));
    }
}
