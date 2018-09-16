package review.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import review.exception.ReviewException;
import review.model.vo.Review;

public class ReviewDao {

	// 나의 리뷰 전체 가져오기
	public ArrayList<Review> selectAllReview(Connection con, String userId) {
		ArrayList<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from review where r_user_id = ? order by review_no desc";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Review review = new Review();
				review.setReview_no(rset.getInt("review_no"));
				review.setUser_id(rset.getString("user_id"));
				review.setR_user_id(rset.getString("user_id"));
				review.setReview_date(rset.getDate("review_date"));
				review.setContent(rset.getString("content"));
				list.add(review);
			}			
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertReview(Connection con, Review review) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into review values(REVIEW_SEQ.NEXTVAL, ?, ?, default, ?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getUser_id());
			pstmt.setString(2, review.getR_user_id());
			pstmt.setString(3, review.getContent());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReviewException("리뷰 등록 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}		
		return result;		
	}

	public int updateReview(Connection con, Review review) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update review set "
				+ "content = ? where review_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, review.getContent());
			pstmt.setInt(2, review.getReview_no());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReviewException("리뷰 수정 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}		
		return result;	
	}

	public int deleteReview(Connection con, int review_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from review where review_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, review_no);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new ReviewException("리뷰 삭제 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}		
		return result;		
	}
}