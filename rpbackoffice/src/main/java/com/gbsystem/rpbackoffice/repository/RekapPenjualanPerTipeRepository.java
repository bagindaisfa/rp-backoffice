package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerTipe;

@Repository
public interface RekapPenjualanPerTipeRepository extends JpaRepository<RekapPenjualanPerTipe, Long> {
	
	@Query(value = "SELECT SUM(kuantitas) FROM detail_pesanan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double countingTipe(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT SUM(total) AS total FROM penjualan WHERE rowstatus = 1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date", nativeQuery = true)
	Double totalPerTipe(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT "
			+ "id,"
			+ "type_name,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY type", nativeQuery = true)
	List<RekapPenjualanPerTipe> rekapTipe(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT "
			+ "id,"
			+ "type_name,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY type ORDER BY SUM(kuantitas) DESC LIMIT 5", nativeQuery = true)
	List<RekapPenjualanPerTipe> rekapTipeTerlaris(int id_store, String start_date, String end_date);

}
