package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.coupontype.model.CouponTypeVO;
import com.orderdetail.model.OrderDetailJDBCDAO;
import com.orderdetail.model.OrderDetailVO;

import util.HibernateUtil;

@Repository
public class OrderListJDBCDAOH implements OrderListDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";
	
	@PersistenceContext
	private Session session;

//	-- 新增訂單資料-使用優惠券
	private static final String  Insert= 
		"insert into orderlist(MemID, CoupNo, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick)"
		+ "values"
		+ "( ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?)";
//	-- 新增訂單資料-沒使用優惠券
    private static final String  InsertNoCoupon= 
    	"insert into orderlist(MemID, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick)"
    	+ "values"
    	+ "( ?, ?, ?, ?, ?, now(), ?, ?, ?, ?)";
//  -- 更改訂單內容		
	private static final String  Update= 
		"update orderlist "
		+ "set OrdStatus=?, RecName=?, RecAddress=?, RecPhone=?, OrdPick=? "
		+ "where OrdNo=?";
//	-- 找出某一筆訂單的所有資料
	private static final String  FindOneOrder= 
		"select OrdNo, MemID, CoupNo, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick "
		+ "from orderlist "
		+ "where OrdNo=?";
//  -- 找出某位會員的所有訂單
	private static final String  FindOrderByMemID= 
			"select OrdNo, MemID, CoupNo, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick "
					+ "from orderlist "
					+ "where MemID=? "
					+ "order by OrdNo";
//	-- 找出某種出貨狀態的訂單
	private static final String  FindOrderByStatus= 
		"select OrdNo, MemID, CoupNo, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick "
		+ "from orderlist "
		+ "where OrdStatus=? "
		+ "order by OrdNo";
