package com.rubypaper.portoneT;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortOneTDao {
	void insert(PortOneTVO vo);
	List<PortOneTVO> select(PortOneTVO vo);
	PortOneTVO edit(PortOneTVO vo);
	List<PortOneTVO> selectAll(PortOneTVO vo);
	
	void orderinsert(OrderVO vo);
}
