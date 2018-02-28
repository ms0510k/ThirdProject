package pys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pys.dao.inoutDAO;
import pys.vo.moneyVO;

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
	/*	int exnum = Integer.parseInt(request.getParameter("exnum"));
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		int outmoney = Integer.parseInt(request.getParameter("output_price"));

		System.out.println("추금할떄 다찍힘? exnum:" + exnum + ", memnum :" + memnum + ", moneyt: " + outmoney);

		moneyVO vo = new moneyVO(exnum, memnum, outmoney, "미승인");
		inoutDAO dao = new inoutDAO();
		// 출금신청을 하는 고객이 이미 출금신청 내역이 있고 미승인상태라면 출금안되게 처리
		int result = dao.outflag(memnum);*/

		System.out.println("사기컨트롤러들어옴");


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
