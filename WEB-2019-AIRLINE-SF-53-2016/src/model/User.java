package model;

import java.sql.Date;

public class User {
	public enum Role {
		USER, ADMIN
	};
	
	private int id;
	private String userName;
	private String userPassword;
	private Date registrationDate;
	private Role role;
	private boolean blocked;
	private boolean deleted;
	public User(int id, String userName, String userPassword, Date registrationDate, Role role, boolean blocked,
			boolean deleted) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
		this.deleted = deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", registrationDate="
				+ registrationDate + ", role=" + role + ", blocked=" + blocked + ", deleted=" + deleted + "]";
	}

}