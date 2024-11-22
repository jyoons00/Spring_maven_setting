package org.doit.ik;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

// Ajax 요청을 처리하는 컨트롤러
@RestController
public class ScottRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class);
	
	@Setter(onMethod=@__({@Autowired})) // 롬복 어노테이션 (위와 동일)
	private DeptMapper deptMapper;
	
	@PostMapping("/scott/dept/new")
	// 응답 상태랑 응답 결과를 담을 수 있는 객체가 ResponseEntity이다.
	// json -> DeptDTO로 바로 반환 가능
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto) {
		logger.info("> ScottRestController.insertDept()...");
		int rowCount = this.deptMapper.insertDept(dto);
		return rowCount==1
				? new ResponseEntity<>("SUCCESS",HttpStatus.OK)  
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;
	}
	
	/*
	// / scott/dept/delete?deptno=50
	@GetMapping("/scott/dept/remove")
	public ResponseEntity<String> reomoveDept(@RequestParam("deptno") int deptno) { // 형변환 필요 X, 리쿼스트 겟파라미터도 필요 X 
		logger.info("> ScottRestController.deleteDept()...");
		
		// int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		int rowCount = this.deptMapper.removeDept(deptno);
		return rowCount==1
				? new ResponseEntity<>("SUCCESS",HttpStatus.OK)  
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;
	}
	*/
	
	
	// / DELETE + /scott/dept/50
	@DeleteMapping("/scott/dept/{deptno}")
	public ResponseEntity<String> reomoveDept(@PathVariable("deptno") int deptno) { // 형변환 필요 X, 리쿼스트 겟파라미터도 필요 X 
		logger.info("> ScottRestController.reomoveDept()...");
		
		// int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		int rowCount = this.deptMapper.removeDept(deptno);
		return rowCount==1
				? new ResponseEntity<>("SUCCESS",HttpStatus.OK)  
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;
	}
	

}
