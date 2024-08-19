package com.ruby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;
import com.rubypaper.wishlist.WishListService;
import com.rubypaper.wishlist.WishListVO;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/wishlist/")
@Controller
public class WishListController {
	@Autowired
	WishListService service;
	
	@Autowired
	BookService bservice;
	
	WishListController(){
		System.out.println("WishListController 생성자");
	}
	
	
	@PostMapping("toggle")
	@ResponseBody
	public String toggleWishlist(@RequestBody WishListVO vo, HttpSession session) {
	    System.out.println("===> wishlist toggle : " + vo);
	    String user_id=vo.getUser_id();
	    Long book_id=vo.getBook_id();
	    String username = (String) session.getAttribute("username");
	    vo.setUser_id(username);

	    // 현재 선택된 상태를 가져옴
	    boolean isSelected = service.isBookInWishlist(vo);
	    System.out.println("Is Selected: " + isSelected);
	    String URL;
	    if (isSelected) {
            // 선택된 상태이면 삭제
            System.out.println("Removed from wishlist"+user_id);
            URL="/wishlist/remove?user_id="+user_id+"&book_id="+book_id;
            return URL;
        } else {
            // 선택되지 않은 상태이면 추가
            System.out.println("Added to wishlist");
            URL="/wishlist/insert?user_id="+user_id+"&book_id="+book_id;
            return URL;
        }
	}
	
	@GetMapping("insert")
    public String insert(@RequestParam(value = "book_id") Long book_id,WishListVO vo, HttpSession session) {
        System.out.println("===> wishlist insert "+book_id);
        String username = (String) session.getAttribute("username");
        vo.setUser_id(username);
        vo.setBook_id(book_id);
        // Wishlist에 추가
        service.insert(vo);
        bservice.update_add(book_id);
        return "redirect:/wishlist/select";
        
    }

	@GetMapping("remove")
    public String remove(@RequestParam(value = "book_id") Long book_id,WishListVO vo, HttpSession session) {
        System.out.println("===> wishlist remove : " + vo);
        String username = (String) session.getAttribute("username");
        vo.setUser_id(username);
        vo.setBook_id(book_id);
        // Wishlist에서 삭제
        service.remove(vo);
        bservice.update_remove(book_id);
        return "redirect:/wishlist/select";
    }


    @GetMapping("select")
    public String select(WishListVO vo, Model model, HttpSession session) {
        System.out.println("===> wishlist select");
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);
        vo.setUser_id(username);
        model.addAttribute("li", service.select(vo));
        System.out.println(service.select(vo));
        return "wishlist/select";
    }
}