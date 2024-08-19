package com.ruby.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;

@Controller
public class StartController {
	
	@Autowired
	BookService service;
	
	StartController(){
		System.out.println("StartController 생성자");
	}
	
	@GetMapping("/index")
	public String index(Model model,BookVO vo) {
		System.out.println("===> index");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    List<BookVO> books = service.top3(vo);

	    for (BookVO book : books) {
	        String publishDate = book.getPublish_date();
	        if (publishDate != null && !publishDate.isEmpty()) {
	            try {
	                // Parsing date-time string to LocalDateTime and then converting to LocalDate
	                LocalDateTime dateTime = LocalDateTime.parse(publishDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	                LocalDate date = dateTime.toLocalDate();
	                book.setPublish_date(date.format(formatter));
	            } catch (Exception e) {
	                System.err.println("Date parsing/formatting error for book ID " + book.getBook_idx() + ": " + e.getMessage());
	                book.setPublish_date("Invalid Date");
	            }
	        } else {
	            book.setPublish_date("No Date Available");
	        }
	    }

	    model.addAttribute("li", books);
		return "index.html";
	}
	
	@GetMapping("/chatBot/chatBot")
    public void chatBot() {
        System.out.println("===> chatBot");
    }
	

}
