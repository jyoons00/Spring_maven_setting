package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getList(); // 페이징 처리 X 
	
	List<BoardVO> getListWithPaging(Criteria criteria); // 페이징 처리 O
	
	int getTotalCount(Criteria criteria); // 총 레코드 수

	void insert(BoardVO board); // 새글 쓰기 
	
	void insertSelectKey(BoardVO board); // 새글 쓰기 + 글 번호(PK) 반환 = 작성완료 메세지를 띄우기 위해서
	// 이 함수를 안만들려면 insert할 때 seq 자동으로 들어간 후에 쿼리를 한번 더 날려서 값을 또 가져와야 하기 때문에
	// 스프링에서는 어떻게하는지 한번 실습해보기 위한 함수이.

	BoardVO read(Long bno); // 게시글 조회
	
	int update(BoardVO board); // 게시글 수정
	
	int remove(BoardVO board);

	
	
	
	
	

}


