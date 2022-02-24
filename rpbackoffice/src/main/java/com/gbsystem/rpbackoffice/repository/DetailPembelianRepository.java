package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPembelian;

@Repository
public interface DetailPembelianRepository extends JpaRepository<DetailPembelian, Long> {
	List<DetailPembelian> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1 AND pembelian_code LIKE %:keyword% OR sku_code LIKE %:keyword%", nativeQuery = true)
	List<DetailPembelian> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1 AND pembelian_code = :pembelian_code", nativeQuery = true)
	List<DetailPembelian> all(String pembelian_code);
	
	@Query(value = "SELECT * FROM detail_pembelian WHERE rowstatus = 1 AND pembelian_id = :pembelian_id", nativeQuery = true)
	DetailPembelian getByPembelian_id(Long pembelian_id);
	

	@Query(value = "SELECT *, :date_from AS date_from, :date_to AS date_to FROM detail_pembelian WHERE rowstatus = 1 AND DATE(tanggal_transaksi) >= :date_from AND DATE(tanggal_transaksi) <= :date_to", nativeQuery = true)
	List<DetailPembelian> LaporanPembelian(Date date_from, Date date_to);

	@Query(value = "SELECT COUNT(id) AS total FROM detail_pembelian WHERE rowstatus = :rowstatus", nativeQuery = true)
	Double totalPembelian(int rowstatus);
	
	@Query(value = "SELECT SUM(A.hpp) AS total FROM detail_pembelian A WHERE A.rowstatus = :rowstatus", nativeQuery = true)
	Double totalHpp(int rowstatus);
}
