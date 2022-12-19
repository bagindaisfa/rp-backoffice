package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.Pembelian;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_supplier, "
			+ "A.nama_supplier, "
			+ "A.pembelian_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_transaksi,"
			+ "(select sum(kuantitas) from detail_pembelian where pembelian_id=A.id and rowstatus=1) AS qty "
			+ "FROM pembelian A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	List<Pembelian> findByRowstatus(int rowstatus);
	
	@Query(value = "SELECT "
			+ "A.id, "
			+ "A.id_supplier, "
			+ "A.nama_supplier, "
			+ "A.pembelian_code, "
			+ "A.rowstatus, "
			+ "A.tanggal_transaksi,"
			+ "(select sum(kuantitas) from detail_pembelian where pembelian_id=A.id and rowstatus=1) AS qty "
			+ "FROM pembelian A WHERE A.rowstatus = 1 AND "
			+ "(A.pembelian_code LIKE %:keyword% OR "
			+ "A.nama_supplier LIKE %:keyword% )", nativeQuery = true)
	List<Pembelian> search(String keyword);
	
	@Query(value = "SELECT * FROM pembelian WHERE rowstatus = 1 AND pembelian_code = :pembelian_code ", nativeQuery = true)
	Pembelian findByPembelianCode(String pembelian_code);
	
}
