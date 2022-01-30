package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangKeluarReport;

public interface PenyimpananBarangKeluarReportRepository extends JpaRepository<PenyimpananBarangKeluarReport, Long> {
	@Query(value = "select p.id, p.tanggal_keluar AS tanggal_keluar, :date_from AS date_from, :date_to AS date_to, "
			+ "p.id_store AS id_store, p.lokasi_store AS lokasi_store, p.artikel, p.nama_barang, "
			+ "p.kuantitas, p.harga_jual from penyimpanan_keluar p "
			+ "WHERE p.rowstatus=1 AND DATE(p.tanggal_keluar) >= :date_from AND DATE(p.tanggal_keluar) <= :date_to", nativeQuery = true)
	List<PenyimpananBarangKeluarReport> PenyimpananBarangKeluarReport(Date date_from, Date date_to);

}
