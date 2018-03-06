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
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		PreparedStatement pstmt5=null;
		ResultSet rs=null;
		int memnum=0;
		String sql1="select memnum from money where connum=?";
		String sql2="insert into thistory values(t_seq.nextval,sysdate,'krw',0,'출금',?,?,?)";
		String sql3="update exchange set exmoney=(exmoney-?) where memnum=? and excoin='krw'";
		String sql4="update money set confirm='승인' where connum=?";
		String sql5="insert into fees values(feenum_seq.nextval,?,?,?,sysdate)";
		try {
			con=DbcpBean.getConn();
			pstmt1=con.prepareStatement(sql1);
			pstmt2=con.prepareStatement(sql2);
			pstmt3=con.prepareStatement(sql3);
			pstmt4=con.prepareStatement(sql4);
			pstmt5=con.prepareStatement(sql5);
			pstmt1.setInt(1, connum);
			rs = pstmt1.executeQuery();
			if (rs.next()) {
				memnum=rs.getInt("memnum");
			}
			pstmt2.setInt(1, outmoney);
			pstmt2.setInt(2, memnum);
			pstmt2.setDouble(3, outmoney*0.01);
			pstmt3.setInt(1, outmoney);
			pstmt3.setInt(2, memnum);
			pstmt4.setInt(1, connum);
			pstmt5.setInt(1, connum);
			pstmt5.setDouble(2, outmoney*0.01);
			pstmt5.setInt(3, memnum);
			pstmt2.executeQuery();
			pstmt3.executeQuery();
			pstmt4.executeQuery();
			pstmt5.executeQuery();
			return 1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				con.close();
				pstmt1.close();
				pstmt2.close();
				pstmt3.close();
				pstmt4.close();
				pstmt5.close();
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
			String sql = "select NVL(count(connum),0) cnt from money where confirm='미승인'";
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
