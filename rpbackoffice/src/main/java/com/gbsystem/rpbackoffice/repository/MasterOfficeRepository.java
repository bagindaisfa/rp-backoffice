package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterOffice;

@Repository
public interface MasterOfficeRepository extends JpaRepository<MasterOffice, Long> {
	List<MasterOffice> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM master_office WHERE rowstatus = 1 AND "
			+ "MATCH(office_name, alamat) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<MasterOffice> search(String keyword);
}
