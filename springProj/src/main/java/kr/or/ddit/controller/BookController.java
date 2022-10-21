package kr.or.ddit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

/*
 * Controller 어노테이션
 * 스프링 프레임워크에게 "프링아, 이 클래스는 웹 브라우저의 요청 (request)를 받아들이는 컨트롤러야!" 라고 알려주는 것
 * "일반 클래스 아니다??!?"
 * 스프링은 servlet-context.xml의 context:component-scan의 설정에 의해 이 클래스를 
 * 자바빈 객체로 등록(메모리에 바인딩)되어있는 클래스
 */
@Slf4j
@Controller
public class BookController {
	//도서관리프로그램
	//BookService 서비스를 호출하기 위해 의존성 주입
	@Autowired
	BookService bookService;
	
	//URI -> http://localhost/create
	// Request : client가 server에 URI를 요청
	// Mapping : create() 메소드 실행
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create() {
		/*
		 * Model : return할 데이터 (String, int, List, Map, VO..)를 담당
		 * View : 화면을 담당 (뷰(view : JSP)의 경로)
		 * VierResolver => prefix + jsp파일명 + suffix
		 */
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("book/create");
		//forwarding
		
		return mav;
	}
	
	//post로 요청한 것 받기
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelAndView createPost(ModelAndView mav, 
			@ModelAttribute BookVO bookVO) { //@ModelAttribute 생략 가능 (데이터를 저장하는 곳 Model!그래서 BookVO
		log.info("bookVO : " + bookVO.toString());
		
		int result = this.bookService.insert(bookVO);
		
		log.info("result : " + result);
		
		if(result<1) { //등록 실패
			// /create(get방식) URI를 재요청
			// 책 입력 화면으로 이동
			mav.setViewName("redirect:/create");
		}else { //등록 성공!
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}
		
		return mav;
	}
}
