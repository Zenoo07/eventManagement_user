package com.eventmanagement.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Size(min = 4, message = "Username should be minimum 4 characters")
	@Size(max = 10, message = "Username should be maximum 10 characters")
	@NotBlank(message = "UserName is Mandatory")
	@Id
	private String userName;

	@Size(min = 6, message = "password should be minimum 6 characters")
	@Size(max = 15, message = "password should be maximum 15 characters")
	@NotBlank(message = "password is Mandatory")
	private String password;

	@NotBlank(message = "User Email is Mandatory")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+com)$", message = "Email should be in correct format")
	private String userEmail;

	@NotBlank(message = "User First Name is Mandatory")
	private String firstName;

	@NotBlank(message = "User Last Name is Mandatory")
	private String lastName;

	private boolean admin;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
