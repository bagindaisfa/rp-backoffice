package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;

@Repository
public interface PenjualanOfficeRepository extends JpaRepository<PenjualanOffice, Long> {
	List<PenjualanOffice> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT COUNT(id) FROM penjualan_office WHERE rowstatus = :rowstatus", nativeQuery = true)
	double counting(int rowstatus);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan_office WHERE rowstatus = :rowstatus", nativeQuery = true)
	double total(int rowstatus);
	
	@Query(value = "SELECT SUM(B.hpp) AS total FROM penjualan_office A LEFT JOIN master_product B ON A.artikel=B.artikel_product WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	double totalHpp(int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan_office WHERE rowstatus=1 AND "
			+ "MATCH(id_transaksi, id_office, lokasi_office) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenjualanOffice> search(String keyword);
}
