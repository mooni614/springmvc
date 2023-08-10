package com.kh.mybatis;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.mybatis.member.vo.Member;

/**
 * DI(Dependency Injection) 의존성주입의 개념
 * - 등장 배경: 서블릿과 같이 복잡한 상속으로 설계된 코드는 결합도가 높다
 * 				코드 유지보수의 어려움이 발생
 * 				강한 결합도를 갖는 경우 코드부 수정이 다른 코드에 영향을 미칠 수 있다.
 * 
 * 상속개념 완전 배제불가, 상속으로 설계된 이유는 로직부를 프레임워크에서 로드하기 위해 
 * 결합도를 낮추기 위한 IOC 개념을 활용
 * -> 직접적인 상속은 배제하고 간적접으로 interface 사용
 * 		생성(new 사용)을 배제하고 어노테이션 또는 xml 활용
 * 
 * -> DI의 개념은 사용자가 직접 객체 생성하지 않고 프레임 웤으ㅔ서 사용자가 만듣 갠체를 직접 생성하여 프로그램에 주입시킴
 * -> 프레임 워크에서 사용자가 만든 객체 생성을 했을 때 제한하고 의존성을 최대한 낮추는 방향으로 설계된다.
 * 장점: 느슨한 결합 구조로 코드의 간결성, 유지보수성 증가, 상속에 의한 복잡성을 감소
 * 단점: DI 사용을 위해 java외 어노테이션 포기와 xml 코드부가 증가할 수 있다.
 * 
 * 단점을 극복하고자.. xml을 걷어내기 위해 스프링 부트가 등장
 * 
 * DI를 활용하기 위해서 MVC 주요 레이어를 붙는 어노테이션 
 * @Controller : url처리가 가능한 controller에 붙는다.
 * @Service : 비지니스 로직처리를 위한 서비스 계층에 붙는다.
 * @Repository : db나 data처리가 가능한 계층 주로(DAO)에 붙는다
 * @Component : 계층이 아닌 일반 객체를 생성할 때 사용하는 어노테이션
 * 
 * 
 * DI를 통한 객체 생성시 활용 어노테이션
 * @Autowired : new 키워드 대신 객체를 생성할 때 사용한다.
 * @Value : 단순 값을 주입할 때 사용한다.
 * @Resource: 자원 연결시 사용, 주로 프로퍼티에 사용한다.
 * 
 * 
 * 
 * 
 * 
 */
@Controller
public class HomeController {
	

	//DI(Bean) 객체 생성하는 방법
	// 1. Bean이 단일 ID로 선언된 경우
	// @AutoWired만 연결하면 된다.
	
	
	
	// 2. bean이 다중 id로 여러개 선언된 경우 
	// UnsatisfiedDependencyException 
	// NoUniqueBeanDefinitionException
	//@Qualifier("bean(서블릿 컨텍스트)의 아이디값 넣기") 빈의 구분자를 같이 삽입하여 사용할 의존객체를 선택할 수 있도록 한다.
	// @Qualifier() 빈 조회 빈을 찾지 못할 경우 필드 또는 파라미터 이름으로 매칭을 시도
	// 그래도 못찾으면 UnsatisfiedDependencyException 예외 발생
	
	
	@Autowired
	@Qualifier("testMember")
	Member member;
	
	@Autowired
	@Qualifier("testMember2")
	Member member2;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		
		model.addAttribute("member",member);
		model.addAttribute("member2",member2);
		
		return "home";
	}
	
}
