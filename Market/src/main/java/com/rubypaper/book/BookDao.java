package com.rubypaper.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
@Mapper
public interface BookDao {
	void insert(BookVO vo);
	void update(BookVO vo);
	void update_add(Long book_id);
	void update_remove(Long book_id);
	void update_edit(BookVO vo);
	void delete(BookVO vo);
	BookVO edit(BookVO vo);
	void updateAmount(@Param("bookIdx") String bookIdx, @Param("amount") int amount);
	List<BookVO> select(BookVO vo);
	List<BookVO> top3(BookVO vo);
	List<BookVO> newList(BookVO vo);
	
	List<BookVO> bestselect(BookVO vo);
	List<BookVO> getBooksForUser(String userId);
	
	List<BookVO> categories(BookVO vo);
	
	 @SelectProvider(type = SqlProvider.class, method = "buildFindByBookTitleOrEtcContainingQuery")
	    List<BookVO> findByBookTitleOrEtcContaining(@Param("words") String[] words);

	    class SqlProvider {
	        public String buildFindByBookTitleOrEtcContainingQuery(@Param("words") String[] words) {
	            StringBuilder query = new StringBuilder("SELECT * FROM book WHERE ");
	            for (int i = 0; i < words.length; i++) {
	                if (i > 0) {
	                    query.append(" OR ");
	                }
	                query.append("(book_title LIKE '%").append(words[i]).append("%' ");
	                query.append("OR book_etc LIKE '%").append(words[i]).append("%')");
	            }
	            return query.toString();
	        }
	    }
	}