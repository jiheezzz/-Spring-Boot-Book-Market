package com.rubypaper.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.member.MemberVO;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginDao dao;
	
	LoginServiceImpl(){
		System.out.println("LoginServiceImpl 생성자");
	}
	
	
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}
}
