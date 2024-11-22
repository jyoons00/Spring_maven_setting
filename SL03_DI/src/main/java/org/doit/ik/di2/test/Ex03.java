package org.doit.ik.di2.test;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {

	// p62, p94(xml을 사용하면 유지보수가 쉽다.)
	public static void main(String[] args) {
		// ApplicationContext ctx 
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

// [1]
		RecordViewImpl rvi =  ctx.getBean("rvi" , RecordViewImpl.class);
// 	[2]RecordViewImpl rvi =  ctx.getBean("getRecordViewImpl" , RecordViewImpl.class);

		rvi.input();
		rvi.output();

		System.out.println(" END.");




	} // main

} // class
