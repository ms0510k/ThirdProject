package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kms.vo.MemberVo;
import test.dbcp.DbcpBean;

public class MemberDao {
	public int insert(MemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getBank());
			pstmt.setInt(6, vo.getAccount());
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
	
	
	//ex 테이블에 초기 거래 내역넣기
	public int exInsert(int memnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			/*String sql = "insert into exchange values(exchange_seq.nextval,?,?,?,?,sysdate)";*/
			
			String sql = "INSERT ALL "+ 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'krw',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'btc',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'eth',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'xrp',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'btg',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'qtum',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'msc',0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney) VALUES (ex_seq,?,'sunc',0)"+
					"select * from DUAL";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memnum);
			pstmt.setInt(2, memnum);
			pstmt.setInt(3, memnum);
			pstmt.setInt(4, memnum);
			pstmt.setInt(5, memnum);
			pstmt.setInt(6, memnum);
			pstmt.setInt(7, memnum);
			pstmt.setInt(8, memnum);
	
			
			
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
	
	//trade테이블에 초기 거래값 넣기
	public int tInsert(int memnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			
			String sql = "insert into thistory values(sysdate,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "krw");
			pstmt.setInt(2, 0);
			pstmt.setString(3, "개설");
			pstmt.setInt(4, 0);
			pstmt.setInt(5, memnum);
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
	
	
	
	
}
