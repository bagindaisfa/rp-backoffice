package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterTipe;

@Repository
public interface MasterTipeRepository extends JpaRepository<MasterTipe, Integer> {
	@Query(value = "SELECT * FROM master_tipe WHERE rowstatus = :rowstatus ORDER BY id", nativeQuery = true)
	List<MasterTipe> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT * FROM master_tipe WHERE rowstatus = 1 AND ( type_name LIKE %:keyword% )", nativeQuery = true)
	List<MasterTipe> search(String keyword);
	
	@Query(value = "SELECT * FROM master_tipe WHERE rowstatus = 1 AND type_name = :type_name", nativeQuery = true)
	MasterTipe findByName(String type_name);
}
