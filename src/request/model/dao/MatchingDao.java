package request.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import request.exception.MatchingException;
import request.model.vo.Matching;

public class MatchingDao {
	public MatchingDao(){}

	public Matching hostMatching(Connection con, String userid) {
		Matching matching = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from matching where user1 = ? and type='H' and process='P'";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				matching = new Matching();
				matching.setMatching_no(rset.getInt("matching_no"));
				matching.setUser1(rset.getString("user1"));
				matching.setUser2(rset.getString("user2"));
				matching.setProcess(rset.getString("process"));
			}			
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}

		return matching;
	}

	public Matching surferMatching(Connection con, String userid) {
		Matching matching = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from matching where user2 = ? and type='H' and process='P'";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				matching = new Matching();
				matching.setMatching_no(rset.getInt("matching_no"));
				matching.setUser1(rset.getString("user1"));
				matching.setUser2(rset.getString("user2"));
				matching.setProcess(rset.getString("process"));
			}			
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}
		return matching;
	}

	public Matching partnerMatching(Connection con, String userid) {
		Matching matching = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from matching where (user1 = ? or user2 = ?) and type='P' and process='P'";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				matching = new Matching();
				matching.setMatching_no(rset.getInt("matching_no"));
				matching.setUser1(rset.getString("user1"));
				matching.setUser2(rset.getString("user2"));
				matching.setProcess(rset.getString("process"));
			}
			
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}
		return matching;
	}
	
	// 번호로 조회
	public Matching selectMatching(Connection con, int matching_no) {
		Matching matching = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from matching where matching_no=?";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, matching_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				matching = new Matching();
				matching.setMatching_no(rset.getInt("matching_no"));
				matching.setUser1(rset.getString("user1"));
				matching.setUser2(rset.getString("user2"));
				matching.setProcess(rset.getString("process"));
				matching.setType(rset.getString("type"));
			}
			
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}
		return matching;
	}
	
	// 취소(삭제)
	public int deleteMatching(Connection con, int matching_no) throws MatchingException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from matching where matching_no=?";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, matching_no);
			
			result = pstmt.executeUpdate();
			
			if(result < 0)
				throw new MatchingException("실패");
			
		} catch(Exception e){
			
		} finally{
			close(pstmt);
		}
		return result;
	}

}
