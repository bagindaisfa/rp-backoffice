package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.StockOffice;

@Repository
public interface StockOfficeRepository extends JpaRepository<StockOffice, Long> {
	List<StockOffice> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM stock_office WHERE rowstatus = 1 AND id_office = :id_office AND artikel = :artikel ", nativeQuery = true)
	List<StockOffice> findById_officeAndArtikel(int id_office, String artikel);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM stock_office WHERE rowstatus = :rowstatus ", nativeQuery = true)
	double totalStockOffice(int rowstatus);
	
	@Query(value = "SELECT * FROM stock_office WHERE rowstatus = 1 AND  "
			+ "MATCH(artikel, nama_barang, tipe, kategori) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<StockOffice> search(String keyword);

}
