package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 결과물을 가진 DTO를 그냥 쓰면 된다
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DeptEmpSalgradeDTO")
public class DeptEmpSalgradeDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	private List<EmpDTO> empList; // 포문 돌려야함 리스트라
	
}
