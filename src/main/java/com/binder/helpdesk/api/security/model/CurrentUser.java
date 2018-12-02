package com.binder.helpdesk.api.security.model;

import com.binder.helpdesk.api.entity.User;

//Permite login na aplicação
public class CurrentUser {

	private String token;
	private User user;
	
	public CurrentUser(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
