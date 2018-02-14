package kms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kms.dao.NoticeDao;
import kms.vo.NoticeVo;
@WebServlet("/admin.do")
public class AdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();
		if(cmd.equals("notice")) {
			response.sendRedirect(context+"/kms_admin/notice_list.jsp");
		}else if(cmd.equals("notice_insert")) {
			response.sendRedirect(context+"/kms_admin/notice_insert.jsp");
		}else if(cmd.equals("notice_insertOk")){
			notice_insert(request,response);
		}
	}
	private void notice_insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String nottitle=request.getParameter("nottitle");
	String notcontent=request.getParameter("notcontent");	

	NoticeVo nv=new NoticeVo(0, nottitle, notcontent, 0, null);
	NoticeDao dao=new NoticeDao();
	int n=dao.insert(nv);
	
	if(n>0) {
		request.setAttribute("result","success");
	}else {
		request.setAttribute("result","fail");
	}
	RequestDispatcher rd=request.getRequestDispatcher("/kms_admin/notice_list.jsp");
	rd.forward(request, response);
	}
	}
