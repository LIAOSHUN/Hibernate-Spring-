package com.actimg.model;

import static common_35.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ActImgJDBCDAO implements ActImgDAO_interface{

	private static final String INSERT_STMT = 
		"insert into actimg (ActID, ActImgFile) "
		+ "values (?, ?)";
	private static final String GET_ALL_STMT = 
		"select ActImgID, ActID, ActImgFile "
		+ "from actimg order by ActID";
	private static final String GET_ONE_STMT = 
		"select ActImgID, ActID, ActImgFile "
		+ "from actimg where ActImgID = ?";
	private static final String DELETE = 
		"delete from actimg where ActImgID = ?";
	private static final String UPDATE = 
		"update actimg set ActID=?, ActImgFile=? where ActImgID = ?";
	
	@Override
	public void insert(ActImgVO actImgVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
				Class.forName(driver);
				
				pstmt.setInt(1, actImgVO.getActID());
				pstmt.setBytes(2, actImgVO.getActImgFile());
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}		
	}

	@Override
	public void update(ActImgVO actImgVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
				Class.forName(driver);

				pstmt.setInt(1, actImgVO.getActID());
				pstmt.setBytes(2, actImgVO.getActImgFile());
				pstmt.setInt(3, actImgVO.getActImgID());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}				
	}

	@Override
	public void delete(Integer actImgID) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {
				Class.forName(driver);

				pstmt.setInt(1, actImgID);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}		
	}

	@Override
	public ActImgVO findByPrimaryKey(Integer actImgID) {
		ActImgVO actImgVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
				) {
				Class.forName(driver);

			pstmt.setInt(1, actImgID);
			ResultSet rs = pstmt.executeQuery();		
			
			while (rs.next()) {
				// actImgVo 也稱為 Domain objects
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return actImgVO;
	}

	@Override
	public List<ActImgVO> getAll() {
		List<ActImgVO> list = new ArrayList<ActImgVO>();
		ActImgVO actImgVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				) {
				Class.forName(driver);
				ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));
				list.add(actImgVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}
	
	
	
	@Override
	public void insertfromAct(ActImgVO actImgVO, Connection con) {
		PreparedStatement pstmt = null;
		try  {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, actImgVO.getActID());
			pstmt.setBytes(2, actImgVO.getActImgFile());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-actImg");
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
		}	
	}

	public static void main(String[] args) {

		ActImgJDBCDAO dao = new ActImgJDBCDAO();

		// 新增
		ActImgVO actImgVO1 = new ActImgVO();
		actImgVO1.setActID(61001);
		actImgVO1.setActImgFile(null);
		dao.insert(actImgVO1);

		// 修改
		ActImgVO actImgVO2 = new ActImgVO();
		actImgVO2.setActID(61002);
		actImgVO2.setActImgFile(null);
		actImgVO2.setActImgID(62001);
		dao.update(actImgVO2);
		
		// 刪除
		dao.delete(62001);

		// 查詢 Primary Key
		ActImgVO actImgVO3 = dao.findByPrimaryKey(62001);
		System.out.print(actImgVO3.getActImgID() + ", ");
		System.out.print(actImgVO3.getActID() + ", ");
		System.out.println(actImgVO3.getActImgFile());
		System.out.println("---------------------");

		// 查詢
		List<ActImgVO> list = dao.getAll();
		for (ActImgVO aActImg : list) {
			System.out.print(aActImg.getActImgID() + ", ");
			System.out.print(aActImg.getActID() + ", ");
			System.out.print(aActImg.getActImgFile());
			System.out.println();
		}
	}
}
