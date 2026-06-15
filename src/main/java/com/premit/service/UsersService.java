package com.premit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premit.dto.UserDto;
import com.premit.entity.UserInfo;
import com.premit.repository.UserInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UsersService {
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	
	public String createAccount(UserDto userDto) {
		System.out.println("USER DTO FROM SERVICE : "+userDto);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setDateOfBirth(userDto.getDateOfBirth());
		userInfo.setEmail(userDto.getEmail());
		userInfo.setFirstName(userDto.getFirstName());
		userInfo.setGender(userDto.getGender());
		userInfo.setLastName(userDto.getLastName());
		userInfo.setMobile(userDto.getMobile());
		userInfo.setPassword(userDto.getPassword());
		userInfo.setUserName(userDto.getUserName());
		
		if(userInfoRepository.findById(userInfo.getUserName()).isPresent()) {
			return userInfo.getUserName()+" already taken, Please try with other username.";
		} else {
			UserInfo savedEntity = userInfoRepository.save(userInfo);
			if(savedEntity!=null) {
				return "Account creation done succcessfully for user "+savedEntity.getFirstName()+", Login with your credentials.";
			} else {
				return "Account creation failed!, Please try again.";	
			}
		}
	}
	
	
	public UserInfo userLogin(String userName,String password) {
		
		UserInfo userInfo = null;
		
		Optional<UserInfo> optional = userInfoRepository.findByUserNameAndPassword(userName, password);
		if(optional.isPresent()) {
			userInfo = optional.get();
		}
		return userInfo;
	}
	
	
	public String passwordUpdate(String currentPassword,String newPassword,String username) {
		Optional<UserInfo> optional = userInfoRepository.findByUserNameAndPassword(username, currentPassword);
		if(optional.isPresent()) {
			UserInfo userInfo = optional.get();
			userInfo.setPassword(newPassword);
			UserInfo savedEntity = userInfoRepository.save(userInfo);
			if(savedEntity==null) {
				return "Password updation failed!, Try again.";
			} else {
				return "Password updated succcessfully.";
			}
		} else {
			return "Password updation failed!, Try again.!";
		}
		
	}
	
	public UserDto usersSearch(String emailId) {
		Optional<UserInfo> optional = userInfoRepository.findByEmail(emailId);
		if(optional.isPresent()) {
			UserInfo userInfo = optional.get();
			UserDto userDto = new UserDto();
			userDto.setFirstName(userInfo.getFirstName());
			userDto.setLastName(userInfo.getLastName());
			userDto.setGender(userInfo.getGender());
			userDto.setDateOfBirth(userInfo.getDateOfBirth());
			userDto.setMobile(userInfo.getMobile());
			return userDto;
		} else {
			return null;
		}
	}
}





















