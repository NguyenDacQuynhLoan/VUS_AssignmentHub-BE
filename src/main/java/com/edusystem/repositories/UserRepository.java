package com.edusystem.repositories;

import com.edusystem.dto.UserAssignmentFilter;
import com.edusystem.entities.Assignment;
import com.edusystem.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *  User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>, PagingAndSortingRepository<User,Long> {
    public User findByUserCode(String code);

    public User findByEmail(String mail);

    @Query("SELECT u, a.title FROM tbl_user u " +
            "LEFT JOIN u.assignments a " +
            "WHERE LOWER(u.userCode) LIKE %:keyword% " +
            "OR FUNCTION('DATE_FORMAT', u.dateOfBirth, '%Y-%m-%d') LIKE %:keyword% " +
            "OR LOWER(u.userName) LIKE %:keyword% " +
            "OR LOWER(u.userRole.code) LIKE %:keyword% " +
            "OR LOWER(u.location) LIKE %:keyword% " +
            "OR LOWER(u.phone) LIKE %:keyword% " +
            "OR u.major LIKE %:keyword% " +
            "OR LOWER(u.email) LIKE %:keyword% " +
            "OR LOWER(a.title) LIKE %:keyword% " +
            "OR LOWER(a.code) LIKE %:keyword%"
    )
    public List<User> searchUser(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT u,a.title FROM tbl_user u " +
            "LEFT JOIN u.assignments a " +
            "WHERE (u.userCode LIKE %:#{#filterObject.userCode}% AND :#{#filterObject.userCode} != '') " +
            "OR (u.userName LIKE %:#{#filterObject.userName}% AND :#{#filterObject.userName} != '') " +
            "OR (u.gender LIKE %:#{#filterObject.gender}% AND :#{#filterObject.gender} != '') " +
            "OR (a.title LIKE %:#{#filterObject.assignmentTitle}% AND :#{#filterObject.assignmentTitle} != '') " +
            "OR (a.code  LIKE %:#{#filterObject.assignmentCode}% AND :#{#filterObject.assignmentCode} != '') " +
            "OR (a.grade = :#{#filterObject.grade} AND :#{#filterObject.grade} != NULL) " +
            "OR (u.major = :#{#filterObject.major} AND :#{#filterObject.major} != NULL) "
    )
    public List<User> filterUser(@Param("filterObject") UserAssignmentFilter filterObject, Pageable pageable);

    public List<User> findUsersBySubjectsCode(String code);

    public User findBySubjectsCode(String code);
}