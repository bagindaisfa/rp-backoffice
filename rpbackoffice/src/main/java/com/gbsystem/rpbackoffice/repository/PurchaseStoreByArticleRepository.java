package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreByArticle;

@Repository
public interface PurchaseStoreByArticleRepository extends JpaRepository<PurchaseStoreByArticle, Long> {
	@Query(value = "SELECT A.id AS id, A.nama_karyawan AS nama_karyawan,A.lokasi_store AS lokasi_store, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,B.ukuran,B.harga,C.type_name AS type_name,C.nama_kategori AS nama_kategori "
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id = B.penjualan_id AND B.rowstatus=1 "
			+ "LEFT JOIN master_product C ON B.artikel = C.artikel_product AND B.ukuran=C.ukuran AND C.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND B.artikel = :artikel", nativeQuery = true)
	List<PurchaseStoreByArticle> PurchaseStoreByArticle(String artikel);
}
