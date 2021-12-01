package com.gbsystem.rpbackoffice.LoginController;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gbsystem.rpbackoffice.Logindomain.Login;
import com.gbsystem.rpbackoffice.Loginservice.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", new Login());
		return mav;
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("login") Login user) {
		Login oauthUser = loginService.login(user.getUsername(), user.getPassword());
		
		System.out.print(oauthUser);
		if(Objects.nonNull(oauthUser)) {
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/login";
	}

}
