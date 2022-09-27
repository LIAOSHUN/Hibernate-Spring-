package com.boxtype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoxTypeDAO implements BoxType_interface {

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

	// 管理員可以新增包廂類型
	private static final String INSERT_STMT = "insert into boxtype (boxname) " + "value (?);";

	// 管理員可以刪除資料列
	private static final String DELETE_STMT = "delete from boxtype " + "where boxtypeid = ?";

	// 管理員可以修改包廂名稱
	private static final String UPDATE_STMT = "update boxtype set boxname = ? where boxtypeid = ?";

	// 管理員可以查詢指定id資料
	private static final String GET_ONE_STMT = "select boxtypeid, boxname " + "from boxtype " + "where boxtypeid = ?";

	// 管理員可以查詢所有包廂類型
	private static final String GET_ALL_STMT = "select * from boxtype";

	@Override
	public void insert(BoxTypeVO boxTypeVO) {
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			) {

			pstmt.setString(1, boxTypeVO.getBoxName());

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer boxTypeID) {
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETE_STMT);
			) {

			pstmt.setInt(1, boxTypeID);

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(BoxTypeVO boxTypeVO) {
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
			) {

			pstmt.setString(1, boxTypeVO.getBoxName());
			pstmt.setInt(2, boxTypeVO.getBoxTypeID());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoxTypeVO findPK(Integer boxTypeID) {
		BoxTypeVO btv = null;
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			) {

			pstmt.setInt(1, boxTypeID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				btv = new BoxTypeVO();
				btv.setBoxTypeID(rs.getInt("BoxTypeID"));
				btv.setBoxName(rs.getString("BoxName"));
			}
			return btv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BoxTypeVO> getAll() {
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();
			) {
			List<BoxTypeVO> list = new ArrayList<>();
			while (rs.next()) {
				BoxTypeVO btv = new BoxTypeVO();
				btv.setBoxTypeID(rs.getInt(1));
				btv.setBoxName(rs.getString(2));
				list.add(btv);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
