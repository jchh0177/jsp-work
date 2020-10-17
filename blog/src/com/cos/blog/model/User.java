package com.cos.blog.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private Timestamp createDate;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", createDate=" + createDate + "]";
	}

}
