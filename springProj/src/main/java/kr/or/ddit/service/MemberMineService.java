package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberMineVO;

//서비스 interface : 비즈니스 로직 
public interface MemberMineService {
	//메소드 시그니처 

	//ATTACH 테이블에 다중 INSERT
	public int insertAttach(List<AttachVO> attachVOList);
	
	//멤버 목록보기
	public List<MemberMineVO> memberList(Map<String, String> map);

	public int memberInsert(MemberMineVO memberMineVO);

	//전체 행 수 구하기
	public int getTotal(Map<String, String> map);

	public int idCheck(String memId);
	
	
	
	
}
