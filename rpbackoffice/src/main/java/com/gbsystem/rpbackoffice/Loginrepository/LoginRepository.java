package com.gbsystem.rpbackoffice.Loginrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.Logindomain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsernameAndPassword(String username, String password);
}
