package com.passerelle.admin.auth;

import com.google.common.base.Optional;
import com.passerelle.admin.core.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class GreetingAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private String login;
    
    private String password;

    public GreetingAuthenticator(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }
	
	@Override
	public Optional<User> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
		if (password.equals(credentials.getPassword())
                && login.equals(credentials.getUsername())) {
			return Optional.of(new User(credentials.getUsername()));
		} else {
			return Optional.absent();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
