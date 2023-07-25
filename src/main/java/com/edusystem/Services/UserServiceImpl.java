package com.edusystem.Services;

import java.sql.SQLException;
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
@Transactional(rollbackFor = {SQLException.class},timeout = 500)
public class UserServiceImpl{
	@Autowired
	SecurityConfig _securityConfig;

	@Autowired
	UserRepository _userRepo;

	public List<User> getAllUsers(){
		List<User> list = _userRepo.findAll();
		return list;
	}

	public User getUserById(Long id){
		Optional<User> existUser = _userRepo.findById(id);
		if(existUser.isPresent()){
			return existUser.get();
		}else {
			throw new NoSuchElementException("User with id is existed: " + id);
		}
	}

	public User createUser(User user) {
		User existUser = getUserById(user.getId());

		if(existUser == null){
			String encodedPassword = _securityConfig
					.passwordEncoder()
					.encode(user.getPassword());

			user.setPassword(encodedPassword);
			return _userRepo.save(user);
		}else {
			throw new NoSuchElementException("User with id is existed: " + user.getId());
		}
	}

	public User updateUser(User user){
		User existUser = getUserById(user.getId());

		if(existUser != null){
			if(user.getPassword().equals(existUser.getPassword())){
				String encodedPassword = _securityConfig
						.passwordEncoder()
						.encode(user.getPassword());

				user.setPassword(encodedPassword);
			}
			return _userRepo.save(user);
		}else {
			throw new NoSuchElementException("User with id is existed: " + user.getId());
		}
	}

	public boolean DeleteUser (Long id){
		User existUser = getUserById(id);
		if(existUser != null){
			_userRepo.deleteById(id);
			return true;
		}
		return false;
	}
}