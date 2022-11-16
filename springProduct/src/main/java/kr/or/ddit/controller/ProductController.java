package kr.or.ddit.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.ProductService;
import kr.or.ddit.vo.CartDetVO;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ProductController {
	// 상품관리 프로그램 spring 바꾸기
	@Autowired
	ProductService productService;
	// URI => http://localhost/create
	// Request : client가 server에 URI요청
	// Mapping : create() 메소드 실행
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView create() {
		/*
			ModelAdnView
			1) Model : return할 데이터(String, int, List, Map, VO.. 등)를 담당
			2) View : 화면을 담당(뷰(view : JSP)의 경로)
				ViewResolver => prefix + jsp파일명 + suffix
		 */
		ModelAndView mav = new ModelAndView();
		
		//product 폴더에있는 addProduct를 찾어가랏!!
		mav.setViewName("product/addProduct");
		// forwarding 방식
		return mav;
	}
	
	// URI : http://localhost/create
	// 요청 파라미터: {"title":"개똥이월드","category":"소설","price":"10000"}
	// BookVO 	: {"bookId":0, "title":"개똥이월드","category":"소설","price":"10000","insertDate",""}
	// <form action="/create" method="post">
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView createPost(ModelAndView mav,
					@ModelAttribute ProductVO productVO) {
		
		int result = this.productService.insert(productVO);
		
		if(result < 1) {
			// /create(get방식) URI를 재요청
			// 책 입력 화면으로 이동
			mav.setViewName("redirect:/addProduct");
		} else {
			mav.setViewName("redirect:/product?productId="+productVO.getProductId());
		}
		
		return mav;
	}
	
	//1) 로그인한 사용자만 접근 가능
	//@PreAuthorize("isAuthenticated()")
	//2) 회원권한을 가진 사용자만 접근 가능
	//@PreAuthorize("hasRole('ROLE_MEMBER')")
	//3) 회원권한 or 관리자 권한을 가진 사용자만 접근 가능(Any => 또는(or연산))
	@PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ModelAndView products(ModelAndView mav) {
		List<ProductVO> data = this.productService.list();
		mav.addObject("product", data);
		//view
		mav.setViewName("product/products");
		
		return mav;
	}
	
	//책 상세보기
		//요청된URI 주소: /detail?bookId=1
		//URL : http://localhost/detail
		//요청(http) 파라미터, 쿼리 스트링: book_id=1
		@RequestMapping(value = "/product", method=RequestMethod.GET)
		public ModelAndView detail(ModelAndView mav, @ModelAttribute ProductVO productVO) { //ModelAttribute 알아서 가져올게! 생략 O
			
			//select 결과 1행을 bookVO에 담을 것이닷
			ProductVO data = this.productService.selectDetail(productVO);
			
			//해석/컴파일하여 html을 응답.
			//데이터(BookVO) 1행을 함께 응답.
			//but, redirect는 데이터를 응답해주지 못함
			mav.setViewName("product/product");
			mav.addObject("product", data);
			mav.addObject("productId", data.getProductId());
			
			return mav;
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView updateGet(@ModelAttribute ProductVO productVO, 
				ModelAndView mav) {
			
			ProductVO data = this.productService.selectDetail(productVO);
			
			mav.addObject("data",data);
			
			mav.setViewName("product/update");
			return mav;
		}
		
		// 파라미터 받는 방법 => VO는 @ModelAttribute / String/int 는 @RequestParam
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public ModelAndView updatePost(@ModelAttribute ProductVO productVO, 
				ModelAndView mav) {
			
			int result = this.productService.update(productVO);
			
			if(result>0) { //업데이트 성공 -> 책 상세페이지(detail.jsp)로 이동
				mav.setViewName("redirect:/product?productId="+productVO.getProductId());
			}else { //업데이트 실패 => 업데이트 뷰(update.jsp)로 페이지 이동
				mav.setViewName("redirect:/update?productId"+productVO.getProductId());
			}
			
			return mav;
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(ModelAndView mav, @RequestParam String productId
				, Map<String, String> map) {
			
			//해당 글 삭제
			int result = this.productService.delete(productId);
			
			if(result>0) {
				mav.setViewName("redirect:/products");
			}else {
				mav.setViewName("redirect:/product?productId="+productId);
			}
			
			return mav;
		}
		
		//장바구니페이지
		@RequestMapping(value="/cart", method = RequestMethod.GET)
		public ModelAndView cart(ModelAndView mav) {
			List<ProductVO> data = this.productService.list();
			mav.addObject("product", data);
			//view
			mav.setViewName("product/cart");
			
			return mav;
		}
		
		
		//요청URI : /addCart
		//요청 파라미터 : {"productId" : "P1235"}
		//장바구니(=세션(cartlist))에 해당 상품을 넣음
		//spring에서 요청 파라미터를 매개변수로 받을 수 있댜 
		@RequestMapping(value="/addCart", method=RequestMethod.POST)
		public String addCart(@RequestParam String productId,
				Model model, @ModelAttribute ProductVO productVO, HttpServletRequest request) {
			log.info("productId는?? " + productId);
			log.info("productVO는?? " + productVO.toString());
			//장바구니에 넣을 상품이 없다면 ?
			if(productId==null) {
				return "redirect:/product?productId=" + productId;
			}
			
			
			//장바구니에 넣을 상품을 검색해보쟈
			ProductVO vo = this.productService.selectDetail(productVO);
			log.info("vo : " + vo);
			
			//검색해보니 상품 결과가 없다면 ㅇㅁㅇ
			if(vo==null) {
				//[상품없음] 예외처리 페이지로 이동
				return "redirect:/exceptionNoProductId";
			}
			
			//장바구니(세션) = > 세션명 : cartlist
			HttpSession session = request.getSession();
			
			//세션에 cartlist가 있는가
			//object여서 캐스팅 필요 ㅇㅁㅇ
			ArrayList<ProductVO> list = (ArrayList<ProductVO>)session.getAttribute("cartlist");
			
			//없다믄 생성해보쟈
			if(list==null) {
				//list는 null이므로 여기서 리스트를 생성해줘야 함
				list = new ArrayList<ProductVO>();
				//cartlist라는 세션명으로 생성
				session.setAttribute("cartlist", list);
			}
			
			//장바구니가 있으면 다음을 실행
			int cnt = 0; //장바구니에 상품이 담긴 개수
			
			for(int i=0;i<list.size();i++) {
				// list는 session 장바구니
				if(list.get(i).getProductId().equals(productId)) {
					cnt++;
					//장바구니에 상품이 이미 들어있다면 장바구니에 담은 개수만 1 증가
					list.get(i).setQuantity(list.get(i).getQuantity()+1);
				}
			}
			//장바구니에 해당 상품이 없다면 
			if(cnt==0) {
				vo.setQuantity(1);
				//최종목표 : 장바구니에 상품을 추가
				list.add(vo);
			}
			
			//장바구니 확인
			for(ProductVO pv : list) {
				log.info("pv는 ?? " + pv.toString());
			}
			
			return "redirect:/product?productId=" + productId;
		}
		
		//주문하기
		@RequestMapping(value="/shippingInfo", method = RequestMethod.GET)
		public ModelAndView shippingInfo(ModelAndView mav, @RequestParam String cartId, Model model) {
			log.info("cartId는?? : " + cartId );
			
			model.addAttribute("cartId", cartId);
			
			mav.setViewName("product/shippingInfo");
			
			return mav;
		}
		
		
		//주문하깃
		@RequestMapping(value="/processShippingInfo", method = RequestMethod.POST)
		public ModelAndView processShippingInfo(ModelAndView mav, @ModelAttribute CartVO cartVO
				, HttpServletResponse response, Model model) throws Exception {
			
			//요청 파라미터 정보를 쿠키에 넣어주쟈
			Cookie cartId = new Cookie("Shipping_cartId", 
					URLEncoder.encode(cartVO.getCartId(),"UTF-8"));
			Cookie name = new Cookie("Shipping_name", 
					URLEncoder.encode(cartVO.getName(),"UTF-8"));
			Cookie shippingDate = new Cookie("Shipping_shippingDate", 
					URLEncoder.encode(cartVO.getShippingDate(),"UTF-8"));
			Cookie country = new Cookie("Shipping_country", 
					URLEncoder.encode(cartVO.getCountry(),"UTF-8"));
			Cookie zipCode = new Cookie("Shipping_zipCode", 
					URLEncoder.encode(cartVO.getZipCode(),"UTF-8"));
			Cookie addressName = new Cookie("Shipping_addressName", 
					URLEncoder.encode(cartVO.getAddressName(),"UTF-8"));
			Cookie addressDetail = new Cookie("Shipping_addressDetail", 
					URLEncoder.encode(cartVO.getAddressDetail(),"UTF-8"));
			
			//쿠키의 유효 기간을 1일로 설정(초단위)->60*60
			cartId.setMaxAge(24*60*60);
			name.setMaxAge(24*60*60);
			shippingDate.setMaxAge(24*60*60);
			country.setMaxAge(24*60*60);
			zipCode.setMaxAge(24*60*60);
			addressName.setMaxAge(24*60*60);
			addressDetail.setMaxAge(24*60*60);
			
			//생성된 쿠키를 등록 (서버에서 생성이 되어 response객체에 담겨서 클라이언트로 가져와짐)
			response.addCookie(cartId);
			response.addCookie(name);
			response.addCookie(shippingDate);
			response.addCookie(country);
			response.addCookie(zipCode);
			response.addCookie(addressName);
			response.addCookie(addressDetail);
			
			model.addAttribute("cartVO", cartVO);
			
			//주문서 페이지로 이동
			
			mav.setViewName("product/orderConfirmation");
			
			return mav;
		}
		
		//주문하깃2
		@RequestMapping(value="/orderConfirmation", method = RequestMethod.GET)
		public ModelAndView orderConfirmation(ModelAndView mav) {
			
			mav.setViewName("product/orderConfirmation");
			
			return mav;
		}
		
		//주문완료
		@RequestMapping(value="/thankCustomer", method = RequestMethod.GET)
		public String thankCustomer(HttpServletRequest request, CartVO cartVO) throws Exception {
			
			//1. 쿠키 정보를 가져와 CART 테이블로 insert
		      String Shipping_name = "";
		      String Shipping_zipCode = "";
		      String Shipping_country = "";
		      String Shipping_addressName = "";
		      String Shipping_addressDetail = "";
		      String Shipping_shippingDate = "";
		      String Shipping_cartId = "";

		      Cookie[] cookies = request.getCookies();
		      
		      //쿠키의 개수만큼 반복
		      for(int i=0;i<cookies.length;i++){
		         Cookie thisCookie = cookies[i];
		         //쿠키 이름 가져옴
//		          out.print(thisCookie.getName() + "<br />");
		         //쿠키 값 가져옴
//		          out.print(URLDecoder.decode(thisCookie.getValue(),"UTF-8")+"<br />");
		         if(thisCookie.getName().equals("Shipping_name")){
		            Shipping_name = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setName(Shipping_name);
		         }
		         if(thisCookie.getName().equals("Shipping_zipCode")){
		            Shipping_zipCode = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setZipCode(Shipping_zipCode);
		         }
		         if(thisCookie.getName().equals("Shipping_country")){
		            Shipping_country = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setCountry(Shipping_country);
		         }
		         if(thisCookie.getName().equals("Shipping_addressName")){
		            Shipping_addressName = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setAddressName(Shipping_addressName);
		         }
		         if(thisCookie.getName().equals("Shipping_addressDetail")){
		        	 Shipping_addressDetail = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		        	 cartVO.setAddressDetail(Shipping_addressDetail);
		         }
		         if(thisCookie.getName().equals("Shipping_shippingDate")){
		            Shipping_shippingDate = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setShippingDate(Shipping_shippingDate);
		         }
		         if(thisCookie.getName().equals("Shipping_cartId")){
		            Shipping_cartId = URLDecoder.decode(thisCookie.getValue(),"UTF-8");
		            cartVO.setCartId(Shipping_cartId);
		         }
		      }
		      
		      log.info("cartVO : " + cartVO.toString());
		      //2. 세션 정보를 가져와 CART_DET 테이블로 다중 insert
		      HttpSession session = request.getSession();
		      ArrayList<ProductVO> list = (ArrayList<ProductVO>)session.getAttribute("cartlist");
		      
		      // 3. CartVO : CartDetVO = 1 : N
		      List<CartDetVO> cartDetVOList = new ArrayList<CartDetVO>();
		     for(ProductVO vo : list) {
		    	 CartDetVO cartDetVO = new CartDetVO();
		    	 cartDetVO.setCartId(cartVO.getCartId());
		    	 cartDetVO.setProductId(vo.getProductId());
		    	 cartDetVO.setUnitPrice(vo.getUnitPrice());
		    	 cartDetVO.setQuantity(vo.getQuantity());
		    	 cartDetVO.setAmount(vo.getUnitPrice() * vo.getQuantity());
		    	 
		    	 cartDetVOList.add(cartDetVO);
		     }
		     cartVO.setCartDetVOList(cartDetVOList);
		     
		     log.info("cartVO : " + cartVO.toString());
		     
		     this.productService.thankCustomer(cartVO);
		      
		      //forwarding
		      return "product/thankCustomer";
		}
		
		//주문 전 취소
		@RequestMapping(value="/checkOutCancelled", method = RequestMethod.GET)
		public ModelAndView checkOutCancelled(ModelAndView mav) {
			
			mav.setViewName("product/checkOutCancelled");
			
			return mav;
		}
		
		//장바구니상품 전체 삭제
		@RequestMapping(value="/removeCart", method = RequestMethod.GET)
		public ModelAndView removeCart(ModelAndView mav, @RequestParam String productId, HttpServletRequest request) {
			
			log.info("productId는?? " + productId);
			
			HttpSession session = request.getSession();
			
			ArrayList<ProductVO> cartlist = (ArrayList<ProductVO>)session.getAttribute("cartlist");
			
			for(int i=0;i<cartlist.size();i++){
				//cartlist.get(i).getProductId() => 장바구니안에 있는 상품의 기본키
				if(cartlist.get(i).getProductId().equals(productId)){
					//remove(Object)
					cartlist.remove(cartlist.get(i));
				}
			}
			
			mav.setViewName("product/cart");
			
			return mav;
		}
		
		//장바구니상품 한개씩 삭제
		@RequestMapping(value="/deleteCart", method = RequestMethod.GET)
		public ModelAndView deleteCart(ModelAndView mav, String cartId, HttpSession session) {
			
			log.info("cartId : " + cartId);
			
			//cartId가 없네? => cart.jsp로 이동
			if(cartId == null || cartId.trim().contentEquals("")) {
				mav.setViewName("product/cart");
				return mav;
			}
			
			//장바구니 비우기
			//session.removeAttribute("세션명"); => 세션 한건만 삭제
			session.invalidate();
			
			mav.setViewName("product/cart");
			
			return mav;
		}
		
		//PRODUCT테이블의 기본키 자동 생성
		//JSON 데이터로 return : {"productId" : "P1234"}
		@ResponseBody
		@PostMapping("/getProductId")
		public Map<String, String> getProductId(){
			Map<String, String> map = new HashMap<String, String>();
			String productId = this.productService.getProductId();
			
			map.put("productId" ,productId);
			
			return map;
		}
		
		
		
}
