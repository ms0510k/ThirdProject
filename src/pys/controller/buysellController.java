package pys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pys.dao.inoutDAO;
import pys.vo.exVO;
import pys.vo.moneyVO;
import pys.vo.tradeVO;

@WebServlet("/buysell.do")
public class buysellController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String context = request.getContextPath();
		if (cmd.equals("buy")) {
			buy(request, response);
		}else if (cmd.equals("sell")) {
			sell(request, response);
		}
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//코인종류
		String coin = request.getParameter("coin");
		//고객번호
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		//주문수량	 buy_max
		double buy_max = Double.parseDouble(request.getParameter("buy_max"));

		//주문가격	buy_input_price
		int buy_input_price = Integer.parseInt(request.getParameter("buy_input_price"));
		
		//주문금액	buy_order_price
		int buy_order_price = Integer.parseInt(request.getParameter("val1"));
		
		//수수료 (약 1%)	 buy_order_commission
		double buy_order_commission = Double.parseDouble(request.getParameter("val2"));
		
		//총 매수량 (약)	buy_order_amount
		double buy_order_amount = Double.parseDouble(request.getParameter("val3"));

	
	
		int fee = (int)(buy_order_commission*buy_input_price);
	
	
		System.out.println("추금할떄 다찍힘? buy_max:" +buy_max + ", buy_input_price :" + buy_input_price
				+ ", buy_order_price: " + buy_order_price+ ", buy_order_commission: " 
				+ buy_order_commission+ ", buy_order_amount: " + buy_order_amount);
		tradeVO vo = new tradeVO(null, coin, buy_order_amount, "미체결", buy_order_price, memnum,fee);
		inoutDAO dao = new inoutDAO();
		// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
		
		//미체결 신청함
		int row = dao.tradein(vo);
		
		exVO vo2 = new exVO(0, memnum, "", buy_order_price);
		//일단 누적테이블에 현금 해당 금액만큼 - 해놓음
		dao.tradein_cash(vo2);
		if (row > 0) {
			request.setAttribute("page", "first.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		} else {

			request.setAttribute("errMsg", "inoutdao tradein 메소드에서 에러 발생");

			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		}

	
	}
	
	
	
	private void sell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*	int exnum = Integer.parseInt(request.getParameter("exnum"));
			int memnum = Integer.parseInt(request.getParameter("memnum"));
			int outmoney = Integer.parseInt(request.getParameter("output_price"));

			System.out.println("추금할떄 다찍힘? exnum:" + exnum + ", memnum :" + memnum + ", moneyt: " + outmoney);

			moneyVO vo = new moneyVO(exnum, memnum, outmoney, "미승인");
			inoutDAO dao = new inoutDAO();
			// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
			int result = dao.outflag(memnum);*/

			System.out.println("팔기컨트롤러들어옴");


			/*if (row > 0) {
				request.setAttribute("page", "first.jsp");
				request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
			} else {

				request.setAttribute("errMsg", "inoutdao out 메소드에서 에러 발생");

				request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
			}
	*/
			request.setAttribute("page","first.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		}
	
	
	
}
