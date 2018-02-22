package kms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import test.dbcp.DbcpBean;

public class LoginDao {
		private static LoginDao instance=new LoginDao();
		private LoginDao() {}
		public static LoginDao getInstance() {
			return instance;
		}
		public int isMember(HashMap<String,String> map) {
			String email=map.get("email");
			String pwd=map.get("pwd");	
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=DbcpBean.getConn();
				String sql="select * from member where email=? and pwd=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,email);
				pstmt.setString(2,pwd);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}else {
					return 0;
				}
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
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
