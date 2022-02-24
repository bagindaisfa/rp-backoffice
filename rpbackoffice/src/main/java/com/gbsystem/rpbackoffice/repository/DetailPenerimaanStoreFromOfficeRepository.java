package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanStoreFromOffice;

@Repository
public interface DetailPenerimaanStoreFromOfficeRepository extends JpaRepository<DetailPenerimaanStoreFromOffice, Long>{

	List<DetailPenerimaanStoreFromOffice> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_office WHERE rowstatus = 1 AND penerimaan_code LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<DetailPenerimaanStoreFromOffice> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_office WHERE rowstatus = 1 AND penerimaan_code = (?1)", nativeQuery = true)
	List<DetailPenerimaanStoreFromOffice> all(String penerimaan_code);
	
	@Query(value = "SELECT * FROM detail_penerimaan_store_from_office WHERE rowstatus = 1 AND penerimaan_store_from_office_id = :penerimaan_store_from_office_id", nativeQuery = true)
	DetailPenerimaanStoreFromOffice getByPenerimaan_store_from_office_id(Long penerimaan_store_from_office_id);

}
