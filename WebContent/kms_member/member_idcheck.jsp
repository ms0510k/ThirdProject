<%@page import="java.sql.SQLException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="test.dbcp.DbcpBean"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email=request.getParameter("email");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	boolean using=false;
	try {
		con=DbcpBean.getConn();
		String sql="select * from member where email=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,email);
		rs=pstmt.executeQuery();
		if(rs.next()){
			using=true;
		}
	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} finally {
		try {
			con.close();
			pstmt.close();
			}catch(SQLException se) {
				System.out.println(se.getMessage());
			}
	}
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.println("<result>");
	pw.println("<using>" + using +"</using>");
	pw.println("</result>");
	pw.close();
%>