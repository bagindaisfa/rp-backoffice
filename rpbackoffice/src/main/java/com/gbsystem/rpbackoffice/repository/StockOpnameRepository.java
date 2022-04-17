package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.StockOpname;

@Repository
public interface StockOpnameRepository extends JpaRepository<StockOpname, Long> {
	List<StockOpname> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND (nama_barang LIKE %:keyword% OR sku_code LIKE %:keyword%)", nativeQuery = true)
	List<StockOpname> search(String keyword);
}
