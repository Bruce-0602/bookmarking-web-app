package com.bruce.bookmark_thrillio.entities;

import com.bruce.bookmark_thrillio.constants.Gender;
import com.bruce.bookmark_thrillio.constants.UserType;

public class User {
	private long id; // using long considering the number of user could exceed the limit of int
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private UserType userType; // not creating multiple classes of users, because the staff specific methods
								// will be implemented in manager classes

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", userType=" + userType + "]";
	}
}
