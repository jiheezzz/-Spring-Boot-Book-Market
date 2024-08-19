package com.rubypaper.cart;

import lombok.Data;

@Data
public class CartVO {
	   private String cart_id;
	   private int amount;
	   private String username;
	   private String book_idx;
	   private String book_name;
	   private String book_photo;
	   private int book_price;
	   
	   private String addr1; 
	   private String addr2;
	   private String addr3; 
	   private String name; 
	   private String phone;
	   private String receive_addr1;
	   private String receive_addr2;
	   private String receive_addr3;
	   private String receive_phone;
	   private String receive_name;
}
