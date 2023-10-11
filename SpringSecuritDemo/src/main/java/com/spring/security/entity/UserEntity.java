package com.spring.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotEmpty(message = "Full Name is required.")
	private String fullName;
	
	@Email(message = "Invalid Email format")
	@NotEmpty(message = "Email is required")
	
	private String email;
	
//	@Size(min = 8, max = 14, message = "Password must be between 8 and 14 characters long")
	@NotEmpty(message = "Password is required")
	private String password;
	
	private String roles;
	
//	for Numerical values always use @NotNull

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserEntity(@Email @NotEmpty String email, @NotEmpty String password, String roles) {
	super();
	this.email = email;
	this.password = password;
	this.roles = roles;
}



	public UserEntity(Long userId, @NotEmpty String fullName, @Email @NotEmpty String email, @NotEmpty String password,
			String roles) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", roles=" + roles + "]";
	}
	
	
	
}
