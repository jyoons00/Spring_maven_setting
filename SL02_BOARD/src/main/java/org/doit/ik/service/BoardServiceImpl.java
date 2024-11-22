package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 이게 있어야 빈 객체가 생성된다.
@Service
@Log4j
@AllArgsConstructor // 이게 있으면 스프링 4.3 부터 자동 주입 시킨다.
public class BoardServiceImpl implements BoardService {

	// @Autowired
	// @Setter
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> getList() {
		log.info("BoardServiceImpl.getList()");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO board) {
		log.info("BoardServiceImpl.register()");
		// this.boardMapper.insert(board);
		this.boardMapper.insertSelectKey(board); // 글번호가 계속 저장돼있다.
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("BoardServiceImpl.get()");
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("BoardServiceImpl.modify()");
		return this.boardMapper.update(board)==1;
	}

	@Override
	public boolean remove(BoardVO board) {
		log.info("BoardServiceImpl.modify()");
		return this.boardMapper.remove(board)==1;
	}

	
	
	
	

} // class
