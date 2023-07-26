package com.edusystem.repositories;

import com.edusystem.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    public Assignment findByCode(String code);

    public List<Assignment> findByUserUserCode(String userCode);

    @Query("SELECT u FROM tbl_assignment u  " +
            "WHERE u.code  LIKE %:conditionValue% " +
            "OR    u.title LIKE %:conditionValue%")
    public List<Assignment> filterAssignments(@Param("conditionValue") String conditionValue);
}
