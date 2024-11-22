package org.doit.ik.aop;

public class Ex01 {
	
	public static void main(String[] args) {
		
		// 204p 스프링 AOP를 사용하지 않는 상황이다.
		
		Calculator calc =  new CalculatorImpl();
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mult(4, 2));
		System.out.println(calc.div(4, 2));
		System.out.println("END.");
		
	
	} // main

} // class
