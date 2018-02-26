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
import javax.servlet.http.HttpSession;

import pys.dao.inoutDAO;
import pys.vo.exVO;
import pys.vo.tradeVO;

@WebServlet("/header.do")
public class HeaderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();//占쏙옙占쌔쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙
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
	
	//입금 출금하기
	private void inout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.DB에서 거래내역 양쪽 가져오기
		String email = (String)request.getParameter("email");
		System.out.println("입출금 이메일 : "+email);
		inoutDAO dao = new inoutDAO();
		//먼저 이메일로 고객번호 가져오기
		int memnum = dao.fintNum(email);
		
		//가져온 고객번호로 고객 거래내역 가져오기
		ArrayList<exVO> eList= dao.exlist(memnum);
		System.out.println("ex 리스트 체크 : "+eList.toString());
		ArrayList<tradeVO> tList = dao.tradelist(memnum);
		System.out.println("trade 리스트 체크 : "+tList.toString());
		
		//2.회원목록을 스코프에 담기
		request.setAttribute("eList",eList);
		request.setAttribute("tList", tList);
		//3.결과를 보여줄 뷰페이지로 이동
		request.setAttribute("page","pys_current/in_out.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
}
	
	
	
	
	
	
	
	