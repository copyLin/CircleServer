package vo;

import java.util.Date;

public class Image {
	
	private int id;
	private String name;
	private int userId;
	private Date sendTime;
	
	public Image() {
		super();
	}

	public Image(int id, String name, int userId, Date sendTime) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.sendTime = sendTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", userId=" + userId + ", sendTime=" + sendTime + "]";
	}

}
