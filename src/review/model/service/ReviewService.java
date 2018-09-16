package review.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import review.exception.ReviewException;
import review.model.dao.ReviewDao;
import review.model.vo.Review;

public class ReviewService {
	
	public ReviewService(){}
	
	// 리뷰 전체 조회
	public ArrayList<Review> selectAllReview(String userId) {
		Connection con = getConnection();
		ArrayList<Review> list = new ReviewDao().selectAllReview(con, userId);
		close(con);
		return list;
	}
	
	// 리뷰 등록
	public int insertReview(Review review) throws ReviewException {
		Connection con = getConnection();
		int result = new ReviewDao().insertReview(con, review);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 리뷰 수정
	/*public int updateReview(Review review) throws ReviewException {
		Connection con = getConnection();
		int result = new ReviewDao().updateReview(con, review);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}*/
			
	// 리뷰 삭제
	public int deleteReview(int review_no) throws ReviewException {
		Connection con = getConnection();
		int result = new ReviewDao().deleteReview(con, review_no);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
				
}
