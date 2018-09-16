package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = 5L;
	
	private int notice_no;
	private Date notice_date;
	private int views;
	private String title;
	private String content;
	
	public Notice(){}
	
	public Notice(int notice_no, Date notice_date, int views, String title, String content) {
		super();
		this.notice_no = notice_no;
		this.notice_date = notice_date;
		this.views = views;
		this.title = title;
		this.content = content;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
	
		
		return "Notice [notice_no=" + notice_no + ", notice_date=" + notice_date + ", views="
		+ views + ", title=" + title + ", content=" + content + "]";
	}
}