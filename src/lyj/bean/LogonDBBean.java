package lyj.bean;

import java.sql.*;
import java.util.Vector;

public class LogonDBBean
{
	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance()
	{
		return instance;
	}
	
	private LogonDBBean() {}
	
	private Connection getConnection() throws Exception
	{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		
		return DriverManager.getConnection(jdbcDriver);
	}
	
	// 데이터를 입력
	public void insertMember(LogonDataBean member) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement
			("insert into MEMBERS values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getBlog());
			pstmt.setTimestamp(8, member.getReg_date());
			pstmt.setString(9, member.getZipcode());
			pstmt.setString(10, member.getAddress());
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	// 로그인 로직에서 사용할...
	public int userCheck(String id, String passwd) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd = "";
		int x = -1;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbpasswd = rs.getString("passwd");
				
				if(dbpasswd.equals(passwd))
					x = 1; // 인증 성공
				else
					x = 0; // 비밀번호 틀림
			}
			else
				x = -1; // 해당 아이디 없음
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	// 회원가입시 ID를 체크할 때 호출
	public int confirmId(String id) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = -1;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = 1; // 해당 아이디 있음
			else
				x = -1; // 해당 아이디 없음
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	// 업데이트시 입력된 데이터를 보여줄 때 사용
	public LogonDataBean getMember(String id) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				member = new LogonDataBean();
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setJumin1(rs.getString("jumin1"));
				member.setJumin2(rs.getString("jumin2"));
				member.setEmail(rs.getString("email"));
				member.setBlog(rs.getString("blog"));
				member.setReg_date(rs.getTimestamp("reg_date"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress(rs.getString("address"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return member;
	}
	
	// 회원정보를 수정
	public void updateMember(LogonDataBean member) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement
				("update MEMBERS set passwd = ?, name = ?, " +
						"email = ?, blog = ?, zipcode = ?, address = ? where id = ?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getBlog());
			pstmt.setString(5, member.getZipcode());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getId());
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	// 회원정보를 삭제
	public int deleteMember(String id, String passwd) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd = "";
		int x = -1;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dbpasswd = rs.getString("passwd");
				
				if(dbpasswd.equals(passwd))
				{
					pstmt = conn.prepareStatement("delete from MEMBERS where id = ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					
					x = 1; // 회원탈퇴 성공
				}
				else
					x = 0; // 비밀번호 틀림
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	// 아이디 찾기
	public LogonDataBean searchId(String name, String jumin1, String jumin2) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean sid = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from MEMBERS where name = ? " +
					"and jumin1 =? and jumin2 = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, jumin1);
			pstmt.setString(3, jumin2);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				sid = new LogonDataBean();
				sid.setId(rs.getString("id"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return sid;
	}
	
	// 비밀번호 찾기
	public LogonDataBean searchPw(String id, String jumin1, String jumin2) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean spw = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ? " +
					"and jumin1 =? and jumin2 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, jumin1);
			pstmt.setString(3, jumin2);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				spw = new LogonDataBean();
				spw.setPasswd(rs.getString("passwd"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null) try { rs.close(); } catch(SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return spw;
	}
}