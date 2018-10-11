package com.revature.model;

public interface User {
	User login(User user);
	boolean viewHomePage();
	User logout();
	
}
