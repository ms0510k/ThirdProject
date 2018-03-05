package lyj.bean;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/asdf.do")  
public class searchid extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("조회 로직");    
//		Enumeration<String> str = request.getAttributeNames();
//		System.out.println(str);     
		//================== 아이디 찾는 로직 =====================
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String id = "asdf1234"; 
		System.out.println(name);
		System.out.println(phone); 
		//====================================================
		
		
	 	
		response.sendRedirect(request.getContextPath()+"/lyj_member/search_id.jsp?name="+name + "&id=" + id );
//		 response.getWriter().println("<html><a></a></html>");
//		 response.flushBuffer(); 
	} 
} 
