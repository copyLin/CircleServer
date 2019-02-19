package vo;

import java.util.Date;

public class Delivery {
	
	private int id;
	private int userId;
	private Date sendTime;
	private String content;
	private double price;
	private boolean flag;
	private int reportNum;
	
	public Delivery() {
		super();
	}

	public Delivery(int id, int userId, Date sendTime, String content, double price, boolean flag, int reportNum) {
		super();
		this.id = id;
		this.userId = userId;
		this.sendTime = sendTime;
		this.content = content;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		return "Delivery [id=" + id + ", userId=" + userId + ", sendTime=" + sendTime + ", content=" + content
				+ ", price=" + price + ", flag=" + flag + ", reportNum=" + reportNum + "]";
	}

}
