package com.rubypaper.bookstore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.rubypaper.review.ReviewVO;
@Mapper
public interface BookStoreDao {
	void insertBookStore(BookStoreVO bookstore);
	
	List<BookStoreVO> searchBookStores(BookStoreVO vo);
	
	int tc(BookStoreVO vo);
}
