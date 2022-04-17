package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.DetailPesanan;

@Repository
public interface DetailPesananRepository extends JpaRepository<DetailPesanan, Long>{

	List<DetailPesanan> findByRowstatus(@Param("rowstatus") int rowstatus); 
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND ( id_transaksi LIKE %:keyword% OR sku_code LIKE %:keyword% )", nativeQuery = true)
	List<DetailPesanan> search(String keyword);
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND id_transaksi = :id_transaksi", nativeQuery = true)
	List<DetailPesanan> all(String id_transaksi);
	
	@Query(value = "SELECT * FROM detail_pesanan WHERE rowstatus = 1 AND penjualan_id = :penjualan_id", nativeQuery = true)
	DetailPesanan getByPenjualan_id(Long penjualan_id);
	
	@Query(value = "SELECT A.* "
			+ "FROM detail_pesanan A "
			+ "LEFT JOIN penjualan B ON A.penjualan_id=B.id "
			+ "WHERE A.rowstatus = 1 AND B.id_store=:id_store AND B.id_karyawan=:id_karyawan AND "
			+ "A.tanggal_transaksi >= :start_date AND A.tanggal_transaksi <= :end_date", nativeQuery = true)
	List<DetailPesanan> findByKaryawanId(int id_store,int id_karyawan,String start_date, String end_date);
}
