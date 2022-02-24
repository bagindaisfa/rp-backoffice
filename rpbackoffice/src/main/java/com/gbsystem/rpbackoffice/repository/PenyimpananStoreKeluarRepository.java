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
	List<PenyimpananStoreKeluar> findByPengiriman_code(String pengiriman_code);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND id_store= :id_store ", nativeQuery = true)
	List<PenyimpananStoreKeluar> getAllPerStoreKeluar(int id_store);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND "
			+ "sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "pengiriman_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword%", nativeQuery = true)
	List<PenyimpananStoreKeluar> search(String keyword);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND id_store = :id_store AND "
			+ "sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "pengiriman_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword%", nativeQuery = true)
	List<PenyimpananStoreKeluar> searchMobile(int id_store, String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_store_keluar where rowstatus = 1 AND sku_code = (?1)"
			+ "AND ((tanggal_masuk <= (?2) AND tanggal_masuk >= (?3))"
			+ "OR tanggal_masuk = (?2) OR tanggal_masuk = (?3))", nativeQuery = true)
	Float generateKuantitasMasuk(String sku_code, Date tanggal_awal, Date tanggal_akhir);
	
	@Query(value = "delete from penyimpanan_store_keluar b where b.pengiriman_code=:pengiriman_code", nativeQuery = true)
	void deleteStoreKeluar(String pengiriman_code);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM penyimpanan_store_keluar WHERE rowstatus = :rowstatus AND id_store = :id_store ", nativeQuery = true)
	double totalQtyKeluar(int rowstatus, int id_store);
}
