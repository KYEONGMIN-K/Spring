package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springmvc.domain.Book;
import com.springmvc.domain.Cart;
import com.springmvc.domain.CartItem;
import com.springmvc.exception.BookIdException;
import com.springmvc.service.BookService;
import com.springmvc.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestCartId(HttpServletRequest req) {
		String sessionId = req.getSession(true).getId();
		return "redirect:/cart/" + sessionId;
	}
	
	@PostMapping
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		System.out.println("넌 어디서 들어오니");
		return cartService.create(cart);
	}
	
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
		System.out.println("장바구니 controller IN : "+ cartId);
		Cart cart = cartService.read(cartId);
				
		model.addAttribute("cart",cart);
		
		return "cart";
	}
	
	@PutMapping("/{cartId}")
	public @ResponseBody Cart read(@PathVariable(value="cartId") String cartId) {
		return cartService.read(cartId);
	}
	
	@PutMapping("/add/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest req) {
		System.out.println("addcartNewItem IN : "+bookId);
		String sessionId = req.getSession(true).getId();
		System.out.println("세션id : "+sessionId);
		Cart cart = cartService.read(sessionId);
		//System.out.println("cart 서비스로부터 받음 : "+cart.getCartId());
		
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		Book book = bookService.getBookById(bookId);
		
		if(book == null) {
			throw new IllegalArgumentException(new BookIdException(bookId));
		}
		System.out.println("addCartItem 전");
		cart.addCartItem(new CartItem(book));
		System.out.println("addCartItem 후");
		cartService.update(sessionId, cart);
	}
	
	@PutMapping("/remove/{bookId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart == null)
			cart = cartService.create(new Cart(sessionId));
		
		Book book = bookService.getBookById(bookId);
		
		if(book==null)
			throw new IllegalArgumentException(new BookIdException(bookId));
		
		cart.removeCartItem(new CartItem(book));
		cartService.update(sessionId, cart);
	}
	
	@DeleteMapping("/delete/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable(value="cartId") String cartId) {
		System.out.println("delete IN : "+ cartId);
	   cartService.delete(cartId);
	}

	
}
