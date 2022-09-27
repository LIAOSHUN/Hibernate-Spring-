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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cart.model.CartService;
import com.orderdetail.model.OrderDetailJDBCDAO;
import com.orderdetail.model.OrderDetailVO;


public class OrderListDAO implements OrderListDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

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
	
	
	
	
	@Override
	public void insert(OrderListVO orderListVO) {
		try(Connection con = ds.getConnection();
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
		try(Connection con = ds.getConnection();
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
	
	
	public void insertWithOrderDetails(Connection con, OrderListVO orderListVO, List<OrderDetailVO> list){
		PreparedStatement pstmt = null;

		try {
			
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

			
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + nextOrdNo + "時,共有" + list.size()
					+ "個訂單明細同時被新增");
			
			
	}catch (SQLException se) {
		System.err.println("rolled back-由-orderlist");
		throw new RuntimeException("rollback error occured. "
		+ se.getMessage());
		}
	}
	
	public void insertWithOrderDetailsNoCoupon(Connection con, OrderListVO orderListVO, List<OrderDetailVO> list) {
		PreparedStatement pstmt = null;

		try {
			
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
	
			
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + nextOrdNo + "時,共有" + list.size()
					+ "個訂單明細同時被新增");
	} catch (SQLException se) {
		System.err.println("rolled back-由-orderlist");
		throw new RuntimeException("rollback error occured. "
		+ se.getMessage());
		}
	}

	@Override
	public void update(OrderListVO orderListVO) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Update)) {
			
			ps.setInt(1, orderListVO.getOrdStatus());
			ps.setString(2, orderListVO.getRecName());
			ps.setString(3, orderListVO.getRecAddress());
			ps.setString(4, orderListVO.getRecPhone());
			ps.setInt(5, orderListVO.getOrdPick());
			ps.setInt(6, orderListVO.getOrdNo());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrderListVO findOneOrder(Integer ordNo) {
		OrderListVO orderListVO = null;
		try (Connection con = ds.getConnection();
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
		
		try (Connection con = ds.getConnection();
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
	public List<OrderListVO> findOrderByMemID(Integer memID) {
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		
		try (Connection con = ds.getConnection();
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
	public List<OrderListVO> getAll() {
		OrderListVO orderListVO = null;
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		
		try (Connection con = ds.getConnection();
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
	public List<OrderListVO> getAll(Map<String, String[]> map) {
		
		List<OrderListVO> list = new ArrayList<OrderListVO>();
		OrderListVO orderListVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
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





	@Override
	public Integer insertWithOrderDetailsNoCoupon(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer insertWithOrderDetails(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return null;
	}






}
