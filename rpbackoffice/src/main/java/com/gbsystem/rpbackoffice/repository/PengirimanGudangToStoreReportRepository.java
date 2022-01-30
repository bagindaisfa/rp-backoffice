package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PengirimanGudangToStoreReport;

@Repository
public interface PengirimanGudangToStoreReportRepository extends JpaRepository<PengirimanGudangToStoreReport, Long> {
	@Query(value = "select p.id, p.tanggal_pengiriman, :date_from AS date_from, :date_to AS date_to, "
			+ "p.pengiriman_code AS kode_pengiriman, p.id_office, p.lokasi_office, p.id_store, p.lokasi_store, "
			+ "d.artikel, d.nama_barang, d.kuantitas from pengiriman_office_to_store p "
			+ "left join detail_pengiriman_office_to_store d on p.pengiriman_code = d.pengiriman_code and d.rowstatus=1 "
			+ "where p.rowstatus=1 and DATE(p.tanggal_pengiriman) >= :date_from AND DATE(p.tanggal_pengiriman) <= :date_to", nativeQuery = true)
	List<PengirimanGudangToStoreReport> PengirimanGudangToStoreReport(Date date_from, Date date_to);

}
