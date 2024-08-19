package com.rubypaper.bookstore;

import java.util.List;

import com.rubypaper.review.ReviewVO;

public interface BookStoreService {
	void saveCSVToDatabase(String filePath);
	
	List<BookStoreVO> searchBookStores(BookStoreVO vo);
	
	int tc(BookStoreVO vo);
}
