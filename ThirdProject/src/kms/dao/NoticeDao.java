package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.NoticeVo;
import test.jdbc.DBConnection;

public class NoticeDao {
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConn();
			String sql = "select NVL(count(num),0) cnt from notice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBConnection.closeConn(conn, pstmt, rs);
		}
	}
	public ArrayList<NoticeVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from notice order by notnum)aa)where rnum>=? and rnum<=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<NoticeVo> list = new ArrayList<>();
			while (rs.next()) {
				int notnum = rs.getInt("notnum");
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontet");
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
			DBConnection.closeConn(conn, pstmt, rs);
		}
	}
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into notice values(notice_seq.nextval,?,?,?,sysdate)";
		try {
			con=DBConnection.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getNottitle());
			pstmt.setString(2, vo.getNotcontent());
			pstmt.setInt(3, 0);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(con,pstmt,null);
		}
	}
}
