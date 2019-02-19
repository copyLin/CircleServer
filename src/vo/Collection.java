package vo;

import java.util.Date;

public class Collection {
	
	private int id;
	private String name;
	private int userId;
	private Date collectionTime;
	private int keyId;
	private String label;
	
	public Collection() {
		super();
	}

	public Collection(int id, String name, int userId, Date collectionTime, int keyId, String label) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.collectionTime = collectionTime;
		this.keyId = keyId;
		this.label = label;
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

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
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

	@Override
	public String toString() {
		return "Collection [id=" + id + ", name=" + name + ", userId=" + userId + ", collectionTime=" + collectionTime
				+ ", keyId=" + keyId + ", label=" + label + "]";
	}
	
}
