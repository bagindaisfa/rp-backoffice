package com.gbsystem.rpbackoffice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Authority;
import com.gbsystem.rpbackoffice.entities.User;
import com.gbsystem.rpbackoffice.repository.UserDetailsRepository;

@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userDetailsRepository.findByUserName(username);
		
		if(null==user) {
			throw new UsernameNotFoundException("User Not Found with userName "+username);
		}
		return user;
	}
	
	public User saveUser(String userName, String password,String firstName,String lastName,String email,String phoneNumber ) {
		
		User user = new User();
		List<Authority> authorityList=new ArrayList<>();
		authorityList.add(createAuthority("USER","User role"));
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		return userDetailsRepository.save(user);
	}
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}

}
