package com.rubypaper.book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.wishlist.WishListDao;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookDao dao;
	
	@Autowired
    WishListDao wdao;
	

	BookServiceImpl(){
		System.out.println("BookServiceImpl 생성자");
	}

	@Override
	public void insert(BookVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(BookVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(BookVO vo) {
		dao.delete(vo);
	}

	@Override
	public BookVO edit(BookVO vo) {
		return dao.edit(vo);
	}

	@Override
	public List<BookVO> select(BookVO vo) {
		System.out.println("imp: "+vo);
		return dao.select(vo);
	}

	
	
	@Override
    public List<BookVO> findByBookTitleContaining(String title) {
        // 단어 단위로 분할
        String[] bookTitleWords = title.split(" ");
        int titleCount = bookTitleWords.length;

        // 디버깅용 출력
        System.out.println("Title count: " + titleCount);
        for (int i = 0; i < titleCount; i++) {
            System.out.println("Word " + i + ": " + bookTitleWords[i]);
        }

        //  쿼리 실행
        return dao.findByBookTitleOrEtcContaining(bookTitleWords);
    }

	@Override
	public List<BookVO> bestselect(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.bestselect(vo);
	}

	@Override
	public void update_edit(BookVO vo) {
		// TODO Auto-generated method stub
		dao.update_edit(vo);
	}

	@Override
	public List<BookVO> categories(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.categories(vo);
	}

	@Override
	public List<BookVO> top3(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.top3(vo);
	}

	@Override
	public List<BookVO> newList(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.newList(vo);
	}

	@Override
	public List<BookVO> getBooksForUser(String userId) {
		 // 모든 책 목록 가져오기
        List<BookVO> books = dao.select(null);
        
        // 각 책에 대해 사용자가 찜한 상태를 설정
        for (BookVO book : books) {
            boolean isInWishlist = wdao.isBookInWishlist(userId, book.getBook_idx());
            book.setIs_selected(isInWishlist ? "Y" : "N");
        }
        
        return books; // 사용자에게 보여줄 책 목록 반환
    }

	@Override
	public void update_add(Long book_id) {
		// TODO Auto-generated method stub
		dao.update_add(book_id);
	}

	@Override
	public void update_remove(Long book_id) {
		// TODO Auto-generated method stub
		dao.update_remove(book_id);
	}

	@Override
	public void updateBookAmount(String bookIdx, int amount) {
		// TODO Auto-generated method stub
		dao.updateAmount(bookIdx, amount);
	}

	
}