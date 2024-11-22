package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.mapper.BoardMapper;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	// private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//@Autowired
	private BoardService boardService; // 의존성 주입



	// 페이징 처리가 안된 컨트롤러 메서드
	// [2]번 방법 (리턴 타입이 void)
	/*
	@GetMapping("/list")
	public void list(Model model) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getList());
	}
	*/
	
	
	
	// 페이징 처리 O 컨트롤러 메서드
	//http://localhost/board/list
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getListWithPaging(criteria));
		// 페이징 블럭 1 2 [3] 4 5 6 7 8 9 10>
		int total = this.boardService.getTotal(criteria);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}
	
	


	/* [3]번 방법
	@GetMapping("/board/list")
	public ModelAndView list(ModelAndView mav) {
		log.info("> BoardController.list()...");
		mav.addObject("list",this.boardService.getList());
		// return "/board/list"; // 이건 포워딩 시키는 것
		return mav;
	}
	 */



	/* [1]번 방법
	@GetMapping("/board/list")
	public String list(Model model) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getList());

		return "/board/list";
	}
	 */



	// register.jsp로 이동하도록 하는 것이다. 이제 Board 폴더에 register.jsp 만들면 된다.
	// 글쓰기
	@GetMapping("/register")
	public void register() {
		log.info("> BoardController.register()...");
	}



	// 새글 메세지
	// <button type="submit">Submit</button>
	@PostMapping("/register")					
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("> BoardController.register()...POST");
		this.boardService.register(board); // 저장용
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>BNO = " + board.getBno());
		rttr.addFlashAttribute("result",board.getBno());
		return "redirect:/board/list";

	}

	@PostMapping(value = {"/modify"})
	public String modify(BoardVO boardVO, RedirectAttributes rttr
			,@ModelAttribute("criteria") Criteria criteria) {
		log.info("> BoardController.modify()...POST");
		
		if (this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result","SUCCESS");
		} // if
		

		rttr.addAttribute("pageNum",criteria.getPageNum()); // 일회성 아님
		rttr.addAttribute("amount",criteria.getAmount()); // 일회성 아님		
		
//		 rttr.addFlashAttribute("pageNum",criteria.getPageNum()); // 일회성
//		 rttr.addFlashAttribute("amount",criteria.getAmount()); // 일회성
		 
		 
		// 리다이렉트 때문에 안넘어감
		return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		//	이것도 되긴 함	return String.format("redirect:/board/get?bno=%d&pageNum=%d&amount=%d", boardVO.getBno(), criteria.getPageNum(), criteria.getAmount());
	}
	
	
	// [3] 
	// http://localhost/board/get?pageNum=4&amount=10&bno=168
	//<a href="/board/get?bno=${ board.bno }">
	@GetMapping(value = {"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model
			, @ModelAttribute("criteria") Criteria criteria ) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO" ,this.boardService.get(bno) ); 
	}
	
	
	/*
	// [2] 
	// http://localhost/board/get?pageNum=4&amount=10&bno=168
	//<a href="/board/get?bno=${ board.bno }">
	@GetMapping(value = {"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model
			, Criteria criteria) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO" ,this.boardService.get(bno) );
		model.addAttribute("criteria" , criteria);

	}
	*/
	
	
	
	 
	/* 
	// [1] 값으로 받기
	//<a href="/board/get/3">
	@GetMapping(value = {"/get/{bno}"})
	public void get(@PathVariable("bno") Long bno, Model model) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO" ,this.boardService.get(bno) );
	}
	*/
	
	@GetMapping(value = {"/remove"})
	public String remove(BoardVO boardVO, RedirectAttributes rttr) {
	log.info("> BoardController.modify()...POST");
		
		if (this.boardService.remove(boardVO)) {
			rttr.addFlashAttribute("result","SUCCESS");
		} // if
		
		return "redirect:/board/list";
	}
	
	

} // class 닫기


