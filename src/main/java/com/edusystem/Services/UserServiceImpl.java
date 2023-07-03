package com.edusystem.Services;

import java.sql.SQLException;
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
public class UserServiceImpl {//extends CRUDServiceImpl<User>
	@Autowired
	SecurityConfig _securityConfig;

	//remove later
	@Autowired
	UserRepository _userRepo;

	public User createAsync(User user) {
		Optional<User> existUser = _userRepo.findById(user.getId());
		if(existUser.isEmpty()){
			return  null;
		}

		if(existUser != null)
		{
			String encodedPassword = _securityConfig.passwordEncoder().encode(user.getPassword());
			user.setPassword(encodedPassword);

			return createAsync(user);
		}else {
			throw new NoSuchElementException("User with id is existed: " + user.getId());
		}
	}
//	@Override
//	public User createAsync(User user) {
//		User existUser = getByIdAsync(user.getId());
//		if(existUser != null)
//		{
//			String encodedPassword = _securityConfig.passwordEncoder().encode(user.getPassword());
//			user.setPassword(encodedPassword);
//
//			return createAsync(user);
//		}else {
//			throw new NoSuchElementException("User with id is existed: " + user.getId());
//		}
//	}
}