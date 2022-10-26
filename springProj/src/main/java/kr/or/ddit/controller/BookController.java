package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//책 상세보기
	//요청된URI 주소: /detail?bookId=1
	//URL : http://localhost/detail
	//요청(http) 파라미터, 쿼리 스트링: book_id=1
	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @ModelAttribute BookVO bookVO) { //ModelAttribute 알아서 가져올게! 생략 O
		log.info("detail에 왔댜");
		
		log.info("bookVO : " + bookVO.toString());
		
		//select 결과 1행을 bookVO에 담을 것이닷
		BookVO data = this.bookService.selectDetail(bookVO);
		
		//해석/컴파일하여 html을 응답.
		//데이터(BookVO) 1행을 함께 응답.
		//but, redirect는 데이터를 응답해주지 못함
		mav.setViewName("book/detail");
		mav.addObject("data", data);
		mav.addObject("bookId", data.getBookId());
		
		return mav;
	}
	
	//요청 URI => http://localhost/list
	//골뱅이 RequesetParam(value="파라미터name(keyword)", required=false(?keyword=일 때 오류방지))
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav, 
			@RequestParam(value="keyword", required=false) String keyword) {
		
		List<BookVO> list = this.bookService.list(keyword);
		
		for(BookVO vo :list) { 
			log.info("vo : " + vo.toString());
		}
		
		//forwarding
		mav.setViewName("book/list");
		//select 결과 목록을 데이터로 넣어줌
		mav.addObject("data", list);
		
		return mav;
	}
	
	//책 수정하기 폼
	// 요청URL => /update?bookId=1
	// 요청URI => /update
	// 요청 파라미터 => bookId=1
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(BookVO bookVO, ModelAndView mav) {
		//책 수정 화면 = 책 상세 화면 + 책 상세 화면
		// 책 입력 화면 형식을 그대로 따라가고, 빈 칸을 데이터로 채워주면 됨.
		//select 결과 1행을 bookVO에 담을 것이닷
		
		//책 상세 데이터
		BookVO data = this.bookService.selectDetail(bookVO);
		mav.addObject("data", data);
		
		//view : jsp의 경로
		//servlet - context.xml에서 설정한대로..
		// /WEB-INF/views/+ ... +.jsp
		
		//forwarding
		mav.setViewName("book/update");
		
		return mav;
		
	}
	
	//책 변경
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute BookVO bookVO, 
			ModelAndView mav) {
		log.info("updatePost=>bookVO : " + bookVO.toString());
		
		int result = this.bookService.update(bookVO);
		
		if(result>0) { //업데이트 성공 -> 책 상세페이지(detail.jsp)로 이동
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}else { //업데이트 실패 => 업데이트 뷰(update.jsp)로 페이지 이동
			mav.setViewName("redirect:/update?bookId"+bookVO.getBookId());
		}
		
		return mav;
	}
	
	//스프링에선 요청 파라미터를 매개변수로 받을 수 있음.
	//매개변수 타입이 int 타입으로 자동 형변환 됨.
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(ModelAndView mav, int bookId) {
		log.info("bookId : " + bookId);
		
		//해당 글 삭제
		int result = this.bookService.delete(bookId);
		
		if(result>0) {
			mav.setViewName("redirect:/list");
		}else {
			mav.setViewName("redirect:/detail?bookId="+bookId);
		}
		
		//redirect => 재요청 -> 88번째줄 메소드를 다시 실행함
		
		return mav;
	}
	
}
