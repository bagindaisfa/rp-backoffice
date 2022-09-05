package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.StockOffice;

@Repository
public interface StockOfficeRepository extends JpaRepository<StockOffice, Long> {
	@Query(value = "SELECT * FROM stock_office WHERE rowstatus = 1", nativeQuery = true)
	List<StockOffice> findByRowstatus();
	
	@Query(value = "SELECT * FROM stock_office WHERE rowstatus = 1 AND id_office = :id_office AND artikel = :artikel", nativeQuery = true)
	StockOffice findById_officeAndArtikel(int id_office, String artikel);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM stock_office WHERE rowstatus = :rowstatus ", nativeQuery = true)
	Double totalStockOffice(int rowstatus);
	
	@Query(value = "SELECT * FROM stock_office WHERE rowstatus = 1 AND "
			+ "(artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "sku_code LIKE %:keyword%)", nativeQuery = true)
	List<StockOffice> search(String keyword);

}
