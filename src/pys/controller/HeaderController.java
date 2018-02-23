package pys.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/header.do")
public class HeaderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();//컨텍스트명 얻어오기
		//System.out.println("context:" + context);
		System.out.println(cmd);
		if(cmd.equals("main")) {
			main(request,response);
		}else if(cmd.equals("chart")) {
			now(request,response);
		}else if(cmd.equals("trade")) {
			trade(request,response);
		}
	}
	
	
	
	
	private void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	private void now(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page","pys_current/chart.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	private void trade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("여기로옴?");
		request.setAttribute("page","pys_current/buysell.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
}
	
	
	
	
	
	
	
	