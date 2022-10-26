package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.ProductDao;
import kr.or.ddit.service.ProductService;
import kr.or.ddit.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	// 객체를 스프링이 관리해줌.
	// db접글을 위해 dao 인스턴스를 주입받는다.
	@Autowired
	ProductDao productDao;

	@Override
	public int insert(ProductVO productVO) {
		return this.productDao.insert(productVO);
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
}
