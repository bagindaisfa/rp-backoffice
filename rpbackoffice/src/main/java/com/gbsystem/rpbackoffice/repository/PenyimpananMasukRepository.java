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
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND penerimaan_code= :penerimaan_code ", nativeQuery = true)
	List<PenyimpananMasuk> findByPenerimaan_code(String penerimaan_code);
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND (nama_barang LIKE %:keyword% OR sku_code LIKE %:keyword%)", nativeQuery = true)
	List<PenyimpananMasuk> search(String keyword);
	
	@Query(value = "delete from penyimpanan_masuk b where b.penerimaan_code=:penerimaan_code", nativeQuery = true)
	void deleteStockMasuk(String penerimaan_code);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_masuk where rowstatus = 1 AND sku_code = (?1)"
			+ "AND ((tanggal_masuk <= (?2) AND tanggal_masuk >= (?3))"
			+ "OR tanggal_masuk = (?2) OR tanggal_masuk = (?3))", nativeQuery = true)
	Double generateKuantitasMasuk(String sku_code, Date tanggal_awal, Date tanggal_akhir);
	
	@Query(value = "SELECT * FROM penyimpanan_masuk WHERE rowstatus = 1 AND penerimaan_code= :penerimaan_code AND artikel=:artikel", nativeQuery = true)
	PenyimpananMasuk getPenyimpananByPenerimanCodeandArtikel(String penerimaan_code, String artikel);
}
