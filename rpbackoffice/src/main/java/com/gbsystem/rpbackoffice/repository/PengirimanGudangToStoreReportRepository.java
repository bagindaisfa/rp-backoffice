package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PengirimanGudangToStoreReport;

@Repository
public interface PengirimanGudangToStoreReportRepository extends JpaRepository<PengirimanGudangToStoreReport, Long> {
	@Query(value = "select p.tanggal_pengiriman, p.pengiriman_code, p.id_office, p.lokasi_office, p.id_store, p.lokasi_store, "
			+ "d.artikel, m.kategori, m.tipe, d.nama_barang, d.kuantitas, d.ukuran from pengiriman_office_to_store p "
			+ "left_join detail_pengiriman_office_to_store d on p.pengiriman_code = d.pengiriman_code and d.rowstatus=1 "
			+ "left join master_product m on d.artikel = m.artikel_product and d.ukuran = m.ukuran and m.rowstatus=1 "
			+ "where p.rowstatus=1 and date_format(p.tanggal_pengiriman,'%Y-%m-%d') = :tanggal_transaksi", nativeQuery = true)
	List<PengirimanGudangToStoreReport> PengirimanGudangToStoreReport(Date tanggal_transaksi);

}
