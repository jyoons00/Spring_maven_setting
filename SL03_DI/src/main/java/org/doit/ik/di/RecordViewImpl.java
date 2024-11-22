package org.doit.ik.di;

import java.util.Scanner;

import lombok.Setter;

@Setter
public class RecordViewImpl implements RecordView{
	
	private RecordImpl record = null; // 널포인트 익셉션
	//	private RecordImpl record = new RecordImpl(); 결합력이 높아서 바로 new로 생성하지 않는다.
	
	public RecordViewImpl() { // 디폴트 생성자
		
	}

	public RecordViewImpl(RecordImpl record) { // 생성자를 통한 주입
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
