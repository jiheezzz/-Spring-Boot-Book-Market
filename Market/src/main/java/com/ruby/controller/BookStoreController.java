package com.ruby.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubypaper.bookstore.BookStoreService;
import com.rubypaper.bookstore.BookStoreVO;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/bookstores")
public class BookStoreController {
	@Autowired
    private BookStoreService bookStoreService;

	@GetMapping("/save-csv")
	public String saveCSVToDatabase() {
	    String filePath = "src/main/webapp/api_file/bookstore.csv";
	    bookStoreService.saveCSVToDatabase(filePath);
	    return "redirect:/api/bookstores/search";  
	}
    
    
    @GetMapping("/search")
    public String searchBookStores(BookStoreVO vo, Model model) {
        List<BookStoreVO> bookstores = bookStoreService.searchBookStores(vo);
        model.addAttribute("li", bookstores);
        int pageSize = 15;
		int pageListSize = 10;
		int startInt  = 0;
		if (vo.getStart() == 0) {
			startInt = 1 ;
		} else {
			startInt = vo.getStart();
		}	
		  int tc  = bookStoreService.tc(vo);
		  model.addAttribute("tc", tc);
		  int totalPage =(int)(Math.ceil(tc / (double) pageSize ));
		  int nowPage =   startInt  / pageSize   + 1  ;
		  int lastPage  = ( totalPage - 1 ) * pageSize + 1;
		  int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
		  int listEndPage = listStartPage + pageListSize - 1 ;	  
		  int  endPage = (totalPage - 1) * pageSize + 1 ; 
		  vo.setStart( startInt );
		  vo.setEnd(startInt + pageSize - 1);
		  model.addAttribute("keyword", vo.getKeyword());
		  model.addAttribute("address1", vo.getAddress1());
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
        return "/api/bookstores/list"; 
    }
    
}

