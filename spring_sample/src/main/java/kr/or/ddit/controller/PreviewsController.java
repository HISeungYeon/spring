package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.MemService;
import kr.or.ddit.vo.MemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/previews")
@Controller
public class PreviewsController {
	
	@Autowired
	MemService memService;
	
	// 요청 URI : /previews/write
	// 방식 : get
	@GetMapping("/write")
	public String write(@ModelAttribute MemVO memVO) {
		
		//forwarding
		return "previews/write";
	}
	
	/*
	 * 요청 URI : /previews/writePost
	 * 방식 : post
	 * request객체에 요청 파라미터가 들어있음
	 * VO -> @ModelAttribute(생략가능) -> @Validated -> 요청 파라미터 검증 
	 * String, Map, int -> @RequestParam
	 * 
	 * 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의함.
	 * BindingResult에는 요청 파라미터 데이터의 바인딩 에러(멤버변수에 없음)와 
	 * 입력값 검증 에러(어노테이션 오류) 정보가 저장됨.
	 */
	@PostMapping("/writePost")
	public String writePost(@Validated MemVO memVO, BindingResult result) {
		
		log.info("memVO : " + memVO.toString());
		//입력값 검증 오류 발생 시 true 반환 / 오류 없으면 false 반환
		log.info("result.hasErrors() : " + result.hasErrors());
		
		//입력값 검증 후 BindingResult가 제공하는 메서드를 이용하여 검사 결과 확인
		/*
		 * 1. hasErrors() : 오류가 발생한 경우 true 반환
		 * 2. hasGlobalErros() : 객체 레벌의 오류가 발생한 경우 true 반환
		 * 3. hasFieldErros() : 필드(멤버변수) 레벨의 오류가 발생한 경우 true 반환
		 * 4. hasFieldErros(String) : 인수에 지정한 필드에 오류 발생 시 true 반환
		 */
		if(result.hasErrors()) { //오류가 있다면
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors(); //객체 레벨
			List<FieldError> fieldErrors = result.getFieldErrors(); //멤버변수 레벨
			
			for(int i=0;i<allErrors.size();i++) {
				ObjectError objectError = allErrors.get(i);
				log.info("allError : " + objectError);
			}
			for(int i=0;i<globalErrors.size();i++) {
				ObjectError objectError = allErrors.get(i);
				log.info("allError : " + objectError);
			}
			for(int i=0;i<fieldErrors.size();i++) {
				FieldError fieldError = fieldErrors.get(i);
				log.info("allError : " + fieldError);
				log.info("fieldError.getDefaultMessage() : " + fieldError.getDefaultMessage());
			}
			
			return "previews/write";
		}
		
		int rslt = this.memService.memInsert(memVO);
		
		return "previews/write";
	}
	
	@GetMapping("/list")
	public String memList(Model model) {
		
		List<MemVO> memVOList = this.memService.memList2();
		
		log.info("memVOList : " + memVOList);
		
		model.addAttribute("memVOList", memVOList);
		
		return "previews/list";
	}
	
	//회원 상세보기
	//요청URI : /previews/detail?userNo=${memVO.userNo}
	//요청 파라미터 : userNo=202211001
	//detail.jsp(견우) <- memVO(오작교) -> previewsController.java(직녀)
	@GetMapping("/detail")
	public String memDetail(@RequestParam String userNo, @ModelAttribute MemVO memVO
					,Model model) {
		
		memVO = this.memService.memDetail(userNo);
		log.info("memVO : " + memVO.toString());
		
		model.addAttribute("memVO", memVO);
		
		return "previews/detail";
	}
	
	//요청URI : /previews/detailPwCheck
	//요청파라미터 (json) : {"userPw":"java"}
	@ResponseBody
	@PostMapping("/detailPwCheck")
	public Map<String, String> detailPwCheck(@RequestBody MemVO memVO) {
		log.info("memVO : " + memVO);
		
		//비밀번호 확인
		int result = this.memService.detailPwCheck(memVO);
		
		//결과 리턴
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result", result+"");
		
		return resultMap;
	}
	
	//요청URT : /previews/updatePost
	//요청파라미터 : memVO 멤버변수들,,
	//방식 : post
	@PostMapping("/updatePost")
	public String updatePost(@ModelAttribute MemVO memVO) {
		log.info("memVO : " + memVO.toString());
		
		//회원정보변경
		int result = this.memService.memUpdate(memVO);
		log.info("result : " + result);
		
		return "redirect:/previews/detail?userNo="+memVO.getUserNo();
	}
	
	@PostMapping("/deletePost")
	public String deletePost(@ModelAttribute MemVO memVO) {
		log.info("memVO : " + memVO.toString());
		log.info("회원정보삭제시쟉!!");
		
		//회원 정보 삭제
		int result = this.memService.memDelete(memVO);
		log.info("result : " + result);
		
		return "redirect:/previews/list";
	}
	
}
