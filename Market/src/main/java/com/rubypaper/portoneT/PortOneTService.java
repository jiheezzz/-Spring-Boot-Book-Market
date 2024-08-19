package com.rubypaper.portoneT;

import java.util.List;

public interface PortOneTService {
	public String insert(PortOneTVO vo);
	List<PortOneTVO> select(PortOneTVO vo);
	PortOneTVO edit(PortOneTVO vo);
	List<PortOneTVO> selectAll(PortOneTVO vo);
	
	
	void orderinsert(OrderVO vo);
}
