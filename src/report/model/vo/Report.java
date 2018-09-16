package report.model.vo;

import java.sql.Date;

public class Report implements java.io.Serializable {
private static final long serialVersionUID = 5L;
	
	private int report_no;
	private String user_id;
	private Date report_date;
	private int views;
	private String title;
	private String content;
	private String complete;
	
	public Report(){}

	public Report(int report_no, String user_id, Date report_date, int views, String title, String content,
			String complete) {
		super();
		this.report_no = report_no;
		this.user_id = user_id;
		this.report_date = report_date;
		this.views = views;
		this.title = title;
		this.content = content;
		this.complete = complete;
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", user_id=" + user_id + ", report_date=" + report_date + ", views="
				+ views + ", title=" + title + ", content=" + content + ", complete=" + complete + "]";
	}
	
	
}
