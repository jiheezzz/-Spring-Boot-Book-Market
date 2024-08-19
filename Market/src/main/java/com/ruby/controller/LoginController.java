package com.ruby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubypaper.member.MemberVO;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/login/")
@Controller
public class LoginController {
	LoginController(){
		System.out.println("LoginController 생성자");
	}
	
	@GetMapping("form")
	public void form() {
		System.out.println("===> form");
	}
	

	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("===> logout");
		session.invalidate();
		return "/index";
	}
}
