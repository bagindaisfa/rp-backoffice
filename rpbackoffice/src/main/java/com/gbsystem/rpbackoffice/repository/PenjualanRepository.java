package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Penjualan;

@Repository
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {
	
	List<Penjualan> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT COUNT(id) FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	double counting(int rowstatus);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	double total(int rowstatus);
	
	@Query(value = "SELECT SUM(B.hpp) AS total FROM detail_pesanan A LEFT JOIN master_product B ON A.artikel=B.artikel_product WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	double totalHpp(int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1"
			+ "MATCH(id_transaksi, id_store, lokasi_store) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<Penjualan> search(String keyword);
}
