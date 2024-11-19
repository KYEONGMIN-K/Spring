package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/loginfailed")
	public String loginerror(Model model) {
		model.addAttribute("error","true");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		//로그아웃 후 어디로 갈 것인지
		return "redirect:/";
	}
}
