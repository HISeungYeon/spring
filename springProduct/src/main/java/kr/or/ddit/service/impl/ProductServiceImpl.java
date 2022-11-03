package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.ProductDao;
import kr.or.ddit.service.ProductService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	// 객체를 스프링이 관리해줌.
	// db접글을 위해 dao 인스턴스를 주입받는다.
	@Autowired
	ProductDao productDao;
	
	@Autowired
	FileUploadUtil fileUploadUtil;

	@Override
	public int insert(ProductVO productVO) {
		
		//PRODUCT 테이블에 insert
		int result = this.productDao.insert(productVO);
		//ATTACH 테이블에 다중 insert
		if(result > 0) { //insert 성공 시
			//파일 업로드 및 insert 수행 1
//			FileUploadUtil.fileUploadAction(productVO.getProductImage(), 
//					productVO.getProductId());
			// 파일 업로드 및 insert 수행 2
			fileUploadUtil.fileUploadAction(productVO.getProductImage(), 
					productVO.getProductId());
			
		}
		
		return result;
	}

	@Override
	public List<ProductVO> list() {
		return this.productDao.list();
	}

	@Override
	public ProductVO selectDetail(ProductVO productVO) {
		return this.productDao.selectDetail(productVO);
	}

	// 책 수정하기
	@Override
	public int update(ProductVO productVO) {
		return this.productDao.update(productVO);
	}

	@Override
	public int delete(String productId) {
		return this.productDao.delete(productId);

	}

	//CART 및 CART_DET 테이블에 insert
	@Override
	public int thankCustomer(CartVO cartVO) {
		//1. CART 테이블에 insert
		int cartInCnt = this.productDao.insertCart(cartVO);
		log.info("cartInCnt는?? " + cartInCnt);
		
		//2. CART_DET 테이블에 insert
		
		return 0;
	}
	
	//ATTACH 테이블에 다중 INSERT
	@Override
	public int insertAttach(List<AttachVO> attachVOList) {
		return this.productDao.insertAttach(attachVOList);
	}
	
	//PRODUCT테이블의 기본키 자동 생성
	@Override
	public String getProductId() {
		return this.productDao.getProductId();
	}
}
