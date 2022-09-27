package com.product_favorites.model;

import java.util.*;

public interface ProductFavoritesDAO_interface {
          public void insert(ProductFavoritesVO productFavoritesVO);
          public void update(ProductFavoritesVO productFavoritesVO);
          public void delete(Integer PdID,Integer MemID);
          public ProductFavoritesVO findByPrimaryKey(Integer PdID,Integer MemID);
          
          public List<ProductFavoritesVO> getAll();
}
