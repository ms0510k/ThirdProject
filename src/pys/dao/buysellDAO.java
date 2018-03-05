package pys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pys.vo.exVO;
import pys.vo.tradeVO;
import test.dbcp.DbcpBean;

public class buysellDAO {

	// 매수 신청한 이후 미체결로 thistory에 넣어준다.
	public int tradein(tradeVO vo) {
		System.out.println("미체결 신청후 thistory 결과 ㅣ " + vo.toString());
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

			String sql = "insert into thistory values(t_seq.nextval,sysdate,?,?,?,?,?,?)";

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
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

	// 매수 신청한 이후 미체결로 된 금액만큼 exchange 테이블의 금액 임시 차감해주기
	public int tradein_cash(exVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

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
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

	// 매도 신청한 이후 미체결 코인량 exchange 테이블의 수량 임시 차감해주기
	public int tradein_coin(exVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

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
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

	// 거래 번호로 해당 거래내역 호출하기
	public tradeVO searchT(int tNum) {
		String sql = "select * from thistory where tnum = ?";
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, tNum);
			rs = ps.executeQuery();
			tradeVO vo = null;
			if (rs.next()) {
				int tnum = rs.getInt(1);
				String tdate = rs.getString(2);
				String coin = rs.getString(3);
				double coinamount = rs.getDouble(4);
				String tradetype = rs.getString(5);
				int tprice = rs.getInt(6);
				int memnum = rs.getInt(7);
				int fee = rs.getInt(8);
				vo = new tradeVO(tnum, tdate, coin, coinamount, tradetype, tprice, memnum, fee);
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.closeconn(con, ps, rs);
		}
	}

	// 매도 신청한 이후 미체결 코인량 trade 테이블 내역 삭제하기
	public int tradein_cancel(tradeVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

			String sql = "delete from thistory where tnum = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getTnum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

	// 매도 신청한 이후 미체결 코인량 exchange 테이블 내역 다시 올려놓기
	public int exin_cancel(tradeVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();
			String sql = "";
			if (vo.getTradetype().equals("미체결_구매")) {

				sql = "update exchange set exmoney = exmoney+? where memnum = ? and excoin = 'krw'";
				pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, (vo.getCoinamount()*vo.getTprice()));
				pstmt.setInt(2, vo.getMemnum());
				return pstmt.executeUpdate();

			} else if(vo.getTradetype().equals("미체결_판매")) {
				sql = "update exchange set examount = examount +? where memnum  = ? and excoin = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, vo.getCoinamount());
				pstmt.setInt(2, vo.getMemnum());
				pstmt.setString(3, vo.getCoin());
				return pstmt.executeUpdate();

			}else {
				return -1;
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

	
	
	
	//미체결 리스트 넘겨줌
	public ArrayList<tradeVO> reserveList() {
		String sql = "select * from thistory where tradetype like '미체결%'";
		PreparedStatement ps = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList<tradeVO> list = new ArrayList<>();
			while (rs.next()) {
				int tnum = rs.getInt(1);
				String tdate = rs.getString(2);
				String coin = rs.getString(3);
				double coinamount  = rs.getDouble(4);
				String tradetype = rs.getString(5);
				int tprice = rs.getInt(6);
				int memnum1 = rs.getInt(7);
				int fee = rs.getInt(8);
				tradeVO vo = new tradeVO(tnum,tdate, coin, coinamount, tradetype, tprice, memnum1,fee);
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
	
	
	
	
	
	
	
	//미체결  thistory 거래내역 체결로 바꿔주기
	public int tradeok_t(int tnum, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

			String sql = "update thistory set tradetype = ? where tnum = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setInt(2, tnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}
	
	
	
	
	
	
	//미체결 후 exchange 테이블에 구매시 수량증가, 판매시 krw 증가시켜주기
	public int buyok_ex(int memnum, String coin, double amount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbcpBean.getConn();

			String sql = "update exchange set examount = examount + ? where memnum = ? and excoin = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, memnum);
			pstmt.setString(3, coin);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}
	
	
	//미체결 후 exchange 테이블에 구매시 수량증가, 판매시 krw 증가시켜주기
		public int sellok_ex(int memnum,  int money) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DbcpBean.getConn();

				String sql = "update exchange set exmoney = exmoney + ? where memnum = ? and excoin = 'krw'";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, money);
				pstmt.setInt(2, memnum);
				return pstmt.executeUpdate();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				try {
					con.close();
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			}
		}
		
		// 거래 체결 후 해당 fee 값 fees 테이블로 넣어주기
		public int feein(int feemoney) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DbcpBean.getConn();

				String sql = "insert into fees(feenum, feemoney) values(feenum_seq.nextval,?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, feemoney);
				return pstmt.executeUpdate();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				try {
					con.close();
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			}
		}

	
}
