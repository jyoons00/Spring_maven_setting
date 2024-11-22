package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {
	
	public static void main(String[] args) {
		
		RecordImpl record = new RecordImpl();
		// 생성자 의존성 주입 (DI)
		
		// [1]번 생성자를 통한 의존성 주입 (DI)
		//  RecordViewImpl rvi = new RecordViewImpl(record); 
		
		// [2]번 Setter 함수를 통한 의존성 주입 (DI)
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	}

}
