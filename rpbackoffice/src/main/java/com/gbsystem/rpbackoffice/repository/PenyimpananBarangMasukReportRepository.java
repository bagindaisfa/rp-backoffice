package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangMasukReport;

@Repository
public interface PenyimpananBarangMasukReportRepository extends JpaRepository<PenyimpananBarangMasukReport, Long> {
	@Query(value = "select p.id, p.tanggal_masuk AS tanggal_masuk, :date_from AS date_from, :date_to AS date_to, "
			+ "p.penerimaan_code, p.artikel AS artikel, p.nama_barang AS nama_barang, "
			+ "p.kuantitas AS kuantitas, p.harga_jual AS harga_jual from penyimpanan_masuk p "
			+ "WHERE p.rowstatus=1 AND DATE(p.tanggal_masuk) >= :date_from AND DATE(p.tanggal_masuk) <= :date_to", nativeQuery = true)
	List<PenyimpananBarangMasukReport> PenyimpananBarangMasukReport(Date date_from, Date date_to);	

}
