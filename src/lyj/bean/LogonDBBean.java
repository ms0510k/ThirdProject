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
			("insert into MEMBERS values(?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getBank());
			pstmt.setInt(6, member.getAccount());			
			pstmt.setTimestamp(8, member.getReg_date());
			
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
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhone(rs.getString("phone"));
				member.setBank(rs.getString("bank"));
				member.setAccount(rs.getInt("account"));				
				member.setReg_date(rs.getTimestamp("reg_date"));
			
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
						"phone = ? where id = ?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
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
	public LogonDataBean searchId(String name, String phone) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean sid = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from MEMBERS where name = ? " +
					"and phone =? ");
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
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
	public LogonDataBean searchPw(String id, String phone) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean spw = null;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ? " +
					"and phone = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, phone);
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