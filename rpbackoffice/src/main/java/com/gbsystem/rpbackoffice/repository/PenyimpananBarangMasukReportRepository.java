package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangMasukReport;

@Repository
public interface PenyimpananBarangMasukReportRepository extends JpaRepository<PenyimpananBarangMasukReport, Long> {
	@Query(value = "select p.id, p.tanggal_masuk AS tanggal_masuk, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.penerimaan_code, p.artikel AS artikel, p.nama_barang AS nama_barang, "
			+ "p.kuantitas AS kuantitas, p.harga_jual AS harga_jual from penyimpanan_masuk p "
			+ "WHERE p.rowstatus=1 AND date_format(p.tanggal_masuk,'%Y-%m-%d') = date_format(:tanggal_transaksi,'%Y-%m-%d')", nativeQuery = true)
	List<PenyimpananBarangMasukReport> PenyimpananBarangMasukReport(Date tanggal_transaksi);	

}
