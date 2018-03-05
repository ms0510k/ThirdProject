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
import pys.vo.listVO;
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
		ArrayList<tradeVO> tList = dao.accepttradeList(memnum);

		listVO btc = new listVO("BTC", 0, 0);
		listVO eth = new listVO("ETH", 0, 0);
		listVO xrp = new listVO("XRP", 0, 0);
		listVO bch = new listVO("BCH", 0, 0);
		listVO qtum = new listVO("QTUM", 0, 0);
		
		
		//코인 5개 각각 넣어주어서 처리하기
		for (int i = 0; i < tList.size(); i++) {
			if(tList.get(i).getCoin().equals("BTC")) {
				if(tList.get(i).getTradetype().equals("체결_구매")) {
				btc.setAmount(btc.getAmount()+tList.get(i).getCoinamount());
				btc.setTotal(btc.getTotal()+(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
				}else if(tList.get(i).getTradetype().equals("체결_판매")) {
					btc.setAmount(btc.getAmount()-tList.get(i).getCoinamount());
					btc.setTotal(btc.getTotal()-(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
				}
			
			}else if(tList.get(i).getCoin().equals("ETH")) {
				if(tList.get(i).getTradetype().equals("체결_구매")) {
				eth.setAmount(eth.getAmount()+tList.get(i).getCoinamount());
				eth.setTotal(eth.getTotal()+(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
				}else if(tList.get(i).getTradetype().equals("체결_판매")) {
					eth.setAmount(eth.getAmount()-tList.get(i).getCoinamount());
					eth.setTotal(eth.getTotal()-(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
				}
			
			}else if(tList.get(i).getCoin().equals("XRP")) {
				if(tList.get(i).getTradetype().equals("체결_구매")) {
					xrp.setAmount(xrp.getAmount()+tList.get(i).getCoinamount());
					xrp.setTotal(xrp.getTotal()+(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}else if(tList.get(i).getTradetype().equals("체결_판매")) {
					xrp.setAmount(xrp.getAmount()-tList.get(i).getCoinamount());
					xrp.setTotal(xrp.getTotal()-(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}
			
			}else if(tList.get(i).getCoin().equals("BCH")) {
				if(tList.get(i).getTradetype().equals("체결_구매")) {
					bch.setAmount(bch.getAmount()+tList.get(i).getCoinamount());
					bch.setTotal(bch.getTotal()+(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}else if(tList.get(i).getTradetype().equals("체결_판매")) {
						bch.setAmount(bch.getAmount()-tList.get(i).getCoinamount());
						bch.setTotal(bch.getTotal()-(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}
			
			}else if(tList.get(i).getCoin().equals("QTUM")) {
				if(tList.get(i).getTradetype().equals("체결_구매")) {
					qtum.setAmount(qtum.getAmount()+tList.get(i).getCoinamount());
					qtum.setTotal(qtum.getTotal()+(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}else if(tList.get(i).getTradetype().equals("체결_판매")) {
						qtum.setAmount(qtum.getAmount()-tList.get(i).getCoinamount());
						qtum.setTotal(qtum.getTotal()-(tList.get(i).getCoinamount()*tList.get(i).getTprice()-tList.get(i).getFee()));
					}
			}
		}
		
		
		
		
		request.setAttribute("BTC", btc);
		request.setAttribute("ETH", eth);
		request.setAttribute("XRP", xrp);
		request.setAttribute("BTG", bch);
		request.setAttribute("QTUM", qtum);
		request.setAttribute("page","pys_current/mylist.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
}
	
	
	
	
	
	
	
	