package pys.controller;

import java.io.IOException;
import java.util.ArrayList;

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
		} else if (cmd.equals("sell")) {
			sell(request, response);
		} else if (cmd.equals("cancel")) {
			cancel(request, response);
		}else if (cmd.equals("tcheck")) {
			tcheck(request, response);
		}
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 코인종류
		String coin = request.getParameter("coin");
		// 고객번호
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		// 주문수량 buy_max
		double buy_max = Double.parseDouble(request.getParameter("buy_max"));

		// 주문가격 buy_input_price
		int buy_input_price = Integer.parseInt(request.getParameter("buy_input_price"));

		// 주문금액 buy_order_price
		int buy_order_price = Integer.parseInt(request.getParameter("val1"));

		// 수수료 (약 1%) buy_order_commission
		double buy_order_commission = Double.parseDouble(request.getParameter("val2"));

		// 총 매수량 (약) buy_order_amount
		double buy_order_amount = Double.parseDouble(request.getParameter("val3"));

		int fee = (int) (buy_order_commission * buy_input_price);

		/*
		 * System.out.println("추금할떄 다찍힘? buy_max:" +buy_max + ", buy_input_price :" +
		 * buy_input_price + ", buy_order_price: " + buy_order_price+
		 * ", buy_order_commission: " + buy_order_commission+ ", buy_order_amount: " +
		 * buy_order_amount);
		 */

		tradeVO vo = new tradeVO(0, null, coin, buy_order_amount, "미체결_구매", buy_input_price, memnum, fee);
		buysellDAO dao = new buysellDAO();
		// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리

		// 미체결 신청함
		int row = dao.tradein(vo);

		exVO vo2 = new exVO(memnum, "krw", buy_order_price, 0);
		// 일단 누적테이블에 현금 해당 금액만큼 - 해놓음
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
		// 코인종류
		String coin = request.getParameter("coin");
		// 고객번호
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		// 주문수량 sell_max
		double sell_max = Double.parseDouble(request.getParameter("sell_max"));

		// 주문가격 sell_input_price
		int sell_input_price = Integer.parseInt(request.getParameter("sell_input_price"));

		// 수수료 (약 1%) sell_order_commission
		int sell_order_commission = Integer.parseInt(request.getParameter("val_1"));

		// 총 매도금액 (약) sell_order_amount
		int sell_order_amount = Integer.parseInt(request.getParameter("val_2"));

		
		 /* System.out.println("추금할떄 다찍힘? coin:" +coin + ", memnum :" + memnum +
		  ", sell_max: " + sell_max+ ", sell_input_price: " + sell_input_price+
		  ", sell_order_commission: " + sell_order_commission+ ", sell_order_amount: "
		  +sell_order_amount);*/
		 

		tradeVO vo = new tradeVO(0, null, coin, sell_max, "미체결_판매", sell_input_price, memnum, sell_order_commission);
		buysellDAO dao = new buysellDAO();
		// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리

		// 미체결 신청함
		int row = dao.tradein(vo);

		exVO vo2 = new exVO(memnum, coin, 0, sell_max);
		// 일단 누적테이블에 현금 해당 금액만큼 - 해놓음
		dao.tradein_coin(vo2);
		if (row > 0) {
			request.setAttribute("page", "first.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		} else {

			request.setAttribute("errMsg", "inoutdao tradein 메소드에서 에러 발생");

			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		}

	}

	//미채결 거래 취소 누를 시 기존 거래 취소 삭제 후 후 exchange 테이블에 해당 금액만큼 늘리기
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 코인종류
		int tnum = Integer.parseInt(request.getParameter("tnum"));
		System.out.println("취소들어옴 번호는? "+tnum);
		buysellDAO dao = new buysellDAO();
		tradeVO vo = dao.searchT(tnum);
		
		//구매인 경우 현금을 다시 올려준다 아닌경우 수량을 늘려준다
		if(vo.getTradetype().equals("미체결_구매")) {
			double amount = vo.getCoinamount()+(vo.getFee()/(double)vo.getTprice());
			vo.setCoinamount(amount);
			/*System.out.println("vo 상태는 : "+vo.toString());*/
			dao.tradein_cancel(vo);
			int row = dao.exin_cancel(vo);
			if (row > 0) {
				request.setAttribute("page", "first.jsp");
				request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
			}
		}else if(vo.getTradetype().equals("미체결_판매")) {
			//먼저 현재 남은 코인 갯수 가져오기 그후 남은 코인과 합산
			dao.tradein_cancel(vo);
			int row = dao.exin_cancel(vo);
			if (row > 0) {
				request.setAttribute("page", "first.jsp");
				request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
			}
			
		}
	}

	
	
	
	
	
	//메인이 실행되면 1초마다 실행되는 문장으로 
		private void tcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int BTC = Integer.parseInt(request.getParameter("BTC"));
		int ETH = Integer.parseInt(request.getParameter("ETH"));
		int XRP = Integer.parseInt(request.getParameter("XRP"));
		int BTG = Integer.parseInt(request.getParameter("BCH"));
		int QTUM = Integer.parseInt(request.getParameter("QTUM"));
		//System.out.println("비트코인:"+BTC+", 이더 : "+ETH+", 리플:"+XRP+", 캐시:"+BTG+", 퀀텀:"+QTUM);
			
		//일단 미체결 리스트 받아옴
		buysellDAO dao = new buysellDAO();
		ArrayList<tradeVO> tList = dao.reserveList();
		for (int i = 0; i < tList.size(); i++) {
			
			//수수료 처리해주기
			
			//구매관련 처리해주기
			if(tList.get(i).getTradetype().equals("미체결_구매")) {
				
				
				
				if(tList.get(i).getCoin().equals("BTC") && tList.get(i).getTprice() <= BTC) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_구매");
					dao.buyok_ex(tList.get(i).getMemnum(), "BTC",tList.get(i).getCoinamount());
					dao.feein(tList.get(i).getFee());
				}else if(tList.get(i).getCoin().equals("ETH") && tList.get(i).getTprice() <= ETH) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_구매");
					dao.buyok_ex(tList.get(i).getMemnum(), "ETH",tList.get(i).getCoinamount());
					dao.feein(tList.get(i).getFee());
				}else if(tList.get(i).getCoin().equals("XRP") && tList.get(i).getTprice() <= XRP) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_구매");
					dao.buyok_ex(tList.get(i).getMemnum(), "XRP",tList.get(i).getCoinamount());
					dao.feein(tList.get(i).getFee());
				}
				else if(tList.get(i).getCoin().equals("BTG") && tList.get(i).getTprice() <= BTG) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_구매");
					dao.buyok_ex(tList.get(i).getMemnum(), "BTG",tList.get(i).getCoinamount());
					dao.feein(tList.get(i).getFee());
				}
				
				else if (tList.get(i).getCoin().equals("QTUM") && tList.get(i).getTprice() <= QTUM) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_구매");
					dao.buyok_ex(tList.get(i).getMemnum(), "QTUM",tList.get(i).getCoinamount());
					dao.feein(tList.get(i).getFee());
				}
				
			}else if(tList.get(i).getTradetype().equals("미체결_판매")) {
				
				int money = (int)(tList.get(i).getCoinamount()*tList.get(i).getTprice() -tList.get(i).getFee());
				if(tList.get(i).getCoin().equals("BTC") && tList.get(i).getTprice() <= BTC) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_판매");
					dao.sellok_ex(tList.get(i).getMemnum(), money);
					dao.feein(tList.get(i).getFee());
				}else if(tList.get(i).getCoin().equals("ETH") && tList.get(i).getTprice() <= ETH) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_판매");
					dao.sellok_ex(tList.get(i).getMemnum(), money);
					dao.feein(tList.get(i).getFee());
				}else if(tList.get(i).getCoin().equals("XRP") && tList.get(i).getTprice() <= XRP) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_판매");
					dao.sellok_ex(tList.get(i).getMemnum(), money);
					dao.feein(tList.get(i).getFee());
				}
				else if(tList.get(i).getCoin().equals("BTG") && tList.get(i).getTprice() <= BTG) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_판매");
					dao.sellok_ex(tList.get(i).getMemnum(), money);
					dao.feein(tList.get(i).getFee());
				}
				
				else if (tList.get(i).getCoin().equals("QTUM") && tList.get(i).getTprice() <= QTUM) {
					dao.tradeok_t(tList.get(i).getTnum(), "체결_판매");
					dao.sellok_ex(tList.get(i).getMemnum(), money);
					dao.feein(tList.get(i).getFee());
				}
				
			}
		}
		
		}

	
		
		
	
}
