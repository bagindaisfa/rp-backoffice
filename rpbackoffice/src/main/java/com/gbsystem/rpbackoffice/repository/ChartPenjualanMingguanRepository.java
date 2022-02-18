package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerminggu;

@Repository
public interface ChartPenjualanMingguanRepository extends JpaRepository<ChartPenjualanPerminggu, Long> {
	
	@Query(value = "SELECT 1 AS id, IFNULL(SUM(total),0) as total, 'Week 1' AS minggu from penjualan where rowstatus=1 AND id_store = :id_store AND DATE(tanggal_transaksi) BETWEEN (CURRENT_DATE - INTERVAL 28 DAY) AND (CURRENT_DATE - INTERVAL 21 DAY)", nativeQuery = true)
	ChartPenjualanPerminggu totalFirstWeek(int id_store);
	
	@Query(value = "SELECT 2 AS id, IFNULL(SUM(total),0) as total, 'Week 2' AS minggu from penjualan where rowstatus=1 AND id_store = :id_store AND DATE(tanggal_transaksi) BETWEEN (CURRENT_DATE - INTERVAL 21 DAY) AND (CURRENT_DATE - INTERVAL 14 DAY)", nativeQuery = true)
	ChartPenjualanPerminggu totalSecondWeek(int id_store);
	
	@Query(value = "SELECT 3 AS id, IFNULL(SUM(total),0) as total, 'Week 3' AS minggu from penjualan where rowstatus=1 AND id_store = :id_store AND DATE(tanggal_transaksi) BETWEEN (CURRENT_DATE - INTERVAL 14 DAY) AND (CURRENT_DATE - INTERVAL 7 DAY)", nativeQuery = true)
	ChartPenjualanPerminggu totalThirdWeek(int id_store);
	
	@Query(value = "SELECT 4 AS id, IFNULL(SUM(total),0) as total, 'Week 4' AS minggu FROM penjualan WHERE rowstatus=1 AND id_store = :id_store AND DATE(tanggal_transaksi) BETWEEN (CURRENT_DATE - INTERVAL 7 DAY) AND CURRENT_DATE", nativeQuery = true)
	ChartPenjualanPerminggu totalFourthWeek(int id_store);
}
