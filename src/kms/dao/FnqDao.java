package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import kms.vo.FnqVo;
import test.dbcp.DbcpBean;

public class FnqDao {
	public int getCounts(String search, String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("fnqtitle")) {
				sql = "select NVL(count(fnqnum),0) cnt from fnq where fnqtitle like '%'||?||'%'";
			}else if(search.equals("fnqcontent")) {
				sql = "select NVL(count(fnqnum),0) cnt from fnq where fnqcontent like '%'||?||'%'";
			}else if(search.equals("fnqresult")) {
				sql = "select NVL(count(fnqnum),0) cnt from fnq where fnqresult like '%'||?||'%'";
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
	public ArrayList<FnqVo> fnqsearch(String search, String word, int startRow, int endRow ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con=DbcpBean.getConn();
			if(search.equals("fnqtitle")) {
			sql = "select * from(select aa.*,rownum rnum from(select * from fnq where fnqtitle like '%'||?||'%' order by fnqnum desc)aa)where rnum>=? and rnum<=?";
			}else if(search.equals("fnqcontent")) {
			sql = "select * from(select aa.*,rownum rnum from(select * from fnq where fnqcontent like '%'||?||'%' order by fnqnum desc)aa)where rnum>=? and rnum<=?";
			}else if(search.equals("fnqresult")) {
			sql = "select * from(select aa.*,rownum rnum from(select * from fnq where fnqresult like '%'||?||'%' order by fnqnum desc)aa)where rnum>=? and rnum<=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<FnqVo> list = new ArrayList<>();
			while (rs.next()) {
				int fnqnum = rs.getInt("fnqnum");
				String fnqtitle = rs.getString("fnqtitle");
				String fnqcontent = rs.getString("fnqcontent");
				String fnqresult = rs.getString("fnqresult");
				FnqVo vo = new FnqVo(fnqnum,fnqtitle,fnqcontent,fnqresult);
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
	public int insert(FnqVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into fnq values(fnq_seq.nextval,?,?,?)";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getFnqtitle());
			pstmt.setString(2, vo.getFnqcontent());
			pstmt.setString(3, vo.getFnqresult());
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
	public int updateOk(FnqVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update fnq set fnqtitle=?, fnqcontent=?, fnqresult=? where fnqnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getFnqtitle());
			pstmt.setString(2, vo.getFnqcontent());
			pstmt.setString(3, vo.getFnqresult());
			pstmt.setInt(4, vo.getFnqnum());
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
	public int delete(int fnqnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="delete from fnq where fnqnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, fnqnum);
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
	public FnqVo getinfo(int fnqnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from fnq where fnqnum=?";
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fnqnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String fnqtitle = rs.getString("fnqtitle");
				String fnqcontent = rs.getString("fnqcontent");
				String fnqresult = rs.getString("fnqresult");
				FnqVo vo = new FnqVo(fnqnum,fnqtitle,fnqcontent,fnqresult);
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
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql = "select NVL(count(fnqnum),0) cnt from fnq";
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
	public ArrayList<FnqVo> list(int startRow, int endRow) {
		String sql = "select * from(select aa.*,rownum rnum from(select * from fnq order by fnqnum desc)aa)where rnum>=? and rnum<=?";
		PreparedStatement pstmt = null;
		Connection con=null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<FnqVo> list = new ArrayList<>();
			while (rs.next()) {
				int fnqnum = rs.getInt("fnqnum");
				String fnqtitle = rs.getString("fnqtitle");
				String fnqcontent = rs.getString("fnqcontent");
				String fnqresult = rs.getString("fnqresult");
				FnqVo vo = new FnqVo(fnqnum,fnqtitle,fnqcontent,fnqresult);
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
