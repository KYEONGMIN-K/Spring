package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.springmvc.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepository{

	private List<Book> listOfBooks = new ArrayList<Book>();
	
	 public BookRepositoryImpl() { 
		 	System.out.println("BookController 생성자 진입");
		 
	        Book book1 = new Book("ISBN1234", "C# 교과서", 30000);
	        book1.setAuthor("박용준");
	        book1.setDescription("C# 교과서는 생애 첫 프로그래밍 언어로 C#을 시작하는 독자를 대상으로 한다. 특히 응용 프로그래머를 위한 C# 입문서로, C#을 사용하여 게임(유니티), 웹, 모바일, IoT 등을 개발할 때 필요한 C# 기초 문법을 익히고 기본기를 탄탄하게 다지는 것이 목적이다.");
	        book1.setPublisher("길벗");
	        book1.setCategory("IT전문서");
	        book1.setUnitsInStock(1000);
	        book1.setReleaseDate("2020/05/29");
	        Book book2 = new Book("ISBN1235", "Node.js 교과서", 36000);
	        book2.setAuthor("조현영");
	        book2.setDescription("이 책은 프런트부터 서버, 데이터베이스, 배포까지 아우르는 광범위한 내용을 다룬다. 군더더기 없는 직관적인 설명으로 기본 개념을 확실히 이해하고, 노드의 기능과 생태계를 사용해 보면서 실제로 동작하는 서버를 만들어보자. 예제와 코드는 최신 문법을 사용했고 실무에 참고하거나 당장 적용할 수 있다.");
	        book2.setPublisher("길벗");
	        book2.setCategory("IT전문서");
	        book2.setUnitsInStock(1000);
	        book2.setReleaseDate("2020/07/25");
	        Book book3 = new Book("ISBN1236", "어도비 XD CC 2020", 25000);
	        book3.setAuthor("김두한");
	        book3.setDescription("어도비 XD 프로그램을 통해 UI/UX 디자인을 배우고자 하는 예비 디자이너의 눈높이에 맞게 기본적인 도구를 활용한 아이콘 디자인과 웹&앱 페이지 디자인, UI 디자인, 앱 디자인에 애니메이션과 인터랙션을 적용한 프로토타이핑을 학습합니다.");
	        book3.setPublisher("길벗");
	        book3.setCategory("IT활용서");
	        book3.setUnitsInStock(1000);
	        book3.setReleaseDate("2019/05/29");

	        listOfBooks.add(book1);
	        listOfBooks.add(book2);
	        listOfBooks.add(book3);
	   }
	
	@Override
	public List<Book> getAllBookList() {
		System.out.println("BookRepo_getAll 진입");
		
		return listOfBooks;
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		System.out.println("BookRepo_getCate 진입");
		List<Book> booksByCategory = new ArrayList<Book>();
		for(int i=0; i< listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if(category.equalsIgnoreCase(book.getCategory()))
				booksByCategory.add(book);
		}
		
		return booksByCategory;
	}

	
	
	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		//4. service에서 넘어온 파라미터를 여기서 처리한다.
		System.out.println("Repo에서 파라미터 받음 : "+filter);
		
		//publisher와 category로 구분된 리스트를 만든다. set은 중복허용x, 
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		
		//Map의 모든 key를 꺼낸다.
		Set<String> booksByFilter = filter.keySet();
		
		//키 중에 publisher라는 문자열을 가지고 있다면 true
		if(booksByFilter.contains("publisher")) {
			for(int j=0; j<filter.get("publisher").size(); j++) {
				//publisher를 가진 value를 하나하나 꺼낸다.
				String publisherName = filter.get("publisher").get(j);
				for(int i=0; i<listOfBooks.size(); i++) {
					Book book = listOfBooks.get(i);
					//꺼낸 value와 현재 DB에 존재하는 DTO(Book) 중 get()으로 꺼낸 것과 같은 publisher라면
					if(publisherName.equalsIgnoreCase(book.getPublisher()))
						//publisher 리스트에 add()
						booksByPublisher.add(book);
				}
			}
		}
		System.out.println("repo_publisher : "+booksByPublisher);
		//키 중 category라는 문자열을 가지고 있다면 true
		if(booksByFilter.contains("category")) {
			for(int i=0; i<filter.get("category").size();i++) {
				
				String category = filter.get("category").get(i);
				List<Book> list = getBookListByCategory(category);
				booksByCategory.addAll(list);
			}
		}
		System.out.println("repo_category : "+booksByCategory);
		//retainAll() : 파라미터로 전달된 Collection 객체가 가진 요소와 비교하여 
		//  			중복된 값들만 남기고 나머지는 제거. 따라서 조건에 부합하는 DTO만 넘겨 출력.
		booksByCategory.retainAll(booksByPublisher);
		
		//model.addAttribute("bookList",booksByCategory);
		//5. 요청에 응답하는 DTO를 담았다면 return (service로)
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		//3. Service에서 호출한 Repo의 메서드로 진입하여 파라미터를 가지고 어떤 DTO를 찾는지 연산
		System.out.println("id_Repo 진입 : "+bookId);
		
		Book bookInfo = null;
		for(int i=0; i<listOfBooks.size(); i++) {
			// book을 생성하며 전체 책을 하나씩 꺼내 넣는다.
			Book book = listOfBooks.get(i);
			// book의 값이 null이 아니고, book의 id를 get했을 때 null이 아니고, 
			// listOfBooks에서 get()하여 꺼낸 book의 id와 현재 함수에 들어올 때 가지고온 파라미터와 같다면 !
			if(book != null && book.getBookId() != null &&book.getBookId().equals(bookId)) {
				// 해당 dto가 맞으니 service로 넘겨줄 수 있게 주소를 넣는다.
				bookInfo = book;
				break;
			}
		}
		System.out.println("id_Repo 결과 : "+bookInfo);
		
		// 4. dto를 찾았으니 service로 돌아간다.
		return bookInfo;
	}

	// MODEL에 도서를 등록하기 위한 절차
	//3. MODEL에 새로 등록할 DTO를 넘겨 등록 (현재 DB구현 X)
	//4. 함수 종료. 호출 위치로 이동
	@Override
	public void setNewBook(Book book) {
		System.out.println("setNewBook Repo IN : "+book.getBookId());
		listOfBooks.add(book);
	}
	
	
	

}
