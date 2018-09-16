package report.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import report.exception.ReportException;
import report.model.vo.Report;

public class ReportDao {
	public ReportDao(){}
	
	public int getListCount(Connection con) 
			throws ReportException {
			int listCount = 0;
			Statement stmt = null;
			ResultSet rset = null;
			
			String query = "select count(*) from report_board";
			
			try {
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()){
					listCount = rset.getInt(1);
				}else{
					throw new ReportException("게시글이 존재하지 않습니다.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ReportException(e.getMessage());
			}finally{
				close(rset);
				close(stmt);
			}
			
			return listCount;
		}
	
	// 전체 글 조회
	public ArrayList<Report> selectAllReport(Connection con, int currentPage, 
			int limit) throws ReportException {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ("
				+ "select rownum rnum, report_no, "
				+ "views, title, contents, "
				+ "report_date, complete, user_id "
				+ "from (select * from report_board "
				+ "order by report_no desc)) "
				+ "where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Report r = new Report();
				
				r.setReport_no(rset.getInt("report_no"));
				r.setReport_date(rset.getDate("report_date"));
				r.setViews(rset.getInt("views"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("contents"));
				r.setComplete(rset.getString("complete"));
				r.setUser_id(rset.getString("user_id"));
				
				list.add(r);
				
				if(list.size() == 0)
					throw new ReportException("전체 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return list;
	}

	// 제목으로 검색조회
	public ArrayList<Report> selectTitle(Connection con, String keyword) throws ReportException {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, report_no, "
				+ "views, title, contents, "
				+ "report_date, complete, user_id "
				+ "from (select * from report_board "
				+ "order by report_no desc)) "
				+ "where title like ?";
				
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Report r = new Report();
				
				r.setReport_no(rset.getInt("report_no"));
				r.setReport_date(rset.getDate("report_date"));
				r.setViews(rset.getInt("views"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("contents"));
				r.setComplete(rset.getString("complete"));
				r.setUser_id(rset.getString("user_id"));
				
				list.add(r);
				
				if(list.size() == 0)
					throw new ReportException("제목 검색 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return list;
	}
	
	//  아이디로 검색조회
		public ArrayList<Report> selectId(Connection con, String writer) throws ReportException {
			ArrayList<Report> list = new ArrayList<Report>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from ("
					+ "select rownum rnum, report_no, "
					+ "views, title, contents, "
					+ "report_date, complete, user_id "
					+ "from (select * from report_board "
					+ "order by report_no desc)) "
					+ "where user_id like ?";

			try{
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + writer + "%");

				rset = pstmt.executeQuery();

				while(rset.next()){
					Report r = new Report();

					r.setReport_no(rset.getInt("report_no"));
					r.setReport_date(rset.getDate("report_date"));
					r.setViews(rset.getInt("views"));
					r.setTitle(rset.getString("title"));
					r.setContent(rset.getString("contents"));
					r.setComplete(rset.getString("complete"));
					r.setUser_id(rset.getString("user_id"));

					list.add(r);

					if(list.size() == 0)
						throw new ReportException("제목 검색 조회 실패");			
				}
			} catch(Exception e){
				e.printStackTrace();
				throw new ReportException(e.getMessage());		
			} finally{
				close(rset);
				close(pstmt);
			}		
			return list;
		}

	// 글 내용 상세조회
	public Report selectReport(Connection con, int reportNo) throws ReportException {
		Report r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from report_board where report_no = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reportNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				r = new Report();
				
				r.setReport_no(rset.getInt("report_no"));
				r.setReport_date(rset.getDate("report_date"));
				r.setViews(rset.getInt("views"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("contents"));
				r.setComplete(rset.getString("complete"));
				r.setUser_id(rset.getString("user_id"));
				
				
				if(r == null)
					throw new ReportException("글 내용 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return r;
	}

	// 신고게시판 입력
	public int insertReport(Connection con, Report report) throws ReportException {
		int result = 0;
		PreparedStatement pstmt = null; 
		
		String query = "insert into report_board values ( REPORT_NO_SEQ.nextval, "
		     + "?, sysdate, ?, ?, ?, default )";
		     		

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(3, report.getTitle());
			pstmt.setString(1, report.getUser_id());
			pstmt.setString(4, report.getContent());
			pstmt.setInt(2, report.getViews());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReportException("공지글 입력 실패");
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;	
	}

	// 공지글 수정
	public int updateReport(Connection con, Report report) throws ReportException {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update report_board set "
				+ "title = ?, contents = ?, report_date=sysdate, COMPLETE = ? "
				+ "where report_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, report.getTitle());
			pstmt.setString(2, report.getContent());
			pstmt.setString(3, report.getComplete());
			pstmt.setInt(4, report.getReport_no());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReportException("공지 수정 실패");			
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;	
	}
	
	// 삭제
	public int deleteReport(Connection con, int reportno) throws ReportException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from report_board where report_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reportno);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReportException(reportno + "번 신고글 삭제 실패!");
		} catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;		
	}
	
	// 조회수 처리
	public int addReadCount(Connection con, 
			int boardNum) throws ReportException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update report_board "
				+ "set views = views + 1 "
				+ "where report_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReportException(
						boardNum + "번 게시글 조회수 증가 처리 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}
}
