package com.rubypaper.wishlist;

import java.util.List;

public interface WishListService {
	void insert(WishListVO vo);
    List<WishListVO> select(WishListVO vo);
    void remove(WishListVO vo);
    boolean isBookInWishlist(WishListVO vo);
}
