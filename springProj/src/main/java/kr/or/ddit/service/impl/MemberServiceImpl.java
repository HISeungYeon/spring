package kr.or.ddit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dao.MemberDao;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	//DI(의존성 주입)
	@Inject
	MemberDao memberDao;
	
	@Inject
	FileUploadUtil fileUploadUtil;
	
	//Member테이블에 insert
	//메서드에 @Transactional 애너테이션을 부여
	/*
	 * 회원 정보를 저장하다가 실패하거나 주소 정보를 저장하다가 실패하거나
	 * 카드 정보를 저장하다가 실패하면 모두 저장이 되지 않고 rollback이 됨.
	 */
	@Transactional
	@Override
	public int memberInsert(MemberVO memberVO) {
		//MEMBER 테이블에 insert
		this.memberDao.memberInsert(memberVO);
		//ADDRESS 테이블에 insert
		this.memberDao.addressInsert(memberVO);
		//CARD 테이블에 insert
		List<CardVO> cardVOList = memberVO.getCardVOList();
		List<CardVO> cardVOList2 = new ArrayList<CardVO>(); 
		
		for(CardVO vo : cardVOList) {
			vo.setUserId(memberVO.getUserId());
			
			cardVOList2.add(vo);
		}
		
		return this.memberDao.insertCard(cardVOList2);
	}
	
}
