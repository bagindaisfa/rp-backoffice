package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerArtikel;

@Repository
public interface RekapPenjualanPerArtikelRepository extends JpaRepository<RekapPenjualanPerArtikel, Long> {
	@Query(value = "SELECT SUM(kuantitas) FROM detail_pesanan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double countingArtikel(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT SUM(total) AS total FROM detail_pesanan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double totalPerArtikel(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT "
			+ "id,"
			+ "artikel,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY artikel", nativeQuery = true)
	List<RekapPenjualanPerArtikel> rekapArtikel(int id_store, String start_date, String end_date);
}
