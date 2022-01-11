package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;

@Repository
public interface DetailPengirimanStoreToStoreRepository extends JpaRepository<DetailPengirimanStoreToStore, Long>{

	List<DetailPengirimanStoreToStore> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pengiriman_store_to_store WHERE rowstatus = 1"
			+ "MATCH(pengiriman_code) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<DetailPengirimanStoreToStore> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pengiriman_store_to_store WHERE rowstatus = 1 AND pengiriman_code = (?1)", nativeQuery = true)
	List<DetailPengirimanStoreToStore> all(String pengiriman_code);
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND pengiriman_store_to_store_id = :pengiriman_store_to_store_id", nativeQuery = true)
	DetailPengirimanStoreToStore getByPengiriman_store_to_store_id(Long pengiriman_store_to_store_id);
}
