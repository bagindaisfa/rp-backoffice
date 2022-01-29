package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangKeluarReport;

public interface PenyimpananBarangKeluarReportRepository extends JpaRepository<PenyimpananBarangKeluarReport, Long> {
	@Query(value = "select p.id, p.tanggal_keluar AS tanggal_keluar, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.id_store AS id_store, p.lokasi_store AS lokasi_store, p.artikel, p.nama_barang, "
			+ "p.kuantitas, p.harga_jual from penyimpanan_keluar p "
			+ "WHERE p.rowstatus=1 AND date_format(p.tanggal_keluar,'%Y-%m-%d') = date_format(:tanggal_transaksi,'%Y-%m-%d')", nativeQuery = true)
	List<PenyimpananBarangKeluarReport> PenyimpananBarangKeluarReport(Date tanggal_transaksi);

}
