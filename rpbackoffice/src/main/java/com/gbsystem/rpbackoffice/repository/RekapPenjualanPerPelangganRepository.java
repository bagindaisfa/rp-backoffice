package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerPelanggan;

@Repository
public interface RekapPenjualanPerPelangganRepository extends JpaRepository<RekapPenjualanPerPelanggan, Long>{
	@Query(value = "SELECT "
			+ "id,"
			+ "nama_pelanggan,"
			+ "no_hp_pelanggan,"
			+ "NULL AS tanggal_transaksi,"
			+ "COUNT(tanggal_transaksi) AS total_kunjungan,"
			+ "SUM(total) AS total "
			+ "FROM penjualan "
			+ "WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY no_hp_pelanggan", nativeQuery = true)
	List<RekapPenjualanPerPelanggan> rekapPelanggan(int id_store, String start_date, String end_date);
	
	
}
