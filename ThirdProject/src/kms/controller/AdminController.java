package kms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kms.dao.CompDao;
import kms.dao.NoticeDao;
import kms.vo.CompVo;
import kms.vo.NoticeVo;
@WebServlet("/admin.do")
public class AdminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		String context=request.getContextPath();
		if(cmd.equals("notice")) {
			notice_list(request,response);
		}else if(cmd.equals("notice_insert")) {
			response.sendRedirect(context+"/kms_admin/notice_insert.jsp");
		}else if(cmd.equals("notice_insertOk")){
			notice_insert(request,response);
		}else if(cmd.equals("detail")){
			notice_detail(request,response);
		}else if(cmd.equals("delete")){
			notice_delete(request,response);
		}else if(cmd.equals("update")){
			notice_update(request,response);
		}else if(cmd.equals("updateOk")) {
			notice_updateOk(request,response);
		}else if(cmd.equals("search")) {
			notice_search(request,response);
		}else if(cmd.equals("comp")) {
			comp_list(request,response);
		}else if(cmd.equals("comp_detail")) {
			comp_detail(request,response);
		}else if(cmd.equals("comp_result")) {
			comp_result(request,response);
		}
	}
	private void comp_result(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int comnum=Integer.parseInt(request.getParameter("comnum"));
	String result=request.getParameter("result");
	CompVo vo=new CompVo(comnum,0,null,null,null,result,0,null);
	CompDao dao=new CompDao();
	int n=dao.update_result(vo);
	if(n>0) {
		RequestDispatcher rd=request.getRequestDispatcher("/admin.do?cmd=comp");
		rd.forward(request, response);
	}else {
		request.setAttribute("result","fail");
	}
	}
	private void comp_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int comnum=Integer.parseInt(request.getParameter("comnum"));
	
	CompDao dao=new CompDao();
	CompVo vo=dao.getinfo(comnum);
	request.setAttribute("vo", vo);
	request.getRequestDispatcher("/kms_admin/comp_detail.jsp").forward(request, response);
	}
	private void comp_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		CompDao dao=new CompDao();
		ArrayList<CompVo> list=dao.list(startRow, endRow);
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
		request.getRequestDispatcher("/kms_admin/comp_list.jsp").forward(request, response);
	}
	private void notice_search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	String search=request.getParameter("search");
	String word=request.getParameter("word");
	String spageNum=request.getParameter("pageNum");
	int pageNum=1;
	if(spageNum!=null) {
		pageNum=Integer.parseInt(spageNum);
	}
	int startRow=(pageNum-1)*5+1;
	int endRow=startRow+4;
	NoticeDao dao=new NoticeDao();
		ArrayList<NoticeVo> list=dao.noticesearch(search,word,startRow,endRow);
		int pageCount=(int)Math.ceil(dao.getCounts(search,word)/5.0);
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
		request.getRequestDispatcher("/kms_admin/notice_searchlist.jsp?search="+search+"&word="+word).forward(request, response);
	}
	private void notice_updateOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int notnum=Integer.parseInt(request.getParameter("notnum"));
	String nottitle=request.getParameter("nottitle");
	String notcontent=request.getParameter("notcontent");
	NoticeVo vo=new NoticeVo(notnum, nottitle, notcontent, 0, null);
	NoticeDao dao=new NoticeDao();
	int n=dao.updateOk(vo);
	if(n>0) {
		RequestDispatcher rd=request.getRequestDispatcher("/admin.do?cmd=notice");
		rd.forward(request, response);
	}else {
		request.setAttribute("result","fail");
	}
	}
	private void notice_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int notnum=Integer.parseInt(request.getParameter("notnum"));
	
	NoticeDao dao=new NoticeDao();
	NoticeVo vo=dao.getinfo(notnum);
	request.setAttribute("vo", vo);
	request.getRequestDispatcher("/kms_admin/notice_update.jsp").forward(request, response);
	}
	private void notice_delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int notnum=Integer.parseInt(request.getParameter("notnum"));
	
	NoticeDao dao=new NoticeDao();
	dao.delete(notnum);
	RequestDispatcher rd=request.getRequestDispatcher("/admin.do?cmd=notice");
	rd.forward(request, response);
	}
	private void notice_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	int notnum=Integer.parseInt(request.getParameter("notnum"));
	
	NoticeDao dao=new NoticeDao();
	NoticeVo vo=dao.getinfo(notnum);
	request.setAttribute("vo", vo);
	request.getRequestDispatcher("/kms_admin/notice_detail.jsp").forward(request, response);
	}
	private void notice_insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String nottitle=request.getParameter("nottitle");
	String notcontent=request.getParameter("notcontent");	

	NoticeVo nv=new NoticeVo(0, nottitle, notcontent, 0, null);
	NoticeDao dao=new NoticeDao();
	int n=dao.insert(nv);
	
	if(n>0) {
		RequestDispatcher rd=request.getRequestDispatcher("/admin.do?cmd=notice");
		rd.forward(request, response);
	}else {
		request.setAttribute("result","fail");
	}
	}
	private void notice_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		NoticeDao dao=new NoticeDao();
		ArrayList<NoticeVo> list=dao.list(startRow, endRow);
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
		request.getRequestDispatcher("/kms_admin/notice_list.jsp").forward(request, response);
	}
	}
