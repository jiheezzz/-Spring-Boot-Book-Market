package com.rubypaper.book;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface BookService {
	void insert(BookVO vo);
	void update(BookVO vo);
	void update_edit(BookVO vo);
	void delete(BookVO vo);
	BookVO edit(BookVO vo);
	void updateBookAmount(@Param("bookIdx") String bookIdx, @Param("amount") int amount);
	List<BookVO> select(BookVO vo);
	List<BookVO> top3(BookVO vo);
	List<BookVO> newList(BookVO vo);
	
	void update_add(Long book_id);
	void update_remove(Long book_id);
	
	List<BookVO> bestselect(BookVO vo);
	
	List<BookVO> categories(BookVO vo);
	
	List<BookVO> findByBookTitleContaining(String title);
	
	List<BookVO> getBooksForUser(String userId);
}
