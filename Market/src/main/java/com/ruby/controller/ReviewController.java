package com.ruby.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.book.BookVO;
import com.rubypaper.review.ReviewService;
import com.rubypaper.review.ReviewVO;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/review/")
@Controller
public class ReviewController {
	@Autowired
	ReviewService service;
	
	ReviewController(){
		System.out.println("ReviewController 생성자");
	}
	
	@GetMapping("form")
	public void form(Model model,ReviewVO vo) {
		System.out.println("===> review form"+vo);
		model.addAttribute("li", vo);
	}
	
	@PostMapping("insert")
	public String insert(ReviewVO vo,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("===> review insert");
		
		service.insert(vo);
		return "redirect:/review/list?start=1&end=15&book_idx="+vo.getBook_idx();
	}
	
	@GetMapping("list")
	public void list(Model model,ReviewVO vo,BookVO bvo) {
		System.out.println("===> review list"+bvo.getBook_idx());
		vo.setBook_idx(bvo.getBook_idx());
		model.addAttribute("li", service.select(vo));
		
		int pageSize = 15;
		int pageListSize = 10;
		int startInt  = 0;
		
		if (vo.getStart() == 0) {
			startInt = 1 ;
		} else {
			startInt = vo.getStart();
		}	
		  
		  int tc  = service.tc(vo);
		  model.addAttribute("tc", tc);
		  int totalPage =(int)(Math.ceil(tc / (double) pageSize ));
		  int nowPage =   startInt  / pageSize   + 1  ;
		  int lastPage  = ( totalPage - 1 ) * pageSize + 1;
		  int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
		  int listEndPage = listStartPage + pageListSize - 1 ;
		  int  endPage = (totalPage - 1) * pageSize + 1 ; 
		  vo.setStart( startInt );
		  vo.setEnd(startInt + pageSize - 1);
		  model.addAttribute("ch1", vo.getCh1());
		  model.addAttribute("ch2", vo.getCh2());
		  model.addAttribute("pageSize", pageSize);
		  model.addAttribute("pageListSize", pageListSize);
		  model.addAttribute("start", startInt);
		  model.addAttribute("end", startInt +pageSize - 1); 
		  model.addAttribute("totalPage", totalPage);	
		  model.addAttribute("nowPage", nowPage);	
		  model.addAttribute("lastPage", lastPage);	
		  model.addAttribute("listStartPage", listStartPage);	
		  model.addAttribute("listEndPage", listEndPage);	 
		  model.addAttribute("endPage", endPage);	

	}
	
	@GetMapping("edit")
	public void edit(Model model,ReviewVO vo) {
		System.out.println("===> review edit"+vo);
		model.addAttribute("li", service.edit(vo));
	}
	
	@GetMapping("update")
	public String update(ReviewVO vo,HttpServletRequest request){
		System.out.println("===> review update");
		
			service.update(vo);
			return "redirect:/review/list?start=1&end=15&book_idx="+vo.getBook_idx();
	}
}