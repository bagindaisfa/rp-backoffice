package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailReturGudangReport;

@Repository
public interface DetailReturGudangReportRepository extends JpaRepository<DetailReturGudangReport, Long> {
	

	@Query(value = "SELECT "
			+ "B.lokasi_store_asal,B.lokasi_office_tujuan,"
			+ "A.id,"
			+ "A.pengiriman_code,"
			+ "B.tanggal_retur,"
			+ "A.sku_code,"
			+ "A.artikel,"
			+ "A.type,"
			+ "A.type_name,"
			+ "A.kategori,"
			+ "A.nama_kategori,"
			+ "A.nama_barang,"
			+ "A.kuantitas,"
			+ "A.harga_jual,"
			+ "IFNULL(A.keterangan,'') AS keterangan "
			+ "FROM detail_retur_gudang A "
			+ "LEFT JOIN retur_gudang B ON A.retur_gudang_id=B.id AND B.rowstatus=1 "
			+ "WHERE A.rowstatus = 1 AND A.pengiriman_code = :pengiriman_code", nativeQuery = true)
	List<DetailReturGudangReport> allReport(String pengiriman_code);

}
