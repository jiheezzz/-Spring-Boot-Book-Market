package com.ruby.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;

import jakarta.servlet.http.HttpServletRequest;
@RequestMapping("/book/")
@Controller
public class BookController {
	@Autowired
	BookService service;
	
	@Autowired
	HttpServletRequest request;
	
	BookController(){
		System.out.println("BookController 생성자");
	}
	
	@GetMapping("form")
	public void form() {
		System.out.println("===> book from");
	}
	
	@PostMapping("insert")
	public String insert(BookVO vo) throws IllegalStateException, IOException {
		System.out.println("===> book insert");

		String path = request.getSession().getServletContext().getRealPath("/img/");
		System.out.println("===> "+path);
		MultipartFile uploadFile = vo.getBook_photo_in(); 
		String fileName = uploadFile.getOriginalFilename();
			if(!fileName.equals("")) {
				//파일명 난수처리
			    Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
				String k = sdf.format(now);
				int ran=(int)(Math.random()*1000)+1 ;
				
				int lastIndex = fileName.lastIndexOf('.'); //.으로구분하여
			    String extension = fileName.substring(lastIndex); //확장자가져옴
			    System.out.println("Extension: " + extension);
			    String img = k + ran + extension;

			    uploadFile.transferTo(new File(path + img));
			    vo.setBook_photo(img);
			}else {
				vo.setBook_photo("space.PNG");	
			}
		service.insert(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("list")
	public void list(Model model,BookVO vo, @RequestParam(value = "ch2", required = false) String ch2) {
		System.out.println("===> book list"+ch2);
		
		vo.setCh2(ch2);
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    

	    List<BookVO> books = service.select(vo);
	    

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
	}
	
	@GetMapping("newList")
	public void newList(Model model,BookVO vo) {
		System.out.println("===> book newList");

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    List<BookVO> books = service.newList(vo);
	    

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
	}
	
	
	@GetMapping("edit")
	public void edit(Model model,BookVO vo) {
		System.out.println("===> book edit");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    BookVO book = service.edit(vo);

	    if (book != null) {
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

	    model.addAttribute("m", book);
	}
	
	@GetMapping("bestList")
	public void bestList(Model model,BookVO vo) {
		System.out.println("===> book bestList");

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    List<BookVO> books = service.bestselect(vo);

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
	}
	
	@GetMapping("manager_list")
	public void manager_list(Model model,BookVO vo) {
		System.out.println("===> book manager_list");

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    List<BookVO> books = service.select(null);

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
	}
	
	@GetMapping("delete")
	public String delete(BookVO vo) {
		System.out.println("===> delete"+vo);
		BookVO m = service.edit(vo);
		String path = request.getSession().getServletContext().getRealPath("/img/");
		String img = m.getBook_photo();
		File f = new File(path + img);
		if(f.exists()) {
			if(!img.equals("space.PNG")) {
				f.delete();
			}
		}
		service.delete(vo);
		return "/book/manager_list";
		
	}
	
	@GetMapping("manager_edit")
	public void manager_edit(Model model,BookVO vo) {
		System.out.println("===> book manager_edit");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    BookVO book = service.edit(vo);

	    if (book != null) {
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

	    model.addAttribute("m", book);
	}
	
	@PostMapping("update")
	public String update(BookVO vo) {
		System.out.println("===> book update");
		BookVO m = service.edit(vo);
		String path = request.getSession().getServletContext().getRealPath("/img/");
		System.out.println(path);
		String img = m.getBook_photo();
		MultipartFile uploadFile = vo.getBook_photo_in(); 
		String fileName = uploadFile.getOriginalFilename();
		System.out.println("===>img:"+img);
		System.out.println("===>fileName:"+fileName);
		if(!img.equals("space.PNG")) {
			if(!fileName.equals("")) {
				File f = new File(path+img);
				f.delete(); //삭제
			}
	    }
	    
		if(fileName.equals("")) {
			service.update_edit(vo);		
		}else {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
			String k = sdf.format(now);
			int ran=(int)(Math.random()*1000)+1 ;
			
			
			int lastIndex = fileName.lastIndexOf('.'); //.으로구분하여
		    String extension = fileName.substring(lastIndex); //확장자가져옴
		    System.out.println("Extension: " + extension);
		    img = k + ran + extension;
	
		    try {
				uploadFile.transferTo(new File(path + img));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    vo.setBook_photo(img);
			service.update(vo);
		}
		return "redirect:/book/manager_list";
	
	}
	
	@GetMapping("categories")
	public void categories(Model model,BookVO vo) {
		System.out.println("===> book categories");

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    List<BookVO> books = service.categories(vo);

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
	}
}
