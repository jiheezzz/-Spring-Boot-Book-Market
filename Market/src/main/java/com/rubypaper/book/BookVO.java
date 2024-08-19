package com.rubypaper.book;



import org.springframework.web.multipart.MultipartFile;

import lombok.Data;




@Data
public class BookVO {
	private String book_idx;
	private String book_name;
	private String book_photo;
	private String writer_name;
	private String publish_company;
	private String publish_date;
	private int book_price;
	private int new_amount;
	private String book_title;
	private String book_etc;
	private MultipartFile book_photo_in;
	private String category_id;
	private String name;
	private String order_idx;
	private int amount;
	
	private String ch2;
	
	private String is_selected;
}
