package request.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import request.exception.MatchingException;
import request.model.dao.MatchingDao;
import request.model.vo.Matching;

public class MatchingService {
	public MatchingService(){}
	
	public Matching hostMatching(String userid) {
		Connection con = getConnection();
		Matching matching = new MatchingDao().hostMatching(con, userid);
		close(con);
		return matching;
	}

	public Matching surferMatching(String userid) {
		Connection con = getConnection();
		Matching matching = new MatchingDao().surferMatching(con, userid);
		close(con);
		return matching;

	}

	public Matching partnerMatching(String userid) {
		Connection con = getConnection();
		Matching matching = new MatchingDao().partnerMatching(con, userid);
		close(con);
		return matching;

	}
	
	// 번호로 조회
	public Matching selectMatching(int matching_no){
		Connection con = getConnection();
		Matching matching = new MatchingDao().selectMatching(con, matching_no);
		close(con);
		return matching;
	}
	
	// 취소(삭제)
	public int deleteMatching(int matching_no) throws MatchingException {
		Connection con = getConnection();
		int result = new MatchingDao().deleteMatching(con, matching_no);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
}
