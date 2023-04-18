package module.app.board.domain;

public class UserInfoVO {
	
	private String userId;
	private String userName;
	private String password;
	private String ci;
	private String di;
	private int misPasswordNum;
	private String gender;
	private String email;
	private String phone;
	private String addr1;
	private String addr2;
	private String postNum;
	private String updDatetime;
	private String creDatetime;
	private String birth;
	private String salt;
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getDi() {
		return di;
	}
	public void setDi(String di) {
		this.di = di;
	}
	public int getMisPasswordNum() {
		return misPasswordNum;
	}
	public void setMisPasswordNum(int misPasswordNum) {
		this.misPasswordNum = misPasswordNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getUpdDatetime() {
		return updDatetime;
	}
	public void setUpdDatetime(String updDatetime) {
		this.updDatetime = updDatetime;
	}
	public String getCreDatetime() {
		return creDatetime;
	}
	public void setCreDatetime(String creDatetime) {
		this.creDatetime = creDatetime;
	}
  
  
}
