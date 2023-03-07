package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.SalesByStore;

@Repository
public interface SalesByStoreRepository extends JpaRepository<SalesByStore, Long> {
	@Query(value = "SELECT B.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_store AS lokasi_store, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,"
			+ "B.harga_baru AS harga, :date_from AS date_from, :date_to AS date_to "
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id = B.penjualan_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_store = :id_store AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to", nativeQuery = true)
	List<SalesByStore> SalesByStore(String id_store, Date date_from, Date date_to);
	
	@Query(value = "SELECT B.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_store AS lokasi_store, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,"
			+ "B.harga_baru AS harga, :date_from AS date_from, :date_to AS date_to "
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id = B.penjualan_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_store = :id_store AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to "
			+ "ORDER BY B.kuantitas DESC", nativeQuery = true)
	List<SalesByStore> BestArticle(String id_store, Date date_from, Date date_to);
}
