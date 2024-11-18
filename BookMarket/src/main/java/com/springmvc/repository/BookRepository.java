package com.springmvc.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.Model;

import com.springmvc.domain.*;

public interface BookRepository {
	List<Book> getAllBookList();
	List<Book> getBookListByCategory(String category);
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	Book getBookById(String bookId);
	void setNewBook(Book book);

	//Set<Book> getBookListByFilter(Map<String, List<String>> filter, Model model);
}
