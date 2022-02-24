package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;

@Repository
public interface PenyimpananKeluarRepository extends JpaRepository<PenyimpananKeluar, Long> {
	
	List<PenyimpananKeluar> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_keluar WHERE rowstatus = 1 AND pengiriman_code= :pengiriman_code ", nativeQuery = true)
	List<PenyimpananKeluar> findByPengiriman_code(String pengiriman_code);
	
	@Query(value = "SELECT * FROM penyimpanan_keluar WHERE rowstatus = 1 AND nama_barang LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<PenyimpananKeluar> search(String keyword);
	
	@Query(value = "delete from penyimpanan_keluar b where b.pengiriman_code=:pengiriman_code", nativeQuery = true)
	void deleteStockKeluar(String pengiriman_code);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_keluar where rowstatus = 1 AND sku_code = (?1)"
			+ "AND ((tanggal_keluar <= (?2) AND tanggal_keluar >= (?3))"
			+ "OR tanggal_keluar = (?2) OR tanggal_keluar = (?3))", nativeQuery = true)
	Double generateKuantitasKeluar(String sku_code, Date tanggal_awal, Date tanggal_akhir);
}
