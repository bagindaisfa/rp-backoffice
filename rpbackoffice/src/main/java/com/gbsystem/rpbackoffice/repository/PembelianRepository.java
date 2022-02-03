package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pembelian;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {
	List<Pembelian> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM pembelian WHERE rowstatus = 1 AND artikel LIKE %:keyword% OR tipe LIKE %:keyword% OR kategori LIKE %:keyword% OR nama_barang LIKE %:keyword% OR ukuran LIKE %:keyword% ", nativeQuery = true)
	List<Pembelian> search(String keyword);
	
	@Query(value = "SELECT * FROM pembelian WHERE rowstatus = 1 AND pembelian_code = :pembelian_code ", nativeQuery = true)
	Pembelian findByPembelianCode(String pembelian_code);
	
}
