package com.edusystem.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.edusystem.Entities.UserEntity;
import com.edusystem.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService{
	@Autowired
	IUserRepository _userReposity;
//EduSystemApplication
// 	inject = constructor
//	IUserRepository _userReposity;
//	ConstructorInjection
//	public UserService(IUserRepository _userReposity) {
//		this._userReposity = _userReposity;
//	}

	@Override
	public List<UserEntity> getAllUsers() {
		return _userReposity.findAll();
	}

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return _userReposity.findById(id);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		Optional<UserEntity> existUser = findUserById(user.getId());
		if(existUser.isPresent()) {
			return _userReposity.save(user);
		 } else {
	        throw new NoSuchElementException("User not found with id: " + user.getId());
	    }
	}

	@Override
	public void deleteUser(Long id) {
		_userReposity.deleteById(id);
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		Optional<UserEntity> existUser = findUserById(user.getId());
		if(!existUser.isPresent())
		{
			return _userReposity.save(user);
		}else {
			throw new NoSuchElementException("User with id is existed: " + user.getId());
		}
	}
}
