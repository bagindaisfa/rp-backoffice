package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangKeluarReport;

public interface PenyimpananBarangKeluarReportRepository extends JpaRepository<PenyimpananBarangKeluarReport, Long> {
	@Query(value = "select p.id, p.tanggal_keluar,  p.id_store, p.lokasi_store, p.penerimaan_code, p.artikel, p.kategori, p.tipe, p.nama_barang, p.ukuran, "
			+ "p.kuantitas, p.hpp, p.harga_jual from penyimpanan_keluar p "
			+ "WHERE p.rowstatus=1 AND date_format(p.tanggal_masuk,'%Y-%m-%d') != :tanggal_transaksi", nativeQuery = true)
	List<PenyimpananBarangKeluarReport> PenyimpananBarangKeluarReport(Date tanggal_transaksi);

}
