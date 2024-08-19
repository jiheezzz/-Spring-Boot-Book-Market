package com.ruby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubypaper.login.LoginService;
import com.rubypaper.member.MemberVO;
import com.rubypaper.security.SecurityUser;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/security/")
@Controller
public class SecurityController {
	@Autowired
	LoginService service;
	
	SecurityController(){
		System.out.println("securityController 생성자");
	}
	
	@GetMapping("accessDenied")
	public void accessDenied() {
		System.out.println("===> accessDenied");
	}
	

	@GetMapping("fail")
	public void fail() {
		System.out.println("===> fail");
	}
	

	@GetMapping("loginSuccess")
	public void loginSuccess(@AuthenticationPrincipal User user, Model model,HttpSession session) {
		System.out.println("===> loginSuccess ");
		session.setAttribute("username", user.getUsername());
		String username = (String) session.getAttribute("username");
	    model.addAttribute("username", username);
		
	}
}
