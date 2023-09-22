package com.edusystem.services;

import java.util.List;

import com.edusystem.dto.UserAssignmentFilter;
import com.edusystem.dto.UserDto;

/**
 * User Services Interfaces
 */
public interface UserServices {
    /**
     *  Get all users with paging
     * @return user list
     */
    public List<UserDto> getAllUsersPaging(Integer pageIndex, Integer pageSize);

    /**
     *  Get all users
     * @return user list
     */
    public List<UserDto> getAllUsers();

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
     * Export List of Users
     * @return List User bytes
     */
    public byte[] exportUsers();

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