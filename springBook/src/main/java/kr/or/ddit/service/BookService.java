package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.BookVO;

// 서비스 interface : 비즈니스 로직
public interface BookService {
	
	public int insert(BookVO bookVO);
	
	//상품 목록
	public List<BookVO> list();

	//상품 상세
	public BookVO selectDetail(BookVO bookVO);

	
	
}


