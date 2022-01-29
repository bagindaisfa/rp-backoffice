package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.SalesByOffice;

@Repository
public interface SalesByOfficeRepository extends JpaRepository<SalesByOffice, Long> {
	@Query(value = "SELECT A.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_office AS lokasi_office, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,"
			+ "B.harga_satuan_barang AS harga "
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office", nativeQuery = true)
	List<SalesByOffice> SalesByOffice(String id_office);
	
	@Query(value = "SELECT A.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_office AS lokasi_office, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,"
			+ "B.harga_satuan_barang AS harga"
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office ORDER BY B.kuantitas DESC", nativeQuery = true)
	List<SalesByOffice> BestArticle(String id_office);
}
