package pys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pys.dao.buysellDAO;
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
	
	
	/*	System.out.println("추금할떄 다찍힘? buy_max:" +buy_max + ", buy_input_price :" + buy_input_price
				+ ", buy_order_price: " + buy_order_price+ ", buy_order_commission: " 
				+ buy_order_commission+ ", buy_order_amount: " + buy_order_amount);*/
		
		
		tradeVO vo = new tradeVO(null, coin, buy_order_amount, "미체결_구매", buy_input_price, memnum,fee);
		buysellDAO	dao = new buysellDAO();
		// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
		
		//미체결 신청함
		int row = dao.tradein(vo);
		
		exVO vo2 = new exVO(0, memnum, "krw", buy_order_price,0);
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
				//코인종류
				String coin = request.getParameter("coin");
				//고객번호
				int memnum = Integer.parseInt(request.getParameter("memnum"));
				//주문수량	 sell_max
				double sell_max = Double.parseDouble(request.getParameter("sell_max"));

				//주문가격	sell_input_price
				int sell_input_price = Integer.parseInt(request.getParameter("sell_input_price"));
				
				
				//수수료 (약 1%)	 sell_order_commission
				int sell_order_commission = Integer.parseInt(request.getParameter("val_1"));
				
				//총 매도금액 (약)	sell_order_amount
				int sell_order_amount = Integer.parseInt(request.getParameter("val_2"));

				/*System.out.println("추금할떄 다찍힘? coin:" +coin + ", memnum :" + memnum
						+ ", sell_max: " + sell_max+ ", sell_input_price: " 
						+ sell_input_price+ ", sell_order_commission: " + sell_order_commission+
						", sell_order_amount: " +sell_order_amount);
				*/
				
				
				tradeVO vo = new tradeVO(null, coin, sell_order_amount, "미체결_판매", sell_input_price, memnum,sell_order_commission);
				buysellDAO	dao = new buysellDAO();
				// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
				
				//미체결 신청함
				int row = dao.tradein(vo);
				
				exVO vo2 = new exVO(0, memnum, coin,0, sell_max);
				//일단 누적테이블에 현금 해당 금액만큼 - 해놓음
				dao.tradein_coin(vo2);
				if (row > 0) {
					request.setAttribute("page", "first.jsp");
					request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
				} else {

					request.setAttribute("errMsg", "inoutdao tradein 메소드에서 에러 발생");

					request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
				}

				
		}
	
	
	
}
