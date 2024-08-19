package com.rubypaper.member;

import java.util.List;

public interface MemberService {
	void insert(MemberVO vo);
	List<MemberVO> select(MemberVO vo);
	
	MemberVO edit(MemberVO vo);
	void update(MemberVO vo);
	
	List<MemberVO> selectUser(MemberVO vo);
	void deleteUser(MemberVO vo);
    void updateUser(MemberVO vo);

}
