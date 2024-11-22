package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

@Setter
public class RecordViewImpl3 implements RecordView3{
	
	// DI에서 사용되는 어노테이션들
	
	// @Setter 롬복 어노테이션
	// @Autowired(required = false) // 입력시엔 에러 나긴함
	// @Autowired // 주입하는 bean객체가 2개 이상 되면 오류가 생긴다.
	// @Resource(name="record1") // 현재는 사용 X 
	@Inject
	@Named(value="record1")
	private RecordImpl3 record = null; // 널포인트 익셉션

	
	public RecordViewImpl3() { // 디폴트 생성자
		
	}

	public RecordViewImpl3(RecordImpl3 record) { // 생성자를 통한 주입
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
