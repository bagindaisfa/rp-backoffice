package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerhari;

@Repository
public interface ChartPenjualanHarianRepository extends JpaRepository<ChartPenjualanPerhari, Long> {

	@Query(value = "SELECT 1 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Monday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalMon(int id_store);

	@Query(value = "SELECT 2 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Tuesday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalTue(int id_store);
	
	@Query(value = "SELECT 3 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Wednesday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalWed(int id_store);
	
	@Query(value = "SELECT 4 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Thursday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalThu(int id_store);
	
	@Query(value = "SELECT 5 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Friday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalFri(int id_store);
	
	@Query(value = "SELECT 6 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Saturday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalSat(int id_store);
	
	@Query(value = "SELECT 7 AS id, IFNULL(SUM(total),0) as total,DAYNAME(tanggal_transaksi) AS hari from penjualan WHERE rowstatus=1 AND DAYNAME(tanggal_transaksi) = 'Sunday' AND id_store=:id_store GROUP BY DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerhari totalSun(int id_store);
}
