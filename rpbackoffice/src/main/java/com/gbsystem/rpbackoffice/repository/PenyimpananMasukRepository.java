package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;

public interface PenyimpananMasukRepository extends JpaRepository<PenyimpananMasuk, Long> {
List<PenyimpananMasuk> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND "
			+ "MATCH(artikel, tipe, kategori, nama_barang, ukuran, keterangan) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenyimpananMasuk> search(String keyword);
}
