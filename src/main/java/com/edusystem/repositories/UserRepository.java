package com.edusystem.repositories;

import com.edusystem.dto.UserAssignmentFilter;
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

    @Query("SELECT u, a.title FROM tbl_user u LEFT JOIN u.assignments a " +
            "WHERE u.userCode LIKE %:keyword% " +
            "OR FUNCTION('DATE_FORMAT', u.dateOfBirth, '%Y-%m-%d') LIKE %:keyword% " +
            "OR u.userName LIKE %:keyword% " +
            "OR u.userRole.code LIKE %:keyword% " +
            "OR u.location LIKE %:keyword% " +
            "OR u.phone LIKE %:keyword% " +
            "OR u.major LIKE %:keyword% " +
            "OR u.email LIKE %:keyword% " +
            "OR a.title LIKE %:keyword%")
    public List<User> searchUser(@Param("keyword") String keyword);

    @Query("SELECT u,a.title FROM tbl_user u " +
            "LEFT JOIN u.assignments a " +
            "WHERE u.userCode LIKE %:#{#filterObject.userCode}%"
//            "WHERE u.userCode = :#{#filterObject.userCode}"
    )
    public List<User> filterUser(@Param("filterObject") UserAssignmentFilter filterObject);
    public List<User> findUsersBySubjectsCode(String code);

    public User findBySubjectsCode(String code);
}