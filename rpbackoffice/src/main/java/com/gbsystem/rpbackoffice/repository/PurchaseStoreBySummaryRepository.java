package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreBySummary;

@Repository
public interface PurchaseStoreBySummaryRepository extends JpaRepository<PurchaseStoreBySummary, Long> {
	@Query(value = "SELECT A.nama_pelanggan AS nama_pelanggan,A.no_hp_pelanggan AS no_hp_pelanggan,C.alamat AS alamat, "
			+ "B.*, :date_from AS date_from, :date_to AS date_to "
			+ "FROM penjualan A "
			+ "LEFT JOIN detail_pesanan B ON A.id_transaksi = B.id_transaksi AND B.rowstatus=1 "
			+ "AND DATE(B.tanggal_transaksi) >= :date_from AND DATE(B.tanggal_transaksi) <= :date_to "
			+ "LEFT JOIN pelanggan C ON A.no_hp_pelanggan = C.no_hp AND C.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.no_hp_pelanggan = :no_hp_pelanggan", nativeQuery = true)
	List<PurchaseStoreBySummary> PurchaseStoreBySummary(String no_hp_pelanggan, Date date_from, Date date_to);
}
