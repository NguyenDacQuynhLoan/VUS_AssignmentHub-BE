package com.edusystem.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.edusystem.configuration._SecurityConfig;
import com.edusystem.dto.ChangePassword;
import com.edusystem.entities.User;
import com.edusystem.enums.Major;
import com.edusystem.repositories.UserRepository;
import com.edusystem.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
	private ModelMapper modelMapper;

	/**
	 *  Get all users
	 * @return user list
	 */
	@Override
	public List<UserDto> getAllUsers(){
		try{
			List<UserDto> userDtoList = new ArrayList<>();

			List<User> userDtoList2 = userRepository.findAll();

			userRepository.findAll()
					.forEach(e -> userDtoList.add(modelMapper.map(e,UserDto.class)));
			return userDtoList;
		}catch (Exception error){
			throw new ExceptionService(error.getMessage());
		}
	}

	/**
	 * Get user by Email
	 * @param email user Email
	 * @return User by email
	 */
	@Override
	public UserDto getUserByEmail(String email) {
		User userByMail = userRepository.findByEmail(email);
		return modelMapper.map(userByMail,UserDto.class);
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
//			if(userRepository.findByUserCode(user.getUserCode()) != null){
//				throw new RuntimeException(user.getUserCode() + " is existed");
//			}

			if(userRepository.findByEmail(user.getEmail()) != null){
				throw new ExceptionService(user.getEmail() + " is existed");
			}

			// encode password
			String encodedPassword = securityConfig
					.passwordEncoder()
					.encode(user.getPassword());
			user.setPassword(encodedPassword);

			// convert to DTO
			User userConverted = modelMapper.map(user,User.class);

//			switch (user.getMajor())
//			{
//				case Computer :
//				{
//					// USER list null
//					if(userRepository.findAll().stream().count() == 0){
//						user.setUserCode("CP001");
//					}else{
//						userRepository.findAll().stream().filter(e ->
//								Integer.parseInt(e.getUserCode().substring(2)) > 1
//						).findFirst();
//					}
//					// user list != null
//
//				}
//
//				break;
//			}

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

			if(userByCode != null) {
				User userMapped = modelMapper.map(user, User.class);
				userMapped.setPassword(userByCode.getPassword());
				userMapped.setId(userByCode.getId());
				userRepository.save(userMapped);

				return user;
			}
			return null;
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
			User userbyCode = userRepository.findByUserCode(model.getUserCode());
			if(model.getOldPassword().equals(userbyCode.getPassword())){
				String encodedPassword = securityConfig
						.passwordEncoder()
						.encode(model.getNewPassword());
				userbyCode.setPassword(encodedPassword);
				return true;
			}
			return false;
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