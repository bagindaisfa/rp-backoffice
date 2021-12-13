package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterTipe;

@Repository
public interface MasterTipeRepository extends JpaRepository<MasterTipe, Long> {
	List<MasterTipe> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_tipe WHERE rowstatus = 1 AND "
			+ "MATCH(type_name) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<MasterTipe> search(String keyword);
}
