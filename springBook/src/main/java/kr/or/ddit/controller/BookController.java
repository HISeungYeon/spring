package kr.or.ddit.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.CartDetVO;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class BookController {
	// 상품관리 프로그램 spring 바꾸기
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public ModelAndView addBook(ModelAndView mav) {
		/*
			ModelAndView
			1) Model : return할 데이터(String, int, List, Map, VO.. 등)를 담당
			2) View : 화면을 담당(뷰(view : JSP)의 경로)
				ViewResolver => prefix + jsp파일명 + suffix
		 */
		
		mav.setViewName("book/addBook");
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public ModelAndView addBookPost(ModelAndView mav, @ModelAttribute BookVO bookVO) {
		
		int result = this.bookService.insert(bookVO);
		
		if(result < 1) {
			// /create(get방식) URI를 재요청
			// 책 입력 화면으로 이동
			mav.setViewName("redirect:/addBook");
		} else {
			mav.setViewName("redirect:/book?bookId="+bookVO.getBookId());
		}
		
		return mav;
	}
	
	//상품 목록
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public ModelAndView books(ModelAndView mav) {
		
		List<BookVO> data = this.bookService.list();
		mav.addObject("listOfBooks", data);
		//view
		mav.setViewName("book/books");
		
		return mav;
	}
	
	//책 상세보기
		//요청된URI 주소: /detail?bookId=1
		//URL : http://localhost/detail
		//요청(http) 파라미터, 쿼리 스트링: book_id=1
		@RequestMapping(value = "/book", method=RequestMethod.GET)
		public ModelAndView detail(ModelAndView mav, @ModelAttribute BookVO bookVO) { //ModelAttribute 알아서 가져올게! 생략 O
			
			//select 결과 1행을 bookVO에 담을 것이닷
			BookVO data = this.bookService.selectDetail(bookVO);
			
			//해석/컴파일하여 html을 응답.
			//데이터(BookVO) 1행을 함께 응답.
			//but, redirect는 데이터를 응답해주지 못함
			mav.setViewName("book/book");
			mav.addObject("book", data);
			mav.addObject("bookId", data.getBookId());
			
			return mav;
		}
		
		
}
