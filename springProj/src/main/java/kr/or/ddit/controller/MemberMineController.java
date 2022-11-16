package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.service.MemberMineService;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.ArticlePage;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberMineVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberMineController {
	
//	MemberService memberService;
	//DI(의존성 주입), IoC(제어의 역전)
	@Inject
	MemberMineService memberMineService;
	
	//요청 URI : /board/list?currentPage=5
	//요청 파라미터 : currentPage = 2 
	@GetMapping("/board/list")
	public ModelAndView memberList(ModelAndView mav, @RequestParam(defaultValue="1", required = false) int currentPage
			,@RequestParam Map<String, String> map) {
		log.info("memberList에 왔닫ㅇ");
		
		log.info("currentPage : " + currentPage);
		// /board/list 이렇게 요청 되었을 경우 처리
		String cPage = map.get("currentPage");
		String show = map.get("show");
		String keyword = map.get("keyword");
		if(cPage==null) {
			map.put("currentPage","1");
		}
		if(show==null) {
			map.put("show", "10");
		}
		if(keyword==null) {
			map.put("keyword", "");
		}
		
		//map : {currentPage=8, show=10}
		log.info("map : " + map);
		
		List<MemberMineVO> list = this.memberMineService.memberList(map);
		
		//MEMBER_MINE테이블의 전체 행 수 구함.
		int total = this.memberMineService.getTotal(map);
		
		//한 화면에 보여질 행 수
		int size = Integer.parseInt(map.get("show"));
		
		for(MemberMineVO vo : list) { 
			log.info("vo : " + vo.toString());
		}
		
		mav.setViewName("board/list");
		
		//(전체 글 수, 현재 페이지, 한 화면에 보여질 행 수, select 결과 list)
		mav.addObject("data", new ArticlePage<MemberMineVO>(total, currentPage, size, list));
		
		return mav;
		
	}
	
	@GetMapping("/board/insert")
	public ModelAndView memberInsert(ModelAndView mav) {
		
		mav.setViewName("board/insert");
		
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView memberInsertPost(ModelAndView mav, @ModelAttribute MemberMineVO memberMineVO) {
		log.info("처음 memberMineVO : " + memberMineVO.toString());
		
		int result = this.memberMineService.memberInsert(memberMineVO);
		
		log.info("나중 memverVO : " + memberMineVO.toString());
		
		mav.setViewName("redirect:/board/list");
		
		return mav;
	}
	
	
	@ResponseBody
	@PostMapping("/board/idCheck")
	public int idCheck(@RequestParam("userId") String memId) {
		
		log.info("memId가 올까요?" + memId);
		
		int result = this.memberMineService.idCheck(memId);
		log.info("result는?? " + result);
		
		return result;
	}
	
//	//선생님의 중복체크!
//	@ResponseBody
//	@PostMapping("/board/idCheckSem")
//	public Map<String, String> idCheckMap(@RequestBody Map<String, String> json) {
//		
//		log.info("json : " + json);
//		
//		Map<String, String> rsltMap = new HashMap<String, String>();
//		
//		int result = this.memberMineService.idCheck(json.get("userId"));
//		
//		log.info("result는?? " + result);
//		
//		rsltMap.put("result", result+"");
//		
//		return rsltMap;
//	}
	
	//요청 URI : /board/detail?memId=
	//URL : /board/detail
	//요청 파라미터 : memId
	@GetMapping("/board/detail")
	public String detail(String memId, Model model) {
		log.info("memId : " + memId);
		
		//회원 상세 정보 (1)
		MemberMineVO memberMineVO = this.memberMineService.detail(memId);
		
		List<AttachVO> attachVOList = memberMineVO.getAttachVOList();
		
		log.info("memberMineVO : " + memberMineVO.toString());
		
		model.addAttribute("memberMineVO", memberMineVO);
		model.addAttribute("attachVOList", attachVOList);
		
		return "board/detail";
	}
	
}




