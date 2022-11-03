package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.BookVO;

// 매퍼xml(book_SQL.xml)을 실행시키는
@Repository
public class BookDao {
	// 1. DI(Dependency Injection) : 의존성 주입
	// new 키워드를 통해 직접 생성하지 않고
	// 스프링이 미리 만들어 놓은(서버 실행 시 미리 root-context.xml을 읽어서
	// 자바빈 객체로 인스턴스화 해놓음)
	// sqlSessionTemplate 타입 객체를 BookDao 객체에 주입하여 사용
	// 2. IoC(Inversion of Control) : 제어의 역전
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insert(BookVO bookVO) {
		// namespace.id 태그 실행
		// insert, update, delete의 결과로 0 또는 1이상(반영개수) 반환
		return this.sqlSessionTemplate.insert("book.insert", bookVO);
	}

	// 상품 목록
	public List<BookVO> list() {
		return this.sqlSessionTemplate.selectList("book.list");
	}

	// 상품 상세
	public BookVO selectDetail(BookVO bookVO) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", bookVO);
	}

	
}
