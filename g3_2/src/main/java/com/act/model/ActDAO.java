package com.act.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.actimg.model.ActImgJDBCDAO;
import com.actimg.model.ActImgVO;

public class ActDAO implements ActDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into activity (StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
			+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus) " 
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select ActID, StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
			+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus " 
			+ "from activity order by ActID";
	private static final String GET_ONE_STMT = "select ActID, StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
			+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus " 
			+ "from activity where ActID = ?";
	private static final String UPDATE = "update activity set StoreID=?, ActTitle=?, ActDescription=?, ActTimeStart=?, ActTimeEnd=?, "
			+ "ActDate=?, RegisMax=?, ActFee=?, ActRegistration=?, ActStatus=? where ActID = ?";
	private static final String GET_IMGS_BYACTID_STMT = "SELECT ActImgID, ActID, ActImgFile "
			+ "FROM actimg where ActID = ? order by ActImgID ";

	@Override
	public void insert(ActVO actVO) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, actVO.getStoreID());
			pstmt.setString(2, actVO.getActTitle());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setObject(4, actVO.getActTimeStart());
			pstmt.setObject(5, actVO.getActTimeEnd());
			pstmt.setObject(6, actVO.getActDate());
			pstmt.setInt(7, actVO.getRegisMax());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getActRegistration());
			pstmt.setInt(10, actVO.getActStatus());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public void update(ActVO actVO) {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

			pstmt.setInt(1, actVO.getStoreID());
			pstmt.setString(2, actVO.getActTitle());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setObject(4, actVO.getActTimeStart());
			pstmt.setObject(5, actVO.getActTimeEnd());
			pstmt.setObject(6, actVO.getActDate());
			pstmt.setInt(7, actVO.getRegisMax());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getActRegistration());
			pstmt.setInt(10, actVO.getActStatus());
			pstmt.setInt(11, actVO.getActID());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public ActVO findByPrimaryKey(Integer actID) {
		ActVO actVO = null;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);) {
			pstmt.setInt(1, actID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVo 也稱為 Domain objects
				actVO = new ActVO();
				actVO.setActID(rs.getInt("actID"));
				actVO.setStoreID(rs.getInt("storeID"));
				actVO.setActTitle(rs.getString("actTitle"));
				actVO.setActDescription(rs.getString("actDescription"));
				actVO.setActTimeStart(rs.getObject("actTimeStart", LocalDateTime.class));
				actVO.setActTimeEnd(rs.getObject("actTimeEnd", LocalDateTime.class));
				actVO.setActDate(rs.getObject("actDate", LocalDateTime.class));
				actVO.setRegisMax(rs.getInt("regisMax"));
				actVO.setActFee(rs.getInt("actFee"));
				actVO.setActRegistration(rs.getInt("actRegistration"));
				actVO.setActStatus(rs.getInt("actStatus"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return actVO;
	}

	@Override
	public List<ActVO> getAll() {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 也稱為 Domain objects
				actVO = new ActVO();
				actVO.setActID(rs.getInt("actID"));
				actVO.setStoreID(rs.getInt("storeID"));
				actVO.setActTitle(rs.getString("actTitle"));
				actVO.setActDescription(rs.getString("actDescription"));
				actVO.setActTimeStart(rs.getObject("actTimeStart", LocalDateTime.class));
				actVO.setActTimeEnd(rs.getObject("actTimeEnd", LocalDateTime.class));
				actVO.setActDate(rs.getObject("actDate", LocalDateTime.class));
				actVO.setRegisMax(rs.getInt("regisMax"));
				actVO.setActFee(rs.getInt("actFee"));
				actVO.setActRegistration(rs.getInt("actRegistration"));
				actVO.setActStatus(rs.getInt("actStatus"));
				list.add(actVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

		return list;
	}

	@Override
	public Set<ActImgVO> getImgsByAct(Integer actID) {
		Set<ActImgVO> set = new HashSet<ActImgVO>();
		ActImgVO actImgVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_IMGS_BYACTID_STMT);) {

			pstmt.setInt(1, actID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));
				set.add(actImgVO);
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return set;
	}

	@Override
	public void insertWithActImgs(ActVO actVO, List<ActImgVO> imglist) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 將auto commit解除
			con.setAutoCommit(false);

			String cols[] = { "ActID" };
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, actVO.getStoreID());
			pstmt.setString(2, actVO.getActTitle());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setObject(4, actVO.getActTimeStart());
			pstmt.setObject(5, actVO.getActTimeEnd());
			pstmt.setObject(6, actVO.getActDate());
			pstmt.setInt(7, actVO.getRegisMax());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getActRegistration());
			pstmt.setInt(10, actVO.getActStatus());
			pstmt.executeUpdate();

			// 取得自增主鍵的值
			String nextActID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextActID = rs.getString(1);
				System.out.println("自增主鍵值= " + nextActID + "(剛新增成功的活動編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 再新增照片
			ActImgJDBCDAO dao = new ActImgJDBCDAO();
			for (ActImgVO aActImg : imglist) {
				aActImg.setActID(new Integer(nextActID));
				dao.insertfromAct(aActImg, con);
			}

			// commit與交還連線
			con.commit();
			con.setAutoCommit(true); // 從連線池取出的連線，要將AutoCommit改回預設值的true才將連線還給連線池

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-act");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

}
