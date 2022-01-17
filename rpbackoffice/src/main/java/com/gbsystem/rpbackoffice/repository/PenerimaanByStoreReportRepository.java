package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanByStoreReport;

@Repository
public interface PenerimaanByStoreReportRepository extends JpaRepository<PenerimaanByStoreReport, Long> {
	@Query(value = "select p.tanggal_penerimaan, p.penerimaan_code, p.id_store, p.lokasi_store, "
			+ "p.artikel, m.kategori, m.tipe, p.nama_barang, p.kuantitas, p.ukuran, p.hpp, p.harga_jual from penerimaan_store p "
			+ "left join master_product m on p.artikel = m.artikel_product and p.ukuran = m.ukuran and m.rowstatus=1 "
			+ "where p.rowstatus=1 and date_format(p.tanggal_penerimaan,'%Y-%m-%d') = :tanggal_transaksi", nativeQuery = true)
	List<PenerimaanByStoreReport> PenerimaanByStoreReport(Date tanggal_transaksi);

}
