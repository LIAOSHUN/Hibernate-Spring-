package com.coupontype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import util.HibernateUtil;


@Repository
public class CouponTypeJDBCDAOH implements CouponTypeDAO_interface{

	@PersistenceContext
	private Session session;
	
	@Override
	public void insert(CouponTypeVO couponTypeVO) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();

		session.persist(couponTypeVO);
		couponTypeVO.setCoupUpd(0);//新增商品預設是上架中
	}
		

		
		
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//
//			NativeQuery<?>nativeQuery = session.createSQLQuery("insert into coupontype "
//					+ "values(:CoupName, :CoupDiscount, :CoupQuantity, :CoupDesc, :CoupUpd, :CoupStart, :CoupEnd) ")
//					.setParameter("CoupName", couponTypeVO.getCoupName())
//					.setParameter("CoupDiscount", couponTypeVO.getCoupDiscount())
//					.setParameter("CoupQuantity", couponTypeVO.getCoupQuantity())
//					.setParameter("CoupDesc", couponTypeVO.getCoupDesc())
//					.setParameter("CoupUpd", 0)
//					.setParameter("CoupStart", couponTypeVO.getCoupStart())
//					.setParameter("CoupEnd", couponTypeVO.getCoupEnd());
//			nativeQuery.executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//	}

	@Override
	public void update(CouponTypeVO couponTypeVO) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		CouponTypeVO oCouponTypeVO = session.get(CouponTypeVO.class, couponTypeVO.getCoupTypeNo());
		couponTypeVO.setCoupStart(oCouponTypeVO.getCoupStart());
		couponTypeVO.setCoupEnd(oCouponTypeVO.getCoupEnd());
		session.merge(couponTypeVO);
	}
//	第二種方法為動態update
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		
//	
//			CouponTypeVO oCouponTypeVO = session.get(CouponTypeVO.class, couponTypeVO.getCoupTypeNo());
//			//修改項目:coupName coupDiscount coupQuantity coupDesc coupUpd
//			final String coupName = couponTypeVO.getCoupName();
//			if(coupName != null && !Objects.equals(coupName, "") ) {
//				oCouponTypeVO.setCoupName(coupName);
//			}
//			
//			final Integer coupDiscount = couponTypeVO.getCoupDiscount();
//			if(coupDiscount != null && !Objects.equals(coupDiscount, "") ) {
//				oCouponTypeVO.setCoupDiscount(coupDiscount);
//			}
//			
//			final Integer coupQuantity = couponTypeVO.getCoupQuantity();
//			if(coupQuantity != null && !Objects.equals(coupQuantity, "")) {
//				oCouponTypeVO.setCoupQuantity(coupQuantity);
//			}
//			final String  coupDesc = couponTypeVO.getCoupDesc();
//			if(coupDesc != null && !Objects.equals("", coupDesc)) {
//				oCouponTypeVO.setCoupDesc(coupDesc);
//			}
//			
//			final Integer coupUpd = couponTypeVO.getCoupUpd();
//			if(coupUpd != null && !Objects.equals("", coupUpd)) {
//				oCouponTypeVO.setCoupUpd(coupUpd);
//			}
			
//	}

	
	@Override
	public void updateQuantity(Integer coupTypeNo, Integer coupQuantity) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
	
		NativeQuery<?>nativeQuery = session.createSQLQuery("update coupontype set "
			+ "CoupQuantity= :coupQuantity where CoupTypeNo= :coupTypeNo")
			.setParameter("coupQuantity", coupQuantity)
			.setParameter("coupTypeNo", coupTypeNo);
		nativeQuery.executeUpdate();
	}

	
	@Override
	public void updateDown(Integer coupTypeNo) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		NativeQuery<?>nativeQuery = session.createSQLQuery("update coupontype set "
			+ " CoupUpd=1  where CoupTypeNo= :coupTypeNo")
			.setParameter("coupTypeNo", coupTypeNo);
		nativeQuery.executeUpdate();
	}
	@Override
	public CouponTypeVO findCouponTypeByType(Integer coupTypeNo) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		CouponTypeVO couponTypeVO = session.get(CouponTypeVO.class, coupTypeNo);
		return couponTypeVO;
	}	

	
	@Override	//HQL
	public List<CouponTypeVO> getAll() {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		Query<CouponTypeVO> query = session.createQuery("FROM CouponTypeVO", CouponTypeVO.class);
		List<CouponTypeVO> list = query.list();
		return list;
	}





	public void delete(Integer coupTypeNo) {
		// TODO Auto-generated method stub
		
	}	


}
