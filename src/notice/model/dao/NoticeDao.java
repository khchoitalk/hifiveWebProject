package notice.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import notice.exception.NoticeException;
import notice.model.vo.Notice;


public class NoticeDao {	
	public NoticeDao(){}

	public int getListCount(Connection con) 
			throws NoticeException {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from notice_board";

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new NoticeException("게시글이 존재하지 않습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}

		return listCount;
	}



	// 전체 글 조회
	public ArrayList<Notice> selectAllNotice(Connection con, int currentPage, 
			int limit) throws NoticeException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ("
				+ "select rownum rnum, notice_no, "
				+ "views, title, contents, "
				+ "notice_date "
				+ "from(select * from notice_board "
				+ "order by notice_no desc)) "
				+ "where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while(rset.next()){
				Notice n = new Notice();

				n.setNotice_no(rset.getInt("notice_no"));
				n.setNotice_date(rset.getDate("notice_date"));
				n.setViews(rset.getInt("views"));
				n.setTitle(rset.getString("title"));
				n.setContent(rset.getString("contents"));

				list.add(n);

				if(list.size() == 0)
					throw new NoticeException("전체 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return list;
	}

	// 제목으로 검색조회
	public ArrayList<Notice> selectTitle(Connection con, String keyword) throws NoticeException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ("
				+ "select rownum rnum, notice_no, "
				+ "views, title, contents, notice_date "
				+ "from (select * from notice_board "
				+ "order by notice_no desc)) "
				+ "where title like ?";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();

				n.setNotice_no(rset.getInt("notice_no"));
				n.setNotice_date(rset.getDate("notice_date"));
				n.setViews(rset.getInt("views"));
				n.setTitle(rset.getString("title"));
				n.setContent(rset.getString("contents"));

				list.add(n);

				if(list.size() == 0)
					throw new NoticeException("제목 검색 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return list;
	}

	// 내용으로 검색조회
	public ArrayList<Notice> selectContent(Connection con, String keyword) throws NoticeException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ("
				+ "select rownum rnum, notice_no, "
				+ "views, title, contents, notice_date "
				+ "from (select * from notice_board "
				+ "order by notice_no desc)) "
				+ "where contents like ?";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();

				n.setNotice_no(rset.getInt("notice_no"));
				n.setNotice_date(rset.getDate("notice_date"));
				n.setViews(rset.getInt("views"));
				n.setTitle(rset.getString("title"));
				n.setContent(rset.getString("contents"));

				list.add(n);

				if(list.size() == 0)
					throw new NoticeException("내용 검색 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return list;
	}

	// 글 내용 상세조회
	public Notice selectNotice(Connection con, int NoticeNo) throws NoticeException {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from notice_board where notice_no = ?";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, NoticeNo);

			rset = pstmt.executeQuery();

			if(rset.next()){
				n = new Notice();

				n.setNotice_no(rset.getInt("notice_no"));
				n.setNotice_date(rset.getDate("notice_date"));
				n.setViews(rset.getInt("views"));
				n.setTitle(rset.getString("title"));
				n.setContent(rset.getString("contents"));


				if(n == null)
					throw new NoticeException("글 내용 조회 실패");			
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());		
		} finally{
			close(rset);
			close(pstmt);
		}		
		return n;
	}

	// 공지글 입력
	public int insertNotice(Connection con, Notice Notice) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null; 
	
	

	   String	query = "insert into notice_board values (NOTICE_NO_SEQ.nextval, "
			           + "sysdate, ?, ?, ?)";
	

		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(3, Notice.getContent());
			pstmt.setString(2, Notice.getTitle());
			pstmt.setInt(1, Notice.getViews());

			result = pstmt.executeUpdate();

			if(result <= 0)
				throw new NoticeException("공지글 입력 실패");
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;	
	}

	// 공지글 수정
	public int updateNotice(Connection con, Notice Notice) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update notice_board set "
				+ "title = ?, contents = ?, notice_date=sysdate "
				+ "where notice_no = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Notice.getTitle());
			pstmt.setString(2, Notice.getContent());
			pstmt.setInt(3, Notice.getNotice_no());

			result = pstmt.executeUpdate();

			if(result <= 0)
				throw new NoticeException("공지 수정 실패");			
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;	
	}

	// 공지글 삭제
	public int deleteNotice(Connection con, int NoticeNo) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from notice_board where notice_no = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, NoticeNo);

			result = pstmt.executeUpdate();

			if(result <= 0)
				throw new NoticeException(NoticeNo + "번 공지 삭제 실패!");
		} catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally{
			close(pstmt);
		}		
		return result;		
	}
	
	// 조회수 처리
	   public int addReadCount(Connection con, 
	         int boardNum) throws NoticeException {
	      int result = 0;
	      PreparedStatement pstmt = null;
	      
	      String query = "update notice_board "
	            + "set views = views + 1 "
	            + "where notice_no = ?";
	      
	      try {
	         pstmt = con.prepareStatement(query);
	         pstmt.setInt(1, boardNum);
	         
	         result = pstmt.executeUpdate();
	         
	         if(result <= 0)
	            throw new NoticeException(
	                  boardNum + "번 게시글 조회수 증가 처리 실패!");
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new NoticeException(e.getMessage());
	      }finally{
	         close(pstmt);
	      }
	      
	      return result;
	   }
}