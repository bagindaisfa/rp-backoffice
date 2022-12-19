package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbsystem.rpbackoffice.entities.ReturGudang;

public interface ReturGudangRepository extends JpaRepository<ReturGudang, Long> {
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office_tujuan, "
			+ "A.id_store_asal, "
			+ "A.lokasi_office_tujuan, "
			+ "A.lokasi_store_asal, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_retur,"
			+ "(SELECT SUM(kuantitas) FROM detail_retur_gudang WHERE retur_gudang_id=A.id AND rowstatus=1) AS qty "
			+ "FROM retur_gudang A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<ReturGudang> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT * FROM retur_gudang WHERE rowstatus = 1 AND pengiriman_code = :pengiriman_code", nativeQuery = true)
	ReturGudang findByPengiriman_code(String pengiriman_code);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_office_tujuan, "
			+ "A.id_store_asal, "
			+ "A.lokasi_office_tujuan, "
			+ "A.lokasi_store_asal, "
			+ "A.pengiriman_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_retur,"
			+ "(SELECT SUM(kuantitas) FROM detail_retur_gudang WHERE retur_gudang_id=A.id AND rowstatus=1) AS qty "
			+ "FROM retur_gudang A WHERE A.rowstatus = 1 AND ("
			+ "A.pengiriman_code LIKE %:keyword% OR "
			+ "A.lokasi_store_asal LIKE %:keyword% OR "
			+ "A.lokasi_office_tujuan LIKE %:keyword%)", nativeQuery = true)
	List<ReturGudang> search(String keyword);
	
	
}
