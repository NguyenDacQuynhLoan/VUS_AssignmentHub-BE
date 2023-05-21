package com.edusystem.Services;

import com.edusystem.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	public List<UserEntity> getAllUsers();

	public Optional<UserEntity> findUserById(Long id);

	public UserEntity updateUser(UserEntity user);

	public void deleteUser(Long id);

	public UserEntity createUser(UserEntity user);

}
