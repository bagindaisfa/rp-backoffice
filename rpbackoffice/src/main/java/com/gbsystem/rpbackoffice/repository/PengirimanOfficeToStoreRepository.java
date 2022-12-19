package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;

public interface PengirimanOfficeToStoreRepository extends JpaRepository<PengirimanOfficeToStore, Long> {
	
	@Query(value = "SELECT "
			+ "A.id,"
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office,"
			+ "A.lokasi_store, "
			+ "A.pengiriman_code,"
			+ "A.rowstatus, "
			+ "A.tanggal_pengiriman, "
			+ "A.keterangan,"
			+ "(select sum(kuantitas) from detail_pengiriman_office_to_store where rowstatus=1 AND pengiriman_code=A.pengiriman_code) AS qty "
			+ "FROM pengiriman_office_to_store A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<PengirimanOfficeToStore> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id,"
			+ "A.id_office, "
			+ "A.id_store, "
			+ "A.lokasi_office,"
			+ "A.lokasi_store, "
			+ "A.pengiriman_code,"
			+ "A.rowstatus, "
			+ "A.tanggal_pengiriman, "
			+ "A.keterangan,"
			+ "(select sum(kuantitas) from detail_pengiriman_office_to_store where rowstatus=1 AND pengiriman_code=A.pengiriman_code) AS qty "
			+ "FROM pengiriman_office_to_store A WHERE A.rowstatus = 1 AND ("
			+ "A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_office LIKE %:keyword% OR "
			+ "A.lokasi_store LIKE %:keyword%)", nativeQuery = true)
	List<PengirimanOfficeToStore> search(String keyword);
	
	@Query(value = "SELECT * FROM pengiriman_office_to_store WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	PengirimanOfficeToStore getByPengirimanCode(String pengiriman_code);
}
