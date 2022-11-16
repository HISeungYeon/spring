package kr.or.ddit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.MemberMineDao;
import kr.or.ddit.mapper.MemberMineMapper;
import kr.or.ddit.service.MemberMineService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.MemberMineVO;

// 스프링아 이 클래스는 서비스 클래스야 ~! 
@Service
public class MemberMineImpl implements MemberMineService {

	//DI : 의존성 주입 
	// 데이터베이스 접근을 위해 BookDao 인스턴스를 주입받쟈
	@Autowired
	MemberMineDao memberMineDao;
	
	@Autowired
	MemberMineMapper memMapper;
	
	@Autowired
	FileUploadUtil fileUploadUtil;

	@Override
	public List<MemberMineVO> memberList(Map<String, String> map) {
		return this.memMapper.memberList(map);
	}
	
	// 멤버 추가
	@Override
	public int memberInsert(MemberMineVO memberMineVO) {
		
		int result = this.memMapper.memberInsert(memberMineVO);
		
		if(result > 0) {
			fileUploadUtil.fileUploadAction(memberMineVO.getMemImage(), 
					memberMineVO.getMemId());
		}
		
		return result;
	}
	
	@Override
	public int getTotal(Map<String, String> map) {
		return this.memMapper.getTotal(map);
	}
	
//	아이디 검사
	@Override
	public int idCheck(String memId) {
		return this.memMapper.idCheck(memId);
	}
	
	//상세 보기
	@Override
	public MemberMineVO detail(String memId){
		return this.memMapper.detail(memId);
	}
}
