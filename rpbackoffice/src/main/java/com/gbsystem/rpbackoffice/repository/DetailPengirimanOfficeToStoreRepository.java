package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanOfficeToStore;

@Repository
public interface DetailPengirimanOfficeToStoreRepository extends JpaRepository<DetailPengirimanOfficeToStore, Long>{

	List<DetailPengirimanOfficeToStore> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pengiriman_office_to_store WHERE rowstatus = 1 AND pengiriman_code LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<DetailPengirimanOfficeToStore> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pengiriman_office_to_store WHERE rowstatus = 1 AND pengiriman_code = (?1)", nativeQuery = true)
	List<DetailPengirimanOfficeToStore> all(String pengiriman_code);
	
	@Query(value = "SELECT * FROM detail_pengiriman_office_to_store WHERE rowstatus = 1 AND pengirman_office_to_store_id = :pengirman_office_to_store_id", nativeQuery = true)
	DetailPengirimanOfficeToStore getByPengirman_office_to_store_id(Long pengirman_office_to_store_id);
}
