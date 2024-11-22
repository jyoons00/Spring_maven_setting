package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {
   
   public static void main(String[] args) {
      // 209p XML 스키마 AOP처리
      
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
            "classpath:org/doit/ik/aop3/application-context3.xml");
      
  
      Calculator calc = ctx.getBean("calc3", Calculator.class);
      
      System.out.println(calc.add(3, 5)); 
      
      System.out.println("END");
   } // main
   
} // class
