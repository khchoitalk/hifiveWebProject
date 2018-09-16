package review.model.vo;

import java.sql.Date;

public class Review implements java.io.Serializable {
	private static final long serialVersionUID = 2L;
	
	private int review_no;
	private String user_id; // 리뷰 작성자 아이디
	private String r_user_id; // 리뷰 당사자 아이디
	private Date review_date;
	private String content;
	
	public Review(){}

	public Review(int review_no, String user_id, String r_user_id, Date review_date, String content) {
		super();
		this.review_no = review_no;
		this.user_id = user_id;
		this.r_user_id = r_user_id;
		this.review_date = review_date;
		this.content = content;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
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

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
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
	public String toString(){
		return this.review_no + ", " + this.user_id + ", " + this.r_user_id
				 + ", " + this.review_date + ", " + this.content;
	}

}
