package com.passerelle.admin.core;

import java.security.Principal;

public class User implements Principal{

	private String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
