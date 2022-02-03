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
	
	@Query(value = "SELECT * FROM penjualan WHERE id = :id", nativeQuery = true)
	Penjualan getById(Long id);
	
	@Query(value = "SELECT COUNT(id) FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double counting(int rowstatus);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double total(int rowstatus);
	
	@Query(value = "SELECT SUM(B.hpp) AS total FROM detail_pesanan A LEFT JOIN master_product B ON A.artikel=B.artikel_product WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	Double totalHpp(int rowstatus);
	
	@Query(value = "SELECT * FROM penjualan WHERE rowstatus = 1 AND "
			+ "id_transaksi LIKE %:keyword% OR "
			+ "id_store LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "nama_pelanggan LIKE %:keyword%", nativeQuery = true)
	List<Penjualan> search(String keyword);
	
}
