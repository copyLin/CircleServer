package vo;

import java.util.Date;

public class Lost {
	
	private int id;
	private int userId;
	private Date sendTime;
	private String content;
	private Date eventTime;
	private String location;
	private String contact;
	private boolean flag;
	private int reportNum;
	
	public Lost() {
		super();
	}

	public Lost(int id, int userId, Date sendTime, String content, Date eventTime, String location, String contact,
			boolean flag, int reportNum) {
		super();
		this.id = id;
		this.userId = userId;
		this.sendTime = sendTime;
		this.content = content;
		this.eventTime = eventTime;
		this.location = location;
		this.contact = contact;
		this.flag = flag;
		this.reportNum = reportNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	@Override
	public String toString() {
		return "Lost [id=" + id + ", userId=" + userId + ", sendTime=" + sendTime + ", content=" + content
				+ ", eventTime=" + eventTime + ", location=" + location + ", contact=" + contact + ", flag=" + flag
				+ ", reportNum=" + reportNum + "]";
	}

		
}
