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
		String context=request.getContextPath();//���ؽ�Ʈ�� ������
		System.out.println(cmd);
		if(cmd.equals("out")) {
			out(request,response);
		}else if(cmd.equals("chart")) {
			now(request,response);
		}else if(cmd.equals("trade")) {
			trade(request,response);
		}else if(cmd.equals("inout")) {
			inout(request,response);
		}
	}
	
	
	
	
	private void out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int exnum = Integer.parseInt(request.getParameter("exnum"));
		int memnum = Integer.parseInt(request.getParameter("memnum"));
		moneyVO vo = new moneyVO(exnum, memnum, "미승인");
		inoutDAO dao = new inoutDAO();
		
		int row = dao.out(vo);
		
	if(row>0){
		request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}else {
		request.setAttribute("errMsg", "���������� ����ó���� ���� �ʾҽ��ϴ�");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
		
		
		
		/*request.setAttribute("page","first.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);*/
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
	
	//�Ա� ����ϱ�
	private void inout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.DB���� �ŷ����� ���� ��������
		String email = (String)request.getParameter("email");
		System.out.println("����� �̸��� : "+email);
		inoutDAO dao = new inoutDAO();
		//���� �̸��Ϸ� ����ȣ ��������
		int memnum = dao.fintNum(email);
		
		//������ ����ȣ�� �� �ŷ����� ��������
		ArrayList<exVO> eList= dao.exlist(memnum);
		System.out.println("ex ����Ʈ üũ : "+eList.toString());
		ArrayList<tradeVO> tList = dao.tradelist(memnum);
		System.out.println("trade ����Ʈ üũ : "+tList.toString());
		
		//2.ȸ������� �������� ���
		request.setAttribute("eList",eList);
		request.setAttribute("tList", tList);
		//3.����� ������ ���������� �̵�
		request.setAttribute("page","pys_current/in_out.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
}
	
	
	
	
	
	
	
	