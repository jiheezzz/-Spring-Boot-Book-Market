package com.rubypaper.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDao dao;
	
	
	ReviewServiceImpl(){
		System.out.println("ReviewServiceImpl 생성자");
	}


	@Override
	public void insert(ReviewVO vo) {
		dao.insert(vo);
	}


	@Override
	public List<ReviewVO> select(ReviewVO vo) {
		return dao.select(vo);
	}


	@Override
	public void update(ReviewVO vo) {
		dao.update(vo);
	}


	@Override
	public void delete(ReviewVO vo) {
		dao.delete(vo);
	}


	@Override
	public ReviewVO edit(ReviewVO vo) {
		return dao.edit(vo);
	}


	@Override
	public int tc(ReviewVO vo) {
		return dao.tc(vo);
	}



	
	
}
