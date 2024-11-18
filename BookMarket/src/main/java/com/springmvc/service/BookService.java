package com.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.Model;

import com.springmvc.domain.*;

public interface BookService {
	List<Book> getAllBookList();
	List<Book> getBookListByCategory(String category);
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	
	Book getBookById(String bookId);
	void setNewBook(Book book);
	//임의로 만든 것
	//void getBookListByFilter(Map<String, List<String>> filter, Model model);
	
}
