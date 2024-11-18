package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Example03Controller {
		
	@GetMapping("/exam03")
	public String requestMethod(Model model) {
		System.out.println("exam03 진입");
		return "webpage08_03";
	}
	
	@GetMapping("/admin/tag")
	public String requestMthod2(Model model) {
		System.out.println("admin 진입");
		return "webpage08_03";
	}
	
	
}
