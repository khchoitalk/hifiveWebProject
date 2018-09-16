package message.model.vo;

public class MessageList implements java.io.Serializable {
	private static final long serialVersionUID = 7L;
	
	private int	list_no;
	private String user1;
	private String user2;

	public MessageList(){}

	public MessageList(int list_no, String user1, String user2) {
		super();
		this.list_no = list_no;
		this.user1 = user1;
		this.user2 = user2;
	}

	public int getList_no() {
		return list_no;
	}

	public void setList_no(int list_no) {
		this.list_no = list_no;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MessageList [list_no=" + list_no + ", user_id=" + user1 + ", sender=" + user2 + "]";
	}
	
}