package org.doit.ik;

import static org.junit.jupiter.api.Assertions.*;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.junit.jupiter.api.Test;

class NoticeDaoTest {

	@Test
	void test() {
		//System.out.println("hello world");
		
		NoticeDao noticeDao = new NoticeDao(); 
		NoticeVO notice = new NoticeVO();
		notice.setTitle("첫글");
		notice.setContent("첫글");
		int rowCount=0;
		try {
			rowCount = noticeDao.insert(notice);
			System.out.println(rowCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("공지사항 추가..");
	}

}

