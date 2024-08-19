package com.rubypaper.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListServiceImpl implements WishListService{
	@Autowired
	WishListDao dao;
	
	WishListServiceImpl(){
		System.out.println("WishListServiceImpl 생성자");
	}

	@Override
	public void insert(WishListVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	 public void remove(WishListVO vo) {
	        dao.remove(vo);
	    }

    public boolean isBookInWishlist(WishListVO vo) {
        return dao.isBookInWishlist(vo);
    }

    public List<WishListVO> select(WishListVO vo) {
        return dao.select(vo);
    }
}
	
