package com.springmvc.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Example03Controller {
	
	@GetMapping("/exam03")
	public String requestForm(Member member) {
		return "webpage09_02";
	}
	
	@PostMapping("/form3")
	public String submitForm(@ModelAttribute("member") Member member, HttpServletRequest request, HttpSession session) {
		
		String name = member.getName();
		String filename = member.getImageFile().getOriginalFilename();
		String save = request.getServletContext().getRealPath("/resources/images");
		System.out.println(save);
		try {
			member.getImageFile().transferTo(new File("c:\\upload\\"+name+"_"+filename));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "webpage09_submit";
	}
}
