package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleMain {
	
	@GetMapping("/")
	public String exampleMain() {
		return "index";
	}
}
