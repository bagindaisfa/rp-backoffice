package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanStoreFromStore;

@Repository
public interface DetailPenerimaanStoreFromStoreRepository extends JpaRepository<DetailPenerimaanStoreFromStore, Long>{

	List<DetailPenerimaanStoreFromStore> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_store WHERE rowstatus = 1 AND penerimaan_code LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<DetailPenerimaanStoreFromStore> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_store WHERE rowstatus = 1 AND penerimaan_code = (?1)", nativeQuery = true)
	List<DetailPenerimaanStoreFromStore> all(String penerimaan_code);
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_store WHERE rowstatus = 1 AND penerimaan_store_from_store_id = :penerimaan_store_from_store_id", nativeQuery = true)
	DetailPenerimaanStoreFromStore getByPenerimaan_store_from_store_id(Long penerimaan_store_from_store_id);

}
