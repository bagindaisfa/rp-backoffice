package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;

@Repository
public interface PenerimaanStoreFromOfficeRepository extends JpaRepository<PenerimaanStoreFromOffice, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office, "
			+ "A.lokasi_store, "
			+ "A.penerimaan_code, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_store_from_office WHERE penerimaan_store_from_office_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_store_from_office A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<PenerimaanStoreFromOffice> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office, "
			+ "A.lokasi_store, "
			+ "A.penerimaan_code, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_penerimaan, "
			+ "(SELECT SUM(kuantitas) from detail_penerimaan_store_from_office WHERE penerimaan_store_from_office_id=A.id and rowstatus=1) AS qty "
			+ "FROM penerimaan_store_from_office A WHERE A.rowstatus = 1 AND "
			+ "(A.lokasi_store LIKE %:keyword% OR "
			+ "A.penerimaan_code LIKE %:keyword% OR "
			+ "A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_office LIKE %:keyword%)", nativeQuery = true)
	List<PenerimaanStoreFromOffice> search(String keyword);
	
	@Query(value = "SELECT * FROM penerimaan_store_from_office WHERE rowstatus = 1 AND pengiriman_code=:pengiriman_code ", nativeQuery = true)
	PenerimaanStoreFromOffice getByPengirimanCodeandArtikel(String pengiriman_code);

}
