package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberMineVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	//DI(의존성 주입), IoC(제어의 역전)
	@Inject
	MemberService memberService;
	
	@Inject
	FileUploadUtil fileUploadUtil;
	
	
	// 요청 파라미터 : register?userId-hongkd&passwd-1234
	@GetMapping("/register")
	public String registerByParameter(String userId, String passwd) {
		log.info("registerByParameter 왔댱");
		
		log.info("userId는 ? " +userId);
		log.info("passwd는 ? " +passwd);
		
		return "success";
	}
	
	//URL 경로 상의 경로 변수로부터 요청 데이터를 취득
	//경로(path) 변수(Variabel)
	//요청 URI : /register/hongkd
	@RequestMapping(value ="/register/{userId}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		log.info("registerByPath 에 왔당");
		
		log.info("userId : " +userId);
		
		return "success";
	}
	
	@GetMapping("register01")
	public String register01() {
		
		return "register";
	}
	
	@PostMapping("register01")
	public String register01Post(String userId, String password, String coin){
			log.info("userId : " + userId);
			log.info("password : " + password);
			log.info("coin : " + coin);
			
			return "success";
		}
	
	@GetMapping("/register/register03")
	public String register03() {
		return "register/register";
	}
	/*
	 * 
	 */
	@PostMapping("/register/register03")
	public String register03Post(String userId, String password, int coin) {
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "register/success";
	}
	
	//
	//URL 경로 상의 경로 변수(PathVariable)가 여러 개일 때
	//@PathVariable 애너테이션을 사용하여 특정한 경로 변수명을 지정. URL에서 경로 변수 값을 가져오기 위한 것
	//요청 URI : /register/register03/a001/100
	@RequestMapping(value="/register/register03/{userId}/{coin}")
	public String register03ByPath(@PathVariable("userId") String userId, @PathVariable("coin") int coin) {
		log.info("register03ByPath에 왔당");
		log.info("userId : " + userId); //a001
		log.info("coin : " + coin); //100
		
		return "register/success";
	}
	
	@GetMapping("/register/register04")
	public String register04ByParam() {
		return "register/register04";
	}
	
	//요청 파라미터 : {"userId":"a001","password":"1234","coin":"100"}
	//@RequestParam => form으로 보내는 이름과 파라미터 이름이 다를 때 사용! but form 이름 = 파라미터 이름이 가장 best
	@PostMapping("/register/register04")
	public String register04ByParamPost(@RequestParam("userId") String id, @RequestParam("password") String pw, String coin) {
		log.info("id ?? " + id);
		log.info("pw ?? " + pw);
		log.info("coin ?? " + coin);
		
		return "register/success";
	}
	
	@GetMapping("/register/register05")
	public String register05ByParam() {
		return "register/register05";
	}
	
	//폼 텍스트 필드 요소의 값을 자바빈즈 매개변수의 정수 타입 매개변수로 처리 됨.
	//요청 파라미터 : {"userId":"a001", "password":"1234","coin":"100"}
	//MemberVO => private int coin
	@PostMapping("/register/register05")
	public String register05ByParamPost(@ModelAttribute MemberVO memberVO, 
			int coin, ArrayList<String> cars,
			AddressVO addressVO, Model model) {
		log.info("처음memverVO : " + memberVO.toString());
		List<CardVO> cardVOList = memberVO.getCardVOList();
		addressVO = memberVO.getAddressVO();
		
		//보유 자동차들
		String car = StringUtils.join(memberVO.getCars(),",");
		memberVO.setCar(car);
		
		//취미들 
		String hobby = StringUtils.join(memberVO.getHobbyList(),",");
		memberVO.setHobby(hobby);
		
		int result = this.memberService.memberInsert(memberVO);
		
		log.info("나중 memverVO : " + memberVO.toString());
		
		return "register/success";
	}
	
	
	//요청URI : /register/registerByGet01?userId=a001&birth=2022/10/31
	@GetMapping("/register/registerByGet01")
	public String registerByGet01(String userId, Date birth) {
		log.info("registerByGet01에 왔닫ㅇ");
		
		log.info("userId : " + userId);
		log.info("birth : " + birth);
		
		return "register/success";
		
	}
	
	//요청URI : /register/registerByGet02?userId=a001&birth=2022/10/31
	@GetMapping("/register/registerByGet02")
	public String registerByGet02(MemberVO memberVO) {
		log.info("registerByGet02에 왔닫ㅇ");
		
		log.info("userId : " + memberVO.getUserId());
		log.info("birth : " + memberVO.getBirth());
		
		return "register/success";
		
	}
	
	/*
	 *  8. 파일 업로드 폼 방식 요청 처리
	 *  파일 업로드 폼 파일<input type="file"...요소(=태그)값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리함
	 */
	
	@GetMapping("/register/register06")
	public String register06() {
		
		log.info("register06에 왔댱");
		
		return "register/register06";
	}
	
	
	@PostMapping("/register/registerFile01")
	public String registerFile01Post(MultipartFile picture) {
		log.info("registerFile01");
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "register/success";
	}
	
	
	@PostMapping("/register/registerFile02")
	public String registerFile02Post(String userId,
			String password,
			MultipartFile picture) {
		log.info("registerFile02");
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "register/success";
	}
	
	@PostMapping("/register/registerFile03")
	public String registerFile03Post(MemberVO memberVO,
			MultipartFile picture) {
		log.info("registerFile03");
		log.info("memberVO : " + memberVO.toString());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "register/success";
	}
	
	@PostMapping("/register/registerFile05")
	public String registerFile05Post(MemberVO memberVO,
			MultipartFile picture, MultipartFile picture2) {
		log.info("registerFile05");
		log.info("memberVO : " + memberVO.toString());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName : " + picture2.getOriginalFilename());
		log.info("size : " + picture2.getSize());
		log.info("contentType : " + picture2.getContentType());
		
		return "register/success";
	}
	
	//List<MultipartFile> pictureList
	@PostMapping("/register/registerFile06")
	public String registerFile06Post(MemberVO memberVO,
			List<MultipartFile> pictureList) {
		log.info("registerFile06");
		log.info("memberVO : " + memberVO.toString());
		
		for(MultipartFile picture : pictureList) {
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		}
		
		
		return "register/success";
	}
	
	@PostMapping("/register/registerFile07")
	public String registerFile07Post(MemberVO memberVO,
			MultipartFile[] pictures) {
		log.info("registerFile07");
		log.info("memberVO : " + memberVO.toString());
		
		MultipartFile[] pictureArray = memberVO.getPictureArray();
		
		for(MultipartFile picture : pictureArray) {
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		
		
		return "register/success";
	}
	
	@GetMapping("/register/register07")
	public String register07Get() {
		return "/register/register07";
	}
	
	//요청 URI : /board/uploadAjax
	//formData.append("file", file) == MultipartFile file
	@RequestMapping(value="/board/uploadAjax", method=RequestMethod.POST, 
			produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile[] file){
		String originalFilenName = file[0].getOriginalFilename();
		log.info("originalFilenName" + originalFilenName);
		ResponseEntity<String> entity = 
				new ResponseEntity<String>("SUCCESS : "+originalFilenName, HttpStatus.OK);
		
		UUID uid = UUID.randomUUID();
		
		this.fileUploadUtil.fileUploadAction(file, uid.toString());
		
		return entity;
	}
	
}




