package com.ruby.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.rubypaper.member.MemberService;
import com.rubypaper.member.MemberVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/member/")
@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	MemberController(){
		System.out.println("MemberConroller 생성자");
	}
	@GetMapping("form")
	public void form() {
		System.out.println("===> member form");
	}
	
	@PostMapping("insert")
	public String insert(MemberVO vo) {
		System.out.println("===> member insert");
		service.insert(vo);
		return "redirect:/login/form";
	}
	
	@GetMapping("ck")
	public void ck(MemberVO vo,HttpServletResponse response) throws IOException {
		System.out.println("===> member ck");
		String m=vo.getUsername();
		int str ; 
		if (m==null) {
			str=0;  // 사용가능 id
		}else {
			str=1;  // 중복 id
		}		

		PrintWriter out = response.getWriter();
		out.println(str);		
		
	}
	
	@GetMapping("list")
	public void list(Model model,MemberVO vo) {
		System.out.println("===> member list");
		model.addAttribute("li", service.select(vo));
	}
	
	@GetMapping("edit")
	public void edit(Model model,MemberVO vo) {
		System.out.println("===> member edit");
		model.addAttribute("m", service.edit(vo));
	}
	
	@GetMapping("update")
	public String update(Model model,MemberVO vo) {
		System.out.println("===> member update"+vo);
		service.update(vo);
		return "redirect:/member/list";
	}
	
	@GetMapping("mypage")
	public void mypage(Model model,MemberVO vo,HttpSession session) {
		System.out.println("===> member mypage");
		String m = (String)session.getAttribute("username");
		vo.setUsername(m);
		model.addAttribute("li", service.selectUser(vo));
	}
	
	@GetMapping("userDelete")
    public ResponseEntity<String> deleteUser(HttpSession session,MemberVO vo) {
		String m = (String)session.getAttribute("username");
		vo.setUsername(m);
		System.out.println("del vo: "+vo);
        service.deleteUser(vo);
        session.invalidate();
        	
        	return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("userUpdate")
    public String updateUser(MemberVO vo,HttpSession session) {
    	System.out.println("===> member userUpdate");
    	String m = (String)session.getAttribute("username");
		vo.setUsername(m);
		System.out.println("update vo: "+vo);
		service.updateUser(vo);
		return "redirect:/member/mypage";
       
    }
    @GetMapping("userEdit")
	public void userEdit(MemberVO vo,HttpSession session,Model model) {
		System.out.println("===> member userEdit");
		String m = (String)session.getAttribute("username");
		vo.setUsername(m);
		System.out.println("edit vo: "+vo);
		model.addAttribute("li", service.selectUser(vo));
	}
	
}
