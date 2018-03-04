package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.CompVo;
import kms.vo.MemberVo;
import kms.vo.NoticeVo;
import pys.dao.inoutDAO;
import test.dbcp.DbcpBean;

public class MemberDao {
	public int updateOk(MemberVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update member set pwd=?, phone=?, bank=?, account=? where email=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getBank());
			pstmt.setInt(4, vo.getAccount());
			pstmt.setString(5, vo.getEmail());
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
	public MemberVo getinfo(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where email=?";
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int memnum = rs.getInt("memnum");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String phone = rs.getString("phone");
				String bank = rs.getString("bank");
				int account = rs.getInt("account");
				Date memdate = rs.getDate("memdate");
				MemberVo vo = new MemberVo(memnum,name,email,pwd,phone,bank,account,memdate);
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
	public int getCount(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select NVL(count(comnum),0) cnt from complaine where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
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
	public ArrayList<CompVo> list(int startRow, int endRow, String email) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from complaine where email=? order by comnum)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<CompVo> list = new ArrayList<>();
			while (rs.next()) {
				int comnum = rs.getInt("comnum");
				int memnum = rs.getInt("memnum");
				String comtitle = rs.getString("comtitle");
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
	public int comp_insertOk(String email, String comtitle, String comcontent) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		int memnum=0;
		try {
			con=DbcpBean.getConn();
			String sql1 = "select memnum from member where email=?";
			String sql2 = "insert into complaine values(complaine_seq.nextval,?,?,?,?,'답변대기중',0,sysdate)";
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt1.setString(1, email);
			rs=pstmt1.executeQuery();
			if(rs.next()) {
				memnum=rs.getInt("memnum");
			}
			pstmt2.setInt(1, memnum);
			pstmt2.setString(2, comtitle);
			pstmt2.setString(3, email);
			pstmt2.setString(4, comcontent);
			pstmt2.executeQuery();
			return 1;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			try {
				con.close();
				pstmt1.close();
				pstmt2.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
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
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'krw',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'BTC',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'ETH',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'XRP',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'BTG',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'QTUM',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'MSC',0,0)" + 
					"      INTO exchange (exnum,memnum,excoin,exmoney,examount) VALUES (ex_seq,?,'SUNC',0,0)"+
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
			
			String sql = "insert into thistory values(t_seq.nextval,sysdate,?,?,?,?,?,0)";
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
