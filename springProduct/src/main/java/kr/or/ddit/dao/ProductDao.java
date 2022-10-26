package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ProductVO;

// 매퍼xml(book_SQL.xml)을 실행시키는
// DAO(Data Access Object) 클래스
// Repository 어노테이션 : 데이터에 접근하는 자바빈 객체로 스프링에 등록해줌
@Repository
public class ProductDao {
	// 1. DI(Dependency Injection) : 의존성 주입
	// new 키워드를 통해 직접 생성하지 않고
	// 스프링이 미리 만들어 놓은(서버 실행 시 미리 root-context.xml을 읽어서
	// 자바빈 객체로 인스턴스화 해놓음)
	// sqlSessionTemplate 타입 객체를 BookDao 객체에 주입하여 사용
	// 2. IoC(Inversion of Control) : 제어의 역전
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insert(ProductVO productVO) {
		// namespace.id 태그 실행
		// insert, update, delete의 결과로 0 또는 1이상(반영개수) 반환
		return this.sqlSessionTemplate.insert("product.insert", productVO);
	}

	// 상품 목록
	public List<ProductVO> list() {
		return this.sqlSessionTemplate.selectList("product.list");
	}

	// 상품 상세
	public ProductVO selectDetail(ProductVO productVO) {
		return this.sqlSessionTemplate.selectOne("product.select_detail", productVO);
	}

	// 상품 수정하기
	// 매퍼 xml에서 insert/update/delete의 resultType은 생략
	public int update(ProductVO productVO) {
		return this.sqlSessionTemplate.update("product.update", productVO);
	}

	// 상품 삭제하기
	public int delete(String productId) {
		return this.sqlSessionTemplate.delete("product.delete", productId);
	}
}
