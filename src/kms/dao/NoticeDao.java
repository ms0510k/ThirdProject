package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.NoticeVo;
import test.dbcp.DbcpBean;

public class NoticeDao {
	public ArrayList<NoticeVo> noticesearch(String search, String word, int startRow, int endRow ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("nottitle")) {
			sql = "select * from(select aa.*,rownum rnum from(select * from notice where nottitle like '%'||?||'%' order by notnum)aa)where rnum>=? and rnum<=?";
			}else if(search.equals("notcontent")) {
			sql = "select * from(select aa.*,rownum rnum from(select * from notice where notcontent like '%'||?||'%' order by notnum)aa)where rnum>=? and rnum<=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<NoticeVo> list = new ArrayList<>();
			while (rs.next()) {
				int notnum = rs.getInt("notnum");
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontent");
				int nothit = rs.getInt("nothit");
				Date notdate = rs.getDate("notdate");
				NoticeVo vo = new NoticeVo(notnum,nottitle,notcontent,nothit,notdate);
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
	public int getCounts(String search, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("nottitle")) {
				sql = "select NVL(count(notnum),0) cnt from notice where nottitle like '%'||?||'%'";
			}else if(search.equals("notcontent")) {
				sql = "select NVL(count(notnum),0) cnt from notice where notcontent like '%'||?||'%'";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
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
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select NVL(count(notnum),0) cnt from notice";
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
	public int updateOk(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update notice set nottitle=?, notcontent=? where notnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getNottitle());
			pstmt.setString(2, vo.getNotcontent());
			pstmt.setInt(3, vo.getNotnum());
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
	public int delete(int notnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="delete from notice where notnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notnum);
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
	public NoticeVo getinfo(int notnum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = "select * from notice where notnum=?";
		String sql2 = "update notice set nothit=(nothit+1) where notnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt1 = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			pstmt1.setInt(1, notnum);
			pstmt2.setInt(1, notnum);
			pstmt2.executeQuery();
			rs = pstmt1.executeQuery();
			if (rs.next()) {
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontent");
				int nothit = rs.getInt("nothit");
				Date notdate = rs.getDate("notdate");
				NoticeVo vo = new NoticeVo(notnum,nottitle,notcontent,nothit,notdate);
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
				pstmt1.close();
				pstmt2.close();
				rs.close();
				}catch(SQLException se) {
					System.out.println(se.getMessage());
				}
		}
	}
	public ArrayList<NoticeVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from notice order by notnum)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<NoticeVo> list = new ArrayList<>();
			while (rs.next()) {
				int notnum = rs.getInt("notnum");
				String nottitle = rs.getString("nottitle");
				String notcontent = rs.getString("notcontent");
				int nothit = rs.getInt("nothit");
				Date notdate = rs.getDate("notdate");
				NoticeVo vo = new NoticeVo(notnum,nottitle,notcontent,nothit,notdate);
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
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into notice values(notice_seq.nextval,?,?,?,sysdate)";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getNottitle());
			pstmt.setString(2, vo.getNotcontent());
			pstmt.setInt(3, 0);
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
}