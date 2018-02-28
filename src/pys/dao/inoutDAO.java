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
			se.printStackTrace();
			return -1;
		} finally {
		DbcpBean.closeconn(con, ps, rs);
		}
	}
	
	
	
	
	
		//매수 신청한 이후 미체결로 thistory에 넣어준다.
	public int tradein(tradeVO vo) {
		System.out.println("미체결 신청후 thistory 결과 ㅣ "+vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			
			String sql = "insert into thistory values(sysdate,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getCoin());
			pstmt.setDouble(2, vo.getCoinamount());
			pstmt.setString(3, vo.getTradetype());
			pstmt.setInt(4, vo.getTprice());
			pstmt.setInt(5, vo.getMemnum());
			pstmt.setDouble(6, vo.getFee());
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
	
	
	//매수 신청한 이후 미체결로 된 금액만큼 exchange 테이블의 금액 임시 차감해주기
		public int tradein_cash(exVO vo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con=DbcpBean.getConn();
				
				String sql = "update exchange set exmoney = exmoney-? where excoin = 'krw' and memnum = ? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getExmoney());
				pstmt.setInt(2, vo.getMemnum());
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
		
		
		
		//출금신청 관리자 money 테이블에 승인여부 결과 찍어서 보낸다
		public int out(moneyVO vo) {
			System.out.println("money에 찍힐 항목1 : "+vo.toString());
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con=DbcpBean.getConn();
				
				String sql = "insert into money values(money_seq.nextval,?,?,?,?,sysdate)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getExnum());
				pstmt.setInt(2, vo.getMemnum());
				pstmt.setInt(3, vo.getOutmoney());
				pstmt.setString(4, vo.getConfirm());
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
		
		
		
		//입금하기 ex테이블과 t 테이블에 데이터 넣어주기
		public int in(tradeVO tvo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con=DbcpBean.getConn();
				
				String sql = "INSERT  INTO thistory  VALUES (sysdate,?,0,?,?,?,0)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tvo.getCoin());
				pstmt.setString(2, tvo.getTradetype());
				pstmt.setInt(3, tvo.getTprice());
				pstmt.setInt(4, tvo.getMemnum());
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
		
		
		//입금하기 ex테이블과 t 테이블에 데이터 넣어주기
				public int in2(exVO exvo) {
					Connection con = null;
					PreparedStatement pstmt = null;
					try {
						con=DbcpBean.getConn();
						
						String sql = "update exchange set exmoney = exmoney+? where memnum = ? and excoin = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, exvo.getExmoney());
						pstmt.setInt(2, exvo.getMemnum());
						pstmt.setString(3, exvo.getExcoin());
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
		Connection con=null;
		PreparedStatement ps = null;
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
				String excoin = rs.getString(3);
				int exmoney = rs.getInt(4);
				exVO vo = new exVO(exnum, memnum1, excoin, exmoney);
				System.out.println(vo.toString());
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
		DbcpBean.closeconn(con, ps, rs);
		}
	}
	
	
	public ArrayList<tradeVO> tradelist(int memnum) {
		String sql = "select * from thistory where memnum = ? and coin = 'krw'";
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
				double coinamount  = rs.getDouble(3);
				String tradetype = rs.getString(4);
				int tprice = rs.getInt(5);
				int memnum1 = rs.getInt(6);
				int fee = rs.getInt(7);
				tradeVO vo = new tradeVO(tdate, coin, coinamount, tradetype, tprice, memnum1,fee);
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
	
	
	
	//출금이력있나 그리고 상탠튼?
	public int outflag(int memnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			
			String sql = "select * from (select * from money where memnum = ?) where confirm = '미승인'";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memnum);
			
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