package com.bruce.bookmark_thrillio.managers;

import java.util.List;

import com.bruce.bookmark_thrillio.constants.Gender;
import com.bruce.bookmark_thrillio.constants.UserType;
import com.bruce.bookmark_thrillio.dao.UserDao;
import com.bruce.bookmark_thrillio.entities.User;

public class UserManager {
	private static UserManager instance = new UserManager(); // for Singleton pattern
	private static UserDao dao = new UserDao();
	
	private UserManager() {
	} // for Singleton pattern

	public static UserManager getInstance() { // for Singleton pattern
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);
		
		return user;
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}

	public User getUser(long userId) {
		return dao.getUser(userId);
		
	}
	
}
