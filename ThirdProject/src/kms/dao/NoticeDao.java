package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.NoticeVo;
import test.dbcp.DbcpBean;

public class NoticeDao {
	public int delete(int notnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="delete from notice where notnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				con.close();
				pstmt.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
	public NoticeVo getinfo(int notnum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = "select * from notice where notnum=?";
		String sql2 = "update notice set nothit=(nothit+1) where notnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt1 = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			pstmt1.setInt(1, notnum);
			pstmt2.setInt(1, notnum);
			pstmt2.executeQuery();
			rs = pstmt1.executeQuery();
			if (rs.next()) {
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontent");
				int nothit = rs.getInt("nothit");
				Date notdate = rs.getDate("notdate");
				NoticeVo vo = new NoticeVo(notnum,nottitle,notcontent,nothit,notdate);
				return vo;
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			try {
				con.close();
				pstmt1.close();
				pstmt2.close();
				rs.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
	public ArrayList<NoticeVo> list() {
		String sql = "select * from notice";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<NoticeVo> list = new ArrayList<>();
			while (rs.next()) {
				int notnum = rs.getInt("notnum");
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontent");
				int nothit = rs.getInt("nothit");
				Date notdate = rs.getDate("notdate");
				NoticeVo vo = new NoticeVo(notnum,nottitle,notcontent,nothit,notdate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			try {
			con.close();
			pstmt.close();
			rs.close();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into notice values(notice_seq.nextval,?,?,?,sysdate)";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getNottitle());
			pstmt.setString(2, vo.getNotcontent());
			pstmt.setInt(3, 0);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				con.close();
				pstmt.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
}
