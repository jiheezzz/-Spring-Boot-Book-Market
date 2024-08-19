package com.rubypaper.review;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewVO {
	private String idx;
	private String name;
	private String memo;
	private String title;
	private String age;
	private String regdate;
	private String book_name;
	private int view_count;
	private String book_idx;
	
	private String ch1;
	private String ch2;
	
	private int start;
	private int end;
	
	private int pageSize;
	private int pageListSize;
	private int totalPage;
	private int nowPage;
	private int listStartPage;
	private int listEndPage;

}
