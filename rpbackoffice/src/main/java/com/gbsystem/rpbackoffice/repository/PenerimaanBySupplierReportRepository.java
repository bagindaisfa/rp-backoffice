package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanBySupplierReport;

@Repository
public interface PenerimaanBySupplierReportRepository extends JpaRepository<PenerimaanBySupplierReport, Long> {
	@Query(value = "select p.id, p.tanggal_penerimaan, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.penerimaan_code AS kode_penerimaan, p.id_supplier, p.nama_supplier, "
			+ "p.artikel, m.kategori, m.type AS tipe, p.nama_barang, p.kuantitas, p.ukuran, p.hpp, p.harga_jual from penerimaan_supplier p "
			+ "left join master_product m on p.artikel = m.artikel_product and p.ukuran = m.ukuran and m.rowstatus=1 "
			+ "where p.rowstatus=1 and date_format(p.tanggal_penerimaan,'%Y-%m-%d') != :tanggal_transaksi", nativeQuery = true)
	List<PenerimaanBySupplierReport> PenerimaanBySupplierReport(Date tanggal_transaksi);

}
