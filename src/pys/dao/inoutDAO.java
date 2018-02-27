package pys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.CompVo;
import pys.vo.exVO;
import pys.vo.moneyVO;
import pys.vo.tradeVO;
import test.dbcp.DbcpBean;

public class inoutDAO {
	
	public int fintNum(String email) {
		String sql = "select memnum from member where email = ?";
		PreparedStatement ps = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return -1;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
		DbcpBean.closeconn(con, ps, rs);
		}
	}
	
	
	
	
	

		
		
		
		//출금신청 관리자 money 테이블에 승인여부 결과 찍어서 보낸다
		public int out(moneyVO vo) {
			System.out.println("money에 찍힐 항목 : "+vo.toString());
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con=DbcpBean.getConn();
				
				String sql = "insert into money values(money_seq.nextval,?,?,?,sysdate,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getExnum());
				pstmt.setInt(2, vo.getMemnum());
				pstmt.setString(3, vo.getConfirm());
				pstmt.setInt(4, vo.getOutmoney());
				return pstmt.executeUpdate();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				try {
					con.close();
					pstmt.close();
					}catch(SQLException se) {
						System.out.println(se.getMessage());
					}
			}
		}
	public ArrayList<exVO> exlist(int memnum) {
		String sql = "select * from exchange where memnum = ?";
		PreparedStatement ps = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memnum);
			rs = ps.executeQuery();
			ArrayList<exVO> list = new ArrayList<>();
			while (rs.next()) {
				int exnum = rs.getInt(1);
				int memnum1 = rs.getInt(2);
				String buysell = rs.getString(3);
				String excoin = rs.getString(4);
				int exmoney = rs.getInt(5);
				String exdate = rs.getString(6);
				exVO vo = new exVO(exnum, memnum1, buysell, excoin, exmoney, exdate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
		DbcpBean.closeconn(con, ps, rs);
		}
	}
	
	
	public ArrayList<tradeVO> tradelist(int memnum) {
		String sql = "select * from thistory where memnum = ?";
		PreparedStatement ps = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memnum);
			rs = ps.executeQuery();
			ArrayList<tradeVO> list = new ArrayList<>();
			while (rs.next()) {
				String tdate = rs.getString(1);
				String coin = rs.getString(2);
				int coinamount  = rs.getInt(3);
				String tradetype = rs.getString(4);
				int tprice = rs.getInt(5);
				int memnum1 = rs.getInt(6);
				tradeVO vo = new tradeVO(tdate, coin, coinamount, tradetype, tprice, memnum1);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
		DbcpBean.closeconn(con, ps, rs);
		}
	}
	
	
	
	
	
	
	
	
}