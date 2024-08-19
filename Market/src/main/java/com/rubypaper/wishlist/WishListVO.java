package com.rubypaper.wishlist;

import lombok.Data;

@Data
public class WishListVO {
	private Long id; 
    private String user_id; 
    private Long book_id; 
    private String createdAt;
    private String book_name;
    private String writer_name;
    private String publish_company;
    private String publish_date;
    private Integer book_price;
    private String book_photo;
    private Long book_idx; 
    
    private Long ck_id;
    private Boolean is_selected; // 항목이 선택되었는지 여부를 나타내는 플래그
}
