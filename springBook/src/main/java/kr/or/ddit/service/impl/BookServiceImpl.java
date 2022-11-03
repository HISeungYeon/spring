package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CartVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
	// 객체를 스프링이 관리해줌.
	// db접글을 위해 dao 인스턴스를 주입받는다.
	@Autowired
	BookDao bookDao;

	@Override
	public int insert(BookVO BookVO) {
		return this.bookDao.insert(BookVO);
	}

	@Override
	public List<BookVO> list() {
		return this.bookDao.list();
	}

	@Override
	public BookVO selectDetail(BookVO BookVO) {
		return this.bookDao.selectDetail(BookVO);
	}

	
}
