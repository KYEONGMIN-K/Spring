package com.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBookList() {
		System.out.println("BookServiceImpl 진입");
		return bookRepository.getAllBookList();
	}
	@Override
	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}
	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		//3. 넘어온 파라미터를 bookrepository.getBookListByFilter(파라미터)로 넘겨준다.
		Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
		bookRepository.getBookListByFilter(filter);
		//6. Repo에서 return한 정보를 가지고 다시 return (controller
		return booksByFilter;
	}
	
	@Override
	public Book getBookById(String bookId) {
		//2. 컨트롤러로부터 요청된 DTO를 주기 위해 함수가 호출되어 왔다.
		System.out.println("id_service 진입 : "+bookId);
		// 컨트롤러에게 DTO를 넘겨주기 위해 Repo의 멤버 메서드를 호출하며 파라미터로 bookId를 넘겨준다.
		Book bookById = bookRepository.getBookById(bookId);
		
		System.out.println("Repo에서 가져온 값 : "+ bookById);
		// 5. Repo로부터 받았으니 controller로 return하며 돌아가자.
		return bookById;
	}
	
	// MODEL에 도서를 등록하기 위한 절차
	//2. Repo의 멤버메서드 setNewBook()에 DTO를 파라미터로 넘긴다.
	//5. 함수 종료. 호출 위치로 이동
	@Override
	public void setNewBook(Book book) {
		System.out.println("setNewBook Service IN : "+book.getBookId());
		bookRepository.setNewBook(book);
	}

	

}
