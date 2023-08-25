package com.edusystem.services;

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
    public List<UserDto> getAllUsers();

//    /**
//     *  Get user by Email
//     * @param email user Email
//     * @return detect User
//     */
//    public UserDto getUserByEmail(String email);
    public void generateDefaultUser();

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
