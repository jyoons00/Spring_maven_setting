package org.doit.ik.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//공지사항과 관련된 모든 컨트롤러 메서드 만드는 컨트롤러.
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeDao noticeDao;
	
	   //삭제
	   @GetMapping(value="/noticeDel.htm")
	   public String noticeDel(@RequestParam("seq") String seq) 
			   throws ClassNotFoundException, SQLException {
	      int rowCount = this.noticeDao.delete(seq);      
	      if(rowCount ==1 ) {
	         //삭제 성공 -> 글 목록( 리다이렉트 )
	      return "redirect:notice.htm";

	      }else {
	         //삭제 실패 -> 글 상세 ( 리다이렉트 )
	      return "redirect:noticeDetail.htm?seq="+seq+"&error";
	      }
	   }
	
	
	
	
	
	
	@GetMapping(value="/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq ,Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		
		return "noticeEdit.jsp";
	}
	
	// 글 수정하기: <form action="" method="post">
	// 액션에는 http://localhost/customer/noticeEdit.htm?seq=2 이라고 보면 됨
	@PostMapping(value="noticeEdit.htm")
	public String noticeEdit(NoticeVO notice, Model model) throws ClassNotFoundException, SQLException {
		int rowCount = this.noticeDao.update(notice);
		
		if(rowCount ==1 ) {
			//수정 성공 -> 글 상세( 리다이렉트 )
		return "redirect:noticeDetail.htm?seq="+notice.getSeq();
		}else {
		   //수정 실패 -> 글 목록 ( 리다이렉트 )
		return "redirect:notice.htm";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	@PostMapping(value="/noticeReg.htm")
	public String noticeReg( NoticeVO notice) throws ClassNotFoundException, SQLException {
		// 대신 아래처럼 안하고 이렇게 받으려면 겟셋터 있고, form 안에 name 속성들이 VO 안에 필드들이랑 이름 똑같아야함
		
		int rowCount = this.noticeDao.insert(notice);
				
		if(rowCount ==1 ) {
			//글쓰기 성공 -> 글 목록 ( 리다이렉트 )
		return "redirect:notice.htm";
		}else {
		   //글쓰기 실패 -> 글쓰기 페이지 ( 포워딩 )
		return "noticeReg.htm?error";
		}
		
	}
	
	/* 스프링에선 이렇게 할 필요 없다.
	@PostMapping(value="/noticeReg.htm")
	public String noticeReg( @RequestParam("title") String title, @RequestParam("content") String content ) {
		
		NoticeVO notice = new NoticeVO();
		notice.setTitle(title);
		
		return "noticeReg.jsp";
	}
	*/
	
	@GetMapping("/noticeReg.htm")
	public String noticeReg(HttpSession session) {
		
		
		
		return "noticeReg.jsp";
		
	}

	
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(Model model,
			@RequestParam(value = "seq") String seq ) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		
		return "noticeDetail.jsp";
		
	}

	/* 컨버팅 전 공지 상세보기
	public class NoticeDetailController implements Controller {

		private NoticeDao noticeDao;



		public NoticeDetailController() {
			//기본생성자..
		}


		//생성자 DI (알트 쉬프트 s)
		public NoticeDetailController(NoticeDao noticeDAO) {
			super();
			this.noticeDao = noticeDAO;
		}


		//공지사항 목록 요청 URL 이 
		//http://localhost/customer/noticeDetail.htm?seq=1
		//으로 온다고 약속.
		@Override
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

			ModelAndView mav = new ModelAndView("noticeDetail.jsp");
			//리턴자료형 ModelAndView pdf 284

			String seq = request.getParameter("seq");
		


			NoticeVO notice = this.noticeDao.getNotice(seq);

			mav.addObject("notice" , notice);
			
			//mav.setViewName("notice.jsp");  위에 매개변수로 줄수도있음
			
			return mav;
		}
	
	*/
	
	
	
	
	
	
	//?page=2&field=검색조건&query=검색어
	@GetMapping("/notice.htm")
	public String notices(Model model , 
									@RequestParam(value = "page", defaultValue = "1" ) int page , 
									@RequestParam(value = "field" , defaultValue = "title") String field , 
									@RequestParam(value = "query", defaultValue = "") String query) throws ClassNotFoundException, SQLException  {
	  //컨버팅 전 코드랑 비교해보면 엄청 짧아짐..
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		model.addAttribute("list" , list);
		model.addAttribute("message", "Hello World!");
		
		
		return "notice.jsp";
		
	}
	/* NoticeController(컨버팅 전) 코드
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

		List<NoticeVO> list = this.noticeDao.getNotices(page,field,query);

		mav.addObject("list" , list);
		mav.addObject("message", "Hello World!");
		
		mav.setViewName("notice.jsp");
		
		return mav;
	
	}
		*/
	
	
	
	
	
	
	
}
