package com.rubypaper.login;

import org.apache.ibatis.annotations.Mapper;

import com.rubypaper.member.MemberVO;

@Mapper
public interface LoginDao {
	MemberVO login(MemberVO vo);
}
