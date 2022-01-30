package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananStockOpnameReport;

@Repository
public interface PenyimpananStockOpnameReportRepository extends JpaRepository<PenyimpananStockOpnameReport, Long> {
	@Query(value = "select p.id, p.tanggal_so, :date_from AS date_from, :date_to AS date_to, "
			+ "p.artikel, p.nama_barang, p.kuantitas_masuk, p.kuantitas_keluar, p.stock, "
			+ "p.stock_opname, p.keterangan from stock_opname p "
			+ "where p.rowstatus=1 and DATE(p.tanggal_so) >= :date_from AND DATE(p.tanggal_so) <= :date_to", nativeQuery = true)
	List<PenyimpananStockOpnameReport> PenyimpananStockOpnameReport(Date date_from, Date date_to);

}
