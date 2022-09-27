package com.productimg.model;

import java.util.*;

public interface ProductImgDAO_interface {
          public void insert(ProductImgVO productimgVO);
          public void update(ProductImgVO productimgVO);
          public void delete(Integer pdImgId);
          
          public ProductImgVO findByPrimaryKey(Integer pdImgID);
//          public List<ProductImgVO> findByPrimaryKeyInBase64(Integer pdImgID);
          
          public List<ProductImgVO> getAll();
}
