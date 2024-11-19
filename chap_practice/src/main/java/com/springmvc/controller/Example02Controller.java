package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Example02Controller {
		
	@GetMapping("/exam02")
	public String requestMethod(Model model) {
		System.out.println("exam02 진입");
		return "webpage08_02";
	}
	
	@GetMapping("/manager/tag")
	public String requestMthod2(Model model) {
		System.out.println("manager 진입");
		return "webpage08_02";
	}
	
	
}
