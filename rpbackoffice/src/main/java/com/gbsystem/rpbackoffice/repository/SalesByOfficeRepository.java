package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.SalesByOffice;

@Repository
public interface SalesByOfficeRepository extends JpaRepository<SalesByOffice, Long> {
	@Query(value = "SELECT B.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_office AS lokasi_office, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang, :qty AS total_qty, :harga AS total_harga,  "
			+ "B.harga_satuan_barang AS harga, :date_from AS date_from, :date_to AS date_to "
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to ", nativeQuery = true)
	List<SalesByOffice> SalesByOffice(String id_office, Date date_from, Date date_to, Double qty, Double harga);
	
	@Query(value = "SELECT B.id AS id, A.nama_pelanggan AS nama_pelanggan,A.lokasi_office AS lokasi_office, "
			+ "B.tanggal_transaksi,B.artikel,B.kuantitas,B.nama_barang,"
			+ "B.harga_satuan_barang AS harga, :date_from AS date_from, :date_to AS date_to "
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to "
			+ "ORDER BY B.kuantitas DESC", nativeQuery = true)
	List<SalesByOffice> BestArticle(String id_office, Date date_from, Date date_to);
	
	@Query(value = "SELECT sum(B.kuantitas) as kuantitas "
			+ "FROM penjualan_office A "
			+ "LEFT JOIN detail_penjualan_office B ON A.id = B.penjualan_office_id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to", nativeQuery = true)
	Double totalQty(String id_office, Date date_from, Date date_to);
	
	@Query(value = "SELECT sum(A.total_penjualan) as total_harga "
			+ "FROM penjualan_office A "
			+ "WHERE A.rowstatus=1 AND A.id_office = :id_office AND DATE(A.tanggal_transaksi) >= :date_from AND DATE(A.tanggal_transaksi) <= :date_to", nativeQuery = true)
	Double totalHarga(String id_office, Date date_from, Date date_to);
}


