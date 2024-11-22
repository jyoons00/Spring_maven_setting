package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component (value = "calc") // 이름을 등록하면 에러가 해결된다.
public class CalculatorImpl2 implements Calculator{

	@Override
	public int add(int x, int y) {		
		int result = x+y;																	 // 핵심 기능
		return result;
	}

	@Override
	public int sub(int x, int y) {		
		int result = x-y;																		 // 핵심 기능
		return result;
	}

	@Override
	public int mult(int x, int y) {		
		int result = x*y;																	 // 핵심 기능
		return result;
	}

	@Override
	public int div(int x, int y) {		
		int result = x/y;																		 // 핵심 기능
		return result;
	}

	
	
	
	
} // class
