package com.edusystem.services;

import com.edusystem.entities.Role;
import com.edusystem.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {
    @Autowired
    RoleRepository _roleRepository;

    public List<Role> getAllRoles() {
        return _roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        Optional<Role> option = _roleRepository.findById(id);
        if(option.isEmpty()){
            return null;
        }
        return option.get();
    }

    public Role updateRole(Role model){
        Role existedRole = getRoleById(model.getId());
        if(existedRole != null && model.getCode().equals(existedRole.getCode()) ){
            return  _roleRepository.save(model);
        }
        return null;
    }

    public Role createRole(Role model){
        Role existedRole = getRoleById(model.getId());
        if(existedRole == null){
            return  _roleRepository.save(model);
        }
        return null;
    }

    public boolean deleteRole(Long id){
        Role existedRole = getRoleById(id);
        if(existedRole != null){
            _roleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
