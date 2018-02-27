package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.ConfVo;
import kms.vo.NoticeVo;
import test.dbcp.DbcpBean;

public class ConfDao {
	public int confirm(int connum, int outmoney) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		int memnum=0;
		String sql="select memnum from money where connum=?";
		String sql2="insert into thistory values(sysdate,'krw',0,'출금',?,?)";
		String sql3="update exchange set exmoney=(exmoney-?) where memnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt1=con.prepareStatement(sql2);
			pstmt2=con.prepareStatement(sql3);
			pstmt.setInt(1, connum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memnum=rs.getInt("memnum");
			}
			pstmt1.setInt(1, outmoney);
			pstmt1.setInt(2, memnum);
			pstmt2.setInt(1, outmoney);
			pstmt2.setInt(2, memnum);
			pstmt1.executeQuery();
			pstmt2.executeQuery();
			return 1;
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
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select NVL(count(connum),0) cnt from money";
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
	public ArrayList<ConfVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select c.connum,m.email,c.outmoney,c.confirm,c.condate from money c, member m where c.memnum=m.memnum and confirm='미승인' order by connum)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ConfVo> list = new ArrayList<>();
			while (rs.next()) {
				int connum = rs.getInt("connum");
				String email = rs.getString("email");
				int outmoney = rs.getInt("outmoney");
				String confirm = rs.getString("confirm");
				Date condate = rs.getDate("condate");
				ConfVo vo = new ConfVo(connum,email,outmoney,confirm,condate);
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
