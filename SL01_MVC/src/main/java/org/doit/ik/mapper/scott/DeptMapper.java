package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {
	
	ArrayList<DeptDTO> selectDept();
	int insertDept(DeptDTO dto); // 추상메서드 추가 
	int removeDept(int deptno);


}
