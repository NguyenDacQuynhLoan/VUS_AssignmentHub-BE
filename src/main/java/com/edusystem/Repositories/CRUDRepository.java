package com.edusystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CRUDRepository<T> extends JpaRepository<T,Long> {
}
