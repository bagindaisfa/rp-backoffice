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
			+ "( sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "pengiriman_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<PenyimpananStoreKeluar> search(String keyword);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND id_store = :id_store AND "
			+ "( sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "pengiriman_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<PenyimpananStoreKeluar> searchMobile(int id_store, String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_store_keluar where rowstatus = 1 AND artikel = (?1)"
			+ "AND ((tanggal_keluar <= (?2) AND tanggal_keluar >= (?3))"
			+ "OR tanggal_keluar = (?2) OR tanggal_keluar = (?3))", nativeQuery = true)
	Float generateKuantitasKeluar(String artikel, Date tanggal_awal, Date tanggal_akhir);
	
	@Query(value = "select * from penyimpanan_store_keluar WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_keluar) >= :date_from AND DATE(tanggal_keluar) <= :date_to", nativeQuery = true)
	List<PenyimpananStoreKeluar> allPenyimpananStoreKeluar(int id_store, Date date_from, Date date_to);
	
	@Query(value = "delete from penyimpanan_store_keluar b where b.pengiriman_code=:pengiriman_code", nativeQuery = true)
	void deleteStoreKeluar(String pengiriman_code);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM penyimpanan_store_keluar WHERE rowstatus = :rowstatus AND id_store = :id_store ", nativeQuery = true)
	Double totalQtyKeluar(int rowstatus, int id_store);
	
	@Query(value = "SELECT * FROM penyimpanan_store_keluar WHERE rowstatus = 1 AND pengiriman_code= :pengiriman_code AND artikel=:artikel", nativeQuery = true)
	PenyimpananStoreKeluar getByPengirimanCodeandArtikel(String pengiriman_code, String artikel);
}
