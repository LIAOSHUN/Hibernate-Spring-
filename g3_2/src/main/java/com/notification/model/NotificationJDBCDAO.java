package com.notification.model;

import static com.common_27.Common_27.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationJDBCDAO implements NotificationDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO notification (MemID,NoticeDescription,NoticeTime,NoticeRead) VALUES (?, ?, now(), ?)";
	private static final String GET_ALL_STMT = "SELECT NoticeID,MemID,NoticeDescription,NoticeTime,NoticeRead FROM notification order by NoticeID";
	private static final String GET_ONE_STMT = "SELECT NoticeID,MemID,NoticeDescription,NoticeTime,NoticeRead FROM notification where NoticeID = ?";
	private static final String DELETE = "DELETE FROM notification where NoticeID = ?";
	private static final String UPDATE = "UPDATE notification set MemID=?,NoticeDescription=?,NoticeTime=?,NoticeRead=? where NoticeID = ?";

	@Override
	public void insert(NotificationVO notificationVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, notificationVO.getMemID());
			pstmt.setString(2, notificationVO.getNoticeDescription());
			pstmt.setInt(3, notificationVO.getNoticeRead());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(NotificationVO notificationVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, notificationVO.getMemID());
			pstmt.setString(2, notificationVO.getNoticeDescription());
			pstmt.setTimestamp(3, notificationVO.getNoticeTime());
			pstmt.setInt(4, notificationVO.getNoticeRead());
			pstmt.setInt(5, notificationVO.getNoticeID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer noticeID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, noticeID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public NotificationVO findByPrimaryKey(Integer noticeID) {
		// TODO Auto-generated method stub

		NotificationVO notificationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, noticeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				notificationVO = new NotificationVO();
				notificationVO.setMemID(rs.getInt("MemID"));
				notificationVO.setNoticeDescription(rs.getNString("NoticeDescription"));
				notificationVO.setNoticeTime(rs.getTimestamp("NoticeTime"));
				notificationVO.setNoticeRead(rs.getInt("NoticeRead"));

	}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return notificationVO;
	}
			
	@Override
	public List<NotificationVO> getAll() {
		// TODO Auto-generated method stub
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				notificationVO = new NotificationVO();
				notificationVO.setNoticeID(rs.getInt("NoticeID"));
				notificationVO.setMemID(rs.getInt("MemID"));
				notificationVO.setNoticeDescription(rs.getNString("NoticeDescription"));
				notificationVO.setNoticeTime(rs.getTimestamp("NoticeTime"));
				notificationVO.setNoticeRead(rs.getInt("NoticeRead"));

			
				list.add(notificationVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

	public static void main(String[] args) {
//
		NotificationJDBCDAO dao = new NotificationJDBCDAO();
//
//		// 新增
//		NotificationVO notificationVO1 = new NotificationVO();
//		notificationVO1.setMemID(11003);
//		notificationVO1.setNoticeDescription("Tibame");
//		notificationVO1.setNoticeRead(1);
//	
//		dao.insert(notificationVO1);
//
//
//		// 刪除
		dao.delete(14006);
//
//		// 查詢
//		NotificationVO notificationVO2 = dao.findByPrimaryKey(14004);
//		System.out.print(notificationVO2.getMemID() + ",");
//		System.out.print(notificationVO2.getNoticeDescription() + ",");
//		System.out.print(notificationVO2.getNoticeTime() + ",");
//		System.out.print(notificationVO2.getNoticeRead());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<NotificationVO> list = dao.getAll();
//		for (NotificationVO notificationVO3 : list) {
//		System.out.print(notificationVO3.getMemID() + ",");
//		System.out.print(notificationVO3.getNoticeDescription() + ",");
//		System.out.print(notificationVO3.getNoticeTime() + ",");
//		System.out.print(notificationVO3.getNoticeRead());
//			System.out.println();
//		}
//	
	}
}
