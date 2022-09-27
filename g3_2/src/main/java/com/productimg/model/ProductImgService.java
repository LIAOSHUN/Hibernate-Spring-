package com.productimg.model;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.List;

public class ProductImgService {

	private ProductImgDAO_interface dao;

	public ProductImgService() {
		dao = new ProductImgDAO();
	}
 
	public ProductImgVO addProductImg(byte[] pdImg, String pdImgName) {

		ProductImgVO productImgVO = new ProductImgVO();

		productImgVO.setPdImg(pdImg);
		productImgVO.setPdImgName(pdImgName);;
		dao.insert(productImgVO);

		return productImgVO;
	}

	public ProductImgVO updateProductImg(Integer pdImgID, byte[] pdImg, String pdImgName) {
		
		ProductImgVO productImgVO = new ProductImgVO();
		
		productImgVO.setPdImgID(pdImgID);;
		productImgVO.setPdImg(pdImg);;
		productImgVO.setPdImgName(pdImgName);;
		dao.update(productImgVO);

		return productImgVO;
	}

	public void deleteProductImg(Integer pdImgID) {
		dao.delete(pdImgID);
	}

	public ProductImgVO getOneProductImg(Integer pdImgID) {
		return dao.findByPrimaryKey(pdImgID);
	}

	public List<ProductImgVO> getAll() {
		return dao.getAll();
	}
}
