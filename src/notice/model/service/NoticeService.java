package notice.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import notice.exception.NoticeException;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import report.exception.ReportException;
import report.model.dao.ReportDao;
import report.model.vo.Report;


public class NoticeService {	
	public NoticeService(){}
	
	public int getListCount() throws NoticeException {
		Connection con = getConnection();
		int listCount = new NoticeDao().getListCount(con);
		close(con);
		return listCount;
	}
	
		
	// 공지글 전체 조회
	public ArrayList<Notice> selectAllNotice(int currentPage, int limit) throws NoticeException {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectAllNotice(con, currentPage, limit);
		close(con);		
		return list;
	}
	
	// 공지글 제목으로 검색조회
	public ArrayList<Notice> selectTitle(String keyword) throws NoticeException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectTitle(con, keyword);
		close(con);
		return list;
	}
	
	// 공지글 내용으로 검색조회
	public ArrayList<Notice> selectContent(String keyword) throws NoticeException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectContent(con, keyword);
		close(con);
		return list;
	}
	
	// 글 상세 페이지
	public Notice selectNotice(int NoticeNo) throws NoticeException {
		Connection con = getConnection();
		Notice Notice = new NoticeDao().selectNotice(con, NoticeNo);
		close(con);

		return Notice;
	}
	
	// 글 작성
	public int insertNotice(Notice Notice) throws NoticeException {
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, Notice);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 수정
	public int updateNotice(Notice Notice) throws NoticeException {
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, Notice);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 삭제
	public int deleteNotice(int NoticeNo) throws NoticeException {
		Connection con = getConnection();
		int result = new NoticeDao().deleteNotice(con, NoticeNo);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
    // 조회수 처리
	   public void addReadCount(int boardNum) throws NoticeException {
	      Connection con = getConnection();
	      int result = new NoticeDao().addReadCount(con, boardNum);
	      if (result > 0)
	         commit(con);
	      else
	         rollback(con);
	      close(con);
	   }



}
