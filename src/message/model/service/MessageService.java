package message.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import message.exception.MessageException;
import message.model.dao.MessageDao;
import message.model.vo.Message;

public class MessageService {

	public int sendMessage(Message msg) throws MessageException {
		Connection con = getConnection();
		int result = new MessageDao().sendMessage(con, msg);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Message> selectMyMessage(int list_no) {
		Connection con = getConnection();
		ArrayList<Message> list = new MessageDao().selectMyMessage(con, list_no);
		close(con);
		return list;
	}


}
