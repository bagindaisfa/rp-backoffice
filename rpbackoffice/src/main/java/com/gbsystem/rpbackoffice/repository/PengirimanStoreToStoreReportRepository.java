package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStoreReport;

@Repository
public interface PengirimanStoreToStoreReportRepository extends JpaRepository<PengirimanStoreToStoreReport, Long> {
	@Query(value = "select p.id, p.tanggal_pengiriman, date_format(:tanggal_transaksi,'%Y-%m-%d') AS tanggal_transaksi, "
			+ "p.pengiriman_code AS kode_pengiriman, p.id_store_asal, p.lokasi_store_asal, p.id_store_tujuan, p.lokasi_store_tujuan, "
			+ "d.artikel, m.kategori, m.type AS tipe, d.nama_barang, d.kuantitas, d.ukuran from pengiriman_store_to_store p "
			+ "left join detail_pengiriman_store_to_store d on p.pengiriman_code = d.pengiriman_code and d.rowstatus=1 "
			+ "left join master_product m on d.artikel = m.artikel_product and d.ukuran = m.ukuran and m.rowstatus=1 "
			+ "where p.rowstatus=1 and date_format(p.tanggal_pengiriman,'%Y-%m-%d') != :tanggal_transaksi", nativeQuery = true)
	List<PengirimanStoreToStoreReport> PengirimanStoreToStoreReport(Date tanggal_transaksi);

}
