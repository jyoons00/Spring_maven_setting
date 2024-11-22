package org.doit.ik.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	
	@DateTimeFormat (pattern="yyyy/MM/dd")
	private Date hiredate; // java.util
	private int sal;
	private double comm;
	private int deptno;
	
	// empDTO + salgradeDTO 1:1관계
	private SalgradeDTO salgradeDTO;

}


