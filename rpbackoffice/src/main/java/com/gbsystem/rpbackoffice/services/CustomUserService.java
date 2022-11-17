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
import com.gbsystem.rpbackoffice.entities.MasterStore;
import com.gbsystem.rpbackoffice.entities.User;
import com.gbsystem.rpbackoffice.repository.MasterStoreRepository;
import com.gbsystem.rpbackoffice.repository.UserDetailsRepository;

@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	MasterStoreRepository eMasterStore;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userDetailsRepository.findByUserName(username);
		
		if(null==user) {
			throw new UsernameNotFoundException("User Not Found with userName "+username);
		}
		return user;
	}
	
	public User saveUser(
			String userName, String password,String firstName,
			String lastName,String email,String phoneNumber,
			int id_office, String lokasi_office, int id_store,
			String lokasi_store, String[] akses_modul ) {
		
		User user = new User();
		List<Authority> authorityList=new ArrayList<>();
		authorityList.add(createAuthority("USER","User role"));
		
		MasterStore store = eMasterStore.findById(id_store); 
		
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setEnabled(true);
		user.setId_office(id_office);
		user.setLokasi_office(lokasi_office);
		user.setId_store(id_store);
		user.setLokasi_store(lokasi_store);
		user.setAlamat_store(store.getAlamat());
		user.setAkses_modul(akses_modul);
		user.setAuthorities(authorityList);
		return userDetailsRepository.save(user);
	}
	
	public User update( 
			long id, String userName, String password,
			String firstName,String lastName,String email,
			String phoneNumber,int id_office, String lokasi_office, int id_store,
			String lokasi_store, String[] akses_modul ) {
		User user = new User();
		user = userDetailsRepository.findById(id);
		List<Authority> authorityList=new ArrayList<>();
		authorityList.add(createAuthority("USER","User role"));
		
		user.setUserName(userName);
		if (password.equals("")) {
			System.out.println("password kosong");
		} else {
			user.setPassword(passwordEncoder.encode(password));
		}
		
		MasterStore store = eMasterStore.findById(id_store);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setEnabled(true);
		user.setId_office(id_office);
		user.setLokasi_office(lokasi_office);
		user.setId_store(id_store);
		user.setLokasi_store(lokasi_store);
		user.setAlamat_store(store.getAlamat());
		user.setAkses_modul(akses_modul);
		user.setAuthorities(authorityList);
		return userDetailsRepository.save(user);
		
	}
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}

	public List<User> search(String keyword){
		return userDetailsRepository.search(keyword);
	}
}
