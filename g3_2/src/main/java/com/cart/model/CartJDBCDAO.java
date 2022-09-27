package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.coupontype.model.CouponTypeVO;
import com.product.model.ProductVO;

import util.HibernateUtil;

@Repository
public class CartJDBCDAO implements CartDAO_interface{

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "CGA103g3";
	
	
	@PersistenceContext
	private Session session;
	
//	-- 找出某樣商品資訊
	private static final String GetOne = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdID = ?";
//	-- 更改某樣商品資訊
	private static final String Update = 
			"UPDATE product set PdAmount=? where PdID = ?";
	
	
	
	
	@Override
	public ProductVO getOne(Integer pdID) {
		
		ProductVO productVO = session.get(ProductVO.class, pdID);
		return productVO;
	}

	@Override
	public void update(Integer count, Integer pdID) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
	
		NativeQuery<?>nativeQuery = session.createSQLQuery("update product set "
			+ "PdAmount= :pdAmount where PdID= :pdID")
			.setParameter("pdAmount", count)
			.setParameter("pdID", pdID);
		nativeQuery.executeUpdate();
	}

}
