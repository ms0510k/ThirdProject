package pys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pys.vo.exVO;
import pys.vo.tradeVO;
import test.dbcp.DbcpBean;

public class buysellDAO {

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
					
					String sql = "update exchange set exmoney = exmoney-? where excoin = ? and memnum = ? ";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, vo.getExmoney());
					pstmt.setString(2, vo.getExcoin());
					pstmt.setInt(3, vo.getMemnum());
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
			
			
	
			//매도 신청한  이후 미체결 코인량 exchange 테이블의 수량 임시 차감해주기
			public int tradein_coin(exVO vo) {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con=DbcpBean.getConn();
					
					String sql = "update exchange set examount = examount-? where excoin = ? and memnum = ? ";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setDouble(1, vo.getExamount());
					pstmt.setString(2, vo.getExcoin());
					pstmt.setInt(3, vo.getMemnum());
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
