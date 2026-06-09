package com.premit.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	private String userName;
	private String Password;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private LocalDate dateOfBirth;
	private long mobile;
}
