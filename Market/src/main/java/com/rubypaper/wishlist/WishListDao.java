package com.rubypaper.wishlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.rubypaper.book.BookVO;
@Mapper
public interface WishListDao {
	void insert(WishListVO vo);
	
    List<WishListVO> select(WishListVO vo);
	
    void remove(WishListVO vo);
    
    boolean isBookInWishlist(WishListVO vo);
	boolean isBookInWishlist(String userId, String book_idx);
}
