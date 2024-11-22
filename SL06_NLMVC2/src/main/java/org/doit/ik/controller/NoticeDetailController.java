package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//공지사항 목록 처리하는 컨트롤러 
public class NoticeDetailController implements Controller {


	private NoticeDao noticeDAO;

	

	public NoticeDetailController() {
		//기본생성자..
	}


	//생성자 DI (알트 쉬프트 s)
	public NoticeDetailController(NoticeDao noticeDAO) {
		super();
		this.noticeDAO = noticeDAO;
	}


	//공지사항 목록 요청 URL 
	//http://localhost/customer/notice.htm?page=3&fields=검색조건&query=검색어
	//으로 온다고 약속.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("noticeDetail.jsp");
		String seq = request.getParameter("seq");
		NoticeVO notice = this.noticeDAO.getNotice(seq);
		mav.addObject("notice" , notice);
		
		return mav;
	}


}
