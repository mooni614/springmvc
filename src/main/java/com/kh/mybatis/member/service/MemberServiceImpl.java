package com.kh.mybatis.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.mybatis.member.dao.MemberDAO;
import com.kh.mybatis.member.vo.Member;


@Service("memberService") // 서비스임을 알리고, bean을 통해서 자동으로 객체 생성 되는 것 
public class MemberServiceImpl implements MemberService{

	
	@Autowired
//	@Qualifier("memDAO") // 생략가능
	private MemberDAO dao;
	
	// 마이바티스 연결하기 위해서 sqlSession 객체를 생성(디비에 접근하는 것)
	// root-context.xml에서 정의한 bean
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate session;
	
	@Override
	public int joinMember(Member member) {
		
		return dao.insertMember(session, member);
	}

	@Override
	public Member searchMember(String id) {
		
		return dao.selectMemberById(session, id);
	}

	@Override
	public List<Member> getMemberList() {
		
		return dao.selectMemberAll(session);
	}

	@Override
	public int deleteMember(String id) {
		
		return dao.deleteMember(session, id);
	}

	
}
