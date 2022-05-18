package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PesananTunggu;

@Repository
public interface PesananTungguRepository extends JpaRepository<PesananTunggu, Long>{
	List<PesananTunggu> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pesanan_tunggu WHERE rowstatus = 1 AND "
			+ "( no_pesanan LIKE %:keyword% OR "
			+ "id_store LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "nama_pelanggan LIKE %:keyword% )", nativeQuery = true)
	List<PesananTunggu> search(String keyword);
	
	@Query(value = "SELECT * FROM pesanan_tunggu WHERE rowstatus = 1 AND no_pesanan=:no_pesanan", nativeQuery = true)
	PesananTunggu findByIdTransaksi(String no_pesanan);
	
	@Query(value = "Delete FROM pesanan_tunggu WHERE no_pesanan=:no_pesanan", nativeQuery = true)
	void deleteByIdTransaksi(String no_pesanan);
	
	@Query(value = "Delete FROM barang_pesanan_tunggu_item WHERE no_pesanan=:no_pesanan", nativeQuery = true)
	void deleteDetailByIdTransaksi(String no_pesanan);
}
