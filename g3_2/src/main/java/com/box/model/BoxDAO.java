package com.box.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BoxDAO implements Box_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("連線池錯誤");
		}
	}
	
	//管理員可以新增包廂
	private static final String INSERT_STMT = 
			"insert into box (StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxBkStart, BoxBkEnd, BoxImg) "
			+ "value (?, ?, ?, ?, ?, ?, ?, ?)";
	
	//管理員可以修改包廂內容
	private static final String UPDATE_STMT = 
			"update box set BoxTypeID = ?, StoreID = ?, BoxCapcity = ?, BoxPrice = ?, BoxDescription = ?, BoxImg = ?, BoxBkStart = ?, BoxBkEnd = ? "
			+ "where BoxID = ?";
	
	//管理員可以刪除包廂
	private static final String DELETE_STMT = 
			"delete from box where BoxID = ? ";
	
	//管理員可以指定門市包廂
	private static final String GET_STORE_BOXES = 
			"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxBkStart, BoxBkEnd, BoxImg from box "
			+ "where StoreID = ?";
	
	private static final String GET_ONE_BOX = 
			"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxBkStart, BoxBkEnd, BoxImg from box "
			+ "where boxID = ?";
	
	//管理員可以查詢所有包廂
	private static final String GET_ALL_STMT = 
			"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxBkStart, BoxBkEnd "
			+ "from box";
	
	private static final String GET_ALL = 
			"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxBkStart, BoxBkEnd "
			+ "from box";
	
//	public static byte[] getImg(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

	@Override
	public void insert(BoxVO boxVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			
			ps.setInt(1, boxVO.getStoreID());
			ps.setInt(2, boxVO.getBoxTypeID());
			ps.setInt(3, boxVO.getBoxCapcity());
			ps.setInt(4,boxVO.getBoxPrice());
			ps.setString(5, boxVO.getBoxDescription());
			ps.setString(6, boxVO.getBoxBkStart());
			ps.setString(7, boxVO.getBoxBkEnd());
			ps.setBytes(8, boxVO.getBoxImg());
			
			ps.execute();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public void update(BoxVO boxVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);

			ps.setInt(1, boxVO.getBoxTypeID());
			ps.setInt(2, boxVO.getStoreID());
			ps.setInt(3, boxVO.getBoxCapcity());
			ps.setInt(4,boxVO.getBoxPrice());
			ps.setString(5, boxVO.getBoxDescription());
			ps.setBytes(6, boxVO.getBoxImg());
			ps.setString(7, boxVO.getBoxBkStart());
			ps.setString(8, boxVO.getBoxBkEnd());
			ps.setInt(9, boxVO.getBoxID());
			
			ps.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public void delete(Integer boxVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE_STMT);
			
			ps.setInt(1, boxVO);
			
			ps.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public List<BoxVO> findPK(Integer boxVO) {
		List<BoxVO> list = new ArrayList<BoxVO>();
		BoxVO bvo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_STORE_BOXES);
			
			
			ps.setInt(1, boxVO);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				bvo = new BoxVO();
				bvo.setBoxID(rs.getInt("BoxID"));
				bvo.setStoreID(rs.getInt("StoreID"));
				bvo.setBoxTypeID(rs.getInt("BoxTypeID"));
				bvo.setBoxCapcity(rs.getInt("BoxCapcity"));
				bvo.setBoxPrice(rs.getInt("BoxPrice"));
				bvo.setBoxDescription(rs.getString("BoxDescription"));
				
//				bvo.setBoxBkStart(rs.getString("BoxBkStart"));
				int start = Integer.valueOf(rs.getString("BoxBkStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String boxBkStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bvo.setBoxBkStart(boxBkStart);
				
//				bvo.setBoxBkEnd(rs.getString("BoxBkEnd"));
				int end = Integer.valueOf(rs.getString("BoxBkEnd").trim());
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String boxBkEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bvo.setBoxBkEnd(boxBkEnd);
				bvo.setBoxImg(rs.getBytes("BoxImg"));
				list.add(bvo);
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public List<BoxVO> getAll() {
		List<BoxVO> list = new ArrayList<BoxVO>();
		BoxVO bvo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream input = null;
		byte[] boxImg;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				bvo = new BoxVO();
				bvo.setBoxID(rs.getInt("BoxID"));
				bvo.setStoreID(rs.getInt("StoreID"));
				bvo.setBoxTypeID(rs.getInt("BoxTypeID"));
				bvo.setBoxCapcity(rs.getInt("BoxCapcity"));
				bvo.setBoxPrice(rs.getInt("BoxPrice"));
				bvo.setBoxDescription(rs.getString("BoxDescription"));
//				bvo.setBoxBkStart(rs.getString("BoxBkStart"));
				int start = Integer.valueOf(rs.getString("BoxBkStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String boxBkStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bvo.setBoxBkStart(boxBkStart);
				
//				bvo.setBoxBkEnd(rs.getString("BoxBkEnd"));
				int end = Integer.valueOf(rs.getString("BoxBkEnd").trim());
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String boxBkEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bvo.setBoxBkEnd(boxBkEnd);
				
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("BoxImg"));
//				try {
//					System.out.println(in.available());
//					boxImg = new byte[in.available()];
//					in.read(boxImg);
//					bvo.setBoxImg(boxImg);
//					
//					in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				list.add(bvo);
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public BoxVO getOneBox(Integer boxID) {
		
		Connection con = null;
		PreparedStatement ps = null;
		BoxVO boxVO = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE_BOX);
			
			ps.setInt(1, boxID);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				boxVO = new BoxVO();
				boxVO.setBoxID(rs.getInt("BoxID"));
				boxVO.setStoreID(rs.getInt("StoreID"));
				boxVO.setBoxTypeID(rs.getInt("BoxTypeID"));
				boxVO.setBoxCapcity(rs.getInt("BoxCapcity"));
				boxVO.setBoxPrice(rs.getInt("BoxPrice"));
				boxVO.setBoxDescription(rs.getString("BoxDescription"));
				boxVO.setBoxBkStart(rs.getString("BoxBkStart"));
				boxVO.setBoxBkEnd(rs.getString("BoxBkEnd"));
				boxVO.setBoxImg(rs.getBytes("BoxImg"));
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return boxVO;
	}
	
	@Override
	public List<BoxVO> getAllInfo() {
		List<BoxVO> list = new ArrayList<BoxVO>();
		BoxVO bvo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				bvo = new BoxVO();
				bvo.setBoxID(rs.getInt("BoxID"));
				bvo.setStoreID(rs.getInt("StoreID"));
				bvo.setBoxTypeID(rs.getInt("BoxTypeID"));
				bvo.setBoxCapcity(rs.getInt("BoxCapcity"));
				bvo.setBoxPrice(rs.getInt("BoxPrice"));
				bvo.setBoxDescription(rs.getString("BoxDescription"));
				bvo.setBoxBkStart(rs.getString("BoxBkStart"));
				bvo.setBoxBkEnd(rs.getString("BoxBkEnd"));
				list.add(bvo);
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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


}
