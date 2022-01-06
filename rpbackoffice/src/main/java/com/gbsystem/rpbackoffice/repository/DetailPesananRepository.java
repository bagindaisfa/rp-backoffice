package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPesanan;

@Repository
public interface DetailPesananRepository extends JpaRepository<DetailPesanan, Long>{

List<DetailPesanan> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1"
			+ "MATCH(id_transaksi) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<DetailPesanan> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND id_transaksi = (?1)", nativeQuery = true)
	List<DetailPesanan> all(String id_transaksi);
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND penjualan_id = :penjualan_id", nativeQuery = true)
	DetailPesanan getByPenjualan_id(Long penjualan_id);
}
