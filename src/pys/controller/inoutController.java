package pys.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pys.dao.inoutDAO;
import pys.vo.exVO;
import pys.vo.moneyVO;
import pys.vo.tradeVO;

@WebServlet("/inout.do")
public class inoutController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();
		System.out.println(cmd);
		if(cmd.equals("out")) {
			out(request,response);
		}else if(cmd.equals("in")) {
			in(request,response);
		}
	}
	
	
	
	
	private void out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int exnum = Integer.parseInt(request.getParameter("exnum"));
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		int outmoney = Integer.parseInt(request.getParameter("output_price"));
		
		System.out.println("추금할떄 다찍힘? exnum:"+exnum + ", memnum :"+memnum+", moneyt: "+outmoney);
		
		moneyVO vo = new moneyVO(exnum, memnum, outmoney,"미승인");
		inoutDAO dao = new inoutDAO();
		//출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
		int result = dao.outflag(memnum);
		
		System.out.println("결과는 : "+result);
		
		int row = dao.out(vo);
		
	if(row>0){
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}else {

		request.setAttribute("errMsg", "inoutdao out 메소드에서 에러 발생");

		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
		
		
		
	
	}
	
	
	private void in(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		int inmoney = Integer.parseInt(request.getParameter("input_price"));
		
		System.out.println("입금할떄 다찍힘?  memnum :"+memnum+", moneyt: "+inmoney);
		
		
		
		inoutDAO dao = new inoutDAO();
		
		
		exVO exvo = new exVO(0, memnum, "krw", inmoney,0);
		tradeVO tvo = new tradeVO(null, "krw", 0, "입금", inmoney, memnum,0);
		int row = dao.in(tvo);
		int row2 = dao.in2(exvo);
		
		
		System.out.println("결과는1  :"+row);
		System.out.println("결과는2  :"+row2);
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	
		
		
	
	}
	
	
	/*private void now(HttpServletRequest request, HttpServletResponse response)
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
	*/
	

	
}
	
	
	
	
	
	
	
	