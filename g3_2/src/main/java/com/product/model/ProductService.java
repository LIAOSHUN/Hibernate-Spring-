package com.product.model;

import java.sql.Timestamp;
import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}
//	Integer pdID, 
	public ProductVO addProduct(String pdName, Integer pdPrice,
			Integer pdAmount, String pdDescription, Integer pdStatus,
			Integer pdStar) {

		ProductVO productVO = new ProductVO();

//		productVO.setPdID(pdID);
		productVO.setPdName(pdName);
		productVO.setPdPrice(pdPrice);
		productVO.setPdAmount(pdAmount);
		productVO.setPdDescription(pdDescription);
		productVO.setPdStatus(pdStatus);
		productVO.setPdStar(pdStar);
//		productVO.setPdUpdate(pdUpdate);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer pdID, String pdName, Integer pdPrice,
			Integer pdAmount, String pdDescription, Integer pdStatus,
			Integer pdStar) {
//		,Timestamp pdUpdate
		
		ProductVO productVO = new ProductVO();
		
		productVO.setPdID(pdID);
		productVO.setPdName(pdName);
		productVO.setPdPrice(pdPrice);
		productVO.setPdAmount(pdAmount);
		productVO.setPdDescription(pdDescription);
		productVO.setPdStatus(pdStatus);
		productVO.setPdStar(pdStar);
//		productVO.setPdUpdate(pdUpdate);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer pdID) {
		dao.delete(pdID);
	}

	public ProductVO getOneProduct(Integer pdID) {
		return dao.findByPrimaryKey(pdID);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
