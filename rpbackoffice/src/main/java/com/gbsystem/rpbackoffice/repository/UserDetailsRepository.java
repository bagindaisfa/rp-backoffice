package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.User;


@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	User findById(long id);
	
	@Query(value = "SELECT * FROM auth_user_details WHERE ( "
			+ "user_name LIKE %:keyword% OR "
			+ "first_name LIKE %:keyword% OR "
			+ "last_name LIKE %:keyword% OR "
			+ "phone_number LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% "
			+ ")", nativeQuery = true)
	List<User> search(String keyword);
}

