package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.StockStore;

public interface StockStoreRepository extends JpaRepository<StockStore, Long> {
	List<StockStore> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM stock_store WHERE rowstatus = 1 AND id_store= :id_store AND artikel =:artikel", nativeQuery = true)
	StockStore findById_storeAndArtikel(int id_store,String artikel);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM stock_store WHERE rowstatus = :rowstatus ", nativeQuery = true)
	Double totalStockStore(int rowstatus);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM stock_store WHERE rowstatus = :rowstatus AND id_store= :id_store", nativeQuery = true)
	Double totalStocPerkStore(int rowstatus, int id_store);
	
	@Query(value = "SELECT * FROM stock_store WHERE rowstatus = 1 AND "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR"
			+ "lokasi_store LIKE %:keyword% ", nativeQuery = true)
	List<StockStore> search(String keyword);
	
	@Query(value = "SELECT * FROM stock_store WHERE rowstatus = 1 AND id_store = :id_store AND kuantitas !=0 ", nativeQuery = true)
	List<StockStore> stockAvailPerStore(int id_store);
}
