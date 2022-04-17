package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKaryawan;

@Repository
public interface RekapPenjualanPerKaryawanRepository extends JpaRepository<RekapPenjualanPerKaryawan, Long> {
	
	//Laporan Moblie
	@Query(value = "SELECT id,id_karyawan,nama_karyawan,SUM(total) as total,COUNT(*) AS jml_transaksi FROM penjualan WHERE rowstatus=1 AND id_store=:id_store AND "
			+ "DATE(tanggal_transaksi) >= :start_date AND DATE(tanggal_transaksi) <= :end_date GROUP BY id_karyawan", nativeQuery = true)
	List<RekapPenjualanPerKaryawan> rekapKaryawan(int id_store, String start_date, String end_date);
	
}
