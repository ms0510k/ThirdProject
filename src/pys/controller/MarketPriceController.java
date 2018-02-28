package pys.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pys.dao.inoutDAO;
import pys.vo.exVO;
import pys.vo.tradeVO;

@WebServlet("/marketprice.do")
public class MarketPriceController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();
		//System.out.println("context:" + context);
		if(cmd.equals("buy")) {
			buy(request,response);
		}else if(cmd.equals("sell")) {
			sell(request,response);
		}else if(cmd.equals("xrp")) {
			list(request,response);
		}else if(cmd.equals("btg")) {
			delete(request,response);
		}else if(cmd.equals("qtum")) {
			update(request,response);
		}else if(cmd.equals("updateOk")) {
			//updateOk(request,response);
		}
	}
	private void sell(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String coin=request.getParameter("coin");
		String email = (String) request.getSession().getAttribute("email");

		System.out.println("차트에서 클릭시 넘어오는 이멜 : "+email);
		
		//먼저 이메일주소로 고객번호 찾기
		inoutDAO dao = new inoutDAO();
		int memnum = dao.fintNum(email);
		System.out.println("고객번호는 " +memnum);
		//해당 고객의 거래내역 같이 뿌려주기
		ArrayList<exVO> eList = dao.exlist(memnum);
		ArrayList<tradeVO> tList = dao.tradelistAll(memnum,coin);
		
		
		
		//받은 고객번호와 코인정보를 buysell.jsp 로 보내기!
		request.setAttribute("type","sell");
		request.setAttribute("coin",coin);
		request.setAttribute("memnum", memnum);
		request.setAttribute("eList", eList);
		request.setAttribute("tList", tList);
		request.setAttribute("page","pys_current/buysell.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	private void buy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String coin=request.getParameter("coin");
		String email = (String) request.getSession().getAttribute("email");

		System.out.println("차트에서 클릭시 넘어오는 이멜 : "+email);
		
		//먼저 이메일주소로 고객번호 찾기
		inoutDAO dao = new inoutDAO();
		int memnum = dao.fintNum(email);
		System.out.println("고객번호는 " +memnum);
		//해당 고객의 거래내역 같이 뿌려주기
		ArrayList<exVO> eList = dao.exlist(memnum);
		ArrayList<tradeVO> tList = dao.tradelistAll(memnum,coin);
		
		//받은 고객번호와 코인정보를 buysell.jsp 로 보내기!
		request.setAttribute("type","buy");
		request.setAttribute("coin",coin);
		request.setAttribute("memnum", memnum);
		request.setAttribute("eList", eList);
		request.setAttribute("tList", tList);
		request.setAttribute("page","pys_current/buysell.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	String id=request.getParameter("id");
		MyUsersDao dao=new MyUsersDao();
		test.beans.MyUsers user=dao.getinfo(id);*/
		request.getRequestDispatcher("/users/update.jsp").forward(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	String id=request.getParameter("id");
		MyUsersDao dao=new MyUsersDao();
		int n=dao.delete(id);
		if(n>0) {
			response.sendRedirect(request.getContextPath()+"/myusers.do?cmd=list");
		}else {
			request.setAttribute("result","fail");
			request.getRequestDispatcher("/users/result.jsp").forward(request, response);
		}*/
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.DB���� ��ü ȸ����� ������
	/*	MyUsersDao dao=new MyUsersDao();
		ArrayList<test.beans.MyUsers> mlist=dao.listAll();
		//2.ȸ������� �������� ���
		request.setAttribute("mlist",mlist);
		//3.����� ������ ���������� �̵�
*/		RequestDispatcher rd=request.getRequestDispatcher("/users/list.jsp");
		rd.forward(request, response);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
	/*	String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");		
		test.beans.MyUsers user=new test.beans.MyUsers(id, pwd, name, email, phone);
		MyUsersDao dao=new MyUsersDao();
		int n=dao.insert(user);
		//3. ����� �������� ���
		if(n>0) {
			request.setAttribute("result","success");
		}else {
			request.setAttribute("result","fail");
		}*/
		//4. ������� ����ϱ� ���� ���������� �̵��ϱ�
		RequestDispatcher rd=request.getRequestDispatcher("/users/result.jsp");
		rd.forward(request, response);
	}
}



























