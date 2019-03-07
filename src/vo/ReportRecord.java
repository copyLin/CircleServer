package vo;

public class ReportRecord {
	
	private int id;
	private int keyId;
	private String label;
	private String reason;
	private int userId;
	
	public ReportRecord() {
		super();
	}
	
	public ReportRecord(int id, int keyId, String label, String reason, int userId) {
		super();
		this.id = id;
		this.keyId = keyId;
		this.label = label;
		this.reason = reason;
		this.userId = userId;
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
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReportRecord [id=" + id + ", keyId=" + keyId + ", label=" + label + ", reason=" + reason + ", userId="
				+ userId + "]";
	}

}
