package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;

@Repository
public interface PenyimpananStoreKeluarRepository extends JpaRepository<PenyimpananStoreKeluar, Long> {
	List<PenyimpananStoreKeluar> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND pengiriman_code= :pengiriman_code ", nativeQuery = true)
	PenyimpananStoreKeluar findByPengiriman_code(String pengiriman_code);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND "
			+ "MATCH(nama_barang) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<PenyimpananStoreKeluar> search(String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_store_keluar where rowstatus = 1 AND artikel = (?1)"
			+ "AND ((tanggal_masuk <= (?2) AND tanggal_masuk >= (?3))"
			+ "OR tanggal_masuk = (?2) OR tanggal_masuk = (?3))", nativeQuery = true)
	Float generateKuantitasMasuk(String artikel, Date tanggal_awal, Date tanggal_akhir);
}
