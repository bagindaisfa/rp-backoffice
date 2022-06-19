package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanByStoreReport;

@Repository
public interface PenerimaanByStoreReportRepository extends JpaRepository<PenerimaanByStoreReport, Long> {
	@Query(value = "select "
			+ "p.id, "
			+ "p.tanggal_penerimaan, "
			+ ":date_from AS date_from, "
			+ ":date_to AS date_to, "
			+ "p.penerimaan_code AS kode_penerimaan, "
			+ "q.id_store, "
			+ "q.lokasi_store, "
			+ "p.artikel, "
			+ "p.nama_barang, "
			+ "p.kuantitas, "
			+ "p.harga_jual "
			+ "from detail_penerimaan_office_from_store p "
			+ "left join penerimaan_office_from_store q on p.penerimaan_office_from_store_id = q.id "
			+ "where p.rowstatus=1 and DATE(p.tanggal_penerimaan) >= :date_from AND DATE(p.tanggal_penerimaan) <= :date_to", nativeQuery = true)
	List<PenerimaanByStoreReport> PenerimaanByStoreReport(Date date_from, Date date_to);

}
