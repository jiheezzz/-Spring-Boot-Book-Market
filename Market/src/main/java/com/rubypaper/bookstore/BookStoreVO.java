package com.rubypaper.bookstore;

import lombok.Data;

@Data
public class BookStoreVO {
	private String id;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String latitude;
    private String longitude;
    private String keyword;
    private int start;
	private int end;
	
	private int pageSize;
	private int pageListSize;
	private int totalPage;
	private int nowPage;
	private int listStartPage;
	private int listEndPage;
}
