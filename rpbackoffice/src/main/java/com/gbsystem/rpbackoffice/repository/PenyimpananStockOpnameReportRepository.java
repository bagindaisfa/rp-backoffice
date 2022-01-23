package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenyimpananStockOpnameReport;

@Repository
public interface PenyimpananStockOpnameReportRepository extends JpaRepository<PenyimpananStockOpnameReport, Long> {
	@Query(value = "select p.id, p.tanggal_so, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.artikel, m.nama_kategori AS kategori, m.type_name AS tipe, p.nama_barang, p.kuantitas_masuk, p.kuantitas_keluar, p.stock, "
			+ "p.stock_opname, p.keterangan from stock_opname p "
			+ "left join master_product m on m.artikel_product = p.artikel and m.rowstatus = 1 "
			+ "where p.rowstatus=1 and date_format(p.tanggal_so,'%Y-%m-%d') = date_format(:tanggal_transaksi,'%Y-%m-%d')", nativeQuery = true)
	List<PenyimpananStockOpnameReport> PenyimpananStockOpnameReport(Date tanggal_transaksi);

}
