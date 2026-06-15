package com.premit.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.premit.dto.UserDto;
import com.premit.entity.UserInfo;
import com.premit.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
		modelAndView.addObject("message",result);
		modelAndView.setViewName("Result");
	
		return modelAndView;
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request) {
		
		UserInfo userInfo = usersService.userLogin(request.getParameter("uname"), request.getParameter("pword"));
		if(userInfo==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Invalid Credentials!, Please try again.<br>");
			modelAndView.setViewName("Result");
			return modelAndView;
		} else  {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("userInfo", userInfo);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("LoginResult");
			modelAndView.addObject("message", "Login successfull for user : "+userInfo.getFirstName()+"<br>");
			return modelAndView;
		}
	}
	
	
	@GetMapping(path="/settings")
	public ModelAndView loadSettingsPage(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired, Please login agian.<br>");
			modelAndView.setViewName("Result");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("Settings");		
			return modelAndView;
		}
	}
	
	@GetMapping(path="/password")
	public ModelAndView loadPasswordUpdateForm(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired, Please login agian.<br>");
			modelAndView.setViewName("Result");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("PasswordUpdate");		
			return modelAndView;
		}
	}
	
	@PostMapping("/updatepassword")
	public ModelAndView passwordUpdate(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired, Please login agian.<br>");
			modelAndView.setViewName("Result");
			return modelAndView;
		} else {
			String userName = request.getParameter("uname");
			String currentPassword = request.getParameter("currentpwd");
			String newPassword = request.getParameter("newpwd");
			String retypedPassword = request.getParameter("retypedpwd");
			
			ModelAndView modelAndView = new ModelAndView();
			
			if(currentPassword.equals(newPassword)) {
				modelAndView.addObject("message", "Your current password and new password cannot be same!<br>");
				modelAndView.setViewName("PasswordUpdateResult");
			}
			else if(newPassword.equals(retypedPassword)) {
				String result = usersService.passwordUpdate(currentPassword, newPassword, userName);
				modelAndView.addObject("message", result);
				modelAndView.setViewName("PasswordUpdateResult2");	
			} else {
				modelAndView.setViewName("PasswordUpdateResult");
				modelAndView.addObject("message", "Your new password and re-typed password both are not matched!<br>");
			}		
			return modelAndView;
		}
	}
	
	@GetMapping("/search")
	public ModelAndView loadSearchUsersPage(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired!, Please login again.<br>");
			modelAndView.setViewName("Result");	
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("SearchUsers");
			return modelAndView;
		}
	}
	
	@GetMapping("/searchuser")
	public ModelAndView searchUserByMailId(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired!, Please login again.<br>");
			modelAndView.setViewName("SearchUsersResult");	
			return modelAndView;
		} else {
			String emailId = request.getParameter("emailId");
			UserDto userDto = usersService.usersSearch(emailId);
			if(userDto==null) {
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.addObject("message", "User not existed with email : "+emailId+"<br>");
				modelAndView.setViewName("SearchUsersResult");	
				return modelAndView;		
			} else {
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.addObject("userDto",userDto);
				modelAndView.setViewName("SearchUsersResult");	
				return modelAndView;	
			}
		}
	}
	
	@GetMapping("/logout")
	public ModelAndView userLogoout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if(httpSession==null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Session expired!, Please login again.<br>");
			modelAndView.setViewName("Result");	
			return modelAndView;
		} else {
			httpSession.invalidate();	
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", "Logged out succesfully.<br>");
			modelAndView.setViewName("LogoutResult");	
			return modelAndView;
		}
	}
	
}



























