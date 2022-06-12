package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanOfficeFromStore;

@Repository
public interface PenerimaanOfficeFromStoreRepository extends JpaRepository<PenerimaanOfficeFromStore, Long> {
   List<PenerimaanOfficeFromStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_office_from_store WHERE rowstatus = 1 AND "
			+ "(lokasi_office LIKE %:keyword% OR "
			+ "retur_code LIKE %:keyword% OR "
			+ "sku_code LIKE %:keyword% OR "
			+ "penerimaan_code LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% )", nativeQuery = true)
	List<PenerimaanOfficeFromStore> search(String keyword);
	
	@Query(value = "SELECT * FROM penerimaan_office_from_store WHERE rowstatus = 1 AND retur_code = :retur_code", nativeQuery = true)
	PenerimaanOfficeFromStore getByRetur_code(String retur_code);
	
	@Query(value = "delete from penerimaan_office_from_store where penerimaan_code=:penerimaan_code", nativeQuery = true)
	PenerimaanOfficeFromStore deletePenerimaan(String penerimaan_code);
	
	@Query(value = "SELECT * FROM penerimaan_office_from_store WHERE rowstatus = 1 AND penerimaan_code = :penerimaan_code", nativeQuery = true)
	PenerimaanOfficeFromStore getByPenerimaan_code(String penerimaan_code);
}
