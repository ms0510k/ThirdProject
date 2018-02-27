package pys.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/marketprice.do")
public class MarketPriceController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();
		//System.out.println("context:" + context);
		if(cmd.equals("btc")) {
			btc(request,response);
		}else if(cmd.equals("eth")) {
			insert(request,response);
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
	private void btc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//4. ������� ����ϱ� ���� ���������� �̵��ϱ�
		request.setAttribute("coin","btc");
		request.getRequestDispatcher("/Main.jsp").forward(request, response);
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



























