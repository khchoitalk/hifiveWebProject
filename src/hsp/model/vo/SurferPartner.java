package hsp.model.vo;

import java.sql.Date;

public class SurferPartner implements java.io.Serializable {
	private static final long serialVersionUID = 8L;
	
	private String user_id;
	private Date start_date;
	private Date End_date;
	private String city;
	private String role;
	private String process;
	private int user_num;

	
	public SurferPartner() {}


	public SurferPartner(String user_id, Date start_date, Date end_date, String city, String role, String process,
			int user_num) {

		super();
		this.user_id = user_id;
		this.start_date = start_date;
		End_date = end_date;
		this.city = city;
		this.role = role;
		this.process = process;
		this.user_num = user_num;

	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {

		return End_date;

	}

	public void setEnd_date(Date end_date) {

		End_date = end_date;

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	
	@Override
	public String toString(){
		return this.user_id+", "+this.start_date+", "+this.End_date+", "+this.city+", "+this.role+", "+this.process+", "+this.user_num;
	}


}
