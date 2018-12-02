package com.binder.helpdesk.api.security.jwt;

import java.io.Serializable;

//Permite login na aplicação
public class JwtAuthenticationResquest implements Serializable {

	private static final long serialVersionUID = 1L;
    private String email;
    private String password;
    
    public JwtAuthenticationResquest() {
    	 super();
    }
	
    public JwtAuthenticationResquest(String email , String password) {
    	this.setEmail(email);
        this.setPassword(password);
    	
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
	
	
	
	
	
}
