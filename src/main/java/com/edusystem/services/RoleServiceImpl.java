package com.edusystem.services;

import com.edusystem.dto.RoleDto;
import com.edusystem.entities.Role;
import com.edusystem.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RoleDto> getAllRoles() {
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleRepository.findAll().forEach(e -> roleDtoList.add(modelMapper.map(e,RoleDto.class)));
        return roleDtoList;
    }

    public Role getRoleById(Long id){
        Optional<Role> option = roleRepository.findById(id);
        if(option.isEmpty()){
            return null;
        }
        return option.get();
    }

    public RoleDto updateRole(RoleDto model){
        Role existedRole = roleRepository.findByCode(model.getCode());
        if(existedRole != null && getRoleById(existedRole.getId()) != null){
             roleRepository.save(existedRole);
             return model;
        }
        return null;
    }

    public RoleDto createRole(RoleDto model){
        Role existedRole = roleRepository.findByCode(model.getCode());
        if(existedRole == null){
            Role roleMapped = modelMapper.map(model,Role.class);
            roleRepository.save(roleMapped);
            return model;
        }
        return null;
    }

    public boolean deleteRole(String code){
        Role existedRole = roleRepository.findByCode(code);
        if(existedRole != null){
            roleRepository.deleteById(existedRole.getId());
            return true;
        }
        return false;
    }
}
