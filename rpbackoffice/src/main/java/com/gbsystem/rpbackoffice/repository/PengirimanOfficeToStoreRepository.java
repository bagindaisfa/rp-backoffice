package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;

public interface PengirimanOfficeToStoreRepository extends JpaRepository<PengirimanOfficeToStore, Long> {
	
List<PengirimanOfficeToStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pengiriman_office_to_store WHERE rowstatus = 1 AND ("
			+ "pengiriman_code LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword%)", nativeQuery = true)
	List<PengirimanOfficeToStore> search(String keyword);
	
	@Query(value = "SELECT * FROM pengiriman_office_to_store WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	PengirimanOfficeToStore getByPengirimanCode(String pengiriman_code);
}
