package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;

public interface PenyimpananKeluarRepository extends JpaRepository<PenyimpananKeluar, Long> {
List<PenyimpananKeluar> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_keluar WHERE rowstatus = 1 AND  "
			+ "MATCH(nama_barang) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenyimpananKeluar> search(String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_keluar where rowstatus = 1 AND artikel = (?1)"
			+ "AND ((tanggal_keluar <= (?2) AND tanggal_keluar >= (?3))"
			+ "OR tanggal_keluar = (?2) OR tanggal_keluar = (?3))", nativeQuery = true)
	Float generateKuantitasKeluar(String artikel, Date tanggal_awal, Date tanggal_akhir);
}
