package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.ProductVO;

// 서비스 interface : 비즈니스 로직
public interface ProductService {
	
	public int insert(ProductVO productVO);
	
	//상품 목록
	public List<ProductVO> list();

	//상품 상세
	public ProductVO selectDetail(ProductVO productVO);

	public int update(ProductVO productVO);

	public int delete(String productId);
	
}


