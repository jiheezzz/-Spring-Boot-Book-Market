package com.ruby.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;
import com.rubypaper.cart.CartService;
import com.rubypaper.cart.CartVO;
import com.rubypaper.portoneT.OrderVO;
import com.rubypaper.portoneT.PortOneTService;
import com.rubypaper.portoneT.PortOneTVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.core.type.TypeReference;


@RequestMapping("/PortOneT/")
@Controller
public class PortOneTController {
	@Autowired
	PortOneTService service;
	
	@Autowired
	CartService cservice;
	
	@Autowired
	BookService bservice;
	
	PortOneTVO vo1 = new PortOneTVO();
    
    PortOneTController(){
    	System.out.println("PortOneTController 생성자");
    }
    
    @GetMapping("generateMpaynum")
    public ResponseEntity<String> generateMpaynum() {
        String mpaynum = "gpay_" + System.currentTimeMillis();
        return ResponseEntity.ok(mpaynum);
    }
    
    
    @PostMapping("insertMPay")
    @ResponseBody
    public String insertMPay(@RequestBody PortOneTVO vo, Model model,CartVO cvo) {
    	System.out.println("vo: "+vo);

        String orderString = vo.getOrder();
        orderString = orderString.substring(2, orderString.length() - 2); // [[" 와 "]] 제거
        String[] items = orderString.split("\",\"");

        try {
            for (String item : items) {
                String[] parts = item.split(",");
                String bookIdx = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                // best상품목록을 위한 판매상세테이블에 insert
                OrderVO orderVO = new OrderVO();
                orderVO.setBook_idx(bookIdx);
                orderVO.setAmount(amount);

                service.orderinsert(orderVO);
            }
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("처리 중 오류 발생: " + e.getMessage());
        }

        String redirectUrl = "";
        try {
            // 서비스 인서트 메서드 호출 및 결과 확인
            redirectUrl = service.insert(vo);
            System.out.println("서비스에서 반환된 URL: " + redirectUrl);
            cservice.deleteAll(cvo);
            System.out.println("장바구니 리셋: ");
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
            redirectUrl = "/PortOneT/fail"; // 오류 발생 시 fail 페이지로 리다이렉트
        }

        return redirectUrl;
    }
    
    @PostMapping("insertMPay2")
    @ResponseBody
    public String insertMPay2(@RequestBody PortOneTVO vo, Model model,CartVO cvo,BookVO bvo) {
    	
    	 // order 문자열을 "]","[" 및 ","로 분리하여 각 아이템을 처리
        String orderString = vo.getOrder();
        orderString = orderString.substring(2, orderString.length() - 2); // [[" 와 "]] 제거
        String[] items = orderString.split("\",\"");

        System.out.println("1: " + Arrays.toString(items));

        try {
        	for (String item : items) {
                String[] parts = item.replaceAll("[\"{}]", "").split(",");
                String bookIdx = parts[0].trim().split(":")[1];
                int amount = Integer.parseInt(parts[1].trim().split(":")[1]);

                System.out.println("2: " + bookIdx + "/" + amount);

                // OrderVO 객체를 생성하여 설정 후 처리
                OrderVO orderVO = new OrderVO();
                orderVO.setBook_idx(bookIdx);
                orderVO.setAmount(amount);

                service.orderinsert(orderVO);
                
             
            }
        } catch (NumberFormatException e) {
            System.err.println("숫자 형식 오류: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("처리 중 오류 발생: " + e.getMessage());
        }

        String redirectUrl = "";
        try {
            // 서비스 인서트 메서드 호출 및 결과 확인
            redirectUrl = service.insert(vo);
            System.out.println("서비스에서 반환된 URL: " + redirectUrl);
            
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
            redirectUrl = "/PortOneT/fail"; // 오류 발생 시 fail 페이지로 리다이렉트
        }

        return redirectUrl;
    }
    
    @GetMapping("result")
    public String result(Model model,PortOneTVO vo,HttpServletRequest request,HttpSession session){
       System.out.println("===> result ");
       String a = (String )session.getAttribute("username");
       vo.setMembernum(a);
       model.addAttribute("li",service.select(vo)); //1)db값중 max값 불러오기 ->사용
       return "/PortOneT/result.html";
    }
    
    @GetMapping("fail")
    public String fail(Model model){
       System.out.println("===> fail");
       model.addAttribute("fail","fail");
       return "/PortOneT/fail.html";
    }
    
    @GetMapping("order")
    public void order(Model model,PortOneTVO vo,HttpSession session){
       System.out.println("===> order");
       String username = (String) session.getAttribute("username");
       vo.setMembernum(username);
       model.addAttribute("orderList",service.select(vo));
    }
    
    @GetMapping("orderAll")
    public void orderAll(Model model,PortOneTVO vo,HttpSession session){
       System.out.println("===> orderAll");
       String username = (String) session.getAttribute("username");
       vo.setMembernum(username);
       model.addAttribute("orderList",service.selectAll(vo));
    }
    
    
}

