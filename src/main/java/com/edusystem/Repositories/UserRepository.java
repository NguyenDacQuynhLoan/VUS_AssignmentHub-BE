package com.edusystem.Repositories;

import com.edusystem.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CRUDRepository<User>{
//    @Query("SELECT CASE " +
//            "           WHEN COUNT(u) > 0 THEN TRUE " +
//            "           ELSE FALSE " +
//            "       END " +
//            "FROM USER u " +
//            "JOIN u.userRoles r " +
//            "WHERE u.id = :userId " +
//            "AND r.code = :roleCode")
//    User findByIdAndUserRoles_Code(Long userId, Integer roleCode);
}
