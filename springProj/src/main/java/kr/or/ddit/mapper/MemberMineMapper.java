package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberMineVO;

public interface MemberMineMapper {
	
	public List<MemberMineVO> memberList(Map<String, String> map);
	
	public int memberInsert(MemberMineVO memberMineVO);
	
//	 MEMBER_MINE테이블의 전체 행 수 구함
	public int getTotal(Map<String, String> map);
	
//	아이디 검사
	public int idCheck(String memId);
	
	//상세 보기
	public MemberMineVO detail(String memId);
}