//	-- 找出所有訂單
	private static final String  GetAll= 
		"SELECT OrdNo, MemID, CoupNo, OrdOriPrice, OrdLastPrice, OrdFee, OrdStatus, OrdCreate, RecName, RecAddress, RecPhone, OrdPick "
		+ "FROM orderlist "
		+ "order by OrdNo";
	
	
	
	
	@Override //(x)
	public void insert(OrderListVO orderListVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Insert)) {
			
			ps.setInt(1, orderListVO.getMemID());
			ps.setInt(2, orderListVO.getCoupNo());
			ps.setDouble(3, orderListVO.getOrdOriPrice());
			ps.setDouble(4, orderListVO.getOrdLastPrice());
			ps.setInt(5, orderListVO.getOrdFee());
			ps.setInt(6, orderListVO.getOrdStatus());
			ps.setString(7, orderListVO.getRecName());
			ps.setString(8, orderListVO.getRecAddress());
			ps.setString(9, orderListVO.getRecPhone());
			ps.setInt(10, orderListVO.getOrdPick());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	@Override //(x)
	public void insertNoCoupon(OrderListVO orderListVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(InsertNoCoupon)) {
			
			ps.setInt(1, orderListVO.getMemID());
			ps.setDouble(2, orderListVO.getOrdOriPrice());
			ps.setDouble(3, orderListVO.getOrdLastPrice());
			ps.setInt(4, orderListVO.getOrdFee());
			ps.setInt(5, orderListVO.getOrdStatus());
			ps.setString(6, orderListVO.getRecName());
			ps.setString(7, orderListVO.getRecAddress());
			ps.setString(8, orderListVO.getRecPhone());
			ps.setInt(9, orderListVO.getOrdPick());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override 
	public Integer insertWithOrderDetails(OrderListVO orderListVO) {
		
		Integer auto = null;
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		java.util.Date date1 = new java.util.Date();//現在時間
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//進行時間轉換
		String formatStr = sdf2.format(date1);
		
		orderListVO.setOrdCreate(java.sql.Timestamp.valueOf(formatStr));
		auto = (Integer)session.save(orderListVO);
		return auto;
	}

	
	@Override 
	public Integer insertWithOrderDetailsNoCoupon(OrderListVO orderListVO) {
		Integer auto = null;
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		java.util.Date date1 = new java.util.Date();//現在時間
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//進行時間轉換
		String formatStr = sdf2.format(date1);
		
		orderListVO.setOrdCreate(java.sql.Timestamp.valueOf(formatStr));
		auto = (Integer)session.save(orderListVO);
		return auto;
	}

	@Override
	public void update(OrderListVO orderListVO) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
	
		OrderListVO oOrderListVO = session.get(OrderListVO.class, orderListVO.getOrdNo());
			//修改項目:OrdStatus RecName RecAddress RecPhone OrdPick
			
			final Integer ordStatus = orderListVO.getOrdStatus();
			if(ordStatus != null && !Objects.equals(ordStatus, "") ) {
				oOrderListVO.setOrdStatus(ordStatus);
			}
			final String  recName = orderListVO.getRecName();
			if(recName != null && !Objects.equals(recName,"")) {
				oOrderListVO.setRecName(recName);
			}
			final String  recAddress = orderListVO.getRecAddress();
			if(recAddress != null && !Objects.equals(recAddress,"")) {
				oOrderListVO.setRecAddress(recAddress);
			}
			final String  recPhone = orderListVO.getRecPhone();
			if(recPhone != null && !Objects.equals(recPhone,"")) {
				oOrderListVO.setRecPhone(recPhone);
			}
			
			final Integer ordPick = orderListVO.getOrdPick();
			if(ordPick != null && !Objects.equals(ordPick, "") ) {
				oOrderListVO.setOrdPick(ordPick);
			}
		
		
	}


	@Override
	public OrderListVO findOneOrder(Integer ordNo) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		OrderListVO orderListVO = session.get(OrderListVO.class, ordNo);
		return orderListVO;
	}

	@Override
	public List<OrderListVO> findOrderByStatus(Integer ordStatus) {
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindOrderByStatus)){
			ps.setInt(1, ordStatus);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orderListVO = new OrderListVO();
				orderListVO.setOrdNo(rs.getInt("OrdNo"));
				orderListVO.setMemID(rs.getInt("MemID"));
				orderListVO.setCoupNo(rs.getInt("CoupNo"));
				orderListVO.setOrdOriPrice(rs.getDouble("OrdOriPrice"));
				orderListVO.setOrdLastPrice(rs.getDouble("OrdLastPrice"));
				orderListVO.setOrdFee(rs.getInt("OrdFee"));
				orderListVO.setOrdCreate(rs.getTimestamp("OrdCreate"));
				orderListVO.setOrdStatus(rs.getInt("OrdStatus"));
				orderListVO.setRecName(rs.getString("RecName"));
				orderListVO.setRecAddress(rs.getString("RecAddress"));
				orderListVO.setRecPhone(rs.getString("RecPhone"));
				orderListVO.setOrdPick(rs.getInt("OrdPick"));
				list.add(orderListVO);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override  //不是getone，不是pk
	public  List<OrderListVO> findOrderByMemID(Integer memID) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		NativeQuery<OrderListVO> nativeQuery = session.createSQLQuery("select * from orderlist "
				+ "where memID = :memID order by OrdNo")
				.setParameter("memID", memID)
				.addEntity(OrderListVO.class);
		List<OrderListVO> list = nativeQuery.list();
		return list;
	}


	@Override
	public List<OrderListVO> getAll() {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		Query<OrderListVO> query = session.createQuery("FROM OrderListVO", OrderListVO.class);
		List<OrderListVO> list = query.list();
		return list;
	}
	
	@Override
	public List<OrderListVO> getAll(Map<String, String[]> map) {
		
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		OrderListVO orderListVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from orderlist "
			          + jdbcUtil_CompositeQuery_orderlist.get_WhereCondition(map)
			          + "order by OrdNo";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					orderListVO = new OrderListVO();
					orderListVO.setOrdNo(rs.getInt("OrdNo"));
					orderListVO.setMemID(rs.getInt("MemID"));
					orderListVO.setCoupNo(rs.getInt("CoupNo"));
					orderListVO.setOrdOriPrice(rs.getDouble("OrdOriPrice"));
					orderListVO.setOrdLastPrice(rs.getDouble("OrdLastPrice"));
					orderListVO.setOrdFee(rs.getInt("OrdFee"));
					orderListVO.setOrdCreate(rs.getTimestamp("OrdCreate"));
					orderListVO.setOrdStatus(rs.getInt("OrdStatus"));
					orderListVO.setRecName(rs.getString("RecName"));
					orderListVO.setRecAddress(rs.getString("RecAddress"));
					orderListVO.setRecPhone(rs.getString("RecPhone"));
					orderListVO.setOrdPick(rs.getInt("OrdPick"));
					list.add(orderListVO);
				}
		
			
				// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


//public static void main(String[] args) {
//	
//	OrderListJDBCDAOH orderListJDBCDAOH = new OrderListJDBCDAOH();
//	List<OrderListVO> list = orderListJDBCDAOH.findOrderByMemID(11001);
//	for (OrderListVO orderListVO : list) {
//		orderListVO.getRecName();
//	}
//};



}
