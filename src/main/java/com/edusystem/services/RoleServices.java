package com.edusystem.services;

import java.util.List;

import com.edusystem.dto.RoleDto;

/**
 *  Role services interface
 */
public interface RoleServices {

    /**
     *  Get all roles
     * @return list of role
     */
    public List<RoleDto> getAllRoles();

    /**
     *  Update role
     * @param model role DTO model
     * @return updated role
     */
    public RoleDto updateRole(RoleDto model);

    /**
     *  Create role
     * @param model role DTO model
     * @return created role
     */
    public RoleDto createRole(RoleDto model);

    /**
     *  Delete role
     * @param code role code
     * @return true if deleted role
     */
    public boolean deleteRole(String code);
}
