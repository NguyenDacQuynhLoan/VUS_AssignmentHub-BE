package com.edusystem.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.edusystem.Configuration.SecurityConfig;
import com.edusystem.Entities.User;
import com.edusystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	SecurityConfig _securityConfig;

	@Autowired
	UserRepository _userReposity;

//	@Override
	public List<User> getAllUsers() {
		return _userReposity.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return _userReposity.findById(id);
	}

	public User updateUser(User user) {
		Optional<User> existUser = findUserById(user.getId());
		if(existUser.isPresent()) {
			return _userReposity.save(user);
		 } else {
	        throw new NoSuchElementException("User not found with id: " + user.getId());
	    }
	}

	public void deleteUser(Long id) {
		_userReposity.deleteById(id);
	}

	public User createUser(User user) {
		Optional<User> existUser = findUserById(user.getId());
		if(!existUser.isPresent())
		{
			 String encodedPassword = _securityConfig.passwordEncoder().encode(user.getPassword());
			user.setPassword(encodedPassword);

			return _userReposity.save(user);
		}else {
			throw new NoSuchElementException("User with id is existed: " + user.getId());
		}
	}
}
