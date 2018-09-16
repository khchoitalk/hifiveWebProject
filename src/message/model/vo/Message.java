package message.model.vo;

import java.sql.Date;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 8L;
	
	private int message_no;
	private Date message_date;
	private int list_no;
	private String sender; // 메시지 보낸 사람 
	private String content;
	
	public Message(){}

	public Message(int message_no, Date message_date, int list_no, String sender, String content) {
		super();
		this.message_no = message_no;
		this.message_date = message_date;
		this.list_no = list_no;
		this.sender = sender;
		this.content = content;
	}

	public int getMessage_no() {
		return message_no;
	}

	public void setMessage_no(int message_no) {
		this.message_no = message_no;
	}

	public Date getMessage_date() {
		return message_date;
	}

	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}

	public int getList_no() {
		return list_no;
	}

	public void setList_no(int list_no) {
		this.list_no = list_no;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Message [message_no=" + message_no + ", message_date=" + message_date + ", list_no=" + list_no
				+ ", sender=" + sender + ", content=" + content + "]";
	}

}