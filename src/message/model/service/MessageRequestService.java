package message.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import message.exception.MessageException;
import message.model.dao.MessageRequestDao;
import message.model.vo.MessageRequest;

public class MessageRequestService {
	public MessageRequestService(){}
	
	// 전체 조회
	public ArrayList<MessageRequest> selectAll() throws MessageException {
		Connection con = getConnection();
		ArrayList<MessageRequest> list = new MessageRequestDao().selectAll(con);
		close(con);
		return list;
	}
	
	// 내가 받은 요청 조회
	public ArrayList<MessageRequest> selectMyAll(String user_id)  {
		Connection con = getConnection();
		ArrayList<MessageRequest> list = new MessageRequestDao().selectMyAll(con, user_id);
		close(con);
		return list;
	}
	
	// 내가 한 요청 조회
	public ArrayList<MessageRequest> selectMyAll2(String user_id) {
		Connection con = getConnection();
		ArrayList<MessageRequest> list = new MessageRequestDao().selectMyAll2(con, user_id);
		close(con);
		return list;
	}

	// 대화요청
	public int insertRequest(String userId, String sender) throws MessageException {
		Connection con = getConnection();
		int result = new MessageRequestDao().insertRequest(con, userId, sender);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updateRequest(String userId, String sender) throws MessageException {
		Connection con = getConnection();
		int result = new MessageRequestDao().updateRequest(con, userId, sender);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 대화요청 취소
	public int deleteRequest(String userId, String sender) throws MessageException {
		Connection con = getConnection();
		int result = new MessageRequestDao().deleteRequest(con, userId, sender);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

		// 나에게 온 대화신청 목록에서 이름 출력
	public String selectSenderName(String user_id, String sender) {
		Connection con = getConnection();
		String userName = new MessageRequestDao().selectSenderName(con, user_id, sender);
		close(con);
		return userName;
	}
	// 내가 신청한 대화 목록에서 이름 출력
	public Object selectUserIdName(String sender, String user_id) {
		Connection con = getConnection();
		String userName = new MessageRequestDao().selectUserIdName(con, sender, user_id);
		close(con);
		return userName;
	}



}
