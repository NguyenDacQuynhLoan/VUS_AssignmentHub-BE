package com.edusystem.services;

import com.edusystem.dto.UserAssignmentFilter;
import com.edusystem.dto.UserDto;

import java.util.List;

/**
 * User Services Interfaces
 */
public interface UserServices {
    /**
     *  Get all users
     * @return user list
     */
    public List<UserDto> getAllUsers(Integer pageIndex, Integer pageSize);

    /**
     *  Search all users
     * @param index Page Index
     * @param size Page Size
     * @param keyword Search keyword
     * @return List searched users
     */
    public List<UserDto> searchUsers(Integer index, Integer size, String keyword);

    /**
     *  Filter all users
     * @param index Page Index
     * @param size Page Size
     * @param filterValue User Assignment model
     * @return List filtered users
     */
    public List<UserDto> filterUsers(Integer index, Integer size, UserAssignmentFilter filterValue);

    /**
     * User by Code
     * @param userCode User Code
     * @return User DTO model
     */
    public UserDto getUserByCode(String userCode);

    /**
     * Get Total Number of Users
     * @return Total number
     */
    public Long totalUsers();

    /**
     *  Create new user
     * @param user User DTO
     * @return new User
     */
    public UserDto createUser(UserDto user);

    /**
     *  Update user
     * @param user User DTO
     * @return updated User DTO
     */
    public UserDto updateUser(UserDto user);

    /**
     *  Delete user
     * @param code User DTO code
     * @return true if deleted
     */
    public boolean DeleteUser (String code);
}