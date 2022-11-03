package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberMineVO;
import lombok.extern.slf4j.Slf4j;

//매퍼xml (book_SQL.xml)을 실행시키는 DAO(Data Access Object) 클래스
//Repository 어노테이션 : 데이터에 접근하는 자바빈 객체로 스프링에 등록해줌
// 스프링아 ! 이거 데이터를 관리하는 특별한 클래스얌 ㅎㅅㅎ
@Slf4j
@Repository
public class MemberMineDao {
	// 1. DI (Dependency Injection) : 의존성 주입
	// new 키워드를 통해 직접 생성하지 않고 스프링이 미리 만들어 놓은 (서버 실행 시 미리 root-context.xml을 읽어서
	// 자바빈 객체로 인스턴스화 해놓음) sqlSessionTemplate 타입 객체를 BookDao 객체를 주입하여 사용함.
	// 2. IoC(Inversion of Control) : 제어의 역전

	@Autowired
	SqlSessionTemplate sqlSessionTemplate; // = new

	// ATTACH 테이블에 다중 INSERT
	public int insertAttach(List<AttachVO> attachVOList) {
		return this.sqlSessionTemplate.insert("memberMine.insertAttach", attachVOList);
	}

	// 멤버 목록보기
	public List<MemberMineVO> memberList(Map<String, String> map) {
		// select 결과를 목록으로 받음. selectList("namespace.id", 파라미터)
		return this.sqlSessionTemplate.selectList("memberMine.memberList", map);
	}
	
	// 멤버 추가
	public int memberInsert(MemberMineVO memberMineVO) {
		// select 결과를 목록으로 받음. selectList("namespace.id", 파라미터)
		return this.sqlSessionTemplate.insert("memberMine.memberInsert", memberMineVO);
	}
	
//	 MEMBER_MINE테이블의 전체 행 수 구함
	public int getTotal(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("memberMine.getTotal", map);
	}
	
//	아이디 검사
//	<select id="idCheck" parameterType="String" resultType="int">
	public int idCheck(String memId) {
		return this.sqlSessionTemplate.selectOne("memberMine.idCheck", memId);
	}
	
}
