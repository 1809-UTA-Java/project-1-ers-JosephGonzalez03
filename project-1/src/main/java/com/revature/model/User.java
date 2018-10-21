package com.revature.model;

public interface User {
	boolean login(String username, String password);
	boolean viewHomePage();
	User logout();
	
}
