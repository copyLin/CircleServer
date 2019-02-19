package vo;

public class User {
	
	private int id;
	private String password;
	private String image;
	private String name;
	private String department;
	private String major;
	private String words;
	
	public User() {
		super();
	}

	public User(int id, String password, String image, String name, String department, String major, String words) {
		super();
		this.id = id;
		this.password = password;
		this.image = image;
		this.name = name;
		this.department = department;
		this.major = major;
		this.words = words;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", image=" + image + ", name=" + name + ", department="
				+ department + ", major=" + major + ", words=" + words + "]";
	}
	

}
