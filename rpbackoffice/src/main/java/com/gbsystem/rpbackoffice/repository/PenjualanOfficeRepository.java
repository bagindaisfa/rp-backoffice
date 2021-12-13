package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;

@Repository
public interface PenjualanOfficeRepository extends JpaRepository<PenjualanOffice, Long> {
	List<PenjualanOffice> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM penjualan_office WHERE rowstatus=1 AND "
			+ "MATCH(id_transaksi, id_office, lokasi_office) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenjualanOffice> search(String keyword);
}
