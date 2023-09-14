package com.edusystem.repositories;

import com.edusystem.entities.Assignment;
import com.edusystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//    @Query("SELECT u FROM tbl_user u  " +
//            "WHERE u.code  LIKE %:conditionValue% " +
//            "OR    u.title LIKE %:conditionValue%")
//    public List<Assignment> filterAssignments(@Param("conditionValue") String conditionValue);

    public List<User> findUsersBySubjectsCode(String code);

    public User findBySubjectsCode(String code);
}