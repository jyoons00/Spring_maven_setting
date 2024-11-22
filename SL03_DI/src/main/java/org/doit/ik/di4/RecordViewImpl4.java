package org.doit.ik.di4;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

// recordViewImpl4
@Setter
@Component
public class RecordViewImpl4 implements RecordView4{
	
	@Inject
	@Named(value="record4")
	private RecordImpl4 record = null; // 널포인트 익셉션

	
	public RecordViewImpl4() { // 디폴트 생성자
		
	}
	

	public RecordViewImpl4(RecordImpl4 record) { // 생성자를 통한 주입
		this.record = record;
	}

	
	@Override
	public void input() {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.print("> kor, eng, mat input ?");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
				
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
	
		} catch (Exception e) {
			e.printStackTrace();
		} // try
		
	} // input

	
	@Override
	public void output() {
		System.out.printf("> kor=%d, end=%d, mat=%d, tot=%d, avg=%.2f\n",
				this.record.getKor(),
				this.record.getEng(),
				this.record.getMat(),
				this.record.total(),
				this.record.avg()
				);
		
		}
	
} // class
