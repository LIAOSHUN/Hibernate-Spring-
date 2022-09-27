package com.memcoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.coupontype.model.CouponTypeVO;
import com.orderlist.model.OrderListVO;

import util.HibernateUtil;

@Repository
public class MemCouponJDBCDAOH implements MemCouponDAO_interface{

	@PersistenceContext
	private Session session;
	
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";
//	-- 新增一筆會員優惠券資料
	private static final String Insert=
		"INSERT INTO memcoupon(MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate) "
		+ "VALUES "
		+ "(?, ?, 0, now(), now())";
			
//	-- 更改 會員擁有的優惠券 資料內容
	private static final String Update=
		"update memcoupon "
		+ "set MemID=?, CoupTypeNo=?, CoupStatus=?, CoupExpDate=?, CoupGetDate=? "
		+ "where CoupNo=?";
//	-- 更改 會員擁有的優惠券 的 使用狀態
	private static final String UpdateStatus=
		"update memcoupon "
		+ "set CoupStatus=? "
		+ "where CoupNo=?";
//	-- 找出 某張優惠券的資訊	
	private static final String GetOne=
			"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
			+ "from memcoupon "
			+ "where CoupNo=?";
	
//	-- 找出 某個會員 擁有的所有優惠券
	private static final String FindMemCouponByMemID=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? "
		+ "order by CoupNo";
		
//	-- 找出 某個會員 已使用(未使用、過期的)的所有優惠券
	private static final String FindMemCouponByStatus=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupStatus=? "
		+ "order by CoupNo";
	
//	-- 找出 某個會員 擁有的某種優惠券(x)
	private static final String FindMemCouponByCoupTypeNo=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "where MemID=? and CoupTypeNo=? "
		+ "order by CoupNo";
	
	
//	-- 找出 所有會員 擁有的優惠券
	private static final String GetAll=
		"select CoupNo, MemID, CoupTypeNo, CoupStatus, CoupExpDate, CoupGetDate "
		+ "from memcoupon "
		+ "order by MemID";
	
	@Override
	public void insert(MemCouponVO memCouponVO) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		java.util.Date date1 = new java.util.Date();//現在時間
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//進行時間轉換
		String formatStr = sdf2.format(date1);
		
		session.persist(memCouponVO);
		memCouponVO.setCoupExpDate(java.sql.Timestamp.valueOf(formatStr));
		memCouponVO.setCoupGetDate(java.sql.Timestamp.valueOf(formatStr));
		memCouponVO.setCoupStatus(0);
		
	}	
		

	@Override //(x)
	public void update(MemCouponVO memCouponVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Update)){
			
			ps.setInt(1, memCouponVO.getMemID());
			ps.setInt(2, memCouponVO.getCoupTypeNo());
			ps.setInt(3, memCouponVO.getCoupStatus());
			ps.setTimestamp(4, memCouponVO.getCoupExpDate());
			ps.setTimestamp(5, memCouponVO.getCoupGetDate());
			ps.setInt(6, memCouponVO.getCoupNo());
	
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	-- 更改 會員擁有的優惠券 的 使用狀態(給結帳用，未使用改成已使用)
	@Override
	public void updateStatus1(Integer coupNo, Integer coupStatus) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		NativeQuery<?>nativeQuery = session.createSQLQuery("update memcoupon set "
			+ " CoupStatus=:coupStatus  where CoupNo= :coupNo")
			.setParameter("coupStatus", coupStatus)
			.setParameter("coupNo", coupNo);
		nativeQuery.executeUpdate();
		
	}
//	-- 更改 會員擁有的優惠券 的 使用狀態 (給排程器偵測用)
	@Override
	public void updateStatusRoutine(Integer coupNo, Integer coupStatus) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		NativeQuery<?>nativeQuery = session.createSQLQuery("update memcoupon set "
			+ " CoupStatus=:coupStatus  where CoupNo= :coupNo")
			.setParameter("coupStatus", coupStatus)
			.setParameter("coupNo", coupNo);
		nativeQuery.executeUpdate();
	}

	
	
	@Override
	public MemCouponVO getOne(Integer coupNo) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		MemCouponVO memCouponVO = session.get(MemCouponVO.class, coupNo);
		return memCouponVO;
	}

	
	
	@Override
	public List<MemCouponVO> findMemCouponByMemID(Integer memID) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		NativeQuery<MemCouponVO> nativeQuery = session.createSQLQuery("select * from memcoupon "
				+ "where memID = :memID order by CoupNo")
				.setParameter("memID", memID)
				.addEntity(MemCouponVO.class);
				List<MemCouponVO> list = nativeQuery.list();
				return list;
	}


	@Override
	public List<MemCouponVO> findMemCouponByStatus(Integer memID, Integer coupStatus) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		NativeQuery<MemCouponVO> nativeQuery = session.createSQLQuery("select * from memcoupon "
				+ "where MemID= :memID and CoupStatus= :coupStatus order by CoupNo ")
				.setParameter("memID", memID)
				.setParameter("coupStatus", coupStatus)
				.addEntity(MemCouponVO.class);
				List<MemCouponVO> list = nativeQuery.list();
				return list;
	}

	@Override //(x)
	public List<MemCouponVO> findMemCouponByCoupTypeNo(Integer memID, Integer coupTypeNo) {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindMemCouponByCoupTypeNo)){
			ps.setInt(1, memID);
			ps.setInt(2, coupTypeNo);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override //(x)
	public List<MemCouponVO> getAll() {
		List<MemCouponVO> list = new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GetAll)){
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				memCouponVO = new MemCouponVO();
				memCouponVO.setCoupNo(rs.getInt("CoupNo"));
				memCouponVO.setMemID(rs.getInt("MemID"));
				memCouponVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				memCouponVO.setCoupStatus(rs.getInt("CoupStatus"));
				memCouponVO.setCoupExpDate(rs.getTimestamp("CoupExpDate"));
				memCouponVO.setCoupGetDate(rs.getTimestamp("CoupGetDate"));
				list.add(memCouponVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	


}
