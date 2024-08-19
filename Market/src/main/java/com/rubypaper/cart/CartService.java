package com.rubypaper.cart;

import java.util.List;

public interface CartService {
	void insert(CartVO vo);
	List<CartVO> select(CartVO vo);
	void update(CartVO vo);
	void delete(CartVO vo);
	void deleteAll(CartVO vo);
	
	void insertMultiple(List<CartVO> cartItems);
}
