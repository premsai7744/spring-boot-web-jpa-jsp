package com.premit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premit.dto.UserDto;
import com.premit.entity.UserInfo;
import com.premit.repository.UserInfoRepository;

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
}
