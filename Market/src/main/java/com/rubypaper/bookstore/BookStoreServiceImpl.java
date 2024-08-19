package com.rubypaper.bookstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.review.ReviewVO;

@Service
public class BookStoreServiceImpl implements BookStoreService{
	@Autowired
    private BookStoreDao dao;
	 

	 public void saveCSVToDatabase(String filePath) {
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            // 첫 번째 줄은 헤더이므로 건너뜁니다.
	            br.readLine();
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(",");
	                BookStoreVO bookstore = new BookStoreVO();
	                bookstore.setId(values[0]); 
	                bookstore.setName(values[4]); 
	                bookstore.setAddress1(values[10]); 
	                bookstore.setAddress2(values[11]);
	                bookstore.setAddress3(values[12]);
	                bookstore.setAddress4(values[14]);
	                bookstore.setLatitude(values[20]);
	                bookstore.setLongitude(values[21]);

	                dao.insertBookStore(bookstore);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public List<BookStoreVO> searchBookStores(BookStoreVO vo) {
		// TODO Auto-generated method stub
		return dao.searchBookStores(vo);
	}

	@Override
	public int tc(BookStoreVO vo) {
		// TODO Auto-generated method stub
		return dao.tc(vo);
	}
	}