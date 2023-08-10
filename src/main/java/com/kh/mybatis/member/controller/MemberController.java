package com.kh.mybatis.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@RequestMapping("/member/index.do")
	public String index() {
		return "member/index";
		
	}
	
	
	//회원가입 : 실제 데이터를 저장하는 공간 데이터페이스, 같은 파일 안에 리스트로 저장할거냐 
	// mybatis 이용해서 데이터를 저장
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll(Model model, Member m) {
		
		System.out.println("가입정보"+m);
		int result = service.joinMember(m); // 데이터베이스로 가기 위한 서비스가 필요
		
		//정상실행되면 1이 옴 모델객체에 "msg" 변수명을 이용해서 "회원가입이 성공하였습니다."
		//result값이 0이면 저장실패, redirect를 이용해서 error.do로 갈것 
		
		if(result > 0) {
			
			model.addAttribute("msg", "회원 가입에 성공하였습니다.");
			return "member/index";
			
		}else {
			return "redirect:error.do";
			
		}
		
	}
	
	
	//회원목록 조회
	@RequestMapping("/member/memberList.do")
	public String memberList(Model model) {
		List<Member> list = service.getMemberList();
		
		model.addAttribute("list",list);
		return "member/memberList";
		
	}
	
	//단일검색
	@RequestMapping("/member/memberSearch.do")
	public String searchMember(Model model, String id) {
		
		Member member = service.searchMember(id);
			//  멤버객체가 없다면 null, 에러페이지 리다이렉트
		// 찾았으면 /member/memberView
		if(member != null) {
			
			model.addAttribute("member",member);
			return "member/memberView";
			
		}else {
			
			return "redirect:error.do";
		}
		
	
		
		
	}
	
	@RequestMapping("/member/memberDel.do")
	public String deleteMember(String id, Model model) {
		
		
		
		//  멤버객체가 없다면 null, 에러페이지 리다이렉트
		// 찾았으면 /member/memberView
		
		int result = service.deleteMember(id);
		
		if(result > 0) {
			
			model.addAttribute("msg","회원 탈퇴에 성공하였습니다.");
			return "member/index";
			
		}else {
			return "redirect:error.do";
			
		}
		
		
	}
	
	
	
	@RequestMapping("/member/error.do")
	public String errorPage(String msg) {
		
		return "common/error";
		
	}
	
}
