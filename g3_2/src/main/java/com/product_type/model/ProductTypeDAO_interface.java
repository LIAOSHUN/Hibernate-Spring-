package com.product_type.model;

import java.util.*;

public interface ProductTypeDAO_interface {
          public void insert(ProductTypeVO productTypeVO);
          public void update(ProductTypeVO productTypeVO);
          public void delete(Integer PdTypeID);
          public ProductTypeVO findByPrimaryKey(Integer PdTypeID);
          public List<ProductTypeVO> getAll();
}
