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
	
	
	//ex거래내역 기본값 추가
	public int exInsert(int memnum) {
		System.out.println(memnum+"고객 ex 추가하기");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql = "insert into exchange values(exchange_seq.nextval,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memnum);
			pstmt.setString(2, "개설");
			pstmt.setString(3, "krw");
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
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
	
	//trade거래내역 기본값 추가
	public int tInsert(int memnum) {
		System.out.println(memnum+"고객 trade 추가하기");
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
