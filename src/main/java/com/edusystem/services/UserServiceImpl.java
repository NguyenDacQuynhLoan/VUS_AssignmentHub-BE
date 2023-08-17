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
		List<UserDto> userDtoList = new ArrayList<>();

		List<User> userDtoList2 = userRepository.findAll();

		userRepository.findAll()
				.forEach(e -> userDtoList.add(modelMapper.map(e,UserDto.class)));
		return userDtoList;
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

		if( userRepository.findByUserCode(user.getUserCode()) == null
		&&  userRepository.findByEmail(user.getEmail())		  == null){
			String encodedPassword = securityConfig
					.passwordEncoder()
					.encode(user.getPassword());

			user.setPassword(encodedPassword);

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
			throw new NoSuchElementException("Existed User with code is : " + user.getUserCode());
		}
	}

	/**
	 *  Update User password
	 * @param model Change Password model
	 * @return true if password is updated
	 */
	public boolean updateUserPassword(ChangePassword model) {
			User userbyCode = userRepository.findByUserCode(model.getUserCode());
			if(model.getOldPassword().equals(userbyCode.getPassword())){
				String encodedPassword = securityConfig
						.passwordEncoder()
						.encode(model.getNewPassword());
				userbyCode.setPassword(encodedPassword);
				return true;
			}
		return false;
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