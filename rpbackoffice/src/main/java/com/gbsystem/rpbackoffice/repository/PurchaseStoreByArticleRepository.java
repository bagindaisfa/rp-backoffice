package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreByArticle;

@Repository
public interface PurchaseStoreByArticleRepository extends JpaRepository<PurchaseStoreByArticle, Long> {
	@Query(value = "SELECT A.id AS id, A.nama_karyawan AS nama_karyawan,A.lokasi_store AS lokasi_store, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,B.harga"
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id = B.penjualan_id AND B.rowstatus=1 "
			+ "AND DATE(B.tanggal_transaksi) >= :date_from AND DATE(B.tanggal_transaksi) <= :date_to "
			+ "WHERE A.rowstatus=1 AND B.artikel = :artikel", nativeQuery = true)
	List<PurchaseStoreByArticle> PurchaseStoreByArticle(String artikel, Date date_from, Date date_to);
	
	@Query(value = "SELECT A.id AS id, A.nama_karyawan AS nama_karyawan,A.lokasi_store AS lokasi_store, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,B.harga "
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id = B.penjualan_id AND B.rowstatus=1 "
			+ "AND DATE(B.tanggal_transaksi) >= :date_from AND DATE(B.tanggal_transaksi) <= :date_to "
			+ "WHERE A.rowstatus=1 AND A.id_store = :id_store ORDER BY B.kuantitas DESC", nativeQuery = true)
	List<PurchaseStoreByArticle> BestArticle(String id_store, Date date_from, Date date_to);
}
