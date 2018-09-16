package report.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import report.exception.ReportException;
import report.model.dao.ReportDao;
import report.model.vo.Report;

public class ReportService {
	public ReportService(){}

	public int getListCount() throws ReportException {
		Connection con = getConnection();
		int listCount = new ReportDao().getListCount(con);
		close(con);
		return listCount;
	}

	// 신고게시판 전체 조회
	public ArrayList<Report> selectAllReport(int currentPage, int limit) throws ReportException {
		Connection con = getConnection();
		ArrayList<Report> list = new ReportDao().selectAllReport(con, currentPage, limit);
		close(con);		
		return list;
	}
	
	// 신고게시판 제목으로 검색조회
	public ArrayList<Report> selectTitle(String title) throws ReportException{
		Connection con = getConnection();
		ArrayList<Report> list = new ReportDao().selectTitle(con, title);
		close(con);
		return list;
	}

	// 신고게시판 아이디로 검색조회
	public ArrayList<Report> selectId(String writer) throws ReportException{
		Connection con = getConnection();
		ArrayList<Report> list = new ReportDao().selectId(con, writer);
		close(con);
		return list;
	}

	// 글 상세 페이지
	public Report selectReport(int reportNo) throws ReportException {
		Connection con = getConnection();
		Report Report = new ReportDao().selectReport(con, reportNo);
		close(con);
		return Report;
	}
	
	// 글 작성
	public int insertReport(Report Report) throws ReportException {
		Connection con = getConnection();
		int result = new ReportDao().insertReport(con, Report);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 수정
	public int updateReport(Report Report) throws ReportException {
		Connection con = getConnection();
		int result = new ReportDao().updateReport(con, Report);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 삭제
	public int deleteReport(int ReportNo) throws ReportException {
		Connection con = getConnection();
		int result = new ReportDao().deleteReport(con, ReportNo);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 조회수 처리
	public void addReadCount(int boardNum) throws ReportException {
		Connection con = getConnection();
		int result = new ReportDao().addReadCount(con, boardNum);
		if (result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
	}
}
