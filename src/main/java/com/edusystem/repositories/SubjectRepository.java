package com.edusystem.repositories;


import com.edusystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Subject Repository
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByCode (String code);
}