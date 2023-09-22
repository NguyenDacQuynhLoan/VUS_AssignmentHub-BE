package com.edusystem.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.opencsv.CSVWriter;

import com.edusystem.configuration.security._SecurityConfig;
import com.edusystem.entities.Role;
import com.edusystem.entities.User;
import com.edusystem.repositories.RoleRepository;
import com.edusystem.repositories.UserRepository;
import com.edusystem.dto.ChangePassword;
import com.edusystem.dto.UserAssignmentFilter;
import com.edusystem.dto.UserDto;

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
	public List<UserDto> getAllUsersPaging(Integer pageIndex, Integer pageSize){
		try{
			Pageable paging = PageRequest.of(pageIndex,pageSize, Sort.by("userCode"));
			return userRepository
				.findAll(paging).stream()
				.map(e -> {
					var temp = e.getUserRole().getName();
					UserDto dto = modelMapper.map(e, UserDto.class);
					dto.setUserRoleName(e.getUserRole().getName());
					dto.setUserRoleCode(e.getUserRole().getCode());
					return dto;
				})
				.collect(Collectors.toList());
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	@Override
	public List<UserDto> getAllUsers() {
		try{
			return userRepository
					.findAll().stream()
					.map(e -> {
						var temp = e.getUserRole().getName();
						UserDto dto = modelMapper.map(e, UserDto.class);
						dto.setUserRoleName(e.getUserRole().getName());
						dto.setUserRoleCode(e.getUserRole().getCode());
						return dto;
					})
					.collect(Collectors.toList());
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 *  Search all users
	 * @param index Page Index
	 * @param size Page Size
	 * @param keyword Search keyword
	 * @return List searched users
	 */
	@Override
	public List<UserDto> searchUsers(Integer index, Integer size, String keyword){
		try{
			Pageable paging = PageRequest.of(index,size, Sort.by("userCode"));
			List<User> userList =  userRepository.searchUser(keyword,paging);

			return  userList
					.stream()
					.map(e -> {
						var temp = e.getUserRole().getName();
						UserDto dto = modelMapper.map(e, UserDto.class);
						dto.setUserRoleName(e.getUserRole().getName());
						dto.setUserRoleCode(e.getUserRole().getCode());
						return dto;
					})
					.collect(Collectors.toList());
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 *  Filter all users
	 * @param index Page Index
	 * @param size Page Size
	 * @param filterValue User Assignment model
	 * @return List filtered users
	 */
	@Override
	public List<UserDto> filterUsers(Integer index, Integer size, UserAssignmentFilter filterValue) {
		try{
			// check date
			if(filterValue.getStartDate() != null){
				return null;
			}

			if(filterValue.getEndDate() != null){

			}

			Pageable paging = PageRequest.of(index,size, Sort.by("userCode"));
			List<User> userList = userRepository.filterUser(filterValue,paging);

			List<UserDto> userDtoList = new ArrayList<>();
			return  userList
					.stream()
					.map(e -> {
						var temp = e.getUserRole().getName();
						UserDto dto = modelMapper.map(e, UserDto.class);
						dto.setUserRoleName(e.getUserRole().getName());
						dto.setUserRoleCode(e.getUserRole().getCode());
						return dto;
					})
					.collect(Collectors.toList());
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 * Export List of Users
	 * @return List user bytes
	 */
	public byte[] exportUsers(){
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

			String[] header = {"User Code","User Name"};
			csvWriter.writeNext(header);

			for (UserDto user : getAllUsers()){
				String[] row = {user.getUserCode(),user.getUserName()};
				csvWriter.writeNext(row);
			}

			csvWriter.close();
			return outputStream.toByteArray();
		}catch (IOException error ){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 * User by Code
	 * @param userCode User Code
	 * @return User DTO model
	 */
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
	 * Get Total Number of Users
	 * @return Total number
	 */
	@Override
	public Long totalUsers() {
		return userRepository.count();
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
		try {
			User userByCode = this.userRepository.findByUserCode(model.getUserCode());
			boolean isMatched = this.securityConfig
					.passwordEncoder()
					.matches(model.getCurrentPassword(), userByCode.getPassword());

			if (!isMatched) {
				throw new ExceptionService("Current Password is incorrect");
			} else {
				String encodedPassword = this.securityConfig.passwordEncoder().encode(model.getNewPassword());
				userByCode.setPassword(encodedPassword);
				this.userRepository.save(userByCode);
				return true;
			}
		} catch (Exception var5) {
			throw new ExceptionService(var5.getMessage());
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