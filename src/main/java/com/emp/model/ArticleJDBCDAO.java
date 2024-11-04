package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/hihidatabase?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "0955888660";

	// SQL語句
	private static final String INSERT_STMT = 
		"INSERT INTO Article (memId, acTitle, acCon, postDate, acStat) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT acId, memId, acTitle, acCon, postDate, acStat FROM Article ORDER BY acId";
	private static final String GET_ONE_STMT = 
		"SELECT acId, memId, acTitle, acCon, postDate, acStat FROM Article WHERE acId = ?";
	private static final String DELETE = 
		"DELETE FROM Article WHERE acId = ?";
	private static final String UPDATE = 
		"UPDATE Article SET memId=?, acTitle=?, acCon=?, postDate=?, acStat=? WHERE acId = ?";

	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getMemId());
			pstmt.setString(2, articleVO.getAcTitle());
			pstmt.setString(3, articleVO.getAcCon());
			pstmt.setTimestamp(4, articleVO.getPostDate());
			pstmt.setInt(5, articleVO.getAcStat());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt);
		}
	}

	@Override
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleVO.getMemId());
			pstmt.setString(2, articleVO.getAcTitle());
			pstmt.setString(3, articleVO.getAcCon());
			pstmt.setTimestamp(4, articleVO.getPostDate());
			pstmt.setInt(5, articleVO.getAcStat());
			pstmt.setInt(6, articleVO.getAcId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt);
		}
	}

	@Override
	public void delete(Integer acId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, acId);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt);
		}
	}

	@Override
	public ArticleVO findByPrimaryKey(Integer acId) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, acId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setAcId(rs.getInt("acId"));
				articleVO.setMemId(rs.getInt("memId"));
				articleVO.setAcTitle(rs.getString("acTitle"));
				articleVO.setAcCon(rs.getString("acCon"));
				articleVO.setPostDate(rs.getTimestamp("postDate"));
				articleVO.setAcStat(rs.getInt("acStat"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt, rs);
		}
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setAcId(rs.getInt("acId"));
				articleVO.setMemId(rs.getInt("memId"));
				articleVO.setAcTitle(rs.getString("acTitle"));
				articleVO.setAcCon(rs.getString("acCon"));
				articleVO.setPostDate(rs.getTimestamp("postDate"));
				articleVO.setAcStat(rs.getInt("acStat"));
				list.add(articleVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt, rs);
		}
		return list;
	}

	private void closeResources(Connection con, PreparedStatement pstmt) {
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

	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		closeResources(con, pstmt);
	}
}
