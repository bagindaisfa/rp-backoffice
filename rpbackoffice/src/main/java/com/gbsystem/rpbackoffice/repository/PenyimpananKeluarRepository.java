package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;

public interface PenyimpananKeluarRepository extends JpaRepository<PenyimpananKeluar, Long> {
List<PenyimpananKeluar> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_keluar WHERE rowstatus = 1 AND  "
			+ "MATCH(artikel, tipe, kategori, nama_barang, ukuran, keterangan, lokasi_store) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenyimpananKeluar> search(String keyword);
}
