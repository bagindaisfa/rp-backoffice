package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterBank;

@Repository
public interface MasterBankRepository extends JpaRepository<MasterBank, Long> {
	@Query(value = "SELECT * FROM master_bank WHERE rowstatus = :rowstatus ORDER BY id", nativeQuery = true)
	List<MasterBank> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT * FROM master_bank WHERE rowstatus = 1 AND ( bank_name LIKE %:keyword% OR owner_name LIKE %:keyword% )", nativeQuery = true)
	List<MasterBank> search(String keyword);
	
	@Query(value = "SELECT * FROM master_bank WHERE rowstatus = 1 AND bank_name = :bank_name", nativeQuery = true)
	MasterBank findByName(String bank_name);
	
	@Query(value = "SELECT * FROM master_bank WHERE rowstatus = 1 AND acc_number = :acc_number", nativeQuery = true)
	MasterBank findByNorek(String acc_number);
	
	@Query(value = "SELECT * FROM master_bank WHERE rowstatus = 1 AND owner_name = :owner_name", nativeQuery = true)
	MasterBank findByOwner(String owner_name);
}
