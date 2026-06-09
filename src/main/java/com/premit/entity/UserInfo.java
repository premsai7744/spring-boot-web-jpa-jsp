package com.premit.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="esy_shpng_users")
public class UserInfo {
	
	/*
	 * username varchar2(30) PRIMARY KEY, password varchar2(30), f_name
	 * varchar2(30), l_name varchar2(30), email varchar2(50), gender varchar2(10),
	 * dob DATE, mobile varchar2(50)
	 */
	
	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="f_name")
	private String firstName;
	
	@Column(name="l_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="dob")
	private LocalDate dateOfBirth;
	
	@Column(name="mobile")
	private long mobile;
}
