package com.passerelle.admin.auth;

import com.passerelle.admin.core.User;

import io.dropwizard.auth.Authorizer;

public class MyAuthorizer implements Authorizer<User> {
	  @Override
	  public boolean authorize(User user, String role) {
	    return role.equals("ADMIN");
	  }
	}
