package com.gbsystem.rpbackoffice.Loginservice;

import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.Logindomain.Login;
import com.gbsystem.rpbackoffice.Loginrepository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginService {
	
	@Autowired
	
	private LoginRepository repo;
	
	public Login login(String username, String password) {
		Login login = repo.findByUsernameAndPassword(username, password);
		return login;
	}
}
