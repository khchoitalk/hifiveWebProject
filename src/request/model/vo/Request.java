package request.model.vo;

import java.sql.Date;

public class Request implements java.io.Serializable {
	private static final long serialVersionUID = 3L;
	
	private int request_no;
	private String user_id; // 신청자 아이디
	private String r_user_id; // 상대방 아이디
	private Date request_date;
	private String role;
	private String process;
	
	public Request() {}
	
	public Request(int request_no, String user_id, String r_user_id, Date request_date, String role, String process) {
		super();
		this.request_no = request_no;
		this.user_id = user_id;
		this.r_user_id = r_user_id;
		this.request_date = request_date;
		this.role = role;
		this.process = process;
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

	public String getR_user_id() {
		return r_user_id;
	}

	public void setR_user_id(String r_user_id) {
		this.r_user_id = r_user_id;
	}

	public Date getRequest_date() {
		return request_date;
	}

	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
		return "Request [request_no=" + request_no + ", user_id=" + user_id + ", r_user_id=" + r_user_id
				+ ", request_date=" + request_date + ", role=" + role + ", process=" + process + "]";
	}
	
}