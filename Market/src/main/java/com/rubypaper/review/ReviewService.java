package com.rubypaper.review;

import java.util.List;

public interface ReviewService {
	void insert(ReviewVO vo);
	List<ReviewVO> select(ReviewVO vo);
	void update(ReviewVO vo);
	void delete(ReviewVO vo);
	ReviewVO edit(ReviewVO vo);
	int tc(ReviewVO vo);
}
