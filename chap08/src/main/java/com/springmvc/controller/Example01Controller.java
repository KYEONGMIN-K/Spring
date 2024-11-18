package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Example01Controller {
		
	@GetMapping("/exam01")
	public String requestMethod(Model model) {
		System.out.println("exam01 진입");
		return "webpage08_01";
	}
	
	@GetMapping("/admin/main")
	public String requestMthod2(Model model) {
		System.out.println("admin 진입");
		model.addAttribute("data","/webpage01/adminPage.jsp");
		return "webpage01/adminPage";
	}
	@GetMapping("/manager/main")
	public String requestMethod3(Model model) {
		System.out.println("manager 진입");
		model.addAttribute("data","/webpage01/managerPage.jsp");
		return "webpage01/managerPage";
	}
	@GetMapping("/member/main")
	public String requestMethod4(Model model) {
		System.out.println("member 진입");
		model.addAttribute("data", "/webpage01/memberPage.jsp");
		return "webpage01/memberPage";
	}
	@GetMapping("/home/main")
	public String requestMethod5(Model model) {
		System.out.println("home 진입");
		model.addAttribute("data", "/webpage01/homePage.jsp");
		return "webpage01/homePage";
	}
	
	
	
}
