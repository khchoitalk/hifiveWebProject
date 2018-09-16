package hsp.model.vo;

import java.sql.Date;

public class Host implements java.io.Serializable {
	private static final long serialVersionUID = 9L;
	
	private String user_id;
	private int user_num; // 수용 인원
	private String p_gender;
	private String check1;
	private String check2;
	private String content;
	private String process;
	private String city;
	private String image1;
	private String image2;
	private String image3;	
	
	public Host() {}

	public Host(String user_id, int user_num, String p_gender, String check1, String check2, String content,
			String process, String city, String image1, String image2, String image3) {
		super();
		this.user_id = user_id;
		this.user_num = user_num;
		this.p_gender = p_gender;
		this.check1 = check1;
		this.check2 = check2;
		this.content = content;
		this.process = process;
		this.city = city;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getP_gender() {
		return p_gender;
	}

	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}

	public String getCheck1() {
		return check1;
	}

	public void setCheck1(String check1) {
		this.check1 = check1;
	}

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	@Override
	public String toString(){
		return this.user_id + ", " + this.user_num + ", " + this.p_gender + ", " + this.check1
				 + ", " + this.check2 + ", " + this.content + ", " + this.process + ", " + this.city
				 + ", " + this.image1 + ", " + this.image2 + ", " + this.image3;
	}

}
