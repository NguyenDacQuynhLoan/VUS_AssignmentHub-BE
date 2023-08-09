package com.edusystem.repositories;

import com.edusystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserCode(String code);
    public List<User> findUsersBySubjectsCode(String code);
//
    public User findBySubjectsCode(String code);
}
