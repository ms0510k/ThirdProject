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
		String context=request.getContextPath();
		System.out.println("커맨드는 : "+cmd);
		if(cmd.equals("main")) {
			main(request,response);
		}else if(cmd.equals("chart")) {
			now(request,response);
		}else if(cmd.equals("trade")) {
			trade(request,response);
		}else if(cmd.equals("inout")) {
			inout(request,response);
		}else if(cmd.equals("mylist")) {
			mylist(request,response);
		}
	}
	
	
	
	
	private void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg=request.getParameter("msg");
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp?msg="+msg).forward(request, response);
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
		String email = (String) request.getSession().getAttribute("email");
	
		inoutDAO dao = new inoutDAO();
		int memnum = dao.fintNum(email);
		
		
		//해당 고객의 거래내역 같이 뿌려주기
				ArrayList<exVO> eList = dao.exlist(memnum);
				ArrayList<tradeVO> tList = dao.tradelistAll(memnum,"BTC");
				
				//받은 고객번호와 코인정보를 buysell.jsp 로 보내기!
				request.setAttribute("type","buy");
				request.setAttribute("coin","BTC");
				request.setAttribute("memnum", memnum);
				request.setAttribute("eList", eList);
				request.setAttribute("tList", tList);

		
		
		request.setAttribute("page","pys_current/buysell.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	private void inout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String)request.getParameter("email");
		inoutDAO dao = new inoutDAO();
		int memnum = dao.fintNum(email);
		
		ArrayList<exVO> eList= dao.exlist(memnum);
		ArrayList<tradeVO> tList = dao.tradelist(memnum);
		
		request.setAttribute("eList",eList);
		request.setAttribute("tList", tList);
		request.setAttribute("page","pys_current/in_out.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	
	private void mylist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String)request.getParameter("email");
		inoutDAO dao = new inoutDAO();
		int memnum = dao.fintNum(email);
		ArrayList<tradeVO> tList = dao.tradelist(memnum);
		
		
		
		//보유수량구하기
		double amount = 0; 
		
		
		
		//매수평균가 구하기
		
		
		
		
		//매수금액 구하기
	/*	민수,다영,세용,
		강희,민아,재희,
		윤수,정규,혁수,
		
		민수,혁수,세용,
		강희,민아,재희,
		윤수,정규,다영,
		
		
		세용,민수
		강희,
		윤수,
		
		
		민수,세용,민아,지호,형진
		강희,재희,성준,영진,혁수
		윤수,정규,다영,민성,송희
		
		
		
		
		형진 송희
		지호 영진
		민성 성준
		*/
		
		
		
		//평가금액 구하기 
		
		
		
		
		//평가손익 구하기
		
		
		
		
		
		request.setAttribute("page","pys_current/mylist.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
}
	
	
	
	
	
	
	
	