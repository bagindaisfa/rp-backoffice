package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangMasukReport;

@Repository
public interface PenyimpananBarangMasukReportRepository extends JpaRepository<PenyimpananBarangMasukReport, Long> {
	@Query(value = "select p.id, p.tanggal_masuk, p.penerimaan_code, p.artikel, p.kategori, p.tipe, p.nama_barang, p.ukuran, "
			+ "p.kuantitas, p.hpp, p.harga_jual from penyimpanan_masuk p "
			+ "WHERE p.rowstatus=1 AND date_format(p.tanggal_masuk,'%Y-%m-%d') != :tanggal_transaksi", nativeQuery = true)
	List<PenyimpananBarangMasukReport> PenyimpananBarangMasukReport(Date tanggal_transaksi);	

}