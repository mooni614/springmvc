package com.kh.mybatis.member.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/*
 * 
 * 마이바티스에서 컬럼의 옵션형태로 값이 들어가기 때문에 
 * 이러한 type을 지원하기 위해서 Helper
 * 취미->축구, 골프, 야구
 * 
 * DB에 insert할 경우 String[] -> String 전환
 * DB에서 java로 추출할 때 String -> String[] 전환하는 역할 필요 
 * 
 * TypeHandler 인터페이스 활용
 * 
 * 
 * */


public class MemberTypeHandler implements TypeHandler<String[]> {

	
	//java -> DB
	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		
		//문자열 join
		// parameter가 null일수 있음
		if(parameter != null) { // 파라미터값이 다 있으면 다 입력할 것
			ps.setString(i, String.join(",",parameter));
			
		}else {
			ps.setString(i,"");
			
		}
		
	}

	
	//DB -> java
	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName).split(","); // 배열타임으로 바뀜
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return rs.getString(columnIndex).split(",");
	}

	
	
	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
