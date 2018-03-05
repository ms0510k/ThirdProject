package kms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kms.dao.CompDao;
import kms.dao.MemberDao;
import kms.dao.NoticeDao;
import kms.vo.CompVo;
import kms.vo.MemberVo;
import kms.vo.NoticeVo;
import pys.dao.inoutDAO;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	      String cmd=request.getParameter("cmd");
	      if(cmd.equals("insert_member")) {
	         member_insert(request,response);
	      }else if(cmd.equals("member_login")) {
	    	  member_login(request,response);
	      }else if(cmd.equals("member_logout")) {
	    	  member_logout(request,response);
	      }else if(cmd.equals("loginForm")) {
	    	  loginForm(request,response);
	      }else if(cmd.equals("joinForm")) {
	    	  joinForm(request,response);
	      }else if(cmd.equals("idForm")) {
	    	  idForm(request,response);
	      }else if(cmd.equals("pwForm")) {
	    	  pwForm(request,response);
	      }else if(cmd.equals("mypage_comp")) {
	    	  mypage_comp(request,response);
	      }else if(cmd.equals("comp_insert")){
	    	  String email=request.getParameter("email");
	          response.sendRedirect(request.getContextPath()+"/kms_mypage/comp_insert.jsp?email="+email);
	      }else if(cmd.equals("comp_insertOk")) {
	    	  comp_insertOk(request,response);
	      }else if(cmd.equals("comp_list")) {
	    	  comp_list(request,response);
	      }else if(cmd.equals("comp_detail")) {
	    	  comp_detail(request,response);
	      }else if(cmd.equals("member_update")) {
	    	  String email=request.getParameter("email");
	    	  response.sendRedirect(request.getContextPath()+"/kms_member/member_update.jsp?email="+email);
	      }else if(cmd.equals("member_update2")) {
	    	  member_update2(request,response);
	      }else if(cmd.equals("member_updateOk")) {
	    	  member_updateOk(request,response);
	      }else if(cmd.equals("member_delete")) {
	    	  String email=request.getParameter("email");
	    	  response.sendRedirect(request.getContextPath()+"/kms_member/member_delete.jsp?email="+email);
	      }else if(cmd.equals("member_delete2")) {
	    	  member_delete2(request,response);
	      }else if(cmd.equals("member_deleteOk")) {
	    	  member_deleteOk(request,response);
	      }
	}
	private void member_deleteOk(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		   String email=request.getParameter("email");
		   MemberDao dao=new MemberDao();
		   int n=dao.deleteOk(email);
		   if(n>0) {
			   String msg="delok";
			   HttpSession session=request.getSession();
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/header.do?cmd=main&msg="+msg);
		   }else {
		      request.setAttribute("result","fail");
		   }
	}
	private void member_delete2(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
	   MemberDao dao=new MemberDao();
	   MemberVo vo=dao.getinfo(email);
	   request.setAttribute("vo", vo);
	   if(pwd.equals(vo.getPwd())) {
	   request.getRequestDispatcher("/kms_member/member_delete2.jsp").forward(request, response);
	   }else {
		   response.sendRedirect(request.getContextPath()+"/kms_member/member_delete.jsp?email="+email);
	   }
}
	private void member_update2(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			String pwd=request.getParameter("pwd");
			String email=request.getParameter("email");
		   MemberDao dao=new MemberDao();
		   MemberVo vo=dao.getinfo(email);
		   request.setAttribute("vo", vo);
		   if(pwd.equals(vo.getPwd())) {
		   request.getRequestDispatcher("/kms_member/member_update2.jsp").forward(request, response);
		   }else {
			   response.sendRedirect(request.getContextPath()+"/kms_member/member_update.jsp?email="+email);
		   }
	}
	private void member_updateOk(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		   String email=request.getParameter("email");
		   String pwd=request.getParameter("pwd");
		   String phone=request.getParameter("phone");
		   String bank=request.getParameter("bank");
		   int account=Integer.parseInt(request.getParameter("account"));
		   MemberVo vo=new MemberVo(0,null,email,pwd,phone,bank,account,null);
		   MemberDao dao=new MemberDao();
		   int n=dao.updateOk(vo);
		   if(n>0) {
		      RequestDispatcher rd=request.getRequestDispatcher("/header.do?cmd=main");
		      rd.forward(request, response);
		   }else {
		      request.setAttribute("result","fail");
		   }
	}
	
	
	
	
	private void loginForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setAttribute("page", "lyj_member/login_member.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	private void joinForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setAttribute("page", "lyj_member/insert_member.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	private void idForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setAttribute("page", "lyj_member/search_idForm.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	private void pwForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setAttribute("page", "lyj_member/search_pwForm.jsp");
		request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void comp_detail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		int comnum=Integer.parseInt(request.getParameter("comnum"));
		   MemberDao dao=new MemberDao();
		   CompVo vo=dao.getinfo(comnum);
		   request.setAttribute("vo", vo);
		   request.getRequestDispatcher("/kms_mypage/comp_detail.jsp").forward(request, response);
	}
	private void comp_list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String email=request.getParameter("email");
		String spageNum=request.getParameter("pageNum");
	      int pageNum=1;
	      if(spageNum!=null) {
	         pageNum=Integer.parseInt(spageNum);
	      }
	      int startRow=(pageNum-1)*5+1;
	      int endRow=startRow+4;
	      MemberDao dao=new MemberDao();
	      ArrayList<CompVo> list=dao.list(startRow, endRow, email);
	      int pageCount=(int)Math.ceil(dao.getCount(email)/5.0);
	      int startPage=((pageNum-1)/4*4)+1;
	      int endPage=startPage+3;
	      if(pageCount<endPage) {
	         endPage=pageCount;
	      }
	      request.setAttribute("list", list);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);
	      request.setAttribute("pageNum", pageNum);
	      request.getRequestDispatcher("/kms_mypage/comp_list.jsp?email="+email).forward(request, response);
	}
	private void comp_insertOk(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String email=request.getParameter("email");
		 String comtitle=request.getParameter("comtitle");
	      String comcontent=request.getParameter("comcontent");
	      MemberDao dao=new MemberDao();      
	      int n=dao.comp_insertOk(email,comtitle,comcontent);
	      if(n>0) {
	    	  
	    	  response.sendRedirect(request.getContextPath()+"/header.do?cmd=main");
	       }else {
	          request.setAttribute("result","fail");
	       }
	}
	private void mypage_comp(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      String spageNum=request.getParameter("pageNum");
	      int pageNum=1;
	      if(spageNum!=null) {
	         pageNum=Integer.parseInt(spageNum);
	      }
	      int startRow=(pageNum-1)*5+1;
	      int endRow=startRow+4;
	      CompDao dao=new CompDao();
	      ArrayList<CompVo> list=dao.mypage_comp_list(startRow, endRow);
	      int pageCount=(int)Math.ceil(dao.getCount()/5.0);
	      int startPage=((pageNum-1)/4*4)+1;
	      int endPage=startPage+3;
	      if(pageCount<endPage) {
	         endPage=pageCount;
	      }
	      request.setAttribute("list", list);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);
	      request.setAttribute("pageNum", pageNum);
	      request.getRequestDispatcher("/kms_mypage/mypage_comp_list.jsp").forward(request, response);
	   }
	protected void member_logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/header.do?cmd=main");
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
		System.out.println("로그인 결과는 :"+n);
		if(n==1) {
			HttpSession session=request.getSession();
			session.setAttribute("email", email);
			request.setAttribute("page","first.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		}else if(n==0){
			
			request.setAttribute("errMsg", "아이디 또는 비밀번호를 확인해 주세요.");
			request.setAttribute("page","lyj_member/login_member.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "아이디 또는 비밀번호를 확인해 주세요.");
			request.setAttribute("page","lyj_member/login_member.jsp");
			request.getRequestDispatcher("/MainContent.jsp").forward(request, response);
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
	      inoutDAO indao = new inoutDAO();
	      MemberVo vo=new MemberVo(0, name, email, pwd, phone, bank, account, null);
	      
	      int n=dao.insert(vo);
	      if(n>0) {
	    	  String msg="meminok";
	    	  int memnum = indao.fintNum(email);
	    	  dao.exInsert(memnum);
	    	  dao.tInsert(memnum);
	    	  response.sendRedirect(request.getContextPath()+"/header.do?cmd=main&msg="+msg);
	       }else {
	          request.setAttribute("result","fail");
	       }
	       }
}
