package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.StockOpnameStore;

@Repository
public interface StockOpnameStoreRepository extends JpaRepository<StockOpnameStore, Long> {
	List<StockOpnameStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM stock_opname_store WHERE rowstatus = 1 AND (lokasi_store LIKE %:keyword% OR nama_barang LIKE %:keyword% OR sku_code LIKE %:keyword%)", nativeQuery = true)
	List<StockOpnameStore> search(String keyword);
}
