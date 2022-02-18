package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.ChartPenjualanPerbulan;

@Repository
public interface ChartPenjualanBulananRepository extends JpaRepository<ChartPenjualanPerbulan, Long> {
	
	@Query(value = "select 1 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=1 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalJan(int id_store);
			
	@Query(value = "select 2 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=2 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalFeb(int id_store);
	
	@Query(value = "select 3 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=3 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalMar(int id_store);
	
	@Query(value = "select 4 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=4 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalApr(int id_store);
	
	@Query(value = "select 5 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=5 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalMay(int id_store);
	
	@Query(value = "select 6 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=6 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalJun(int id_store);
	
	@Query(value = "select 7 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=7 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalJul(int id_store);
	
	@Query(value = "select 8 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=8 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalAug(int id_store);
	
	@Query(value = "select 9 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=9 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalSept(int id_store);
	
	@Query(value = "select 10 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=10 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalOct(int id_store);
	
	@Query(value = "select 11 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=11 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalNov(int id_store);
	
	@Query(value = "select 12 as id, date_format(tanggal_transaksi, '%M') as bulan,IFNULL(SUM(total),0) as total from penjualan WHERE YEAR(tanggal_transaksi) = YEAR(NOW()) AND MONTH(tanggal_transaksi)=12 and id_store= :id_store group by date_format(tanggal_transaksi, '%M')", nativeQuery = true)
	ChartPenjualanPerbulan totalDec(int id_store);
}
