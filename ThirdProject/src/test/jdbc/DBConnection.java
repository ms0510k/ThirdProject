package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static Connection getConn() throws SQLException {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			con=DriverManager.getConnection(url,"scott","tiger");
			return con;
		}catch(ClassNotFoundException ce) {
			System.out.println("드라이버로딩실패:" + ce.getMessage());
		}
		return con;
	}
	public static void closeConn(Connection con) {
		try {
			if(con!=null) {
				con.close();
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	public static void closeConn(Connection con,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
















