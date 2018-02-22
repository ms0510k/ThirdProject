package kms.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kms.dao.MemberDao;
import kms.vo.MemberVo;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	      String cmd=request.getParameter("cmd");
	      if(cmd.equals("member_insert")) {
	         member_insert(request,response);
	      }else if(cmd.equals("member_login")) {
	    	  member_login(request,response);
	      }else if(cmd.equals("member_logout")) {
	    	  member_logout(request,response);
	      }
	}
	protected void member_logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/header.jsp");
	}
	protected void member_login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		HashMap<String, String> map=new HashMap<>();
		map.put("email", email);
		map.put("pwd", pwd);
		kms.dao.LoginDao dao=kms.dao.LoginDao.getInstance();
		int n=dao.isMember(map);
		if(n==1) {
			HttpSession session=request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect(request.getContextPath()+"/header.jsp");
		}else if(n==0){
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 틀립니다.");
			request.getRequestDispatcher("/kms_member/member_login.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "오류로 인해 로그인실패");
			request.getRequestDispatcher("/kms_member/member_login.jsp").forward(request, response);
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
