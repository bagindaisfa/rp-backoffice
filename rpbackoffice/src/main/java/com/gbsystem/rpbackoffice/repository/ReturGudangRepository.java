package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.ReturGudang;

public interface ReturGudangRepository extends JpaRepository<ReturGudang, Long> {
	
	List<ReturGudang> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM retur_gudang WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	ReturGudang findByPengiriman_code(String pengiriman_code);
	
	@Query(value = "SELECT * FROM retur_gudang WHERE rowstatus = 1 AND (nama_gudang LIKE %:keyword% OR lokasi_office_tujuan LIKE %:keyword%)", nativeQuery = true)
	List<ReturGudang> search(String keyword);
	
	
}
