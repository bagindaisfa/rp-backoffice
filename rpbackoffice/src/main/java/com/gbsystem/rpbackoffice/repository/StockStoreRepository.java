package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.StockStore;

public interface StockStoreRepository extends JpaRepository<StockStore, Long> {
	List<StockStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM stock_store WHERE rowstatus = 1 AND id_store= :id_store AND artikel =:artikel ", nativeQuery = true)
	List<StockStore> findById_storeAndArtikel(int id_store,String artikel);
	
	@Query(value = "SELECT * FROM stock_store WHERE rowstatus = 1 AND  "
			+ "MATCH(artikel, nama_barang, tipe, kategori) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<StockStore> search(String keyword);
}
