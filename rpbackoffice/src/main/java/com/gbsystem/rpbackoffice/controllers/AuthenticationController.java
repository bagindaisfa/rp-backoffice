package com.gbsystem.rpbackoffice.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.config.JWTTokenHelper;

import com.gbsystem.rpbackoffice.entities.User;

import com.gbsystem.rpbackoffice.requests.AuthenticationRequest;
import com.gbsystem.rpbackoffice.responses.LoginResponse;
import com.gbsystem.rpbackoffice.responses.UserInfo;
import com.gbsystem.rpbackoffice.services.CustomUserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomUserService customUserService;	
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user=(User)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		User userObj=(User) userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		
		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		response.setId_pengguna(userObj.getId());
		response.setNamaPengguna(userObj.getFirstName()+" "+userObj.getLastName());
		response.setId_office(userObj.getId_office());
		response.setLokasi_office(userObj.getLokasi_office());
		response.setId_store(userObj.getId_store());
		response.setLokasi_store(userObj.getLokasi_store());
		response.setAkses_modul(userObj.getAkses_modul());
		

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(userObj.getFirstName());
		userInfo.setLastName(userObj.getLastName());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		
		
		return ResponseEntity.ok(userInfo);
		
	}
	
	@PostMapping(value = "/auth/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody String  saveUser(
			@RequestParam("userName") String userName,@RequestParam("password") String password,
    		@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
    		@RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber,
    		@RequestParam("id_office") int id_office, @RequestParam("lokasi_office") String lokasi_office,
    		@RequestParam("id_store") int id_store,@RequestParam("lokasi_store") String lokasi_store,
    		@RequestParam("akses_modul") String[] akses_modul) throws Exception{
		
		customUserService.saveUser(
				userName, password, firstName,
				lastName, email, phoneNumber,
				id_office, lokasi_office, id_store,
				lokasi_store, akses_modul);
			return "Success!";
		
	}
	@PostMapping(value = "/auth/updateUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody String  saveUser(
			@RequestParam("id") long id, @RequestParam("userName") String userName,@RequestParam("password") String password,
    		@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
    		@RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("id_office") int id_office, @RequestParam("lokasi_office") String lokasi_office,
    		@RequestParam("id_store") int id_store,@RequestParam("lokasi_store") String lokasi_store, @RequestParam("akses_modul") String[] akses_modul) throws Exception{
		
		customUserService.update(id,
				userName, password, firstName,
				lastName, email, phoneNumber,
				id_office, lokasi_office, id_store,
				lokasi_store, akses_modul);
			return "Success!";
		
	}
}

