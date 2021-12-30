package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PengirimanStore;

public interface PengirimanStoreRepository extends JpaRepository<PengirimanStore, Long> {
	
List<PengirimanStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pengiriman_store WHERE rowstatus = 1 AND  "
			+ "MATCH(lokasi_store_asal, lokasi_store_tujuan, nama_barang) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PengirimanStore> search(String keyword);

}
