package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//공지사항 목록 처리하는 컨트롤러 
public class NoticeController implements Controller {

	
	
	
	private NoticeDao noticeDAO;



	public NoticeController() {
		//기본생성자..
	}


	//생성자 DI (알트 쉬프트 s)
	public NoticeController(NoticeDao noticeDAO) {
		super();
		this.noticeDAO = noticeDAO;
	}


	//공지사항 목록 요청 URL 이 
	//http://localhost/customer/notice.htm?page=3&fields=검색조건&query=검색어
	//으로 온다고 약속.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		//리턴자료형 ModelAndView pdf 284

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDAO.getNotices(page,field,query);

		mav.addObject("list" , list);
		mav.addObject("message", "Hello World!");
		
		mav.setViewName("notice.jsp");
		
		return mav;
	}


}
