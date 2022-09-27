package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.orderdetail.model.OrderDetailJDBCDAO;
import com.orderdetail.model.OrderDetailVO;

public class OrderListJDBCDAO implements OrderListDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

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
		+ "set CoupNo=?, OrdOriPrice=?, OrdLastPrice=?, OrdFee=?, OrdStatus=?, OrdCreate=now(), RecName=?, RecAddress=?, RecPhone=?, OrdPick=? "
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
		+ "order by MemID";
	
	
	
	
	@Override
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
	

	@Override
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
	
	
	public void insertWithOrderDetails(OrderListVO orderListVO, List<OrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"OrdNo"};
			pstmt = con.prepareStatement(Insert , cols);			
			pstmt.setInt(1, orderListVO.getMemID());
			pstmt.setInt(2, orderListVO.getCoupNo());
			pstmt.setDouble(3, orderListVO.getOrdOriPrice());
			pstmt.setDouble(4, orderListVO.getOrdLastPrice());
			pstmt.setInt(5, orderListVO.getOrdFee());
			pstmt.setInt(6, orderListVO.getOrdStatus());
			pstmt.setString(7, orderListVO.getRecName());
			pstmt.setString(8, orderListVO.getRecAddress());
			pstmt.setString(9, orderListVO.getRecPhone());
			pstmt.setInt(10, orderListVO.getOrdPick());

			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			Integer nextOrdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrdNo = rs.getInt(1);
				System.out.println("自增主鍵值= " + nextOrdNo +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrderDetailVO od : list) {
				od.setOrdNo(new Integer(nextOrdNo)) ;
				dao.insert2(od,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + nextOrdNo + "時,共有" + list.size()
					+ "個訂單明細同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderlist");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}
	
	public void insertWithOrderDetailsNoCoupon(OrderListVO orderListVO, List<OrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"OrdNo"};
			pstmt = con.prepareStatement(InsertNoCoupon , cols);			
			pstmt.setInt(1, orderListVO.getMemID());
			pstmt.setDouble(2, orderListVO.getOrdOriPrice());
			pstmt.setDouble(3, orderListVO.getOrdLastPrice());
			pstmt.setInt(4, orderListVO.getOrdFee());
			pstmt.setInt(5, orderListVO.getOrdStatus());
			pstmt.setString(6, orderListVO.getRecName());
			pstmt.setString(7, orderListVO.getRecAddress());
			pstmt.setString(8, orderListVO.getRecPhone());
			pstmt.setInt(9, orderListVO.getOrdPick());

			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			Integer nextOrdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrdNo = rs.getInt(1);
				System.out.println("自增主鍵值= " + nextOrdNo +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrderDetailVO od : list) {
				od.setOrdNo(new Integer(nextOrdNo)) ;
				dao.insert2(od,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + nextOrdNo + "時,共有" + list.size()
					+ "個訂單明細同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderlist");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public void update(OrderListVO orderListVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Update)) {
			
			ps.setInt(1, orderListVO.getCoupNo());
			ps.setDouble(2, orderListVO.getOrdOriPrice());
			ps.setDouble(3, orderListVO.getOrdLastPrice());
			ps.setInt(4, orderListVO.getOrdFee());
			ps.setInt(5, orderListVO.getOrdStatus());
			ps.setString(6, orderListVO.getRecName());
			ps.setString(7, orderListVO.getRecAddress());
			ps.setString(8, orderListVO.getRecPhone());
			ps.setInt(9, orderListVO.getOrdPick());
			ps.setInt(10, orderListVO.getOrdNo());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrderListVO findOneOrder(Integer ordNo) {
		OrderListVO orderListVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindOneOrder)){
			ps.setInt(1, ordNo);
			
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
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
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

	@Override
	public List<OrderListVO> getAll() {
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GetAll)){
			
			
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
	
	@Override
	public List<OrderListVO> findOrderByMemID(Integer memID) {
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindOrderByMemID)){
			ps.setInt(1, memID);
			
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
	
	
	
	
	
	
	
	
	@Override
	public List<OrderListVO> getAll(Map<String, String[]> map) {

		
		return null;
	}

	
	
	
	public static void main(String[] args) {
		OrderListJDBCDAO dao = new OrderListJDBCDAO();
		
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>() ;
		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
		orderDetailVO1.setPdID(21002);
		orderDetailVO1.setItemSales(4);
		orderDetailVO1.setPrice(180);
		orderDetailVO2.setPdID(21001);
		orderDetailVO2.setItemSales(5);
		orderDetailVO2.setPrice(180);
		list.add(orderDetailVO1);
		list.add(orderDetailVO2);
		
		
//		-- 新增訂單資料-使用優惠券
//		OrderListVO orderListVO1 = new OrderListVO();
//		
//		orderListVO1.setMemID(11001);
//		orderListVO1.setCoupNo(27002);
//		orderListVO1.setOrdOriPrice(180.0);
//		orderListVO1.setOrdLastPrice(160.0);
//		orderListVO1.setOrdFee(30);
//		orderListVO1.setOrdStatus(1);
//		orderListVO1.setRecName("王曉明");
//		orderListVO1.setRecAddress("台北市中山區");
//		orderListVO1.setRecPhone("01-22222");
//		orderListVO1.setOrdPick(1);
////		dao.insert(orderListVO1);
//		dao.insertWithOrderDetails(orderListVO1, list);
		
//		-- 新增訂單資料-沒使用優惠券
		OrderListVO orderListVO2 = new OrderListVO();
		
		orderListVO2.setMemID(11002);
		orderListVO2.setOrdOriPrice(200.0);
		orderListVO2.setOrdLastPrice(180.0);
		orderListVO2.setOrdFee(60);
		orderListVO2.setOrdStatus(3);
		orderListVO2.setRecName("曾可愛");
		orderListVO2.setRecAddress("舊金山");
		orderListVO2.setRecPhone("01-333");
		orderListVO2.setOrdPick(2);
//		dao.insertNoCoupon(orderListVO2);
		dao.insertWithOrderDetailsNoCoupon(orderListVO2, list);
		
//		-- 更改訂單內容		
//		OrderListVO orderListVO3 = new OrderListVO();
//		orderListVO3.setCoupNo(27003);
//		orderListVO3.setOrdOriPrice(200.6);
//		orderListVO3.setOrdLastPrice(180.0);
//		orderListVO3.setOrdFee(60);
//		orderListVO3.setOrdStatus(3);
//		orderListVO3.setRecName("曾饅頭");
//		orderListVO3.setRecAddress("舊金山");
//		orderListVO3.setRecPhone("01-333");
//		orderListVO3.setOrdPick(2);
//		orderListVO3.setOrdNo(22003);
//		dao.update(orderListVO3);
		
//		-- 找出某一筆訂單的所有資料
//		OrderListVO orderListVO4 = dao.findOneOrder(22001);
//		System.out.println(orderListVO4.getOrdNo() + ",");
//		System.out.println(orderListVO4.getMemID() + ",");
//		System.out.println(orderListVO4.getCoupNo() + ",");
//		System.out.println(orderListVO4.getOrdOriPrice() + ",");
//		System.out.println(orderListVO4.getOrdLastPrice() + ",");
//		System.out.println(orderListVO4.getOrdFee() + ",");
//		System.out.println(orderListVO4.getOrdStatus() + ",");
//		System.out.println(orderListVO4.getOrdCreate() + ",");
//		System.out.println(orderListVO4.getRecName() + ",");
//		System.out.println(orderListVO4.getRecAddress() + ",");
//		System.out.println(orderListVO4.getRecPhone() + ",");
//		System.out.println(orderListVO4.getOrdPick() );
//		System.out.println();
		
//		-- 找出某種出貨狀態的訂單
//		List<OrderListVO> list = dao.findOrderByStatus(0);
//		for(OrderListVO od : list) {
//			System.out.println(od.getOrdNo() + ",");
//			System.out.println(od.getMemID() + ",");
//			System.out.println(od.getCoupNo() + ",");
//			System.out.println(od.getOrdOriPrice() + ",");
//			System.out.println(od.getOrdLastPrice() + ",");
//			System.out.println(od.getOrdFee() + ",");
//			System.out.println(od.getOrdStatus() + ",");
//			System.out.println(od.getOrdCreate() + ",");
//			System.out.println(od.getRecName() + ",");
//			System.out.println(od.getRecAddress() + ",");
//			System.out.println(od.getRecPhone() + ",");
//			System.out.println(od.getOrdPick() );
//			System.out.println();
//		}
		
//		-- 找出所有訂單
//		List<OrderListVO> list2 = dao.getAll();
//		for(OrderListVO od : list2) {
//			System.out.println(od.getOrdNo() + ",");
//			System.out.println(od.getMemID() + ",");
//			System.out.println(od.getCoupNo() + ",");
//			System.out.println(od.getOrdOriPrice() + ",");
//			System.out.println(od.getOrdLastPrice() + ",");
//			System.out.println(od.getOrdFee() + ",");
//			System.out.println(od.getOrdStatus() + ",");
//			System.out.println(od.getOrdCreate() + ",");
//			System.out.println(od.getRecName() + ",");
//			System.out.println(od.getRecAddress() + ",");
//			System.out.println(od.getRecPhone() + ",");
//			System.out.println(od.getOrdPick() );
//			System.out.println();
//		}
		
		
	}


	@Override
	public Integer insertWithOrderDetails(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer insertWithOrderDetailsNoCoupon(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return null;
	}



















}
