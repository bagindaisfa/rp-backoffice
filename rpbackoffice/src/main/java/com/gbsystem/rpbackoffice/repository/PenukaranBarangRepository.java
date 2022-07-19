package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenukaranBarang;

@Repository
public interface PenukaranBarangRepository  extends JpaRepository<PenukaranBarang, Long> {
	@Query(value = "SELECT * FROM penukaran_barang WHERE rowstatus = 1 AND id_store = :id_store ORDER BY tanggal_masuk DESC", nativeQuery = true)
	List<PenukaranBarang> getAllPerStore(int id_store);
	
	@Query(value = "SELECT * FROM penukaran_barang WHERE rowstatus = 1 AND id_store = :id_store AND"
			+ "( penerimaan_code LIKE %:keyword% OR "
			+ "sku_code LIKE %:keyword% OR "
			+ "artikel LIKE %:keyword% OR "
			+ "type_name LIKE %:keyword% OR "
			+ "nama_kategori LIKE %:keyword% OR "
			+ "nama_barang LIKE %:keyword% OR "
			+ "no_hp_pelanggan LIKE %:keyword% OR "
			+ "nama_pelanggan LIKE %:keyword% ) ORDER BY tanggal_masuk DESC", nativeQuery = true)
	List<PenukaranBarang> searchPerStore(int id_store, String keyword);
	
	@Query(value = "SELECT SUM(A.kuantitas) AS kuantitas FROM penukaran_barang A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	Double totalPenukaran(int rowstatus);
}
