package kms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kms.vo.FeesVo;
import test.dbcp.DbcpBean;

public class FeesDao {
	public int getCounts(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("fees_day")) {
				sql = "select count(cnt) from (select NVL(count(feenum),0) cnt from fees group by to_char(feedate,'yyyy/mm/dd'))";
			}else if(search.equals("fees_month")) {
				sql = "select count(cnt) from (select NVL(count(feenum),0) cnt from fees group by to_char(feedate,'yyyy/mm'))";
			}
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
	public ArrayList<FeesVo> feessearch(String search, int startRow, int endRow ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("fees_day")) {
			sql = "select bb,to_date(feedate,'yyyy/mm/dd')feedate from(select aa.*,rownum rnum from(select sum(feemoney)bb,to_char(feedate,'yyyy/mm/dd') feedate from fees group by to_char(feedate,'yyyy/mm/dd'))aa)where rnum>=? and rnum<=?";
			}else if(search.equals("fees_month")) {
			sql = "select bb,to_date(feedate,'yyyy/mm')feedate from(select aa.*,rownum rnum from(select sum(feemoney)bb,to_char(feedate,'yyyy/mm') feedate from fees group by to_char(feedate,'yyyy/mm'))aa)where rnum>=? and rnum<=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<FeesVo> list = new ArrayList<>();
			while (rs.next()) {
				int feemoney = rs.getInt("bb");
				Date feedate = rs.getDate("feedate");
				FeesVo vo = new FeesVo(0,0,feemoney,0,feedate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			try {
			rs.close();
			pstmt.close();
			con.close();	
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
			String sql = "select NVL(count(feenum),0) cnt from fees";
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
	public ArrayList<FeesVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from fees order by feenum desc)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<FeesVo> list = new ArrayList<>();
			while (rs.next()) {
				int feenum = rs.getInt("feenum");
				int connum = rs.getInt("connum");
				int feemoney = rs.getInt("feemoney");
				int memnum = rs.getInt("memnum");
				Date feedate = rs.getDate("feedate");
				FeesVo vo = new FeesVo(feenum,connum,feemoney,memnum,feedate);
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
