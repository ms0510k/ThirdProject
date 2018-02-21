package kms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kms.dao.MemberDao;
import kms.dao.NoticeDao;
import kms.vo.MemberVo;
import kms.vo.NoticeVo;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	      String cmd=request.getParameter("cmd");
	      String context=request.getContextPath();
	      if(cmd.equals("member_insert")) {
	         member_insert(request,response);
	      }
	}
	private void member_insert(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      String name=request.getParameter("name");
	      String email=request.getParameter("email");
	      String pwd=request.getParameter("pwd");
	      String phone=request.getParameter("phone");
	      String bank=request.getParameter("bank");
	      int account=Integer.parseInt(request.getParameter("account"));
	      MemberDao dao=new MemberDao();
	      MemberVo vo=new MemberVo(0, name, email, pwd, phone, bank, account, null);
	      int n=dao.insert(vo);
	      if(n>0) {
	    	  response.sendRedirect(request.getContextPath()+"/kms_member/member_login.jsp");
	       }else {
	          request.setAttribute("result","fail");
	       }
	       }
}
