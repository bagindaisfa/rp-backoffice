package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStore;

@Repository
public interface PenerimaanStoreRepository extends JpaRepository<PenerimaanStore, Long> {
   List<PenerimaanStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penerimaan_store WHERE rowstatus = 1 AND nama_barang LIKE %:keyword% OR nama_pelanggan LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<PenerimaanStore> search(String keyword);
	
	@Query(value = "SELECT * FROM penerimaan_store WHERE rowstatus = 1 AND penerimaan_code = :penerimaan_code", nativeQuery = true)
	List<PenerimaanStore> findByPenerimaan_code(String penerimaan_code);
	
	@Query(value = "delete from penerimaan_store where penerimaan_code=:penerimaan_code", nativeQuery = true)
	PenerimaanStore deletePenerimaan(String penerimaan_code);
}
