package message.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import message.exception.MessageException;
import message.model.vo.Message;

public class MessageDao {

	// 메시지 전송
	public int sendMessage(Connection con, Message msg) throws MessageException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "insert into message "
				+ "values (m_seq.nextval, sysdate, ?, ?, ?)";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, msg.getList_no());
			pstmt.setString(2, msg.getSender());
			pstmt.setString(3, msg.getContent());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new MessageException("보내기 실패");			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}		
		return result;
	}

	public ArrayList<Message> selectMyMessage(Connection con, int list_no) {
		ArrayList<Message> list = new ArrayList<Message>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from message where list_no = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, list_no);
			
			rset = pstmt.executeQuery();

			while(rset.next()){
				Message msg = new Message();
				msg.setMessage_no(rset.getInt("message_no"));
				msg.setMessage_date(rset.getDate("message_date"));
				msg.setList_no(list_no);
				msg.setSender(rset.getString("sender"));
				msg.setContent(rset.getString("content"));
				list.add(msg);
			}
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}	
}