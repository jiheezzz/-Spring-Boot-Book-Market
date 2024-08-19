package com.rubypaper.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.rubypaper.member.MemberVO;

public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;

	public SecurityUser(MemberVO vo ) {
		super(vo.getUsername(), 
			  "{noop}"+vo.getPassword(), //평문사용할 때 {noop}사용
			  AuthorityUtils.createAuthorityList(vo.getRole().toString()));
  	}	


}
