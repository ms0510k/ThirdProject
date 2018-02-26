package pys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.CompVo;
import test.dbcp.DbcpBean;

public class inoutDAO {
	
	public int update_result(CompVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update complaine set comresult=? where comnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getComresult());
			pstmt.setInt(2, vo.getComnum());
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
	public CompVo getinfo(int comnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from complaine where comnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int memnum = rs.getInt("memnum");
				String comtitle = rs.getString("comtitle");
				String email = rs.getString("email");
				String comcontent = rs.getString("comcontent");
				String comresult = rs.getString("comresult");
				int comhit = rs.getInt("comhit");
				Date comdate = rs.getDate("comdate");
				CompVo vo = new CompVo(comnum,memnum,comtitle,email,comcontent,comresult,comhit,comdate);
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
				pstmt.close();
				rs.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select NVL(count(comnum),0) cnt from complaine";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
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
	public ArrayList<CompVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from complaine where comresult='�떟蹂���湲곗쨷' order by comnum)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<CompVo> list = new ArrayList<>();
			while (rs.next()) {
				int comnum = rs.getInt("comnum");
				int memnum = rs.getInt("memnum");
				String comtitle = rs.getString("comtitle");
				String email = rs.getString("email");
				String comcontent = rs.getString("comcontent");
				String comresult = rs.getString("comresult");
				int comhit = rs.getInt("comhit");
				Date comdate = rs.getDate("comdate");
				CompVo vo = new CompVo(comnum,memnum,comtitle,email,comcontent,comresult,comhit,comdate);
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
}