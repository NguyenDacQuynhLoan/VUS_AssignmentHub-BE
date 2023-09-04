package com.edusystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edusystem.services.RoleServiceImpl;
import com.edusystem.dto.RoleDto;

import java.util.List;

/**
 *  Role Controller
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController extends ExceptionController {
    @Autowired
    RoleServiceImpl _roleService;

    @GetMapping
    public List<RoleDto> getAllRoles(){
        return _roleService.getAllRoles();
    }
}
