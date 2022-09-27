package TestHibernate;

import java.util.List;
import java.util.Objects;

import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.sql.Select;

import com.coupontype.model.CouponTypeVO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import util.HibernateUtil;

public class testCouponType {

//	public static void main(String[] args) {
//		-- insert
//		CouponTypeVO couponTypeVO = new CouponTypeVO();
//		couponTypeVO.setCoupName("優惠券");
//		couponTypeVO.setCoupDiscount(20);
//		couponTypeVO.setCoupQuantity(200);
//		couponTypeVO.setCoupDesc("221221221211112221");
//		couponTypeVO.setCoupStart(java.sql.Date.valueOf("2022-11-01"));
//		couponTypeVO.setCoupEnd(java.sql.Date.valueOf("2022-12-01"));
//		insert(couponTypeVO);
		

//		-- updateById
//		CouponTypeVO couponTypeVO = new CouponTypeVO();
//		couponTypeVO.setCoupTypeNo(26014);
//		couponTypeVO.setCoupName("xxxx優惠券");
//		couponTypeVO.setCoupDiscount(50);
//		couponTypeVO.setCoupQuantity(100);
//		couponTypeVO.setCoupDesc("xxxxxxxxxx");
//		couponTypeVO.setCoupUpd(1);
//		update(couponTypeVO);
		


//		updateQuantity(26015, 1000);
//		updateDown(26011);
		
//		-- selectbyId 
//		CouponTypeVO couponTypeVO =findCouponTypeByType(26001);
//		System.out.println(couponTypeVO.getCoupName());
		
//		-- selectAll
//		testCouponType dao = new testCouponType();
//		for(CouponTypeVO couponTypeVO : dao.getAll()) {
//			System.out.println(couponTypeVO.getCoupName());
//		}
//	}

	
	
//	public static void insert(CouponTypeVO couponTypeVO) {
//			
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			session.persist(couponTypeVO);
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//	}
	public static void insert(CouponTypeVO couponTypeVO) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//
//			session.persist(couponTypeVO);
//			couponTypeVO.setCoupUpd(0);//新增商品預設是上架中
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
	}
	
	
	
	public static void update(CouponTypeVO couponTypeVO) {

//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//
//			session.merge(couponTypeVO);
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//		
	}
	
	
	public static void updateQuantity(Integer coupTypeNo, Integer coupQuantity) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//
//			NativeQuery<?>nativeQuery = session.createSQLQuery("update coupontype set "
//					+ "CoupQuantity= :coupQuantity where CoupTypeNo= :coupTypeNo")
//					.setParameter("coupQuantity", coupQuantity)
//					.setParameter("coupTypeNo", coupTypeNo);
//			nativeQuery.executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
		
	}
	
	
	public static void updateDown(Integer coupTypeNo) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//
//			NativeQuery<?>nativeQuery = session.createSQLQuery("update coupontype set "
//					+ " CoupUpd=1  where CoupTypeNo= :coupTypeNo")
//					.setParameter("coupTypeNo", coupTypeNo);
//			nativeQuery.executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
		
	}
	
	
//	public static CouponTypeVO findCouponTypeByType(Integer coupTypeNo) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			CouponTypeVO couponTypeVO = session.get(CouponTypeVO.class, coupTypeNo);
//			transaction.commit();
//			session.close();
//			return couponTypeVO;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
		
	}	
	
	
	//HQL
//	public List<CouponTypeVO> getAll() {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Query<CouponTypeVO> query = session.createQuery("FROM CouponTypeVO", CouponTypeVO.class);
//			List<CouponTypeVO> list = query.list();
//			transaction.commit();
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//		
//	}	
//}
