package com.springmvc.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Example01Controller {

	@GetMapping("/form")
	public String requestForm() {
		System.out.println("GET매핑 IN");
		return "webpage09_01";
	}
	
	@PostMapping("/form")
	public String submitForm(@RequestParam("name") String name,
							@RequestParam("fileImage") MultipartFile file, HttpServletRequest request) {
		String save = request.getServletContext().getRealPath("/resources/images");
		System.out.println("POST매핑 IN");
		String filename=file.getOriginalFilename();
		System.out.println("POST매핑 filename : "+ filename);
		File f = new File("c:\\upload\\"+name+"_"+filename);
		
		try {
			file.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("try catch end");
		return "webpage09_submit";
	}
}
