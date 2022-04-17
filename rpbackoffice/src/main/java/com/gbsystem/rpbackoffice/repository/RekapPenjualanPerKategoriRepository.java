package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKategori;

@Repository
public interface RekapPenjualanPerKategoriRepository extends JpaRepository<RekapPenjualanPerKategori, Long> {
	@Query(value = "SELECT "
			+ "id,"
			+ "nama_kategori,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY kategori", nativeQuery = true)
	List<RekapPenjualanPerKategori> rekapkategori(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT "
			+ "id,"
			+ "nama_kategori,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY kategori ORDER BY SUM(kuantitas) DESC LIMIT 5", nativeQuery = true)
	List<RekapPenjualanPerKategori> rekapkategoriTerlaris(int id_store, String start_date, String end_date);
}
