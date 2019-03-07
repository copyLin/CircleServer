package vo;

import java.util.Date;

public class ChatRecord {
	
	private int id;
	private String content;
	private int fromId;
	private int toId;
	private Date sendTime;
	private boolean flag;
	private boolean state;
	
	public ChatRecord() {
		super();
	}

	public ChatRecord(int id, String content, int fromId, int toId, Date sendTime, boolean flag, boolean state) {
		super();
		this.id = id;
		this.content = content;
		this.fromId = fromId;
		this.toId = toId;
		this.sendTime = sendTime;
		this.flag = flag;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ChatRecord [id=" + id + ", content=" + content + ", fromId=" + fromId + ", toId=" + toId + ", sendTime="
				+ sendTime + ", flag=" + flag + ", state=" + state + "]";
	}

}
