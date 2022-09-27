package com.product_type_details.model;

import java.util.*;

public interface ProductTypeDetailsDAO_interface {
          public void insert(ProductTypeDetailsVO productTypeDetailsVO);
          public void update(ProductTypeDetailsVO productTypeDetailsVO);
          public void delete(Integer PdID,Integer PdTypeID);
          public ProductTypeDetailsVO findByPrimaryKey(Integer PdID,Integer PdTypeID);
          public List<ProductTypeDetailsVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
