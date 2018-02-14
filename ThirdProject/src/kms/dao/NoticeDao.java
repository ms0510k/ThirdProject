package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kms.vo.NoticeVo;
import test.jdbc.DBConnection;

public class NoticeDao {
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
