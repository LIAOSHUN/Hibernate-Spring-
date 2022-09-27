package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer pdID);
          public ProductVO findByPrimaryKey(Integer pdID);
          public List<ProductVO> getAll();
}
