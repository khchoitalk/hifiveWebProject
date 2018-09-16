package message.model.vo;

public class MessageRequest implements java.io.Serializable {
	private static final long serialVersionUID = 6L;
	
	private int request_no;
	private String user_id;
	private String sender;
	private String accept;
	
	public MessageRequest(){}

	public MessageRequest(int request_no, String user_id, String sender, String accept) {
		super();
		this.request_no = request_no;
		this.user_id = user_id;
		this.sender = sender;
		this.accept = accept;
	}

	public int getRequest_no() {
		return request_no;
	}

	public void setRequest_no(int request_no) {
		this.request_no = request_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		return this.request_no + ", " + this.user_id + "," + this.sender + "," + this.accept;
	}

}