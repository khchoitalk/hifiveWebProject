package message.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import message.exception.MessageException;
import message.model.vo.MessageRequest;

public class MessageRequestDao {

	public ArrayList<MessageRequest> selectAll(Connection con) throws MessageException {
		ArrayList<MessageRequest> list = new ArrayList<MessageRequest>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from message_request";
		
		try{
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				MessageRequest mr = new MessageRequest();
				mr.setRequest_no(rset.getInt("request_no"));
				mr.setUser_id(rset.getString("user_id"));
				mr.setSender(rset.getString("sender"));
				mr.setAccept(rset.getString("accept"));
				
				list.add(mr);				
			}
			
			if(list.size() == 0)
				throw new MessageException("데이터가 하나도 없습니다.");
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	// 내가 받은 대화 요청 조회
	public ArrayList<MessageRequest> selectMyAll(Connection con, String user_id) {
		ArrayList<MessageRequest> list = new ArrayList<MessageRequest>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from message_request where user_id = ? and accept='A'";

		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			rset = pstmt.executeQuery();			
			
			while(rset.next()){
				MessageRequest mr = new MessageRequest();
				mr.setRequest_no(rset.getInt("request_no"));
				mr.setUser_id(rset.getString("user_id"));
				mr.setSender(rset.getString("sender"));
				mr.setAccept(rset.getString("accept"));
				
				list.add(mr);		
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 내가 한 대화 요청 조회
	public ArrayList<MessageRequest> selectMyAll2(Connection con, String user_id) {
		ArrayList<MessageRequest> list = new ArrayList<MessageRequest>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from message_request where sender = ? and accept='A'";
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			rset = pstmt.executeQuery();			
			
			while(rset.next()){
				MessageRequest mr = new MessageRequest();
				mr.setRequest_no(rset.getInt("request_no"));
				mr.setUser_id(rset.getString("user_id"));
				mr.setSender(rset.getString("sender"));
				mr.setAccept(rset.getString("accept"));
				
				list.add(mr);		
			}
		
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertRequest(Connection con, String userId, String sender) throws MessageException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into message_request values "
				+ "(mr_seq.nextval, ?, ?, default)";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, sender);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MessageException("에러");
			
		} catch(Exception e){
			throw new MessageException(e.getMessage());
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int updateRequest(Connection con, String userId, String sender) throws MessageException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update message_request set accept='Y' where user_id=? and sender=?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, sender);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MessageException("요청받기 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}		
		return result;
	}
	
	public int deleteRequest(Connection con, String userId, String sender) throws MessageException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from message_request "
				+ "where user_id=? and sender=?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, sender);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MessageException("에러");
		} catch(Exception e){
			
		} finally{
			close(pstmt);
		}
		return result;
	}
	
	// 나에게 온 대화신청 목록에서 이름 출력
	public String selectSenderName(Connection con, String user_id, String sender) {
		String userName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select u.user_name "
				+ "from message_request m "
				+ "join users u on (m.sender = u.user_id) "
				+ "where m.user_id=? and m.sender=?";	
			try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, sender);
			
			rset = pstmt.executeQuery();
				if(rset.next())
				userName = rset.getString(1);	
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);	
		}	
		return userName;
	}
	// 내가 신청한 대화 목록에서 이름 출력
	public String selectUserIdName(Connection con, String sender, String user_id) {
		String userName = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select u.user_name "
				+ "from message_request m "
				+ "join users u on (m.user_id = u.user_id) "
				+ "where m.sender=? and m.user_id=?";	
	
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sender);
			pstmt.setString(2, user_id);
			
			rset = pstmt.executeQuery();
				if(rset.next())
				userName = rset.getString(1);	
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);	
		}	
		return userName;
	}
	
}