package com.edusystem.repositories;


import com.edusystem.entities.Subject;
import com.edusystem.enums.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Subject Repository
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByCode (String code);
    public Subject findByMajor(Major major);
}