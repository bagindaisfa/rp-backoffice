package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PengirimanGudang;

public interface PengirimanGudangRepository extends JpaRepository<PengirimanGudang, Long> {
	
List<PengirimanGudang> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pengiriman_gudang WHERE rowstatus = 1 AND  "
			+ "MATCH(nama_barang, nama_gudang) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PengirimanGudang> search(String keyword);
}