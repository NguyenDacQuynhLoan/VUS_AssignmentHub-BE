package com.edusystem.services;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.edusystem.configuration.security._SecurityConfig;
import com.edusystem.dto.ChangePassword;
import com.edusystem.entities.Role;
import com.edusystem.entities.User;
import com.edusystem.repositories.RoleRepository;
import com.edusystem.repositories.UserRepository;
import com.edusystem.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User services
 */
@Service
@Transactional(rollbackFor = {SQLException.class},timeout = 500)
public class UserServiceImpl implements UserServices{
	@Autowired
	private _SecurityConfig securityConfig;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired private PasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 *  Get all users
	 * @return user list
	 */
	@Override
	public List<UserDto> getAllUsers(){
		try{
			List<UserDto> userDtoList = new ArrayList<>();
			userDtoList = userRepository
					.findAll().stream()
					.map(e -> {
						var temp = e.getUserRole().getName();
						UserDto dto = modelMapper.map(e, UserDto.class);
						dto.setUserRoleName(e.getUserRole().getName());
						dto.setUserRoleCode(e.getUserRole().getCode());
						return dto;
					})
					.collect(Collectors.toList());
			return userDtoList;
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	@Override
	public UserDto getUserByCode(String userCode) {
		try {
			User user = userRepository.findByUserCode(userCode);
			UserDto userDto = modelMapper.map(user,UserDto.class);
			Role role = roleRepository.findByCode(user.getUserRole().getCode());

			userDto.setUserRoleCode(role.getCode());
			userDto.setUserRoleName(role.getName());

			return userDto;
		}catch(Exception error){
			throw new ExceptionService(error.getMessage());
		}
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
		try{
			// validate
			if(userRepository.findByUserCode(user.getUserCode()) != null){
				throw new RuntimeException(user.getUserCode() + " is existed");
			}

			if(userRepository.findByEmail(user.getEmail()) != null){
				throw new ExceptionService(user.getEmail() + " is existed");
			}

			Role userRole = roleRepository.findByCode(user.getUserRoleCode());
			if(userRole == null){
				throw new ExceptionService("Role is not exist.");
			}

			// encode password
			String encodedPassword = securityConfig
					.passwordEncoder()
					.encode(user.getPassword());
			user.setPassword(encodedPassword);

			// convert to DTO
			User userConverted = modelMapper.map(user,User.class);
			userConverted.setUserRole(userRole);

			// add User
			userRole.addUser(userConverted);

			userRepository.save(userConverted);
			return user;
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 *  Update user
	 * @param user User DTO
	 * @return updated User DTO
	 */
	@Override
	public UserDto updateUser(UserDto user){
		try {
			User userByCode = userRepository.findByUserCode(user.getUserCode());

			if(userByCode == null) {
				throw new ExceptionService("Can\'t find User " + user.getUserCode());
			}

			User userMapped = modelMapper.map(user, User.class);
			userMapped.setPassword(userByCode.getPassword());
			userMapped.setId(userByCode.getId());

			Role role = roleRepository.findByCode(user.getUserRoleCode());
			userMapped.setUserRole(role);
			role.removeUser(userByCode);
			role.addUser(userMapped);

			roleRepository.save(role);
			userRepository.save(userMapped);

			return user;
		}catch(Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 *  Update User password
	 * @param model Change Password model
	 * @return true if password is updated
	 */
	public boolean updateUserPassword(ChangePassword model) {
		try{
			User userByCode = userRepository.findByUserCode(model.getUserCode());
			boolean isMatched = securityConfig.passwordEncoder()
					.matches(model.getCurrentPassword(),userByCode.getPassword());
			if(!isMatched){
				throw new ExceptionService("Current Password is incorrect");
			}

			String encodedPassword = securityConfig
					.passwordEncoder()
					.encode(model.getNewPassword());
			userByCode.setPassword(encodedPassword);
			userRepository.save(userByCode);
			return true;
		}catch (Exception error) {
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 *  Delete user
	 * @param code User DTO code
	 * @return true if deleted
	 */
	@Override
	public boolean DeleteUser (String code){
		try{
			User userByCode = userRepository.findByUserCode(code);
			User existUser = getUserById(userByCode.getId());
			if(existUser != null){
				userRepository.deleteById(userByCode.getId());
				return true;
			}
			return false;
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}
}