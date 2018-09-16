package request.model.vo;

import java.sql.Date;

public class Matching implements java.io.Serializable {
	private static final long serialVersionUID = 11L;
	
	private int matching_no;
	private String user1;
	private String user2;
	private String type;
	private String process;
	
	public Matching(){}

	public Matching(int matching_no, String user1, String user2, String type, String process) {
		super();
		this.matching_no = matching_no;
		this.user1 = user1;
		this.user2 = user2;
		this.type = type;
		this.process = process;
	}

	public int getMatching_no() {
		return matching_no;
	}

	public void setMatching_no(int matching_no) {
		this.matching_no = matching_no;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Matching [matching_no=" + matching_no + ", user1=" + user1 + ", user2=" + user2 + ", type=" + type
				+ ", process=" + process + "]";
	}

}
