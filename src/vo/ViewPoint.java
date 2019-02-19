package vo;

import java.util.Date;

public class ViewPoint {
	
	private int id;
	private int keyId;
	private String label;
	private int toId;
	private String content;
	private int userId;
	private Date sendTime;
	private boolean flag;
	
	public ViewPoint() {
		super();
	}

	public ViewPoint(int id, int keyId, String label, int toId, String content, int userId, Date sendTime,
			boolean flag) {
		super();
		this.id = id;
		this.keyId = keyId;
		this.label = label;
		this.toId = toId;
		this.content = content;
		this.userId = userId;
		this.sendTime = sendTime;
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ViewPoint [id=" + id + ", keyId=" + keyId + ", label=" + label + ", toId=" + toId + ", content="
				+ content + ", userId=" + userId + ", sendTime=" + sendTime + ", flag=" + flag + "]";
	}

	
}
