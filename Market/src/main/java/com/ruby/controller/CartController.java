package com.ruby.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;
import com.rubypaper.cart.CartService;
import com.rubypaper.cart.CartVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/cart/")
@Controller
public class CartController {
	@Autowired
	CartService service;
	
	@Autowired
	BookService bservice;
	
	CartController(){
		System.out.println("CartController 생성자");
	}
	
	@PostMapping("insert")
	public String insert(CartVO vo) {
		System.out.println("===> insert ");
		service.insert(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("list")
	public void list(Model model,CartVO vo) {
		System.out.println("===> list ");
		model.addAttribute("li",service.select(vo));
	}
	
	@GetMapping("update")
	public String update(CartVO vo) {
		System.out.println("===> update ");
		vo.setAmount(vo.getAmount());
		vo.setCart_id(vo.getCart_id());
		System.out.println("===>vo: "+vo);
		service.update(vo);
		return "redirect:/cart/list";
	}
	
	@GetMapping("delete")
	public String delete(CartVO vo) {
		System.out.println("===> delete ");
		vo.setCart_id(vo.getCart_id());
		System.out.println("===>vo: "+vo);
		service.delete(vo);
		return "redirect:/cart/list";
	}
	
	@PostMapping("insertMultiple")
	@ResponseBody
	public void insertMultiple(@RequestBody List<CartVO> cartItems) {
		System.out.println("===> insertMultiple "+cartItems);
	    cartItems.forEach(item -> System.out.println("Book ID: " + item.getBook_idx() + ", Amount: " + item.getAmount()));
	    service.insertMultiple(cartItems);
	    
	}
	
	
}
