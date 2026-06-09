package com.premit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.premit.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

}
