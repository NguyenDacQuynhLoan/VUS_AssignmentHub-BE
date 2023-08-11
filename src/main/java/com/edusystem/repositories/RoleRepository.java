package com.edusystem.repositories;

import com.edusystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByCode(String code);
}