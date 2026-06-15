package com.premit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.premit.entity.UserInfo;
import java.util.List;


public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
	Optional<UserInfo> findByUserNameAndPassword(String username,String password);
	Optional<UserInfo> findByEmail(String email);
	
	
}
