package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanBySupplierReport;

@Repository
public interface PenerimaanBySupplierReportRepository extends JpaRepository<PenerimaanBySupplierReport, Long> {
	@Query(value = "select p.id, p.tanggal_penerimaan, :date_from AS date_from, :date_to AS date_to, "
			+ "p.penerimaan_code AS kode_penerimaan, p.id_supplier, p.nama_supplier, "
			+ "p.artikel, p.nama_barang, p.kuantitas, p.harga_jual from penerimaan_supplier p "
			+ "where p.rowstatus=1 AND DATE(p.tanggal_penerimaan) >= :date_from AND DATE(p.tanggal_penerimaan) <= :date_to", nativeQuery = true)
	List<PenerimaanBySupplierReport> PenerimaanBySupplierReport(Date date_from, Date date_to);

}
