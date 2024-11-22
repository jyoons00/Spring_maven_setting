package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface BoardService {
	
	List<BoardVO> getList();
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotal(Criteria criteria); // 총 레코드 수
	
	void register(BoardVO board);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	
	boolean remove(BoardVO board);
	
	

}
