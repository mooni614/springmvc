package com.kh.mybatis.member.vo;



import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // 매개변수 모두 받는 생성자
@NoArgsConstructor // 기본생성자
@Getter
@Setter
@Data
public class Member {

	
	private String 	 id; 
	private String 	 password;  
	private String 	 name;  
	private int 	 age; 
	private String 	 gender;
	private String 	 phone; 
	private String 	 address; 
	private String 	 email;  
	private String[] hobby; // 축구,야구,영화보기
	private Date 	 enrollDate;
	
}

