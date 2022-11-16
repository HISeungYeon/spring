package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.MemberMineVO;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/form")
@Slf4j
@Controller
public class FormController {
	
	//<form:form modelAttribute="member" method="post" action="register">
	@GetMapping("/registerForm01")
	public String registerForm01(Model model) {
		log.info("registerForm01");
		
		model.addAttribute("member", new MemberMineVO());
		
		return "form/registerForm";
	}
	
	//<form:form modelAttribute="memberMineVO" method="post" action="register">
	@GetMapping("/registerForm02")
	public String registerForm02(@ModelAttribute MemberMineVO memberMineVO) {
		log.info("registerForm02");
		
		
		return "form/registerForm02";
	}
	
	//<form:form modelAttribute="memberMineVO" method="post" action="register">
	@GetMapping("/registerForm03")
	public String registerForm03(@ModelAttribute("memberMineVO") MemberMineVO memberVO,
				Model model) {
		log.info("registerForm03");
		
		//폼 객체의 프로퍼티에 값을 미리 지정해놓음
		memberVO.setMemId("hi");
		memberVO.setMemName("이승돌");
		//password는 값을 설정하여 view에 전달하더라도 password 필드에 반영이 안됨..ㅇㅁㅇ
		memberVO.setMemPass("1234");
		
		//체크박스
		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("Sports", "Sports");
		hobbyMap.put("Music", "Music");
		hobbyMap.put("Movie", "Movie");
		
		//라이오 버튼
		Map<String, String> genderMap = new HashMap<String, String>();
		genderMap.put("Male", "Male");
		genderMap.put("Female", "Female");
		genderMap.put("Other", "Other");
		
		//셀렉트 박스
		Map<String, String> nationalityMap = new HashMap<String, String>();
		nationalityMap.put("Korea", "Korea");
		nationalityMap.put("Germany", "Germany");
		nationalityMap.put("Australia", "Australia");
		
		
		model.addAttribute("hobbyMap", hobbyMap);
		model.addAttribute("genderMap", genderMap);
		model.addAttribute("nationalityMap", nationalityMap);
		
		
		return "form/registerForm03";
	}
	
	//요청 URI : /form/register
	//방식 :post
	//ㅍㅏ라미터 : {"memId": "hi", "memName":"이승돌"}
	@PostMapping("/register")
	public String registerPost(@Validated MemberMineVO memberMineVO, Model model
			,BindingResult result) {
		log.info("memberMineVO : " + memberMineVO.toString());
		
		//registerForm03.jsp에서 post 요청 시 Validated 확인 후 문제 발생 시 폼 화면으로 돌아감.
		if(result.hasErrors()) {
			return "form/registerForm03";
		}
		
		String[] hobbyList = memberMineVO.getHobbyList();
		
		if(hobbyList!=null) {
			for(String hobby:hobbyList) {
				log.info("hobby : " + hobby);
			}
		}
		
		model.addAttribute("hobbyList", hobbyList);
		
		return "form/success";
	}
	
}
