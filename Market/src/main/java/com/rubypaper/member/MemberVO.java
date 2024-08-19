package com.rubypaper.member;

import lombok.Data;

@Data
public class MemberVO {
	private String username;
	private String password;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String name;
	private String phone;
	private String gender;
	private String addr1;
	private String addr2;
	private String addr3;
	private String role;
	private String etc;
	private String regdate;
}
