package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanByStoreReport;

@Repository
public interface PenerimaanByStoreReportRepository extends JpaRepository<PenerimaanByStoreReport, Long> {
	@Query(value = "select p.id, p.tanggal_penerimaan, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.penerimaan_code AS kode_penerimaan, p.id_store, p.lokasi_store, "
			+ "p.artikel, p.nama_barang, p.kuantitas, p.harga_jual from penerimaan_store p "
			+ "where p.rowstatus=1 and date_format(p.tanggal_penerimaan,'%Y-%m-%d') = date_format(:tanggal_transaksi,'%Y-%m-%d')", nativeQuery = true)
	List<PenerimaanByStoreReport> PenerimaanByStoreReport(Date tanggal_transaksi);

}
