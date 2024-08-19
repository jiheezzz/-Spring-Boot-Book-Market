package com.rubypaper.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao dao;
	
	CartServiceImpl(){
		System.out.println("CartServiceImpl 생성자");
	}

	public void insert(CartVO vo) {
		dao.insert(vo);
	}
	
	public List<CartVO> select(CartVO vo) {
		// TODO Auto-generated method stub
		return dao.select(vo);
	}

	public void update(CartVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	public void delete(CartVO vo) {
		// TODO Auto-generated method stub
		dao.delete(vo);
	}

	@Override
	public void deleteAll(CartVO vo) {
		// TODO Auto-generated method stub
		dao.deleteAll(vo);
	}

	@Override
	public void insertMultiple(List<CartVO> cartItems) {
		// TODO Auto-generated method stub
		dao.insertMultiple(cartItems);
	}

	

}
