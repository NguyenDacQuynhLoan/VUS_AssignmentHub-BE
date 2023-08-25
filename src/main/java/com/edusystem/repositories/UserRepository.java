package com.edusystem.repositories;

import com.edusystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *  User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserCode(String code);

    public User findByEmail(String mail);

    public List<User> findUsersBySubjectsCode(String code);

    public User findBySubjectsCode(String code);
}