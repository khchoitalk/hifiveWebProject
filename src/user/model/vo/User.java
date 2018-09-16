package user.model.vo;

import java.sql.Date;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String user_Id;
	private String user_Pw;
	private String user_Name;
	private String email;
	private String phone;
	private Date birth;
	private String gender;
	private Date join_Date;
	private String safety_check;
	private String nationality;
	private String address;
	private String hobby;
	private String job;
	private String content;
	private String restriction;
	private String profile_image;
	
	public User() {}
	
	public User(String user_Id, String user_Pw, String user_Name, String email, String phone, Date birth, String gender,
			Date join_Date, String safety_check, String restriction) {
		super();
		this.user_Id = user_Id;
		this.user_Pw = user_Pw;
		this.user_Name = user_Name;
		this.email = email;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.join_Date = join_Date;
		this.safety_check = safety_check;
		this.restriction = restriction;
	}

	public User(String user_Id, String user_Pw, String user_Name, String email, String phone, Date birth, String gender,
			Date join_Date, String safety_check, String nationality, String address, String hobby, String job,
			String content, String restriction, String profile_image) {
		super();
		this.user_Id = user_Id;
		this.user_Pw = user_Pw;
		this.user_Name = user_Name;
		this.email = email;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.join_Date = join_Date;
		this.safety_check = safety_check;
		this.nationality = nationality;
		this.address = address;
		this.hobby = hobby;
		this.job = job;
		this.content = content;
		this.restriction = restriction;
		this.profile_image = profile_image;
	}
	
	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Pw() {
		return user_Pw;
	}

	public void setUser_Pw(String user_Pw) {
		this.user_Pw = user_Pw;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoin_Date() {
		return join_Date;
	}

	public void setJoin_Date(Date join_Date) {
		this.join_Date = join_Date;
	}

	public String getSafety_check() {
		return safety_check;
	}

	public void setSafety_check(String safety_check) {
		this.safety_check = safety_check;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString(){
		return this.user_Id + "," + this.user_Pw + "," + this.user_Name 
				+ "," + this.email + "," + this.phone + "," + this.birth 
				+ "," + this.gender + "," + this.join_Date + "," + this.safety_check
				+ "," + this.nationality + "," + this.address + "," + this.hobby + "," 
				+ this.job + "," +  this.content + ", " + this.restriction 
				+ ", " + this.profile_image;
	}	
}	
