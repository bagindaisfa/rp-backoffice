package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;

@Repository
public interface PenyimpananStoreMasukRepository extends JpaRepository<PenyimpananStoreMasuk, Long> {
	List<PenyimpananStoreMasuk> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	@Query(value = "SELECT * FROM penyimpanan_store_masuk WHERE rowstatus = 1 AND penerimaan_code= :penerimaan_code ", nativeQuery = true)
	List<PenyimpananStoreMasuk> findByPenerimaan_code(String penerimaan_code);
	
	@Query(value = "SELECT * FROM penyimpanan_store_masuk WHERE rowstatus = 1 AND id_store= :id_store ", nativeQuery = true)
	List<PenyimpananStoreMasuk> getAllPerStoreMasuk(int id_store);
	
	@Query(value = "SELECT * FROM penyimpanan_store_masuk WHERE rowstatus = 1 AND "
			+ "( sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "penerimaan_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<PenyimpananStoreMasuk> search(String keyword);
	
	@Query(value = "SELECT * FROM penyimpanan_store_masuk WHERE rowstatus = 1 AND id_store = :id_store AND "
			+ "( sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "penerimaan_code LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "lokasi_store LIKE %:keyword% OR "
			+ "lokasi_office LIKE %:keyword% )", nativeQuery = true)
	List<PenyimpananStoreMasuk> searchMobile(int id_store, String keyword);
	
	@Query(value = "SELECT SUM(kuantitas) FROM penyimpanan_store_masuk where rowstatus = 1 AND sku_code = (?1)"
			+ "AND ((tanggal_masuk <= (?2) AND tanggal_masuk >= (?3))"
			+ "OR tanggal_masuk = (?2) OR tanggal_masuk = (?3))", nativeQuery = true)
	Float generateKuantitasMasuk(String sku_code, Date tanggal_awal, Date tanggal_akhir);
	
	@Query(value = "delete from penyimpanan_store_masuk b where b.penerimaan_code=:penerimaan_code", nativeQuery = true)
	void deleteStoreMasuk(String penerimaan_code);
	
	@Query(value = "SELECT SUM(kuantitas) AS kuantitas FROM penyimpanan_store_masuk WHERE rowstatus = :rowstatus AND id_store = :id_store", nativeQuery = true)
	double totalQtyMasuk(int rowstatus, int id_store);
	
	@Query(value = "SELECT * FROM penyimpanan_store_masuk WHERE rowstatus = 1 AND penerimaan_code= :penerimaan_code AND artikel=:artikel", nativeQuery = true)
	PenyimpananStoreMasuk getByPenerimaanCodeandArtikel(String penerimaan_code, String artikel);
}
