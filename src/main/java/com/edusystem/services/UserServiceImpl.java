package com.edusystem.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.edusystem.configuration.SecurityConfig;
import com.edusystem.entities.User;
import com.edusystem.repositories.UserRepository;
import com.edusystem.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {SQLException.class},timeout = 500)
public class UserServiceImpl implements UserServices{
	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 *  Get all users
	 * @return user list
	 */
	@Override
	public List<UserDto> getAllUsers(){
		List<UserDto> userDtoList = new ArrayList<>();
		userRepository.findAll()
				.forEach(e -> userDtoList.add(modelMapper.map(e,UserDto.class)));
		List<User> allUsers = userRepository.findAll();
		return userDtoList;
	}

	/**
	 * Get user by Id
	 * @param id Id
	 * @return User by Id
	 */
	private User getUserById(Long id){
		Optional<User> existUser = userRepository.findById(id);
		if(existUser.isPresent()){
			return existUser.get();
		}
		return null;
	}

	/**
	 *  Create new user
	 * @param user User DTO
	 * @return new User
	 */
	@Override
	public UserDto createUser(UserDto user){
		User userByCode = userRepository.findByUserCode(user.getUserCode());
		if(userByCode == null){
			String encodedPassword = securityConfig
					.passwordEncoder()
					.encode(user.getPassword());

			user.setPassword(encodedPassword);

			User userConverted = modelMapper.map(user,User.class);

			userRepository.save(userConverted);

			return user;
		}else {
			throw new NoSuchElementException("Existed User with code is: " + user.getUserCode());
		}
	}

	/**
	 *  Update user
	 * @param user User DTO
	 * @return updated User DTO
	 */
	@Override
	public UserDto updateUser(UserDto user){
		User userByCode = userRepository.findByUserCode(user.getUserCode());
		if(userByCode != null && getUserById(userByCode.getId()) != null){
			if(user.getPassword().equals(userByCode.getPassword())){
				String encodedPassword = securityConfig
						.passwordEncoder()
						.encode(user.getPassword());
				user.setPassword(encodedPassword);
			}
			User userMapped = modelMapper.map(user,User.class);
			userMapped.setId(userMapped.getId());
			userRepository.save(userMapped);

			return user;
		}else {
			throw new NoSuchElementException("Existed User with code is : " + user.getUserCode());
		}
	}

	/**
	 *  Delete user
	 * @param code User DTO code
	 * @return true if deleted
	 */
	@Override
	public boolean DeleteUser (String code){
		User userByCode = userRepository.findByUserCode(code);
		User existUser = getUserById(userByCode.getId());
		if(existUser != null){
			userRepository.deleteById(userByCode.getId());
			return true;
		}
		return false;
	}
}