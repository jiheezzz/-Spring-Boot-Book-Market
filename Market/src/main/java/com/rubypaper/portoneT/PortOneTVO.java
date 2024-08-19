package com.rubypaper.portoneT;

import lombok.Data;



@Data
public class PortOneTVO {	
	private String idx;
	private int dataPrice;
	private String dataName;
	private String dataGoodsnum;
	private String today;
	
	private String gname;  // 헬스장 이름
	private String gymnum;   // 헬스장 고유번호 
	   	   
	private String mmail;  // 회원메일
	private String mname;  // 회원이름
	private String mphone;  // 회원 전화번호 
	private String membernum; // username 
	   	   
	private String  mpaynum     ;   // 결제번호
	
	private String   mpaymethod  ;    // 결제수단
	private String   mpayproduct ;    // 헬스장 이름 + 상품이름
	private String   mpayprice  ;    // 결제금액
	private String   mpaydate  ;      // 결제일 
	private String   mpaygym    ;     // 헬스장 이름
	private int   mpayperiod ;    // 상품이용기간
	private String   mpaytime   ;    // "" :  (1) -- ?
	private String   trainername  ;  // "" : 트레이너 이름 - 입력 안함.
	private String   ggoodsnum  ;    //상품번호
	private String   tgoodsint  ;     //     tgoodsint (2) -- ?
	private String	cnt   ;
	private String order;
	
	private String name;
	private String phone;
	private String addr1;
	private String addr2;
	private String addr3;

}
