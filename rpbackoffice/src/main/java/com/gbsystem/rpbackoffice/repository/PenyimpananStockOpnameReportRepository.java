package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananStockOpnameReport;

@Repository
public interface PenyimpananStockOpnameReportRepository extends JpaRepository<PenyimpananStockOpnameReport, Long> {
	@Query(value = "select tanggal_so, artikel, kategori, tipe, nama_barang, kuantitas_masuk, kuantitas_keluar, stock, "
			+ "stock_opname, keterangan from stock_opname where rowstatus=1 and "
			+ "date_format(p.tanggal_so,'%Y-%m-%d') != :tanggal_transaksi", nativeQuery = true)
	List<PenyimpananStockOpnameReport> PenyimpananStockOpnameReport(Date tanggal_transaksi);

}
