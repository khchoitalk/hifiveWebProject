package message.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import message.exception.MessageException;
import message.model.dao.MessageListDao;
import message.model.dao.MessageRequestDao;
import message.model.vo.MessageList;

public class MessageListService {

	public MessageListService(){}

	// 내 대화목록 전체 검색 (내가 user1일때)
	public ArrayList<MessageList> selectMyList1(String user1){
		Connection con = getConnection();
		ArrayList<MessageList> list = new MessageListDao().selectMyList1(con, user1);
		close(con);
		return list;
	}
	// 내 대화목록 전체 검색 (내가 user2일때)
	public ArrayList<MessageList> selectMyList2(String user2){
		Connection con = getConnection();
		ArrayList<MessageList> list = new MessageListDao().selectMyList2(con, user2);
		close(con);
		return list;
	}

	// list_no로 user_id, sender 찾을때 user1, user2
	public MessageList selectOne (int list_no) throws MessageException {
		Connection con = getConnection();
		MessageList mList = new MessageListDao().selectOne(con, list_no);
		close(con);
		return mList;
	}

	// 대화번호 찾기
	public int selectList(String user_id, String sender){
		Connection con = getConnection();
		int list_no = new MessageListDao().selectList(con, user_id, sender);
		close(con);
		return list_no;
	}

	public String selectUserName(String user1, String user2) {
		Connection con = getConnection();
		String userName = new MessageListDao().selectUserName(con, user1, user2);
		close(con);
		return userName;
	}

	public String selectUserName2(String user2, String user1) {
		Connection con = getConnection();
		String userName = new MessageListDao().selectUserName2(con, user2, user1);
		close(con);
		return userName;
	}

	// 대화요청
	public int insertMessageRequest(String userId, String sender) {
		Connection con = getConnection();
		int result = new MessageListDao().insertMessageRequest(con, userId, sender);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	// 대화 목록 있는지 확인
	public int checkMList(String user1, String user2) {
		Connection con = getConnection();
		int result = new MessageListDao().checkMList(con, user1, user2);
		close(con);
		return result;
	}
	
	// 리스트 수
	public int listCount(String userid) {
		Connection con = getConnection();
		int result = new MessageListDao().listCount(con, userid);
		close(con);
		return result;
	}
}
