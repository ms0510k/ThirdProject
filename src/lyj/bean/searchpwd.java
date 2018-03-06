package lyj.bean;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/asdfg.do")  
public class searchpwd extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("조회 로직");    
//		Enumeration<String> str = request.getAttributeNames();
//		System.out.println(str);     
		//================== 비밀번호 찾는 로직 =====================
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		String pwd = "1111123"; 
		System.out.println(id);
		System.out.println(phone); 
		//====================================================
		
		
	 	
		response.sendRedirect(request.getContextPath()+"/lyj_member/search_pw.jsp?id="+id + "&pwd=" + pwd );
//		 response.getWriter().println("<html><a></a></html>");
//		 response.flushBuffer(); 
	} 
} 
