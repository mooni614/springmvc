package com.kh.mybatis.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mybatis.member.vo.Member;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insertMember(SqlSessionTemplate session, Member member) {
		
		return session.insert("memberMapper.insertMember", member); //괄호안에는 mapper.xml파일의 (namespace.아이디값)
	}

	@Override
	public List<Member> selectMemberAll(SqlSessionTemplate session) {
		
		return session.selectList("memberMapper.selectMemberAll");
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, String id) {
		
		
		// selectList() : 여러개의 객체를 리스트로 반환해주는 메서드
		// selectOne() : 하나의 객체를 반환해줌
		return session.selectOne("memberMapper.selectMemberById",id);
	}

	@Override
	public int deleteMember(SqlSessionTemplate session, String id) {
		
		return session.delete("memberMapper.deleteMember",id);
	}

}
