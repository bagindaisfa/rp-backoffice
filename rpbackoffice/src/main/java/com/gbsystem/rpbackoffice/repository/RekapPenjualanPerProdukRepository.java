package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerProduk;

@Repository
public interface RekapPenjualanPerProdukRepository extends JpaRepository<RekapPenjualanPerProduk, Long> {
	@Query(value = "SELECT "
			+ "id,"
			+ "artikel,"
			+ "nama_barang,"
			+ "nama_kategori,"
			+ "SUM(harga) AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY artikel ", nativeQuery = true)
	List<RekapPenjualanPerProduk> rekapProduk(int id_store, String start_date, String end_date);
	
	@Query(value = "SELECT "
			+ "id,"
			+ "artikel,"
			+ "nama_barang,"
			+ "nama_kategori,"
			+ "SUM(harga) AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY artikel "
			+ "ORDER BY :orderBy :sortDir", nativeQuery = true)
	List<RekapPenjualanPerProduk> rekapProdukShorted(int id_store, String start_date, String end_date, String orderBy, String sortDir);
	
	@Query(value = "SELECT TOP 5"
			+ "id,"
			+ "artikel,"
			+ "nama_barang,"
			+ "nama_kategori,"
			+ "SUM(harga)AS harga_jual,"
			+ "SUM(kuantitas) AS total_terjual,"
			+ "SUM(total) AS penjualan_kotor "
			+ "FROM detail_pesanan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY artikel ORDER BY SUM(kuantitas) DESC LIMIT 5", nativeQuery = true)
	List<RekapPenjualanPerProduk> rekapProdukTerlaris(int id_store, String start_date, String end_date);
}
