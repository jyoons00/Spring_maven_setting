package org.doit.ik.di4;

public interface Record4 {
	
	// 세개의 차이를 중심으로 114p 읽어보기
	// @Autowired는 생성자, 필드, 메서드에 붙일 수 있다.
	// @Resources
	// @Inject
	
	// 1. 총점을 반환하는 메서드
	int total();
	// 2. 평균		"
	double avg();
}
