package pys.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/header.do")
public class HeaderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();//ï¿½ï¿½ï¿½Ø½ï¿½Æ®ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		System.out.println(cmd);
		if(cmd.equals("main")) {
			main(request,response);
		}else if(cmd.equals("chart")) {
			now(request,response);
		}else if(cmd.equals("trade")) {
			trade(request,response);
		}else if(cmd.equals("inout")) {
			inout(request,response);
		}
	}
	
	
	
	
	private void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	private void now(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String coin = request.getParameter("coin");
		if(coin != null) {
			request.setAttribute("page","pys_current/chart.jsp?coin="+coin);
		}else {
	
		request.setAttribute("page","pys_current/chart.jsp");
		}
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	private void trade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setAttribute("page","pys_current/buysell.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	private void inout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.DB¿¡¼­ ÀüÃ¼ È¸¿ø¸ñ·Ï ¾ò¾î¿À±â
		
		
		//2.È¸¿ø¸ñ·ÏÀ» ½ºÄÚÇÁ¿¡ ´ã±â
		//request.setAttribute("mlist",mlist);
		//3.°á°ú¸¦ º¸¿©ÁÙ ºäÆäÀÌÁö·Î ÀÌµ¿
		request.setAttribute("page","pys_current/in_out.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
}
	
	
	
	
	
	
	
	