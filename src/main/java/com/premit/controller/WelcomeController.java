package com.premit.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.premit.dto.UserDto;
import com.premit.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {
	
	@Autowired
	public UsersService usersService;
	
	@RequestMapping(path="/welcome",method=RequestMethod.GET)
	public String loadWelcomePage() {
		return "Welcome";
	}
	
	@RequestMapping(path="/register",method=RequestMethod.GET)
	public String loadRegisterForm() {
		return "AccountCreation";
	}
	
	@RequestMapping(path="/create",method=RequestMethod.POST)
	public ModelAndView createUserAccount(HttpServletRequest request) {
		
		LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dob"));
		long mobile = Long.parseLong(request.getParameter("mble"));
		
		UserDto userDto = new UserDto();
		
		userDto.setDateOfBirth(dateOfBirth);
		userDto.setFirstName(request.getParameter("fname"));
		userDto.setGender(request.getParameter("gender"));
		userDto.setLastName(request.getParameter("lname"));
		userDto.setMobile(mobile);
		userDto.setPassword(request.getParameter("pword"));
		userDto.setUserName(request.getParameter("uname"));
		userDto.setEmail(request.getParameter("email"));
		
		String result = usersService.createAccount(userDto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result",result);
		modelAndView.setViewName("Result");
	
		return modelAndView;
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request) {
		
		String result = usersService.userLogin(request.getParameter("uname"), request.getParameter("pword"));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result",result);
		modelAndView.setViewName("Result");
	
		return modelAndView;
	}
	
}



























