package com.springmvc.controller;

import java.text.DateFormat;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	/*
	 * 여기서는 home()이 doGet, doPost의 역할을 한다.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//WEB-INF/views/(    ).jsp
	      Date date = new Date();
	      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	      
	      String formattedDate = dateFormat.format(date);
	      
	      model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
}
