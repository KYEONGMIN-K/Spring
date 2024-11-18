package com.springmvc.controller;

import java.util.*;

import com.springmvc.domain.Book;
import com.springmvc.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//1. 컨트롤러 지정
@Controller
//@RequestMapping(value="/books", method=RequestMethod.GET)
@RequestMapping("/books")
public class BookController {
	// 인젝션
	// BookService도 <component-scan>에 등록되어 있어야한다.
	@Autowired
	private BookService bookService;
	
	//2. 함수 생성 및 매핑
	//@RequestMapping(value="/books", method=RequestMethod.GET)
	@GetMapping	//default mapping
	public String requestBookList(Model model) {
		System.out.println("BookController 진입");
		
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList",list);
		
		return "books";
	}
	
	// DB 내 모든 책을 출력하는 함수
	@GetMapping("/all")
	public ModelAndView requestAllBooks(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		modelAndView.addObject("bookList", list);
		modelAndView.setViewName("books");
		return modelAndView;
		
	}
	
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		System.out.println("category값 : "+ bookCategory);
		List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		model.addAttribute("bookList", booksByCategory);
		
		return "books";
	}
	
	//1. url뒤에 파라미터가 딸려 날아오게 된다.
	// http://localhost:8080 서버
	// /BookMarket 프로젝트명
	// /books 컨트롤러 매핑명
	// /filter 함수 매핑명
	// 여기부터 변수처리 : /bookFilter ; 여기서 ';'이 구분자
	// publisher(key)=%EA%B8%B8%EB%B2%97(value);(구분자)
	// category(key)=IT%EC%A0%84%EB%AC%B8%EC%84%9C(value)
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(
						//위 getmapping의 bookfilter
		@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, 
		Model model) {
		System.out.println("controller_filter 진입 : "+ bookFilter);
		//2. 여기서 service로 들어온 파라미터를 넘겨준다.
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
		//bookService.getBookListByFilter(bookFilter,model);
		//7. serv -> Repo , 다시 Repo -> serv로 Set을 가지고 돌아왔다면 model에 넣고 view 리졸브로 이동
		model.addAttribute("bookList",booksByFilter);
		return "books";
	}
		
	// 요청 url : http://localhost:8080/BookMarket/books/book?id=ISBN1234
	// http://localhost:8080 서버
	// /BookMarket 프로젝트명
	// /books 컨트롤러 매핑명
	// /book 메서드 매핑명
	// ?를 구분자로 id=ISBN1234 key=value
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
								//id라는 key로 날아오는 값을 bookId에 담겠다
		System.out.println("bookId");
		//서비스로부터 DTO 받기. 여기서 시작. 
		Book bookById = bookService.getBookById(bookId);
		System.out.println("model에 저장할 응답 : "+bookById);
		// 6. service > Repo 접근 후 dto를 찾았으면 다시 service로 왔다가 controller로 와서 
		// 요청 응답을 하기 위해 model에 가져온 dto를 담아준다.
		model.addAttribute("book",bookById);
		
		//뷰 리졸브로 이동
		return "book";
	}
	
	//도서 등록 입력을 위한 페이지 매핑 : GET
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		System.out.println("addbook GET IN");
		return "addBook";
	}
	
	//도서 등록을 위한 입력을 마친 후 '등록' 버튼을 눌렀을 때 : POST
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		System.out.println("addbook POST IN");
		// MODEL에 도서를 등록하기 위한 절차
		// 1. Service 멤버 메소드를 호출하여 Repo로 접근
		bookService.setNewBook(book);
		// 6. setNewBook() 함수 종료. 
		// Create는 따로 출력할 것이 없으니 redirect 사용
		System.out.println("addbook POST END");
		return "redirect:/books";
	}
	
	/*
	 * 메서드 수준의 modelAttribute를 선언하면 
	 * controller로 들어오는 모든 요청 매핑으로 가기 전에
	 * 해당 메서드가 수행된다. 	
	*/
	@ModelAttribute
	public void addAttributes(Model model) {
		System.out.println("title print IN");
		model.addAttribute("addTitle", "신규 도서 등록");
	}

	//처음에 삽입한다는 것. 허용된 것과 허용되지 않은 것을 구분하여 삽입할 수 있다.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name","unitPrice", "author",
				"description", "publisher","category", "unitsInStock",
				"totalPages","releaseDate","condition");
	}
}
