package com.springmvc.controller;

import java.util.*;

import com.springmvc.domain.Book;
import com.springmvc.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//1. 컨트롤러 지정
@Controller
public class BookController {
	// 인젝션
	@Autowired
	private BookService bookService;
	
	//2. 함수 생성 및 매핑
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public String requestBookList(Model model) {
		System.out.println("BookController 진입");
		
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList",list);
		
		return "books";
	}
		
}
