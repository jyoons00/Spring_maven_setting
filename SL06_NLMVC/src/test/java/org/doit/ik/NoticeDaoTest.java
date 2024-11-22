package org.doit.ik;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.junit.jupiter.api.Test;

class NoticeDaoTest {

   @Test
   void testNoticeInsert() {
      System.out.println("테스트코드 작동하나???");
      
      NoticeDao noticeDao = new NoticeDao();
      NoticeVO notice = new NoticeVO();
      notice.setTitle("테스트용 첫 게시글");
      notice.setContent("콘텐츠 테스트용");
      notice.setFilesrc("파일파일 테스트");
      int rowCount = 0;
      try {
         rowCount = noticeDao.insert(notice);
         System.out.println(rowCount);//영향받은 레코드 수
      } catch (ClassNotFoundException e) {
         
         e.printStackTrace();
      } catch (SQLException e) {
         
         e.printStackTrace();
      }
      System.out.println("공지사항 추가 완료~");
      fail("Not yet implemented");
   }

}
