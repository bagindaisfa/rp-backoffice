package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;

@Repository
public interface PenyimpananMasukRepository extends JpaRepository<PenyimpananMasuk, Long> {
List<PenyimpananMasuk> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND "
			+ "MATCH(nama_barang) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenyimpananMasuk> search(String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_masuk where rowstatus = 1 AND artikel = (?1)"
			+ "AND ((tanggal_masuk <= (?2) AND tanggal_masuk >= (?3))"
			+ "OR tanggal_masuk = (?2) OR tanggal_masuk = (?3))", nativeQuery = true)
	Float generateKuantitasMasuk(String artikel, Date tanggal_awal, Date tanggal_akhir);
}
