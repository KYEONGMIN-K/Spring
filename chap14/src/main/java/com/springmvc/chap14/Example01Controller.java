package com.springmvc.chap14;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Example01Controller {
	
	@GetMapping("/json")
	public String showForm() {
		return "webpage14_02";
	}
	
	@PostMapping("/test")
	public void submit(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);
	}
}
