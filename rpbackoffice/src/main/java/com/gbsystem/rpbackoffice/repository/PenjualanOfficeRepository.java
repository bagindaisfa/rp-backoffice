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
	
	@Query(value = "SELECT COUNT(id) FROM penjualan_office WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double counting(int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan_office WHERE rowstatus=1 AND (id_transaksi LIKE %:keyword% OR id_office LIKE %:keyword% OR lokasi_office LIKE %:keyword%)", nativeQuery = true)
	List<PenjualanOffice> search(String keyword);
}
